package com.mycompany.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;

public class Login extends Form {

    public static Form loginForm;

    public Login(Form hi) {
        super("Connexion", new BoxLayout(BoxLayout.Y_AXIS));
        loginForm = hi;
        addGUIs();
       Toolbar tb= hi.getToolbar();
       
    }

    private void addGUIs() {


        Button frontendBtn = new Button("Front");
       
        frontendBtn.addActionListener(l -> new com.mycompany.forms.AccueilFront(this).show());
        this.add(frontendBtn);


        Button backendBtn = new Button("Back");
        backendBtn.addActionListener(l -> new com.mycompany.forms.AccueilBack(this).show());

        this.add(backendBtn);
    }

}
