/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.controller.pedido;

import br.com.curso.dao.PedidoDAO;
import br.com.curso.model.Cliente;
import br.com.curso.model.Servico;
import br.com.curso.model.Pedido;
import br.com.curso.model.Veiculo;
import br.com.curso.utils.Conversao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "PedidoCadastrar", urlPatterns = {"/PedidoCadastrar"})
public class PedidoCadastrar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensagem = null;
        try{           
            //busca parametros do formulario (ajax) - view
            int idPedido = Integer.parseInt(request.getParameter("idpedido"));
            String nomePedido = request.getParameter("nomepedido");
            Date dataPedido = Date.valueOf(request.getParameter("datapedido"));
            double valorTotal = Conversao.valorDinheiro(request.getParameter("valortotal"));
            String pago = request.getParameter("pago");
            int idCliente = Integer.parseInt(request.getParameter("idcliente"));
            int idVeiculo = Integer.parseInt(request.getParameter("idveiculo"));
            int idServico = Integer.parseInt(request.getParameter("idservico"));
            
            //cria objeto de cidade.
            Veiculo oVeiculo = new Veiculo();
            oVeiculo.setIdVeiculo(idVeiculo);
            
            Servico oServico = new Servico();
            oServico.setIdServico(idServico);
            
            Cliente oCliente = null;
            try {
                oCliente = Cliente.clienteVazio();
            } catch (ParseException e) {
                // Handle the ParseException if needed
                e.printStackTrace();
            }
            oCliente.setIdCliente(idCliente);
            
            //gera objeto de pedido
            Pedido oPedido = new Pedido(idPedido, nomePedido, dataPedido, valorTotal, pago, oCliente, oServico, oVeiculo);
             PedidoDAO dao = new PedidoDAO();

            if(dao.cadastrar(oPedido)){
                //mensagem = "Cadastrado com Sucesso!";
                response.getWriter().write("1");
            }else{
                //mensagem = "Problemas ao cadastrar Despesa!";
                response.getWriter().write("0");
            }
        } catch (Exception e) {
            System.out.println("Problemas no servelet Cadastrar Pedido!Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
