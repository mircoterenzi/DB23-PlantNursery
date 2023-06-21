package it.unibo.nursery.view.impl;

import javax.xml.catalog.CatalogFeatures.Feature;

import it.unibo.nursery.logics.api.Features;
import it.unibo.nursery.logics.impl.FeaturesImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ApplicationController {

    private FxAppView view;

    private Features logics = new FeaturesImpl();

    @FXML
    private TextField nome_fornitore_add;
    @FXML
    private Button add_fornitore;
    @FXML
    private TextField nome_employee_add;
    @FXML
    private TextField conome_employee_add;
    @FXML
    private TextField CF_employee_add;
    @FXML
    private TextField income_employee_add;
    @FXML
    private TextField hire_date;

    public ApplicationController(FxAppView view) {
        this.view = view;
    }

    @FXML
    void addFornitoreOnClick(ActionEvent event) {
        logics.addSupplier(nome_fornitore_add.getText());
        nome_fornitore_add.clear();
    }
    
    //TODO: aggiungere comandi addFornitoreOnClick

}


