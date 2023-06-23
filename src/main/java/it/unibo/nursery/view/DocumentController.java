package it.unibo.nursery.view;

import java.net.URL;
import java.util.ResourceBundle;

import it.unibo.nursery.model.Features;
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
    @FXML private TextField type;
    @FXML private TextField width;
    @FXML private Button changeProductButton;

    private Features features;
    private boolean isAPlant = true;

    public DocumentController(Features features) {
        this.features = features;
    }

    @FXML
    void addToInvoiceOnClick(ActionEvent event) {

    }

    @FXML
    void addToReceiptOnClick(ActionEvent event) {

    }

    @FXML
    void changeProductOnClick(ActionEvent event) {
        isAPlant = !isAPlant;
        if (isAPlant) {
            type.setPromptText("Nome scientifico");
            changeProductButton.setText("Voglio inserire un accessorio");
            height.setVisible(true);
            width.setVisible(true);
        } else {
            type.setPromptText("Tipo accessorio");
            changeProductButton.setText("Voglio inserire una pianta");
            height.setVisible(false);
            width.setVisible(false);
        }
    }

    @FXML
    void completeInvoiceOnClick(ActionEvent event) {

    }

    @FXML
    void completeReceiptOnClick(ActionEvent event) {

    }

}
