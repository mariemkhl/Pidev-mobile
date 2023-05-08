/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;
    
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.mycompany.entity.Product;
import com.mycompany.services.ServiceProduct;
import java.io.IOException;
import java.util.ArrayList;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Dialog;
import com.codename1.ui.layouts.FlowLayout;



//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author Nour Benkairia
 */
public class AffichProducts extends Form{
    


/**
 *
 * @author khaledguedria
 */

    //var
    ServiceProduct sp = ServiceProduct.getInstance();
     Product product  = new Product();

    public AffichProducts() throws IOException {

//        //custom
//        this.setLayout(BoxLayout.y());
//        this.setTitle("All Products");
//        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
////            new HomeForm().showBack();
//        });
//
//        //widgets
//        SpanLabel sl = new SpanLabel();
//        sl.setText(sp.affichageProduct2().toString());
//
//        //end
//        this.add(sl);



 


//        //custom
//        this.setLayout(BoxLayout.y());
//        this.setTitle("All Products");
//        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
////            new HomeForm().showBack();
//        });
//
//        //widgets
//        SpanLabel sl = new SpanLabel();
//        sl.setText(sp.affichageProduct2().toString());
//
//        //end
//        this.add(sl);


// Custom phone design layout
this.setLayout(new BorderLayout());
this.setTitle("All Products");
this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
    // Handle back button action
});

//        //custom
//        this.setLayout(BoxLayout.y());
//        this.setTitle("All Products");
//        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
////            new HomeForm().showBack();
//        });
//
//        //widgets
//        SpanLabel sl = new SpanLabel();
//        sl.setText(sp.affichageProduct2().toString());
//
//        //end
//        this.add(sl);


// Custom phone design layout
this.setLayout(new BorderLayout());
this.setTitle("All Products");
this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
    // Handle back button action
});

// Create a Container to hold the product buttons
Container productContainer = new Container();
productContainer.setLayout(BoxLayout.y());

// Get the list of products
ServiceProduct sp = ServiceProduct.getInstance();
ArrayList<Product> products = sp.affichageProduct2();

for (Product product : products) {
    // Create a MultiButton for each product
    MultiButton multiButton = new MultiButton();
    multiButton.setTextLine1(product.getNom());
    multiButton.setTextLine2(product.getDescription());
    multiButton.setTextLine3("Price: " + product.getPrix());

    // Load the image from the file system
    Image image = Image.createImage("file:/C:/Users/Nour Benkairia/Documents/espritSem1_2022/Projet_Symfony/Artisty6/public/assets/img/products/" + product.getImg());
    Label imageLabel = new Label(image.scaled(150, 170));
    multiButton.setIcon(imageLabel.getIcon());

    // Add the update button
//    Button updateButton = new Button("Update");
//    updateButton.addActionListener(e -> {
//        // Handle update button action
//        UpdateProdForm updateForm = new UpdateProdForm(product);
//        updateForm.show();
//    });
//    productContainer.add(updateButton);

Button updateButton = new Button("Update");
updateButton.addActionListener(e -> {
    // Handle update button action
    UpdateProdForm updateForm = new UpdateProdForm(product);
    updateForm.show();
});
updateButton.setUIID("UpdateButton"); // Set a custom UIID for styling purposes
Container updateButtonContainer = FlowLayout.encloseCenter(updateButton);
productContainer.add(updateButtonContainer);


    // Add the delete button
   Button deleteButton = new Button("Delete");
deleteButton.addActionListener(e -> {
    // Handle delete button action
    boolean deleted = sp.deleteProduct(product.getId_p());
    if (deleted) {
        productContainer.removeComponent(multiButton);
        productContainer.removeComponent(updateButton);
        productContainer.removeComponent(deleteButton);
        productContainer.revalidate();
        Dialog.show("Success", "Product deleted successfully", "OK", null);
    } else {
        Dialog.show("Error", "Failed to delete product", "OK", null);
    }
});
productContainer.add(FlowLayout.encloseRightMiddle(deleteButton));

    // Add the MultiButton to the productContainer
    productContainer.add(multiButton);
}

// Add the productContainer to the form
this.add(BorderLayout.CENTER, productContainer);
















        
        
        
        
        
        
        
        
        

    }

}
    
    
    
    
    
    
    
    
    
    
    
    
    

