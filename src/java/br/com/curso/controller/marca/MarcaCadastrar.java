/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.controller.marca;

import br.com.curso.dao.MarcaDAO;
import br.com.curso.dao.GenericDAO;
import br.com.curso.model.Marca;
import br.com.curso.model.Estado;
import br.com.curso.model.Marca;
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
@WebServlet(name = "MarcaCadastrar", urlPatterns = {"/MarcaCadastrar"})
public class MarcaCadastrar extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        String mensagem = null;
        try{
        int idMarca = Integer.parseInt(request.getParameter("idmarca"));
        String nomeMarca = request.getParameter("nomemarca");
        String imagemMarca = request.getParameter("imagemmarca");
       //gera objeto de Marca
        Marca oMarca = new Marca(idMarca, nomeMarca, imagemMarca);
        //instancia camada dao de cidade
        MarcaDAO dao = new MarcaDAO();
        
        if(dao.cadastrar(oMarca)){
            //mensagem ="Cadastrado com Sucesso!";
            response.getWriter().write("1");
        }else{
            //mensagem ="Problemas ao cadastrar Marca!";
            response.getWriter().write("0");
              }
            } catch (Exception e) {
                System.out.println("Problemas no servelet Cadastrar Marca!Erro: " + e.getMessage());
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
