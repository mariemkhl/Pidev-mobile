package com.artisty.gui.back.reservations;


import com.artisty.entities.Reservations;
import com.artisty.services.ReservationsService;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AfficherToutReservations extends Form {

    Form previous;

    public static Reservations currentReservations = null;
    Button addBtn;

    TextField searchTF;
    ArrayList<Component> componentModels;


    public AfficherToutReservations(Form previous) {
        super("Reservationss", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        addBtn = new Button("Ajouter");
        addBtn.setUIID("buttonWhiteCenter");
        this.add(addBtn);


        ArrayList<Reservations> listReservationss = ReservationsService.getInstance().getAll();
        componentModels = new ArrayList<>();

        searchTF = new TextField("", "Chercher reservations par Name");
        searchTF.addDataChangedListener((d, t) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            for (Reservations reservations : listReservationss) {
                if (reservations.getName().toLowerCase().startsWith(searchTF.getText().toLowerCase())) {
                    Component model = makeReservationsModel(reservations);
                    this.add(model);
                    componentModels.add(model);
                }
            }
            this.revalidate();
        });
        this.add(searchTF);


        if (listReservationss.size() > 0) {
            for (Reservations reservations : listReservationss) {
                Component model = makeReservationsModel(reservations);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentReservations = null;
            new AjouterReservations(this).show();
        });

    }

    Label nameLabel, dateLabel;


    private Container makeModelWithoutButtons(Reservations reservations) {
        Container reservationsModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        reservationsModel.setUIID("containerRounded");


        nameLabel = new Label("Name : " + reservations.getName());
        nameLabel.setUIID("labelDefault");

        dateLabel = new Label("Date : " + new SimpleDateFormat("dd-MM-yyyy").format(reservations.getDate()));
        dateLabel.setUIID("labelDefault");


        reservationsModel.addAll(

                nameLabel, dateLabel
        );

        return reservationsModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeReservationsModel(Reservations reservations) {

        Container reservationsModel = makeModelWithoutButtons(reservations);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentReservations = reservations;
            new ModifierReservations(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce reservations ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = ReservationsService.getInstance().delete(reservations.getId());

                if (responseCode == 200) {
                    currentReservations = null;
                    dlg.dispose();
                    reservationsModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du reservations. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        reservationsModel.add(btnsContainer);

        return reservationsModel;
    }

}