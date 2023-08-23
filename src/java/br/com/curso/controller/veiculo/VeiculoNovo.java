
package br.com.curso.controller.veiculo;

import br.com.curso.dao.MarcaDAO;
import br.com.curso.dao.ModeloDAO;
import br.com.curso.dao.TipoDAO;
import br.com.curso.model.Veiculo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "VeiculoNovo", urlPatterns = {"/VeiculoNovo"})
public class VeiculoNovo extends HttpServlet {

    
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException, Exception {
       response.setContentType("text/html;charset=iso-8859-1");
       Veiculo oVeiculo = new Veiculo();
       request.setAttribute("veiculo", oVeiculo);
       TipoDAO oTipoDAO = new TipoDAO();
       request.setAttribute("tipos", oTipoDAO.listar());
       ModeloDAO oModeloDAO = new ModeloDAO();
       request.setAttribute("modelos", oModeloDAO.listar());
       MarcaDAO oMarcaDAO = new MarcaDAO();
       request.setAttribute("marcas", oMarcaDAO.listar());
       request.getRequestDispatcher("/cadastros/veiculo/veiculoCadastrar.jsp").forward(request, response);
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
            Logger.getLogger(VeiculoNovo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VeiculoNovo.class.getName()).log(Level.SEVERE, null, ex);
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
