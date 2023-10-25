/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class Pedido {
    private int idPedido;
    private String nomePedido;
    private Date dataPedido;
    private double valorTotal;
    private String pago;
    private Cliente cliente;
    private Servico servico;
    private Veiculo veiculo;

    public Pedido(int idPedido, String nomePedido, Date dataPedido, double valorTotal, String pago, Cliente cliente, Servico servico, Veiculo veiculo) {
        this.idPedido = idPedido;
        this.nomePedido = nomePedido;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.pago = pago;
        this.cliente = cliente;
        this.servico = servico;
        this.veiculo = veiculo;
    }

    public Pedido() throws ParseException {
        this.idPedido = 0;
        this.nomePedido = "";
        this.dataPedido = Conversao.dataAtual();
        this.valorTotal = 0;
        this.pago = "A";
        this.cliente = Cliente.clienteVazio();
        this.servico = new Servico();
        this.veiculo = new Veiculo();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNomePedido() {
        return nomePedido;
    }

    public void setNomePedido(String nomePedido) {
        this.nomePedido = nomePedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal + servico.getValorServico();
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    public void calcularValorTotal(List<Servico> servicosSelecionados) {
        valorTotal = 0.0;

        for (Servico servico : servicosSelecionados) {
            valorTotal += servico.getValorServico();
        }
    }
    
    
}
