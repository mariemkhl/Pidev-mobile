/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.entity.Category;
import com.mycompany.entity.Product;
import com.mycompany.services.ServiceCategory;
import com.mycompany.services.ServiceProduct;
import java.io.IOException;
import java.util.List;







import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entity.Product;
import com.mycompany.services.ServiceProduct;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.codename1.ui.EncodedImage;
import com.codename1.ui.URLImage;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.Util;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;

import com.codename1.util.StringUtil;


/**
 *
 * @author Nour Benkairia
 */
public class AddCategForm {
        Category c = new Category();
    private ServiceCategory serviceCategory;
    private String imageUrl;
    private Container imageContainer;
    private Button imgs;
    private Label statusLabel;
    private Button chooseImageButton;
    Label imageNameLabel = new Label();

    
    public AddCategForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());

    }

    public AddCategForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        serviceCategory = serviceCategory.getInstance();
          initGuiBuilderComponents(resourceObjectInstance);
       
//        getToolbar().setUIID("Container");
//        getToolbar().getTitleComponent().setUIID("AddFormTitle");
//        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "AddFormTitle", 3.5f);
//        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
//        getContentPane().setUIID("AddProduct");
        
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

    






//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField prodnameField = new com.codename1.ui.TextField();
    
    private com.codename1.ui.TextField prodImgField = new com.codename1.ui.TextField();
    
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_4 = new com.codename1.ui.Button();

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
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


    } // </editor-fold> // </editor-fold>
    
    


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
    
    
}
