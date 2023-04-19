/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jean
 */
public abstract class Pessoa {
    
    protected static Integer ID = 1;
    protected Integer idPessoa;
    protected String nome;

    public Pessoa() {
        nome = "";
        idPessoa = ID;
        ID++;
    }

    public Pessoa(String nome) {
        this.nome = nome;
        idPessoa = ID;
        ID++;
    }
    
    public Pessoa(Integer idPessoa, String nome){
        this.idPessoa = idPessoa;
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }
    
}
