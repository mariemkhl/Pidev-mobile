/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.entity.Category;
import com.mycompany.entity.Product;
import com.mycompany.services.ServiceCategory;
import java.io.IOException;
import java.util.List;

/**
 * A simple details form
 *
 * @author Shai Almog
 */
public class DetailsForm extends com.codename1.ui.Form {
    
       Category c = new Category();
    private ServiceCategory serviceCategory;
    private String imageUrl;
    private Container imageContainer;
    private Button imgs;
    private Label statusLabel;
    private Button chooseImageButton;
    Label imageNameLabel = new Label();

    public DetailsForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public DetailsForm(com.codename1.ui.util.Resources resourceObjectInstance) {
         serviceCategory = serviceCategory.getInstance();
        setToolbar(new Toolbar(true));
        initGuiBuilderComponents(resourceObjectInstance);
        Form last =  Display.getInstance().getCurrent();
        getToolbar().setBackCommand("", e -> last.show());
    }
    
    
    
    private void showImage(String fileUrl) {
    try {
        // Create an EncodedImage from the file URL
        EncodedImage encodedImage = EncodedImage.create(fileUrl);

        // Create a URLImage with the EncodedImage
        URLImage urlImage = URLImage.createToStorage(encodedImage, fileUrl, fileUrl);

        // Create an image label with the URLImage
        Label imageLabel = new Label(urlImage);

        // Create a container to hold the image label
        Container imageBoxContainer = new Container();
        imageBoxContainer.setLayout(new BorderLayout());
        imageBoxContainer.getStyle().setBorder(Border.createLineBorder(1, 0xAAAAAA));

        // Add the image label to the container
        imageBoxContainer.add(BorderLayout.CENTER, imageLabel);

        // Clear the previous image container and add the new image container to the form
        imageContainer.removeAll();
        imageContainer.add(imageBoxContainer);

        // Revalidate the container to update the UI
        imageContainer.revalidate();

        // Set the image URL in the appropriate text field
        prodImgField.setText(fileUrl);

        // Set the image name in the label
        imageNameLabel.setText(fileUrl);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    
     private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField prodnameField = new com.codename1.ui.TextField();
    
    private com.codename1.ui.TextField prodImgField = new com.codename1.ui.TextField();
    
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_4 = new com.codename1.ui.Button();
    
    
    
      private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_2.addActionListener(callback);
    }

//   

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {

        private com.codename1.ui.Component cmp;

        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if (sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if (sourceComponent == gui_Button_2) {
                try {
                    onButton_2ActionEvent(ev);
                } catch (IOException ex) {
//                    Logger.getLogger(AddProdForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    

//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.FlowLayout());
        setTitle("DetailsForm");
        setName("DetailsForm");
        
        
              List<String> categoryList;

        guiBuilderBindComponentListeners();
//        setLayout(new com.codename1.ui.layouts.BorderLayout());
//        setTitle("ADD Product");
//        setName("Add ProductForm");
//        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);

        gui_Component_Group_1.setName("Component_Group_1");

        gui_Component_Group_1.addComponent(prodnameField);
        gui_Component_Group_1.setName("prodnameField");
    
        gui_Component_Group_1.addComponent(prodImgField);
        gui_Component_Group_1.setName("prodImgField");

        imageContainer = new Container();
        imageContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

//        chooseImageButton = new Button("Choose Image");
//        chooseImageButton.addActionListener(e -> chooseImage());
        
        
                
Button selectImageButton = new Button("Select Image");
selectImageButton.addActionListener(e -> {
    // Display a file picker dialog for image selection
    Display.getInstance().openGallery((ActionEvent ev) -> {
        if (ev != null && ev.getSource() != null) {
            String imagePath = (String) ev.getSource();
            System.out.println(imagePath);
            System.out.println(prodImgField.getText());
                  
            // Get the index of the last slash in the path
            int lastIndex = imagePath.lastIndexOf("/");
            
            // Extract the image name from the path
            String imageName = imagePath.substring(lastIndex + 1);
            System.out.println(imageName);
            
            // Display the image name in a label or text field
            // Assuming you have a label named 'imageNameLabel'
            prodImgField.setText(imageName);

            // Do whatever you need to do with the selected image path
            // e.g., load the image, save the path, etc.
        }
    }, Display.GALLERY_IMAGE);
});

// Add the button to your form
gui_Container_1.addComponent(selectImageButton);
        
        

        

        
        

        // Add the button and the image container to the GUI container
//        gui_Container_1.addComponent(chooseImageButton);
        gui_Container_1.addComponent(imageContainer);

      


        prodnameField.setText("Category Name");
       
        prodImgField.setText("Img");
        gui_Container_1.addComponent(gui_Button_2);
        gui_Container_1.addComponent(gui_Button_3);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("ADD");
        gui_Button_2.setName("ADD");
        gui_Button_1.setName("ADD ");
        
        

        Button viewProductsButton = new Button("View Products");
viewProductsButton.addActionListener(e -> {
            try {
                AffichProducts affichProducts = new AffichProducts();
                affichProducts.show();
            } catch (IOException ex) {
//                Logger.getLogger(AddProdForm.class.getName()).log(Level.SEVERE, null, ex);
            }
});

// Add the button to your form
gui_Container_1.addComponent(viewProductsButton);


        
        
        
        
     
    }


    
     public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) throws IOException {
         // Create a new Product object
    Product product = new Product();
    product.setNom(prodnameField.getText());
  

    // Get the image name from the prodImgField
    String imageName = prodImgField.getText();

    // Set the image name in the product object
    product.setImg(imageName);

   

    // Call the addProduct() method from the ServiceProduct class
    if (serviceCategory.addCategory(c)) {
        Dialog.show("Success", "Catgeory added successfully", "OK", null);

//        AffichProducts affichProducts = new AffichProducts();
//        affichProducts.show();
    } else {
        Dialog.show("Error", "Failed to add Category", "OK", null);
    }
    }
    

// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
