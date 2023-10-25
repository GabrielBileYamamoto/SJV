package br.com.curso.controller.pedido;

import br.com.curso.dao.ClienteDAO;
import br.com.curso.dao.EstadoDAO;
import br.com.curso.dao.ServicoDAO;
import br.com.curso.dao.VeiculoDAO;
import br.com.curso.model.Pedido;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PedidoNovo", urlPatterns = {"/PedidoNovo"})
public class PedidoNovo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=iso-8859-1");
        Pedido oPedido = new Pedido();
        request.setAttribute("pedido", oPedido);
        ClienteDAO oClienteDAO = new ClienteDAO();
        request.setAttribute("clientes", oClienteDAO.listar());
        ServicoDAO oServicoDAO = new ServicoDAO();
        request.setAttribute("servicos", oServicoDAO.listar());
        VeiculoDAO oVeiculoDAO = new VeiculoDAO();
        request.setAttribute("veiculos", oVeiculoDAO.listar());;
        request.getRequestDispatcher("/cadastros/pedido/pedidoCadastrar.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(PedidoNovo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(PedidoNovo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
