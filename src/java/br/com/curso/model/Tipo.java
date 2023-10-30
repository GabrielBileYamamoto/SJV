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
    private String imagem;

    public Tipo() {
        this.idTipo = 0;
        this.categoria = "";
        this.imagem = "";
    }

    public Tipo(int idTipo, String categoria, String imagem) {
        this.idTipo = idTipo;
        this.categoria = categoria;
        this.imagem = imagem;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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