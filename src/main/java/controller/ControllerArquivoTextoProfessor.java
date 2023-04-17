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
import model.Professor;

/**
 *
 * @author Jean
 */
public class ControllerArquivoTextoProfessor extends ControllerArquivoTexto {

    private List<Professor> listProfessor = new ArrayList();
    private Professor professor = null;

    public void lerProfessores() {
        setArquivo("Abrir");
        ler();
        String aux = getTexto();

        StringTokenizer tokens = new StringTokenizer(aux, "\n;");
        listProfessor = new ArrayList();
        while (tokens.hasMoreTokens()) {
            professor = new Professor();
            professor.setIdPessoa(Integer.parseInt(tokens.nextToken()));
            professor.setNome(tokens.nextToken());
            professor.setDisciplina(tokens.nextToken());
            professor.setCpf(Integer.parseInt(tokens.nextToken()));
            listProfessor.add(professor);
        }
    }

    public void gravarProfessores() {

        preGravacao(listProfessor);
        escrever(false);
    }

    public List<Professor> getListProfessor() {
        return listProfessor;
    }

    public void setListProfessor(List<Professor> listProfessor) {
        this.listProfessor = listProfessor;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setProfessor(String nome, Integer cpf, String disciplina) {
        professor = new Professor();
        professor.setNome(nome);
        professor.setCpf(cpf);
        professor.setDisciplina(disciplina);
    }

    public void gravarProfessor() {
        listProfessor.add(professor);
        preGravacao(professor.toString());
        escrever(true);
    }

    public int updateProfessor(Integer idPessoa, String nome, String disciplina, Integer cpf) {
        AtomicInteger verifica = new AtomicInteger(0);//Serve para thread Safe...
        listProfessor.forEach(x -> {
            if (x.getIdPessoa().equals(idPessoa)) {
                x.setNome(nome);
                x.setCpf(cpf);
                x.setDisciplina(disciplina);
                verifica.set(1);
            }
        });
        if (verifica.get() == 1) {
            preGravacao(listProfessor);
            escrever(false);
        }

        return verifica.get();
    }

    public boolean delete() {
        professor = consProfessor();
        if (professor != null) {
            listProfessor.remove(professor);
            preGravacao(listProfessor);
            escrever(false);
            return true;
        }
        return false;
    }

    public Professor consProfessor() {
        Optional<Professor> op = listProfessor.stream().filter(x -> x.getIdPessoa().equals(professor.getIdPessoa())).findFirst();
        try {
            return op.get();
        } catch (NoSuchElementException n) {
            return null;
        }

    }
}
