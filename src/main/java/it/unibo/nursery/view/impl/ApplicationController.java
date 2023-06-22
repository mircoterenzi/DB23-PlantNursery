package it.unibo.nursery.view.impl;

import it.unibo.nursery.db.CarePlan;
import it.unibo.nursery.db.Supplier;
import it.unibo.nursery.model.api.Features;
import it.unibo.nursery.model.impl.FeaturesImpl;
import it.unibo.nursery.utils.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML private TableView employeeView;
    @FXML private CheckBox fertilizer;
    @FXML private TextField hireDate;
    @FXML private TextField plantID;
    @FXML private TextField plantTypeID;
    @FXML private TableView plantView;
    @FXML private TextField shiftDate;
    @FXML private TextField shiftEndTime;
    @FXML private TextField shiftStartingTime;
    @FXML private TextField statEndDate;
    @FXML private TextField statStartingDate;
    @FXML private TableView statView;
    @FXML private TextField supplierID;
    @FXML private TextField supplierName;
    @FXML private TableView supplierView;
    
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
        plantView.getColumns().clear();
        TableColumn<CarePlan,Integer> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<CarePlan,String> water = new TableColumn<>("Acqua");
        water.setCellValueFactory(new PropertyValueFactory<>("water"));
        TableColumn<CarePlan,Integer> light = new TableColumn<>("Livello luce");
        light.setCellValueFactory(new PropertyValueFactory<>("light"));
        TableColumn<CarePlan,String> fertilizer = new TableColumn<>("Concime");
        fertilizer.setCellValueFactory(new PropertyValueFactory<>("fertilizer"));
        TableColumn<CarePlan,Integer> minTemp = new TableColumn<>("Temperatura min");
        minTemp.setCellValueFactory(new PropertyValueFactory<>("minTemp"));
        TableColumn<CarePlan,String> maxTemp = new TableColumn<>("Temperatura max");
        maxTemp.setCellValueFactory(new PropertyValueFactory<>("maxTemp"));
        plantView.getColumns().addAll(id,water,light,fertilizer,minTemp,maxTemp);
        plantView.setItems(features.viewCarePlan(Integer.parseInt(plantID.getText())));
    }

    @FXML
    void viewEmployeesOnShiftOnClick(ActionEvent event) {

    }

    @FXML
    void viewMoreTreatedOnClick(ActionEvent event) {
        /* 
        Optional<Date> start = Utils.buildDate(statStartingDate.getText());
        Optional<Date> end = Utils.buildDate(statEndDate.getText());
        if( end.isPresent() && start.isPresent()){
            ResultTable res = features.viewMoreTreated(start.get(),end.get());
            statStartingDate.clear();
            statEndDate.clear();
            statView.setText(res.getTableToString());
        }
        */
    }

    @FXML
    void viewNextShiftOnClick(ActionEvent event) {

    }

    @FXML
    void viewProductsOnClick(ActionEvent event) {

    }

    @FXML
    void viewSuppliersOnClick(ActionEvent event) {
        showSuppliers(plantView, features.viewProducts(Integer.parseInt(plantID.getText())));
    }

    private void showSuppliers(TableView<Supplier> view, ObservableList<Supplier> data) {
        view.getColumns().clear();
        TableColumn<Supplier,Integer> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Supplier,String> name = new TableColumn<>("Nome");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        view.getColumns().addAll(id, name);
        view.setItems(data);
    }
}
