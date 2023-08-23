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
public class Tipo {
    private int idTipo;
    private String categoria;

    public Tipo() {
        this.idTipo = 0;
        this.categoria = "";
    }

    public Tipo(int idTipo, String categoria) {
        this.idTipo = idTipo;
        this.categoria = categoria;
    }


    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}