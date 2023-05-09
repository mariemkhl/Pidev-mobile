package com.artisty.services;

import com.artisty.entities.Reservations;
import com.artisty.utils.Statics;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservationsService {

    public static ReservationsService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Reservations> listReservationss;


    private ReservationsService() {
        cr = new ConnectionRequest();
    }

    public static ReservationsService getInstance() {
        if (instance == null) {
            instance = new ReservationsService();
        }
        return instance;
    }

    public ArrayList<Reservations> getAll() {
        listReservationss = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/reservations");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listReservationss = getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listReservationss;
    }

    private ArrayList<Reservations> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Reservations reservations = new Reservations(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        (String) obj.get("name"),
                        new SimpleDateFormat("dd-MM-yyyy").parse((String) obj.get("date"))

                );

                listReservationss.add(reservations);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listReservationss;
    }

    public int add(Reservations reservations) {

        cr = new ConnectionRequest();

        cr.setHttpMethod("POST");
        cr.setUrl(Statics.BASE_URL + "/reservations/add");

        cr.addArgument("name", reservations.getName());
        cr.addArgument("date", new SimpleDateFormat("dd-MM-yyyy").format(reservations.getDate()));


        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    public int edit(Reservations reservations) {

        cr = new ConnectionRequest();
        cr.setHttpMethod("POST");
        cr.setUrl(Statics.BASE_URL + "/reservations/edit");
        cr.addArgument("id", String.valueOf(reservations.getId()));

        cr.addArgument("name", reservations.getName());
        cr.addArgument("date", new SimpleDateFormat("dd-MM-yyyy").format(reservations.getDate()));


        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    public int delete(int reservationsId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/reservations/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(reservationsId));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }
    
      public void recupererpdf(Reservations res) throws DocumentException, BadElementException, IOException {
    
            
    // Ouvrir le document PDF
    Document document = new Document();
    PdfWriter writer = PdfWriter.getInstance(document, Storage.getInstance().createOutputStream("Event.pdf"));
    document.open();

    // Ajouter le titre de la facture
    //Font fontTitre = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.GREEN);
    Font fontTitre = FontFactory.getFont(FontFactory.COURIER_BOLD, 25, new BaseColor(10, 150, 100));
    Chunk chunkTitre = new Chunk("ARTISTY                                     ", fontTitre);
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
    PdfPCell cell;
    cell = new PdfPCell(new Phrase("Nom Event"));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Date Event"));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);

//    cell = new PdfPCell(new Phrase("Adresse Event"));
//    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
//    table.addCell(cell);
//
//    cell = new PdfPCell(new Phrase("Prix Event"));
//    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
//    table.addCell(cell);

    // Ajouter les données du tableau
    // Récupérer la valeur du TextField
       

// Récupérer les données du tableau



    // Vérifier si le numéro de livraison correspond à celui entré dans le TextField
    
        table.addCell(res.getName());
        table.addCell(res.getDate().toString());

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
cb.showTextAligned(Element.ALIGN_RIGHT, "Signature du Livreur : ", PageSize.A4.getWidth() - 399, 400, 0);
cb.endText();

cb.beginText();
cb.setFontAndSize(FontFactory.getFont(FontFactory.COURIER_BOLD, 14, new BaseColor(0, 200, 0)).getBaseFont(), 18);
cb.showTextAligned(Element.ALIGN_RIGHT, "FACTURE : ", PageSize.A4.getWidth() - 260, 700, 0);
cb.endText();

cb.beginText();
cb.setFontAndSize(FontFactory.getFont(FontFactory.COURIER_BOLD, 14, new BaseColor(0, 128, 0)).getBaseFont(), 14);
cb.showTextAligned(Element.ALIGN_RIGHT, "Signature du Client : ", PageSize.A4.getWidth() - 407, 350, 0);
cb.endText();

/*Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.BLACK);
Paragraph p = new Paragraph("BUGBUSTERS", font);
p.setAlignment(Element.ALIGN_RIGHT);
document.add(p);

   */ // Fermer le document PDF
    document.close();
     }
    
    
    
    
}
