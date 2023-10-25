package br.com.curso.controller.pedido;

import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.PedidoDAO;
import br.com.curso.model.Pedido;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PedidoGerar", urlPatterns = {"/PedidoGerar"})
public class PedidoGerar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, response.getOutputStream());
            documento.open();
            Paragraph titulo = new Paragraph("Relatório de Pedidos");
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph(" "));

            PdfPTable tabela = new PdfPTable(7);
            tabela.setWidthPercentage(100); // Define a largura da tabela como 100% da página

            PdfPCell col1 = new PdfPCell(new Paragraph("Pedido"));
            col1.setHorizontalAlignment(Element.ALIGN_CENTER); // Centralize o texto na célula

            PdfPCell col2 = new PdfPCell(new Paragraph("Cliente"));
            col2.setHorizontalAlignment(Element.ALIGN_CENTER); // Centralize o texto na célula

            PdfPCell col3 = new PdfPCell(new Paragraph("Serviço"));
            col3.setHorizontalAlignment(Element.ALIGN_CENTER); // Centralize o texto na célula

            PdfPCell col4 = new PdfPCell(new Paragraph("Placa do Veículo"));
            col4.setHorizontalAlignment(Element.ALIGN_CENTER); // Centralize o texto na célula

            PdfPCell col5 = new PdfPCell(new Paragraph("Valor Total"));
            col5.setHorizontalAlignment(Element.ALIGN_CENTER); // Centralize o texto na célula

            PdfPCell col6 = new PdfPCell(new Paragraph("Data"));
            col6.setHorizontalAlignment(Element.ALIGN_CENTER); // Centralize o texto na célula

            PdfPCell col7 = new PdfPCell(new Paragraph("Pago"));
            col7.setHorizontalAlignment(Element.ALIGN_CENTER); // Centralize o texto na célula

// Adicione as células ao cabeçalho da tabela
            tabela.addCell(col1);
            tabela.addCell(col2);
            tabela.addCell(col3);
            tabela.addCell(col4);
            tabela.addCell(col5);
            tabela.addCell(col6);
            tabela.addCell(col7);

            GenericDAO dao = new PedidoDAO();
            List<Object> pedidos = dao.listar();

            float[] columnWidths = {2, 2, 2.5f, 2, 2, 2, 2}; // Por exemplo, 1 unidade para a primeira coluna e 2 unidades para as outras
            tabela.setWidths(columnWidths);
            for (Object obj : pedidos) {
                if (obj instanceof Pedido) {
                    Pedido pedido = (Pedido) obj;
                    PdfPCell cell = new PdfPCell();
                    cell.setBorderColor(BaseColor.BLACK);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(5);
                    Font cellFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
                    cell.setPhrase(new Phrase(pedido.getNomePedido().toString(), cellFont));
                    tabela.addCell(cell);
                    cell = new PdfPCell();
                    cell.setBorderColor(BaseColor.BLACK);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(5);
                    cell.setPhrase(new Phrase(pedido.getCliente().getNome(), cellFont));
                    tabela.addCell(cell);
                    cell = new PdfPCell();
                    cell.setBorderColor(BaseColor.BLACK);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(5);
                    cell.setPhrase(new Phrase(pedido.getServico().getDescricao(), cellFont));
                    tabela.addCell(cell);
                    cell = new PdfPCell();
                    cell.setBorderColor(BaseColor.BLACK);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(5);
                    cell.setPhrase(new Phrase(pedido.getVeiculo().getPlacaVeiculo(), cellFont));
                    tabela.addCell(cell);
                    cell = new PdfPCell();
                    cell.setBorderColor(BaseColor.BLACK);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(5);
                    cell.setPhrase(new Phrase(String.format("%.2f", pedido.getServico().getValorServico()), cellFont));
                    tabela.addCell(cell);
                    cell = new PdfPCell();
                    cell.setBorderColor(BaseColor.BLACK);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(5);
                    cell.setPhrase(new Phrase(pedido.getDataPedido().toString(), cellFont));
                    tabela.addCell(cell);

                    // Adicione a célula "Pago" ou "Não Pago" com base no valor de pagamento (I ou A)
                    PdfPCell cellPagamento = new PdfPCell();
                    cellPagamento.setBorderColor(BaseColor.BLACK);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellPagamento.setPadding(5);
                    Font fontPagamento = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);

                    if (pedido.getPago().equals("I")) {
                        fontPagamento.setColor(BaseColor.GREEN);
                        cellPagamento.setPhrase(new Phrase("Pago", fontPagamento));
                    } else {
                        fontPagamento.setColor(BaseColor.RED);
                        cellPagamento.setPhrase(new Phrase("Não Pago", fontPagamento));
                    }

                    tabela.addCell(cellPagamento);
                }
            }
            
            
            
            documento.add(tabela);
            documento.close();
        } catch (Exception e) {
            e.printStackTrace();
            documento.close();
        }
        request.getRequestDispatcher("/cadastros/pedido/pedido.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Relatório de Pedidos";
    }
}
