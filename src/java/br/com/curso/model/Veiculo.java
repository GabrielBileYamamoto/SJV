/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class Veiculo{
    private int idVeiculo;
    private String placaVeiculo;
    private String cor;
    private Date ano;
    private Tipo tipo;
    private Modelo modelo;
    private Marca marca;
    private String imagemVeiculo;

    public Veiculo() {
        this.idVeiculo = 0;
        this.placaVeiculo = "";
        this.cor = "";
        this.ano = Conversao.dataAtual();
        this.tipo = new Tipo();
        this.modelo = new Modelo();
        this.marca = new Marca();
    }

    public Veiculo(int idVeiculo, String placaVeiculo, String cor, Date ano, Tipo tipo, Modelo modelo, Marca marca, String imagemVeiculo) {
        this.idVeiculo = idVeiculo;
        this.placaVeiculo = placaVeiculo;
        this.cor = cor;
        this.ano = ano;
        this.tipo = tipo;
        this.modelo = modelo;
        this.marca = marca;
         this.imagemVeiculo = imagemVeiculo;
    }

    public String getImagemVeiculo() {
        return imagemVeiculo;
    }

    public void setImagemVeiculo(String imagemVeiculo) {
        this.imagemVeiculo = imagemVeiculo;
    }
    
    

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

}