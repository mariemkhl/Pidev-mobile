package com.artisty.gui.front;

import com.artisty.gui.Login;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

public class AccueilFront extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;
    Form previous;
    public static Form accueilForm;

    public AccueilFront(Form previous) {
        super(new BorderLayout());
        this.previous = previous;
        accueilForm = this;
        addGUIs();
    }

    private void addGUIs() {
        label = new Label("Espace client");
        label.setUIID("links");
        Button btnDeconnexion = new Button();
        btnDeconnexion.setMaterialIcon(FontImage.MATERIAL_ARROW_FORWARD);
        btnDeconnexion.addActionListener(action -> {
            Login.loginForm.showBack();
        });

        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");
        userContainer.add(BorderLayout.CENTER, label);
        userContainer.add(BorderLayout.EAST, btnDeconnexion);

        Tabs tabs = new Tabs();
        tabs.addTab("Events", FontImage.MATERIAL_MENU, 5, new com.artisty.gui.front.event.AfficherToutEvent(previous));
        tabs.addTab("Reservationss", FontImage.MATERIAL_MENU, 5, new com.artisty.gui.front.reservations.AfficherToutReservations(previous));

        this.add(BorderLayout.CENTER, tabs);
    }

}