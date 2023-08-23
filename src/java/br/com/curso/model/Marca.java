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
public class Marca {
    private int idMarca;
    private String nomeMarca;
     private String imagemMarca;

    public Marca() {
        this.idMarca = 0;
        this.nomeMarca = "";
        
        
    }

    public Marca(int idMarca, String nomeMarca, String imagemMarca) {
        this.idMarca = idMarca;
        this.nomeMarca = nomeMarca;
        this.imagemMarca = imagemMarca;
    }

    public String getImagemMarca() {
        return imagemMarca;
    }

    public void setImagemMarca(String imagemMarca) {
        this.imagemMarca = imagemMarca;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

}