/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.controller.pedido;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
// Classe para definir o evento de imagem de fundo
    class BackgroundImageEvent extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                // Carregue a imagem de fundo
                Image imagemDeFundo = Image.getInstance("C:\\GabrielBile\\SJV-teste\\web\\img\\background2.");
                imagemDeFundo.setAbsolutePosition(0, 0); // Posição da imagem
                imagemDeFundo.scaleAbsolute(document.getPageSize()); // Redimensiona a imagem para preencher a página

                PdfContentByte over = writer.getDirectContentUnder();
                over.addImage(imagemDeFundo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }