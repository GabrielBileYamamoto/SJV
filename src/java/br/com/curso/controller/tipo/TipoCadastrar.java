/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.controller.tipo;

import br.com.curso.dao.TipoDAO;
import br.com.curso.dao.GenericDAO;
import br.com.curso.model.Tipo;
import br.com.curso.model.Estado;
import br.com.curso.model.Tipo;
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
@WebServlet(name = "TipoCadastrar", urlPatterns = {"/TipoCadastrar"})
public class TipoCadastrar extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        String mensagem = null;
        try{
        int idTipo = Integer.parseInt(request.getParameter("idtipo"));
        String categoria = request.getParameter("categoria");
        String imagem = request.getParameter("imagem");
       //gera objeto de Tipo
        Tipo oTipo = new Tipo(idTipo, categoria, imagem);
        //instancia camada dao de cidade
        TipoDAO dao = new TipoDAO();
        
        if(dao.cadastrar(oTipo)){
            //mensagem ="Cadastrado com Sucesso!";
            response.getWriter().write("1");
        }else{
            //mensagem ="Problemas ao cadastrar Tipo!";
            response.getWriter().write("0");
              }
            } catch (Exception e) {
                System.out.println("Problemas no servelet Cadastrar Tipo!Erro: " + e.getMessage());
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
