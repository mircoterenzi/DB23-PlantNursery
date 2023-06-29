package it.unibo.nursery.view;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.nursery.db.Accessory;
import it.unibo.nursery.db.Plant;
import it.unibo.nursery.model.Features;
import it.unibo.nursery.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/** The DocumentController class is responsible for handling the title document-managment scene and related actions. */
public class DocumentController {

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

    private final Features features;
    private final List<Plant> plants = new LinkedList<>();
    private final List<Accessory> accessories = new LinkedList<>();
    private final List<Integer> products = new LinkedList<>();
    private boolean isAPlant = true;

    /**
     * Constructor for DocumentController class.
     * @param features
     */
    public DocumentController(final Features features) {
        this.features = features;
    }

    /**
     * Handles the action event when the add-to-invoice button is clicked.
     * Adds a new product to the current invoice.
     * @param event
     */
    @FXML
    void addToInvoiceOnClick(final ActionEvent event) {
        try {
            if (isAPlant) {
                plants.add(new Plant(description.getText(), type.getText(),
                        Float.parseFloat(width.getText()),
                        Float.parseFloat(height.getText()),
                        Float.parseFloat(price.getText())));
                description.clear();
                type.clear();
                price.clear();
                width.clear();
                height.clear();

            } else {
                accessories.add(new Accessory(description.getText(), type.getText()));
                description.clear();
                type.clear();
                price.clear();
            }
        } catch (final NumberFormatException e) {
                //nothing to do just wait for a valid input
            }
    }

    /**
     * Handles the action event when the add-to-receipt button is clicked.
     * Adds a new product to the current receipt.
     * @param event
     */
    @FXML
    void addToReceiptOnClick(final ActionEvent event) {
        try {
            products.add(Integer.parseInt(productID.getText()));
            productID.clear();
        } catch (final NumberFormatException e) {
            //nothing to do just wait for a valid input
        }
    }

    /**
     * Handles the action event when the change-product button is clicked.
     * Switches between add-plant and add-accessory view.
     * @param event
     */
    @FXML
    void changeProductOnClick(final ActionEvent event) {
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

    /**
     * Handles the action event when the complete-invoice button is clicked.
     * End current invoice.
     * @param event
     */
    @FXML
    void completeInvoiceOnClick(final ActionEvent event) {
        final Optional<Date> data = Utils.buildDate(dateInvoice.getText());
        if (data.isPresent()) {
            try {
                final int supplier = Integer.parseInt(supplierID.getText());
                if (supplier != 1) {
                    features.processInvoice(supplier, data.get(), plants, accessories);
                    plants.clear();
                    accessories.clear();
                    supplierID.clear();
                    dateInvoice.clear();
                } else {
                    supplierID.setText("invalid id");
                }
            } catch (final NumberFormatException e) {
                //nothing to do just wait for a valid input
            }
        }
    }

    /**
     * Handles the action event when the complete-receipt button is clicked.
     * End current receipt.
     * @param event
     */
    @FXML
    void completeReceiptOnClick(final ActionEvent event) {
        final Optional<Date> data = Utils.buildDate(dateReceipt.getText());
        if (data.isPresent()) {
            try {
                final int employee = Integer.parseInt(employeeID.getText());
                features.issueReceipt(data.get(), employee, products);
                products.clear();
                dateReceipt.clear();
            } catch (final NumberFormatException e) {
                //nothing to do just wait for a valid input
            }
        }
    }
}
