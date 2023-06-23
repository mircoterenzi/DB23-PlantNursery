package it.unibo.nursery.view;

import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import it.unibo.nursery.db.Accessory;
import it.unibo.nursery.db.Plant;
import it.unibo.nursery.model.Features;
import it.unibo.nursery.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DocumentController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TextField dateInvoice;
    @FXML private TextField dateReceipt;
    @FXML private TextField description;
    @FXML private TextField height;
    @FXML private TextField price;
    @FXML private TextField productID;
    @FXML private TextField supplierID;
    @FXML private TextField employeeID;
    @FXML private TextField type;
    @FXML private TextField width;
    @FXML private Button changeProductButton;

    private Features features;
    private boolean isAPlant = true;
    private List<Plant> plants = new LinkedList<>();
    private List<Accessory> accessories = new LinkedList<>();
    private List<Integer> products = new LinkedList<>();

    public DocumentController(Features features) {
        this.features = features;
    }

    @FXML
    void addToInvoiceOnClick(ActionEvent event) {
        try{
            if(isAPlant){
                plants.add(new Plant(description.getText(), type.getText(),
                                    Float.parseFloat(width.getText()),
                                    Float.parseFloat(height.getText()),
                                    Float.parseFloat(price.getText())));
                description.clear();
                type.clear();
                price.clear();
                width.clear();
                height.clear();

            }else{
                accessories.add(new Accessory(description.getText(), type.getText()));
                description.clear();
                type.clear();
                price.clear();
            }
        }catch ( final NumberFormatException e){
                //nothing to do just wait for a valid input
            }
    }

    @FXML
    void addToReceiptOnClick(ActionEvent event) {
        try{
            products.add(Integer.parseInt(productID.getText()));
            productID.clear();
        }catch ( final NumberFormatException e){
            //nothing to do just wait for a valid input
        }
    }

    @FXML
    void changeProductOnClick(ActionEvent event) {
        isAPlant = !isAPlant;
        if (isAPlant) {
            type.setPromptText("Nome scientifico");
            changeProductButton.setText("Voglio inserire un accessorio");
            price.setVisible(true);
            height.setVisible(true);
            width.setVisible(true);
        } else {
            type.setPromptText("Tipo accessorio");
            changeProductButton.setText("Voglio inserire una pianta");
            price.setVisible(false);
            height.setVisible(false);
            width.setVisible(false);
        }
    }

    @FXML
    void completeInvoiceOnClick(ActionEvent event) {
        Optional<Date> data = Utils.buildDate(dateInvoice.getText());
        
        if (data.isPresent()) {
            try{
                int supplier = Integer.parseInt(supplierID.getText());
                features.processInvoice(supplier, data.get(), plants, accessories);
                plants.clear();
                accessories.clear();
                supplierID.clear();
                dateInvoice.clear();
            } catch ( final NumberFormatException e){
                //nothing to do just wait for a valid input
            }
        }
    }

    @FXML
    void completeReceiptOnClick(ActionEvent event) {
        Optional<Date> data = Utils.buildDate(dateReceipt.getText());
        
        if (data.isPresent()) {
            try{
                int employee = Integer.parseInt(employeeID.getText());
                features.issueReceipt(data.get(), employee, products);
                products.clear();
                dateReceipt.clear();

            } catch ( final NumberFormatException e){
                //nothing to do just wait for a valid input
            }
        }
    }

}
