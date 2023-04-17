/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;
import model.Diretor;

/**
 *
 * @author Jean
 */
public class ControllerArquivoTextoDiretor extends ControllerArquivoTexto {

    private List<Diretor> listDiretor = new ArrayList();
    private Diretor diretor = null;

    public void lerDiretores() {
        setArquivo("Abrir");
        ler();
        String aux = getTexto();

        StringTokenizer tokens = new StringTokenizer(aux, "\n;");
        listDiretor = new ArrayList();
        while (tokens.hasMoreTokens()) {
            diretor = new Diretor();
            diretor.setIdPessoa(Integer.parseInt(tokens.nextToken()));
            diretor.setNome(tokens.nextToken());
            diretor.setCpf(Integer.parseInt(tokens.nextToken()));
            listDiretor.add(diretor);
        }
    }

    public void gravarDiretores() {
 
        preGravacao(listDiretor);
        escrever(false);
    }

    public List<Diretor> getListDiretor() {
        return listDiretor;
    }

    public void setListDiretor(List<Diretor> listDiretor) {
        this.listDiretor = listDiretor;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public void setDiretor(String nome, Integer cpf) {
        diretor = new Diretor();
        diretor.setNome(nome);
        diretor.setCpf(cpf);
    }    
    
    public void gravarDiretor(){
        listDiretor.add(diretor);
        preGravacao(diretor.toString());
        escrever(true);
    }
    
    public int updateDiretor(Integer idPessoa, String nome, Integer cpf){
        AtomicInteger verifica = new AtomicInteger(0);//Serve para thread Safe...
        listDiretor.forEach(x -> {
            if( x.getIdPessoa().equals(idPessoa)){
                x.setNome(nome);
                x.setCpf(cpf);
                verifica.set(1);
            } });
        
        if(verifica.get() == 1 ){
            preGravacao(listDiretor);
            escrever(false);   
        }       
        return verifica.get();
    }
    
    public boolean delete(){
        diretor = consDiretor();
        if(diretor!= null){
            listDiretor.remove(diretor);
            preGravacao(listDiretor);
            escrever(false);
            return true;
        }
        return false;
    }
    
    public Diretor consDiretor(){
        Optional<Diretor> op = listDiretor.stream().filter( x -> x.getIdPessoa().equals(diretor.getIdPessoa())).findFirst();        
        try{
            return op.get();
        }catch(NoSuchElementException n){
            return null;
        }     
    }
}
