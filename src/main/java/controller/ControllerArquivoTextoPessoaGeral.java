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
import java.util.function.Function;
import java.util.stream.Collectors;
import model.Aluno;
import model.Diretor;
import model.Pessoa;
import model.Professor;

/**
 *
 * @author Jean
 * @param <T>
 */
public class ControllerArquivoTextoPessoaGeral<T extends Pessoa> extends ControllerArquivoTexto {

    private List<T> lista = new ArrayList();
    private T pessoa = null;

    public final static Function<StringTokenizer, Aluno> FUNCAOALUNO = (token -> {
        Aluno pessoa = new Aluno();
        pessoa.setIdPessoa(Integer.parseInt((token.nextToken())));
        pessoa.setNome(token.nextToken());
        pessoa.setTurma(token.nextToken());
        return pessoa;
    });

    public final static Function<StringTokenizer, Professor> FUNCAOPROFESSOR = (token -> {
        Professor pessoa = new Professor();
        pessoa.setIdPessoa(Integer.parseInt((token.nextToken())));
        pessoa.setNome(token.nextToken());
        pessoa.setDisciplina(token.nextToken());
        pessoa.setCpf(Integer.parseInt(token.nextToken()));
        return pessoa;
    });

    public final static Function<StringTokenizer, Diretor> FUNCAODIRETOR = (token -> {
        Diretor pessoa = new Diretor();
        pessoa.setIdPessoa(Integer.parseInt((token.nextToken())));
        pessoa.setNome(token.nextToken());
        pessoa.setCpf(Integer.parseInt(token.nextToken()));
        return pessoa;
    });

    public void lerPessoas(Function<StringTokenizer, T> funcao) {
        setArquivo("Abrir");
        ler();
        String aux = getTexto();

        StringTokenizer tokens = new StringTokenizer(aux, "\n;");
        lista = new ArrayList();
        while (tokens.hasMoreTokens()) {
            pessoa = funcao.apply(tokens);
            lista.add(pessoa);
        }
    }

    public void gravarLista() {
        preGravacao(lista);
        escrever(false);
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    public T getPessoa() {
        return pessoa;
    }

    public void setPessoa(T pessoa) {
        this.pessoa = pessoa;
    }

    public void gravarPessoa() {
        lista.add(pessoa);
        preGravacao(pessoa.toString());
        escrever(true);
    }

    public int updatePessoa() {
        AtomicInteger verifica = new AtomicInteger(0);//Serve para thread Safe...

        lista = lista.stream().
                map(x -> {
                    if (x.getIdPessoa().equals(pessoa.getIdPessoa())) {
                        verifica.set(1);
                        return pessoa;
                    }
                    return x;
                }).collect(Collectors.toList());

        if (verifica.get() == 1) {
            gravarLista();
        }

        return verifica.get();
    }

    public boolean deletePessoa() {
        pessoa = consPessoa();
        if (pessoa != null) {
            lista.remove(pessoa);
            gravarLista();
            return true;
        }
        return false;
    }

    public T consPessoa() {
        Optional<T> op = lista.stream().filter(x -> x.getIdPessoa().equals(pessoa.getIdPessoa())).findFirst();
        try {
            return op.get();
        } catch (NoSuchElementException n) {
            return null;
        }

    }

    //Sobrecarga...
    public void preGravacao(List<? extends Pessoa> lista) {//Estava muito repetitivo
        StringBuilder aux = new StringBuilder();
        lista.forEach(x -> aux.append(x.toString()));
        setTexto(aux.toString());
        if (getArquivo() == null) {
            setArquivo("Salvar");
        }
    }

    public void preGravacao(String aux) {//Estava muito repetitivo
        setTexto(aux);
        if (getArquivo() == null) {
            setArquivo("Salvar");
        }
    }

}
