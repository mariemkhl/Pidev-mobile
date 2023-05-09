package com.artisty.gui.back.event;


import com.artisty.entities.Event;
import com.artisty.entities.Utilisateur;
import com.artisty.services.EventService;
import com.artisty.services.UtilisateurService;
import com.artisty.utils.AlertUtils;
import com.artisty.utils.Statics;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import java.io.IOException;
import java.util.ArrayList;

public class ModifierEvent extends Form {


    Resources theme = UIManager.initFirstTheme("/theme");
    String selectedImage;
    boolean imageEdited = false;


    Event currentEvent;

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



    ImageViewer imageIV;
    Button selectImageButton;

    Button manageButton;

    Form previous;

    public ModifierEvent(Form previous) {
        super("Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentEvent = AfficherToutEvent.currentEvent;

        addGUIs();
        addActions();


        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {

      /*  String[] utilisateurStrings;
        int utilisateurIndex;
        utilisateurPC = PickerComponent.createStrings("").label("Utilisateur");
        listUtilisateurs = UtilisateurService.getInstance().getAll();
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


        imageLabel = new Label("Image : ");
        imageLabel.setUIID("labelDefault");
        selectImageButton = new Button("Ajouter une image");

        nameTF.setText(currentEvent.getName());
        dateTF.getPicker().setDate(currentEvent.getDate());
        locationTF.setText(currentEvent.getLocation());

        categorieTF.setText(currentEvent.getCategorie());
        nbplacetotalTF.setText(String.valueOf(currentEvent.getNbplacetotal()));

        descriptionTF.setText(currentEvent.getDescription());

        /*utilisateurPC.getPicker().setSelectedString(currentEvent.getUtilisateur().getEmail());
        selectedUtilisateur = currentEvent.getUtilisateur();*/


        if (currentEvent.getImage() != null) {
            selectedImage = currentEvent.getImage();
            String url = Statics.EVENT_IMAGE_URL + currentEvent.getImage();
            Image image = URLImage.createToStorage(
                    EncodedImage.createFromImage(theme.getImage("default.jpg").fill(1100, 500), false),
                    url,
                    url,
                    URLImage.RESIZE_SCALE
            );
            imageIV = new ImageViewer(image);
        } else {
            imageIV = new ImageViewer(theme.getImage("default.jpg").fill(1100, 500));
        }
        imageIV.setFocusable(false);


        manageButton = new Button("Modifier");
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
                imageLabel, imageIV,
                selectImageButton,
                nameLabel, nameTF,
                dateTF,
                locationLabel, locationTF,

                categorieLabel, categorieTF,
                nbplacetotalLabel, nbplacetotalTF,

                descriptionLabel, descriptionTF,
               
                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {

        selectImageButton.addActionListener(a -> {
            selectedImage = Capture.capturePhoto(900, -1);
            try {
                imageEdited = true;
                imageIV.setImage(Image.createImage(selectedImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
            selectImageButton.setText("Modifier l'image");
        });

        manageButton.addActionListener(action -> {
            if (controleDeSaisie()) {
                int responseCode = EventService.getInstance().edit(
                        new Event(
                                currentEvent.getId(),


                                nameTF.getText(),
                                dateTF.getPicker().getDate(),
                                locationTF.getText(),
                                currentEvent.getUtilisateur(),
                                categorieTF.getText(),
                                (int) Float.parseFloat(nbplacetotalTF.getText()),
                                selectedImage,
                                descriptionTF.getText()

                        ), imageEdited
                );
                if (responseCode == 200) {
                    AlertUtils.makeNotification("Event modifié avec succes");
                    showBackAndRefresh();
                } else {
                    Dialog.show("Erreur", "Erreur de modification de event. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            }
        });
    }

    private void showBackAndRefresh() {
        ((AfficherToutEvent) previous).refresh();
        previous.showBack();
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



        if (selectedImage == null) {
            Dialog.show("Avertissement", "Veuillez choisir une image", new Command("Ok"));
            return false;
        }


        return true;
    }
}