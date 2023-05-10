/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

/**s
 *
 * @author ASUS
 */

import com.mycompany.entity.Reservations;
import com.codename1.io.Storage;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
//import com.artisty.entities.Resevations;


import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 *
 * @author farah
 */
public class Pdf {
    public void recupererpdf(Reservations res) throws DocumentException, BadElementException, IOException {
          
          // Ouvrir le document PDF
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, Storage.getInstance().createOutputStream("Reservation.pdf"));
        document.open();

        // Ajouter le titre de la facture
// Ajout d'un paragraphe au document PDF avec la police de caractères créée
        Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL, BaseColor.GRAY);
        Paragraph p = new Paragraph(" PLATEFORME ARTISTY ", font1);

        p.setAlignment(Element.ALIGN_RIGHT);
         document.add(p);

//        LineSeparator line = new LineSeparator();
//        line.setLineColor(new BaseColor(10, 150, 100));
//        line.setLineWidth(2);
//        document.add(line);
//
//      

// Récupérer les données du tableau
        Font font5 = new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.NORMAL, BaseColor.BLUE);

        document.add(new Paragraph(" "));
        Paragraph p1 = new Paragraph(" Booking Ticket  ", font5);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        
        p1.setAlignment(Element.ALIGN_CENTER);
                    document.add(p1);

        
        

        Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLDITALIC, BaseColor.BLACK);

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Dear Client :  ", font2));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Your reservation has been made successfully  with name: "  + res.getName() + "", font2));
      //  document.add(new Paragraph(" " + res.getName() + "", font2));
        document.add(new Paragraph(" in  : " + res.getDate() + "", font2));
                document.add(new Paragraph(" Thanks for participation , Have a nice day  " , font2));
                
                 document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
         document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));



        PdfContentByte canvas = writer.getDirectContent();
        Rectangle rect = canvas.getPdfDocument().getPageSize();
        //table.setTotalWidth(table.getTotalWidth());
//        table.writeSelectedRows(0, -1, (rect.getLeft() + rect.getRight() - table.getTotalWidth()) / 2, rect.getTop() - table.getTotalHeight() - 200, canvas);

        
        PdfContentByte cb = writer.getDirectContent();
        
//        cb.beginText();
//        cb.setFontAndSize(FontFactory.getFont(FontFactory.COURIER_BOLD, 14, new BaseColor(0, 128, 0)).getBaseFont(), 14);
//        cb.showTextAligned(Element.ALIGN_RIGHT, "BUGBUSTERS", PageSize.A4.getWidth() - 20, 400, 0);
//        cb.endText();

//PdfContentByte cb = writer.getDirectContent();
        cb.beginText();
        cb.setFontAndSize(FontFactory.getFont(FontFactory.COURIER_BOLD, 14, new BaseColor(0, 128, 0)).getBaseFont(), 14);
        cb.showTextAligned(Element.ALIGN_RIGHT, "Signature  : ", PageSize.A4.getWidth() - 399, 400, 0);
        cb.endText();
        


        /*Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.BLACK);
Paragraph p = new Paragraph("BUGBUSTERS", font);
p.setAlignment(Element.ALIGN_RIGHT);
document.add(p);

         */ // Fermer le document PDF
        document.close();
    
            
    
     }
    
}