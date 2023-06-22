package it.unibo.nursery.view.impl;

import java.util.Date;
import java.util.Optional;

import it.unibo.nursery.model.api.Features;
import it.unibo.nursery.model.api.ResultTable;
import it.unibo.nursery.model.impl.FeaturesImpl;
import it.unibo.nursery.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ApplicationController {

    @FXML private Button add_employee;
    @FXML private Button add_fornitore;
    @FXML private TextField dateTreatment;
    @FXML private TextField discount;
    @FXML private TextField employeeCF;
    @FXML private TextField employeeID;
    @FXML private TextField employeeName;
    @FXML private TextField employeeSalary;
    @FXML private TextField employeeSurname;
    @FXML private TextArea employeeView;
    @FXML private CheckBox fertilizer;
    @FXML private TextField hireDate;
    @FXML private TextField plantID;
    @FXML private TextField plantTypeID;
    @FXML private TextArea plantView;
    @FXML private TextField shiftDate;
    @FXML private TextField shiftEndTime;
    @FXML private TextField shiftStartingTime;
    @FXML private TextField statEndDate;
    @FXML private TextField statStartingDate;
    @FXML private TextArea statView;
    @FXML private TextField supplierID;
    @FXML private TextField supplierName;
    @FXML private TextArea supplierView;
    
    private Features features;

    public ApplicationController() {
        features = new FeaturesImpl();
    }

    @FXML
    void addEmployeeOnClick(ActionEvent event) {
        features.addEmployee(
            employeeName.getText(),
            employeeSurname.getText(),
            employeeCF.getText(), 
            Float.parseFloat(employeeSalary.getText()),
            Utils.buildDate(hireDate.getText()).orElse(null) ); //TODO in else metti oggi
        employeeName.clear();
        employeeSurname.clear();
        employeeCF.clear();
        employeeSalary.clear();
        hireDate.clear();
    }

    @FXML
    void addSupplierOnClick(ActionEvent event) {
        features.addSupplier(supplierName.getText());
        supplierName.clear();
    }

    @FXML
    void addTreatmentOnClick(ActionEvent event) {

    }

    @FXML
    void applyDiscountOnClick(ActionEvent event) {

    }

    @FXML
    void viewBestSellersOnClick(ActionEvent event) {

    }

    @FXML
    void viewCarePlanOnClick(ActionEvent event) {

    }

    @FXML
    void viewEmployeesOnShiftOnClick(ActionEvent event) {

    }

    @FXML
    void viewMoreTreatedOnClick(ActionEvent event) {
        Optional<Date> start = Utils.buildDate(statStartingDate.getText());
        Optional<Date> end = Utils.buildDate(statEndDate.getText());
        if( end.isPresent() && start.isPresent()){
            ResultTable res = features.viewMoreTreated(start.get(),end.get());
            statStartingDate.clear();
            statEndDate.clear();
            statView.setText(res.getTableToString());
        }
    }

    @FXML
    void viewNextShiftOnClick(ActionEvent event) {

    }

    @FXML
    void viewProductsOnClick(ActionEvent event) {
        ResultTable res = features.viewProducts(Integer.parseInt(supplierID.getText()));
        supplierView.setText(res.getTableToString());

    }

    @FXML
    void viewSuppliersOnClick(ActionEvent event) {

    }

}
