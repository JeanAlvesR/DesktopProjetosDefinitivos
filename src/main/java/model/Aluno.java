/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jean
 */
public class Aluno extends Pessoa{
    
    private String turma;

    public Aluno() {
    }

    public Aluno(Integer idPessoa){
        this.idPessoa = idPessoa;
    }
    
    public Aluno(String nome, String turma) {
        super(nome);
        this.turma = turma;
    }
    
    public Aluno(Integer idPessoa, String nome, String turma) {
        super(idPessoa, nome);
        this.turma = turma;
    }
    
    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    
    @Override
    public String toString() {
        return idPessoa + ";"+ nome + ";" + turma + "\n";
    }
    
    
}
