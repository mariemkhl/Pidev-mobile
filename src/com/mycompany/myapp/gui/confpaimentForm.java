package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Paiment;
import com.mycompany.myapp.services.ServicePaiment;

public class confpaimentForm extends Form {

    private int id; // Declare the id variable

    public confpaimentForm(String numCarte, String nomCarte, String cvCode, String prixTot) {
        super("Confirmation de paiement");

        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Label numCarteLabel = new Label("Numéro de carte: " + numCarte);
        Label nomCarteLabel = new Label("Nom sur la carte: " + nomCarte);
        Label cvCodeLabel = new Label("Code CV: " + cvCode);
        Label prixTotLabel = new Label("Montant total: " + prixTot);
        Button supprimerButton = new Button("Supprimer");

        Container container = getContentPane();
        container.addComponent(numCarteLabel);
        container.addComponent(nomCarteLabel);
        container.addComponent(cvCodeLabel);
        container.addComponent(prixTotLabel);
        container.addComponent(supprimerButton);

        supprimerButton.addActionListener((evt) -> {
            if (Dialog.show("Confirmation", "Are you sure you want to delete this payment?", "Yes", "No")) {
                ServicePaiment.supprimerPaiment(id);
                // Display success message or perform additional actions
                Dialog.show("Success", "Payment deleted successfully", "OK", null);
            }
        });
    }

    public confpaimentForm(Resources theme) {
        // Constructor for theme
    }
}

