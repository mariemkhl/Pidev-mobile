/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artisty.utils;

/**
 *
 * @author ASUS
 */





import com.artisty.entities.Reservations;
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
import javafx.collections.ObservableList;

/**
 *
 * @author farah
 */
public class Pdf {
     public void recupererpdf(Reservations res) throws DocumentException, BadElementException, IOException {
    
            
    // Ouvrir le document PDF
    Document document = new Document();
    PdfWriter writer = PdfWriter.getInstance(document, Storage.getInstance().createOutputStream("Event.pdf"));
    document.open();

    // Ajouter le titre de la facture
    //Font fontTitre = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.GREEN);
    Font fontTitre = FontFactory.getFont(FontFactory.COURIER_BOLD, 25, new BaseColor(10, 150, 100));
    Chunk chunkTitre = new Chunk("ARTISTY      ", fontTitre);
    document.add(chunkTitre);
    
    LineSeparator line = new LineSeparator();
line.setLineColor(new BaseColor(10, 150, 100));
line.setLineWidth(2);
document.add(line);
    
    
  /*  Image image1 = Image.getInstance("C:\\Users\\farah\\OneDrive\\Desktop\\log.png"); 
image1.setAbsolutePosition(520, PageSize.A4.getHeight() - -120 - image1.getHeight());
image1.scaleToFit(100, 70);
document.add(image1);
*/
 /* Image image = Image.getInstance("C://Users//farah//.cn1//My project-1.jpg"); 
image.setAbsolutePosition(PageSize.A4.getWidth() - 200, 300);
document.add(image);
  
    */// Ajouter le tableau
    PdfPTable table = new PdfPTable(4); // Nombre de colonnes
    table.setWidthPercentage(100); // Largeur de la table
    table.setSpacingBefore(10f); // Espace avant la table
    table.setSpacingAfter(10f); // Espace après la table
    // Ajouter les cellules
//   
       

// Récupérer les données du tableau

 Font font5 = new Font(Font.FontFamily.TIMES_ROMAN, 36, Font.NORMAL, BaseColor.DARK_GRAY);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Paragraph p1 = new Paragraph(" Booking Ticket  ", font5);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            
                        Font font2 = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLDITALIC, BaseColor.BLACK);

             document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Dear Client :  ", font2));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("your reservation has been made successfully :  ", font2));
            document.add(new Paragraph(" " + res.getName() + "", font2));
            document.add(new Paragraph(" Avec la date  : " + res.getDate() + "", font2));



    // Vérifier si le numéro de livraison correspond à celui entré dans le TextField
    
//        table.addCell(res.getName());
//        table.addCell(res.getDate().toString());

//        table.addCell(colab.getAdresseevent());
//        table.addCell(String.valueOf(colab.getPrixvehiculeevent()));

    // Centrer le tableau
    table.setTotalWidth(new float[] { 50f, 130f, 200f, 50f }); // ajustez les valeurs en fonction de vos besoins

    PdfContentByte canvas = writer.getDirectContent();
    Rectangle rect = canvas.getPdfDocument().getPageSize();
    //table.setTotalWidth(table.getTotalWidth());
    table.writeSelectedRows(0, -1, (rect.getLeft() + rect.getRight() - table.getTotalWidth()) / 2, rect.getTop() - table.getTotalHeight() - 200, canvas);

    
  
    
    // Ajouter une image en bas à droite de la page
/*Image image = Image.getInstance("C:\\Users\\farah\\.cn1\\My project-1.jpg");
image.setAbsolutePosition(PageSize.A4.getWidth() - 200, 300);
document.add(image);






*/PdfContentByte cb = writer.getDirectContent();
cb.beginText();
cb.setFontAndSize(FontFactory.getFont(FontFactory.COURIER_BOLD, 14, new BaseColor(0, 128, 0)).getBaseFont(), 14);
cb.showTextAligned(Element.ALIGN_RIGHT, "BUGBUSTERS", PageSize.A4.getWidth() - 20, 400, 0);
cb.endText();

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