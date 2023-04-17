package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.Pessoa;

/**
 *
 * @author Jean
 */
public class ControllerArquivoTexto extends ControllerArquivo {

    private String texto = null;
    private BufferedReader leitor = null;
    private BufferedWriter escritor = null;

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return true caso a operação de leitura seja bem sucedida ou false
     * caso contrário.
     */
    @Override
    public boolean ler() {
        StringBuilder line = new StringBuilder();
        try {
            leitor = new BufferedReader(new FileReader(arquivo));
            while (leitor.ready()) {
                line.append(leitor.readLine()).append("\n");
            }
            leitor.close();
            setTexto(line.toString());
            return true;
        } catch (FileNotFoundException erro) {
            //erro.printStackTrace(); //usado para debug
            System.err.println(erro.getMessage() + "Arquivo não encontrado.");
            return false;
        } catch (IOException erro) {
            System.err.println(erro.getMessage() + "Erro ao ler arquivo.");
            return false;
        }
    }

    /**
     * @param append se o texto será continuado a partir do seu
     * final (append = false) ou se o arquivo será sobrescrito (append = false)
     * @return true caso a operação de escrita seja bem sucedida ou false
     * caso contrário.
     */
    @Override
    public boolean escrever(boolean append) {
        if (arquivo != null) {
            try {
                escritor = new BufferedWriter(new FileWriter(arquivo, append));
                escritor.write(getTexto());
                escritor.close();
                return true;
            } catch (IOException erro) {
                System.err.println(erro.getMessage() + "Erro ao ler arquivo.");
                return false;
            }
        } else {
            return false;
        }
    }

    public String converteListaString(List<? extends Pessoa> lista){ //Recebe qualquer lista extendida de pessoa -> Generic é bem útil kk
        StringBuilder aux = new StringBuilder();
        lista.forEach( x -> aux.append(x.toString()));
        return aux.toString();
    }

    //Sobrecarga...
    public void preGravacao(List<? extends Pessoa> lista){//Estava muito repetitivo
       setTexto(converteListaString(lista));
        if(getArquivo() == null){
            setArquivo("Salvar");
        }
   }
    public void preGravacao(String aux){//Estava muito repetitivo
       setTexto(aux);
        if(getArquivo() == null){
            setArquivo("Salvar");
        }
   }
}