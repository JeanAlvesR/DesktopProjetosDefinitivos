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
import model.Aluno;

/**
 *
 * @author Jean
 */
public class ControllerArquivoTextoAluno extends ControllerArquivoTexto {

    private List<Aluno> listAluno = new ArrayList();
    private Aluno aluno = null;

    public void lerAlunos() {
        setArquivo("Abrir");
        ler();
        String aux = getTexto();

        StringTokenizer tokens = new StringTokenizer(aux, "\n;");
        listAluno = new ArrayList();
        while (tokens.hasMoreTokens()) {
            aluno = new Aluno();
            aluno.setIdPessoa(Integer.parseInt(tokens.nextToken()));
            aluno.setNome(tokens.nextToken());
            aluno.setTurma(tokens.nextToken());
            listAluno.add(aluno);
        }
    }

    public void gravarAlunos() {
 
        preGravacao(listAluno);
        escrever(false);
    }

    public List<Aluno> getListAluno() {
        return listAluno;
    }

    public void setListAluno(List<Aluno> listAluno) {
        this.listAluno = listAluno;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setAluno(String nome, String turma) {
        aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setTurma(turma);
    }    
    
    public void gravarAluno(){
        listAluno.add(aluno);
        preGravacao(aluno.toString());
        escrever(true);
    }
    
    public int updateAluno(Integer idPessoa, String nome, String turma){
        AtomicInteger verifica = new AtomicInteger(0);//Serve para thread Safe...
        listAluno.forEach(x -> {
            if( x.getIdPessoa().equals(idPessoa)){
                x.setNome(nome);
                x.setTurma(turma);
                verifica.set(1);
            } });
        
        if(verifica.get() == 1 ){
            preGravacao(listAluno);
            escrever(false);   
        }        
        return verifica.get();
    }
    
    public boolean delete(){
        aluno = consAluno();
        if(aluno!= null){
            listAluno.remove(aluno);
            preGravacao(listAluno);
            escrever(false);
            return true;
        }
        return false;
    }
    
    public Aluno consAluno(){
        Optional<Aluno> op = listAluno.stream().filter( x -> x.getIdPessoa().equals(aluno.getIdPessoa())).findFirst();
        try{
            return op.get();
        }catch(NoSuchElementException n){
            return null;
        }
        
    }
}
