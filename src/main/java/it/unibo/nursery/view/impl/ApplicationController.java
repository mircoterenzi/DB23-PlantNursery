package it.unibo.nursery.view.impl;

import java.util.Date;
import java.util.List;

import javax.xml.catalog.CatalogFeatures.Feature;

import it.unibo.nursery.logics.api.Features;
import it.unibo.nursery.logics.impl.FeaturesImpl;
import it.unibo.nursery.utils.Utils;
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
    private TextField cognome_employee_add;
    @FXML
    private TextField CF_employee_add;
    @FXML
    private TextField income_employee_add;
    @FXML
    private TextField hire_date;
    @FXML
    private Button add_employee;

    public ApplicationController(FxAppView view) {
        this.view = view;
    }

    @FXML
    void addFornitoreOnClick(ActionEvent event) {
        logics.addSupplier(nome_fornitore_add.getText());
        nome_fornitore_add.clear();
    }
    
    @FXML 
    void addEmployeeOnClick(ActionEvent event){
        logics.addEmployee(
            nome_employee_add.getText(),
            cognome_employee_add.getText(),
            CF_employee_add.getText(), 
            Float.parseFloat(income_employee_add.getText()),
            Utils.buildDate(hire_date.getText()).orElse(null) ); //TODO in else metti oggi
        nome_employee_add.clear();
        cognome_employee_add.clear();
        CF_employee_add.clear();
        income_employee_add.clear();
        hire_date.clear();
    }
    @FXML
    void processInvoice(ActionEvent event){

    }
    //TODO: aggiungere comandi addFornitoreOnClick

}


