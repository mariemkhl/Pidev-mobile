package com.mycompany.forms;


import com.mycompany.entity.Reservations;
import com.mycompany.forms.AccueilFront;
import com.mycompany.services.ReservationsService;
import com.mycompany.utils.AlertUtils;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

public class AjouterReservations extends Form {


    TextField nameTF;
    Label nameLabel;
    PickerComponent dateTF;


    Button manageButton;

    Form previous;

    public AjouterReservations(Form previous) {
        super("Ajouter", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> AccueilFront.accueilForm.showBack());
    }

    private void addGUIs() {


        nameLabel = new Label("Name : ");
        nameLabel.setUIID("labelDefault");
        nameTF = new TextField();
        nameTF.setHint("Tapez le name");


        dateTF = PickerComponent.createDate(null).label("Date");


        manageButton = new Button("Ajouter");
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(

                nameLabel, nameTF,
                dateTF,

                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {

        manageButton.addActionListener(action -> {
            if (controleDeSaisie()) {
                int responseCode = ReservationsService.getInstance().add(
                        new Reservations(


                                nameTF.getText(),
                                dateTF.getPicker().getDate()
                        )
                );
                if (responseCode == 200) {
                    AlertUtils.makeNotification("Reservations ajouté avec succes");
                    showBackAndRefresh();
                } else {
                    Dialog.show("Erreur", "Erreur d'ajout de reservations. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            }
        });
    }

    private void showBackAndRefresh() {
        ((AfficherToutReservations) previous).refresh();
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


        return true;
    }
}