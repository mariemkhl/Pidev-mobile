package com.mycompany.forms;

import com.mycompany.entity.Event;
import com.mycompany.entity.Utilisateur;
import com.mycompany.forms.AccueilFront;
import com.mycompany.services.EventService;
import com.mycompany.services.UtilisateurService;
import com.mycompany.utils.AlertUtils;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import java.io.IOException;
import java.util.ArrayList;

public class AjouterEvent extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    String selectedImage;
    boolean imageEdited = false;

    TextField nameTF;
    TextField locationTF;
    TextField categorieTF;
    TextField nbplacetotalTF;
    TextField imageTF;
    TextField descriptionTF;
    Label nameLabel;
    Label locationLabel;
    Label categorieLabel;
    Label nbplacetotalLabel;
    Label imageLabel;
    Label descriptionLabel;
    PickerComponent dateTF;

    //ArrayList<Utilisateur> listUtilisateurs;
    //PickerComponent utilisateurPC;
    Utilisateur selectedUtilisateur = null;

    ImageViewer imageIV;
    Button selectImageButton;

    Button manageButton;

    Form previous;

    public AjouterEvent(Form previous) {
        super("Ajouter", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

       // getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> AccueilFront.accueilForm.showBack());
    }

    private void addGUIs() {

        /*String[] utilisateurStrings;
        int utilisateurIndex;
        utilisateurPC = PickerComponent.createStrings("").label("Utilisateurttttttt");
       // listUtilisateurs = UtilisateurService.getInstance().getAll();
        utilisateurStrings = new String[listUtilisateurs.size()];
        utilisateurIndex = 0;
        for (Utilisateur utilisateur : listUtilisateurs) {
            utilisateurStrings[utilisateurIndex] = utilisateur.getEmail();
            utilisateurIndex++;
        }
        if (listUtilisateurs.size() > 0) {
            utilisateurPC.getPicker().setStrings(utilisateurStrings);
            utilisateurPC.getPicker().addActionListener(l -> selectedUtilisateur = listUtilisateurs.get(utilisateurPC.getPicker().getSelectedStringIndex()));
        } else {
            utilisateurPC.getPicker().setStrings("");
        }*/
        nameLabel = new Label("Name : ");
        nameLabel.setUIID("labelDefault");
        nameTF = new TextField();
        nameTF.setHint("Tapez le name");

        dateTF = PickerComponent.createDate(null).label("Date");

        locationLabel = new Label("Location : ");
        locationLabel.setUIID("labelDefault");
        locationTF = new TextField();
        locationTF.setHint("Tapez le location");

        categorieLabel = new Label("Categorie : ");
        categorieLabel.setUIID("labelDefault");
        categorieTF = new TextField();
        categorieTF.setHint("Tapez le categorie");

        nbplacetotalLabel = new Label("Nbplacetotal : ");
        nbplacetotalLabel.setUIID("labelDefault");
        nbplacetotalTF = new TextField();
        nbplacetotalTF.setHint("Tapez le nbplacetotal");

        descriptionLabel = new Label("Description : ");
        descriptionLabel.setUIID("labelDefault");
        descriptionTF = new TextField();
        descriptionTF.setHint("Tapez le description");

//        imageLabel = new Label("Image : ");
//        imageLabel.setUIID("labelDefault");
//        selectImageButton = new Button("Ajouter une image");

      //  imageIV = new ImageViewer(theme.getImage("default.jpg").fill(1100, 500));

        manageButton = new Button("Ajouter");
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
             //   imageLabel, imageIV,
               // selectImageButton,
                nameLabel, nameTF,
                dateTF,
                locationLabel, locationTF,
                categorieLabel, categorieTF,
                nbplacetotalLabel, nbplacetotalTF,
                descriptionLabel, descriptionTF,
                //utilisateurPC,
                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {

      /*  selectImageButton.addActionListener(a -> {
            selectedImage = Capture.capturePhoto(900, -1);
            try {
                imageEdited = true;
                imageIV.setImage(Image.createImage(selectedImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
            selectImageButton.setText("Modifier l'image");
        });*/
        Utilisateur u = new Utilisateur();
        u.setId(1);
        selectedUtilisateur = u;

        manageButton.addActionListener(action -> {
            if (controleDeSaisie()) {
                int responseCode = EventService.getInstance().add(
                        new Event(
                                nameTF.getText(),
                                dateTF.getPicker().getDate(),
                                locationTF.getText(),
                                selectedUtilisateur,
                                categorieTF.getText(),
                                (int) Float.parseFloat(nbplacetotalTF.getText()),
                                //selectedImage,
                                "vbgfgf",
                                descriptionTF.getText()
                        )
                );
                if (responseCode == 200) {
                    AlertUtils.makeNotification("Event ajouté avec succes");
                    showBackAndRefresh();
                } else {
                    Dialog.show("Erreur", "Erreur d'ajout de event. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            }
        });
    }

    private void showBackAndRefresh() {
        ((AfficherToutEvent) previous).refresh();
        AccueilFront.accueilForm.showBack();
    }

    private boolean controleDeSaisie() {

        if (nameTF.getText().equals("")) {
            Dialog.show("Avertissement", "Name vide", new Command("Ok"));
            return false;
        }

        if (dateTF.getPicker().getDate() == null) {
            Dialog.show("Avertissement", "Veuillez saisir la date", new Command("Ok"));
            return false;
        }

        if (locationTF.getText().equals("")) {
            Dialog.show("Avertissement", "Location vide", new Command("Ok"));
            return false;
        }

        if (categorieTF.getText().equals("")) {
            Dialog.show("Avertissement", "Categorie vide", new Command("Ok"));
            return false;
        }

        if (nbplacetotalTF.getText().equals("")) {
            Dialog.show("Avertissement", "Nbplacetotal vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(nbplacetotalTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", nbplacetotalTF.getText() + " n'est pas un nombre valide (nbplacetotal)", new Command("Ok"));
            return false;
        }

        if (descriptionTF.getText().equals("")) {
            Dialog.show("Avertissement", "Description vide", new Command("Ok"));
            return false;
        }

        if (selectedUtilisateur == null) {
            Dialog.show("Avertissement", "Veuillez choisir un utilisateur", new Command("Ok"));
            return false;
        }

//        if (selectedImage == null) {
//            Dialog.show("Avertissement", "Veuillez choisir une image", new Command("Ok"));
//            return false;
//        }

        return true;
    }
}
