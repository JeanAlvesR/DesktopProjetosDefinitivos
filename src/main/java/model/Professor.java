/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jean
 */
public class Professor extends Diretor{
    
    private String disciplina;

    public Professor(String nome, String disciplina, Integer cpf) {
        super(nome, cpf);
        this.disciplina = disciplina;
    }

    public Professor(Integer idPessoa ,String nome, String disciplina, Integer cpf) {
        super(idPessoa, nome, cpf);
        this.disciplina = disciplina;
    }
    
    public Professor(Integer idPessoa){
        this.idPessoa = idPessoa;
    }
    
    public Professor() {
        disciplina = "";
    }

    
    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    
    @Override
    public String toString() {
        return idPessoa + ";"+ nome+";"+disciplina+";"+cpf +"\n";
    }

}
