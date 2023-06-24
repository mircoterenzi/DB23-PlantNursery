package it.unibo.nursery.view;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import it.unibo.nursery.db.CarePlan;
import it.unibo.nursery.db.Employee;
import it.unibo.nursery.db.PlantCure;
import it.unibo.nursery.db.PlantSold;
import it.unibo.nursery.db.Product;
import it.unibo.nursery.db.Shift;
import it.unibo.nursery.db.Supplier;
import it.unibo.nursery.model.Features;
import it.unibo.nursery.utils.Utils;
import javafx.beans.property.SimpleStringProperty;
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
    @FXML private TextField employeeTreatmentID;
    @FXML private TableView employeeView;
    @FXML private CheckBox fertilizer;
    @FXML private TextField hireDate;
    @FXML private TextField productID;
    @FXML private TextField plantTypeID;
    @FXML private TextField plantTreatmentID;
    @FXML private TextField plantToSearchID;
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
    private AppView view;

    public ApplicationController(AppView view, Features features) {
        this.view = view;
        this.features = features;
    }

    @FXML
    void addEmployeeOnClick(ActionEvent event) {
        features.addEmployee(
            employeeName.getText(),
            employeeSurname.getText(),
            employeeCF.getText(), 
            Float.parseFloat(employeeSalary.getText()),
            Utils.buildDate(hireDate.getText()).get() );
        showEmployees(employeeView, features.viewAllEmployees());
        employeeName.clear();
        employeeSurname.clear();
        employeeCF.clear();
        employeeSalary.clear();
        hireDate.clear();
        this.initialize();
    }

    @FXML
    void addSupplierOnClick(ActionEvent event) {
        features.addSupplier(supplierName.getText());
        showSuppliers(supplierView, features.viewAllSuppliers());
        supplierName.clear();
        this.initialize();
    }

    @FXML
    void addTreatmentOnClick(ActionEvent event) {
        features.addTreatment(
                Integer.parseInt(plantTreatmentID.getText()),
                Integer.parseInt(employeeTreatmentID.getText()),
                dateTreatment.getText(),
                fertilizer.isSelected());
        plantTreatmentID.clear();
        employeeTreatmentID.clear();
        dateTreatment.clear();
        fertilizer.setSelected(false);
    }

    @FXML
    void applyDiscountOnClick(ActionEvent event) {
        features.applyDiscount(
                plantTypeID.getText(),
                Float.parseFloat(discount.getText()));
        plantTypeID.clear();
        discount.clear();
        this.initialize();
    }

    @FXML
    void viewBestSellersOnClick(ActionEvent event) {
        statView.getColumns().clear();
        TableColumn<PlantSold,Integer> sold = new TableColumn<>("Nome");
        sold.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<PlantSold,String> quantity = new TableColumn<>("Unità vendute");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        statView.getColumns().addAll(sold,quantity);
        statView.setItems(features.viewBestSelling(statStartingDate.getText(), statEndDate.getText()));
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
        plantView.setItems(features.viewCarePlan(Integer.parseInt(plantToSearchID.getText())));
    }

    @FXML
    void viewEmployeesOnShiftOnClick(ActionEvent event) {
        showEmployees(employeeView, features.viewOnShift(
                shiftDate.getText(), 
                Integer.parseInt(shiftStartingTime.getText()), 
                Integer.parseInt(shiftEndTime.getText())));
    }

    @FXML
    void viewMoreTreatedOnClick(ActionEvent event) {
        Optional<Date> start = Utils.buildDate(statStartingDate.getText());
        Optional<Date> end = Utils.buildDate(statEndDate.getText());
        var titles = List.of("Id", "Tipologia", "Giorni di cura", "Annaffiatura teorica",
                "Annaffiatura effettiva", "Concimazione teorica", "Concimazione effettiva");
        if (end.isPresent() && start.isPresent()) {
            ObservableList<PlantCure> values = features.viewMoreTreated(start.get(), end.get());
            statView.setItems(values);
            statView.getColumns().clear(); // Clear existing columns before adding new ones

            for (int i = 0; i < titles.size(); i++) {
                TableColumn<PlantCure, String> column = new TableColumn<>(titles.get(i));
                int columnIndex = i;

                column.setCellValueFactory(cellData -> {
                    PlantCure plantCure = cellData.getValue();
                    String cellValue = switch (columnIndex) {
                        case 0 -> String.valueOf(plantCure.getId_prodotto());
                        case 1 -> plantCure.getScientificName();
                        case 2 -> String.valueOf(plantCure.getDays_in_care());
                        case 3 -> String.valueOf(plantCure.getExpected_water());
                        case 4 -> String.valueOf(plantCure.getGiven_water());
                        case 5 -> String.valueOf(plantCure.getExpected_fertilizer());
                        case 6 -> String.valueOf(plantCure.getGiven_fertilizer());
                        default -> null;
                    };
                    return new SimpleStringProperty(cellValue);
                });

                statView.getColumns().add(column);
            }
        }
    }

    @FXML
    void viewNextShiftOnClick(ActionEvent event) {
        employeeView.getColumns().clear();
        TableColumn<Shift,Integer> id = new TableColumn<>("Codice reparto");
        id.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        TableColumn<Shift,String> date = new TableColumn<>("Data");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Shift,Integer> startingTime = new TableColumn<>("Ora inizio");
        startingTime.setCellValueFactory(new PropertyValueFactory<>("startingTime"));
        TableColumn<Shift,String> endTime = new TableColumn<>("Ora fine");
        endTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        employeeView.getColumns().addAll(id,date,startingTime,endTime);
        employeeView.setItems(features.viewNextShift(Integer.parseInt(employeeID.getText())));
    }

    @FXML
    void viewProductsOnClick(ActionEvent event) {
        TableColumn<String, String> name = new TableColumn<>("Nome");
        employeeView.getColumns().addAll(name);
        employeeView.setItems(features.viewProducts(Integer.parseInt(supplierID.getText())));
    }

    @FXML
    void viewSuppliersOnClick(ActionEvent event) {
        showSuppliers(plantView, features.viewSuppliers(productID.getText()));
    }

    @FXML
    void removeSupplierOnClick(ActionEvent event) {
        features.removeSupplier(Integer.parseInt(supplierID.getText()));
        supplierID.clear();
        this.initialize();
    }

    @FXML
    void openDocumentManagerOnClick(ActionEvent event) {
        view.openDocumentScene();
    }

    @FXML
    void initialize() {
        showSuppliers(supplierView, features.viewAllSuppliers());
        showEmployees(employeeView, features.viewAllEmployees());
        showProducts(plantView, features.viewAllProducts());
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

    private void showEmployees(TableView<Employee> view, ObservableList<Employee> data) {
        view.getColumns().clear();
        TableColumn<Employee,String> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Employee,Integer> name = new TableColumn<>("Nome");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Employee,String> surname = new TableColumn<>("Cognome");
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        TableColumn<Employee,Integer> taxCode = new TableColumn<>("CF");
        taxCode.setCellValueFactory(new PropertyValueFactory<>("taxCode"));
        TableColumn<Employee,String> salary = new TableColumn<>("Stipendio");
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        TableColumn<Employee,Integer> employmentDate = new TableColumn<>("Data assunzione");
        employmentDate.setCellValueFactory(new PropertyValueFactory<>("employmentDate"));
        view.getColumns().addAll(id, name, surname, taxCode, salary, employmentDate);
        view.setItems(data);
    }

    private void showProducts(TableView<Product> view, ObservableList<Product> data) {
        view.getColumns().clear();
        TableColumn<Product,String> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Product,Integer> description = new TableColumn<>("Descrizione");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<Product,String> price = new TableColumn<>("Prezzo");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Product,Integer> type = new TableColumn<>("Tipologia");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        view.getColumns().addAll(id, description, price, type);
        view.setItems(data);
    }
}
