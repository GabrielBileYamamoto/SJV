/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.controller.veiculo;

import br.com.curso.dao.VeiculoDAO;
import br.com.curso.dao.ClienteDAO;

import br.com.curso.dao.PessoaDAO;
import br.com.curso.model.Veiculo;
import br.com.curso.model.Cliente;

import br.com.curso.model.Pessoa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "VeiculoBuscarPlaca", urlPatterns = {"/VeiculoBuscarPlaca"})
public class VeiculoBuscarPlaca extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try{
            String placaVeiculo = request.getParameter("placaveiculo");
            int id = 0;
            String JsonRetorno="";
            if (placaVeiculo != ""){
                VeiculoDAO oVeiDAO = new VeiculoDAO();
                //busca adm por cpf.
                Veiculo oVei = (Veiculo) oVeiDAO.carregar(oVeiDAO.verificarPlaca(placaVeiculo));
                //gera retorno
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                JsonRetorno = gson.toJson(oVei);
            }
            response.setCharacterEncoding("iso-8859-1");
            response.getWriter().write(JsonRetorno);
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar veiculo por PlacaVeiculo"
            + "Erro: " + ex.getMessage());
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
