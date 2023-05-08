/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entity.Product;
import com.mycompany.services.ServiceProduct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;


/**
 *
 * @author Nour Benkairia
 */
public class UpdateProdForm extends com.codename1.ui.Form {
     private ServiceProduct serviceProduct;
    private String imageUrl;
    private Container imageContainer;
    private Button imgs;
    private Label statusLabel;
    private Button chooseImageButton;
    private Product product;

    public UpdateProdForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
//               imgs = new Button("image");        
//    statusLabel = new Label("");
//
//      
//        imgs.addActionListener(e -> chooseImage());
//
//         imageContainer = new Container();
//    imageContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
//
//
//int containerWidth = Display.getInstance().getDisplayWidth();
//int containerHeight = Display.getInstance().getDisplayHeight() / 2;
//imageContainer.setPreferredW(containerWidth);
//imageContainer.setPreferredH(containerHeight);
//
////        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
////            previous.showBack();
////        });
    }
    
        public UpdateProdForm(Product product) {
        this(com.codename1.ui.util.Resources.getGlobalResources());
        this.product = product;
        // initialize other variables and components

        // Set the values of the fields with the product data
        prodnameField.setText(product.getNom());
        descripField.setText(product.getDescription());
        priceField.setText(String.valueOf(product.getPrix()));
        prodImgField.setText(product.getImg());
        CategoryField.setText(product.getCat_p());
        userField.setText(String.valueOf(product.getUser_id()));
        urlField.setText(product.getUrl());
    }

    public UpdateProdForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        serviceProduct = ServiceProduct.getInstance();
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("AddFormTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "AddFormTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("AddProduct");

    }

    private void chooseImage() {
        Display.getInstance().openGallery((e) -> {
            if (e != null && e.getSource() != null) {
                String fileUrl = (String) e.getSource();
                showImage(fileUrl);
            }
        }, Display.GALLERY_IMAGE);

    }

    private void showImage(String fileUrl) {
        try {
            // Read the image file as an input stream
            InputStream is = FileSystemStorage.getInstance().openInputStream(fileUrl);

            // Create an image from the input stream
            Image image = Image.createImage(is);

            // Clear the container and add the image label to it
            imageContainer.removeAll();
            Label imageLabel = new Label(image);
            imageContainer.add(imageLabel);

            // Revalidate the container to update the UI
            imageContainer.revalidate();

            // Set the image URL in the appropriate text field
            prodImgField.setText(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void uploadImage() {
        String imageUrl = prodImgField.getText();
        if (imageUrl == null || imageUrl.isEmpty()) {
            statusLabel.setText("No image selected");
            return;
        }

        try {
            // Create a MultipartRequest to perform the image upload
            MultipartRequest request = new MultipartRequest() {
                @Override
                protected void readResponse(InputStream input) throws IOException {
                    // Handle the server response after image upload
                    String response = Util.readToString(input);
                    statusLabel.setText(response);
                }
            };

            // Set the URL of the server-side upload script
            request.setUrl("http://your-server-url.com/upload.php");

            // Add the image file to the request
            request.addData("image", imageUrl, "image/jpeg");

            // Send the request to the server
            NetworkManager.getInstance().addToQueue(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField prodnameField = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField descripField = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField priceField = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField prodImgField = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField CategoryField = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField userField = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField urlField = new com.codename1.ui.TextField();
//    private com.codename1.ui.TextField dateachatField = new com.codename1.ui.TextField();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_4 = new com.codename1.ui.Button();

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_2.addActionListener(callback);
    }

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
//                    Logger.getLogger(UpdateProdForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public void dataChanged(int type, int index) {
        }
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        List<String> categoryList;

        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("ADD Product");
        setName("Add ProductForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);

        gui_Component_Group_1.setName("Component_Group_1");

        gui_Component_Group_1.addComponent(prodnameField);
        gui_Component_Group_1.setName("prodnameField");
        gui_Component_Group_1.addComponent(descripField);
        gui_Component_Group_1.setName("descripField");
        gui_Component_Group_1.addComponent(priceField);
        gui_Component_Group_1.setName("priceField");
        gui_Component_Group_1.addComponent(prodImgField);
        gui_Component_Group_1.setName("prodImgField");

        imageContainer = new Container();
        imageContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        chooseImageButton = new Button("Choose Image");
        chooseImageButton.addActionListener(e -> chooseImage());

        // Add the button and the image container to the GUI container
        gui_Container_1.addComponent(chooseImageButton);
        gui_Container_1.addComponent(imageContainer);

        gui_Component_Group_1.addComponent(CategoryField);
        gui_Component_Group_1.setName("CategoryField");

//   categoryList = ServiceProduct.getInstance().getCategoriesFromDatabase();
//        
//
//ComboBox<String> categoryComboBox = new ComboBox<>();
//for (String category : categoryList) {
//    categoryComboBox.addItem(category);
//}
//
//
//gui_Component_Group_1.addComponent(categoryComboBox);
//gui_Component_Group_1.setName("CategoryField");
        gui_Component_Group_1.addComponent(userField);
        gui_Component_Group_1.setName("userField");
        gui_Component_Group_1.addComponent(urlField);
        gui_Component_Group_1.setName("urlField");
//        gui_Component_Group_1.addComponent(dateA);

        prodnameField.setText("Product Name");
        descripField.setText("Description");
        priceField.setText("price");
        CategoryField.setText("category");
        userField.setText("user");
        urlField.setText("Url");
//        dateAchatField.setText("dateA");
        prodImgField.setText("Img");
        gui_Container_1.addComponent(gui_Button_2);
        gui_Container_1.addComponent(gui_Button_3);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("UPDATE");
        gui_Button_2.setName("UPDATE");
        gui_Button_1.setName("UPDATE ");

    } // </editor-fold> // </editor-fold>

   public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) throws IOException {
    // Retrieve the product ID from the selected product
    int productId = product.getId_p();

    // Set the updated data to the product object
    product.setNom(prodnameField.getText());
    product.setDescription(descripField.getText());
    product.setPrix(Double.parseDouble(priceField.getText()));
    product.setImg(prodImgField.getText());
    product.setCat_p(CategoryField.getText());
    product.setUser_id(Integer.parseInt(userField.getText()));
    product.setUrl(urlField.getText());

    // Call the updateProduct() method from the ServiceProduct class
    if (serviceProduct.updateProduct(product)) {
        Dialog.show("Success", "Product updated successfully", "OK", null);
        AffichProducts affichProducts = new AffichProducts();
        affichProducts.show();
    } else {
        Dialog.show("Error", "Failed to update product", "OK", null);
    }
}

    
}
