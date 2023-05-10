package com.mycompany.forms;


import com.mycompany.entity.Reservations;
import com.mycompany.services.ReservationsService;
import com.mycompany.utils.AlertUtils;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

public class ModifierReservations11 extends Form {


    Reservations currentReservations;

    TextField nameTF;
    Label nameLabel;
    PickerComponent dateTF;


    Button manageButton;

    Form previous;

    public ModifierReservations11(Form previous) {
        super("Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentReservations = AfficherToutReservations.currentReservations;

        addGUIs();
        addActions();


        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {


        nameLabel = new Label("Name : ");
        nameLabel.setUIID("labelDefault");
        nameTF = new TextField();
        nameTF.setHint("Tapez le name");


        dateTF = PickerComponent.createDate(null).label("Date");


        nameTF.setText(currentReservations.getName());
        dateTF.getPicker().setDate(currentReservations.getDate());


        manageButton = new Button("Modifier");
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
                int responseCode = ReservationsService.getInstance().edit(
                        new Reservations(
                                currentReservations.getId(),


                                nameTF.getText(),
                                dateTF.getPicker().getDate()

                        )
                );
                if (responseCode == 200) {
                    AlertUtils.makeNotification("Reservations modifié avec succes");
                    showBackAndRefresh();
                } else {
                    Dialog.show("Erreur", "Erreur de modification de reservations. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            }
        });
    }

    private void showBackAndRefresh() {
        ((AfficherToutReservations) previous).refresh();
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


        return true;
    }
}