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
public class Modelo {
    private int idModelo;
    private String nomeModelo;

    
    public Modelo() {
        this.idModelo = 0;
        this.nomeModelo = "";
    }

    public Modelo(int idModelo, String nomeModelo) {
        this.idModelo = idModelo;
        this.nomeModelo = nomeModelo;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

}