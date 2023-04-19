/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Aluno;

/**
 *
 * @author jean
 */
public class TesteControllerNovo {

    public static void main(String[] args) {
        ControllerArquivoTextoPessoaGeral<Aluno> cg = new ControllerArquivoTextoPessoaGeral();

        cg.lerPessoas(token -> {
            Aluno pessoa = new Aluno();
            pessoa.setIdPessoa(Integer.parseInt((token.nextToken())));
            pessoa.setNome(token.nextToken());
            pessoa.setTurma(token.nextToken());
            return pessoa;
        }
        );
        cg.getLista().forEach(System.out::println);
        System.out.println("\n\n");
        cg.updateAluno();
       
        
        cg.getLista().forEach(System.out::println);
        
        cg.setPessoa(new Aluno(4));
        
        cg.delete();
        System.out.println("\n\n");
        cg.getLista().forEach(System.out::println);
        
    }
}
