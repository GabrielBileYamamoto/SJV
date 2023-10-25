/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.controller.pedido;

import br.com.curso.dao.ClienteDAO;
import br.com.curso.dao.PedidoDAO;
import br.com.curso.dao.EstadoDAO;
import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.ServicoDAO;
import br.com.curso.dao.VeiculoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "PedidoCarregar", urlPatterns = {"/PedidoCarregar"})
public class PedidoCarregar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try {
            int idPedido = Integer.parseInt(request.getParameter("idPedido"));
            PedidoDAO oPedidoDAO = new PedidoDAO();
            request.setAttribute("pedido", oPedidoDAO.carregar(idPedido));
            ClienteDAO oClienteDAO = new ClienteDAO();
            request.setAttribute("clientes", oClienteDAO.listar());
            ServicoDAO oServicoDAO = new ServicoDAO();
            request.setAttribute("servicos", oServicoDAO.listar());
            VeiculoDAO oVeiculoDAO = new VeiculoDAO();
            request.setAttribute("veiculos", oVeiculoDAO.listar());;
            request.getRequestDispatcher("/cadastros/pedido/pedidoCadastrar.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Erro carregar pedido " + ex.getMessage());
            ex.printStackTrace();
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
