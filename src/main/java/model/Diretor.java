/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Jean
 */
public class Diretor extends Pessoa{
    
    protected Integer cpf;

    public Diretor() {
        cpf = 0;
    }

    public Diretor(String nome, Integer cpf) {
        super(nome);
        this.cpf = cpf;
    }

    public Diretor(Integer idPessoa){
        this.idPessoa = idPessoa;
    }
    
    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    
    @Override
    public String toString() {
        return idPessoa + ";"+ nome + ";" + cpf+"\n";
    } 
    
}
