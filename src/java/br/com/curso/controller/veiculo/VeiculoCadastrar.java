/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.controller.veiculo;

import br.com.curso.utils.Conversao;
import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.VeiculoDAO;
import br.com.curso.model.Marca;
import br.com.curso.model.Modelo;
import br.com.curso.model.Tipo;
import br.com.curso.model.Veiculo;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "VeiculoCadastrar", urlPatterns = {"/VeiculoCadastrar"})
public class VeiculoCadastrar extends HttpServlet {

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
        response.setContentType("text/html;charset=iso-8859-1");
        String mensagem = null;
        try{
        int idVeiculo = Integer.parseInt(request.getParameter("idveiculo"));
        String placaVeiculo = request.getParameter("placaveiculo");
        String cor = request.getParameter("cor");
        Date ano = Date.valueOf(request.getParameter("ano"));
        int idTipo = Integer.parseInt(request.getParameter("idtipo"));
        int idModelo = Integer.parseInt(request.getParameter("idmodelo"));
        int idMarca = Integer.parseInt(request.getParameter("idmarca"));
        String imagemVeiculo = request.getParameter("imagemveiculo");
        //cria objeto de Tipo.
        Tipo oTipo = new Tipo();
        oTipo.setIdTipo(idTipo);
        //cria objeto de Modelo.
        Modelo oModelo = new Modelo();
        oModelo.setIdModelo(idModelo);
        //cria objeto de Marca.
        Marca oMarca = new Marca();
        oMarca.setIdMarca(idMarca);
        
        //gera objeto de Veiculo
        Veiculo oVeiculo = new Veiculo(idVeiculo, placaVeiculo, cor, ano, oTipo, oModelo, oMarca, imagemVeiculo);
        //instancia camada dao de veiculo
        VeiculoDAO dao = new VeiculoDAO();
        
        if(dao.cadastrar(oVeiculo)){
            //mensagem ="Cadastrado com Sucesso!";
            response.getWriter().write("1");
        }else{
            //mensagem ="Problemas ao cadastrar Veiculo!";
            response.getWriter().write("0");
              }
            } catch (Exception e) {
                System.out.println("Problemas no servelet Cadastrar Veiculo!Erro: " + e.getMessage());
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

