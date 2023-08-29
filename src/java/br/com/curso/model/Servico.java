/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.model;

/**
 *
 * @author Aluno
 */
public class Servico {
    private int idServico;
    private String nomeServico;
    private String descricao;
    private double valorServico;

    public Servico() {
        idServico = 0;
        nomeServico = "";
        descricao = "";
        valorServico = 0;
        
    }

    public Servico(int idServico, String nomeServico, String descricao, double valorServico) {
        this.idServico = idServico;
        this.nomeServico = nomeServico;
        this.descricao = descricao;
        this.valorServico = valorServico;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorServico() {
        return valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }
    
    
}
