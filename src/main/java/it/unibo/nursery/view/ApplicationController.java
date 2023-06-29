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
import it.unibo.nursery.db.SimpleType;
import it.unibo.nursery.db.Supplier;
import it.unibo.nursery.model.Features;
import it.unibo.nursery.utils.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/** The ApplicationController class is responsible for handling the main application scene and related actions. */
public class ApplicationController {

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

    private final Features features;
    private final AppView view;

    /**
     * Constructor for ApplicationController class.
     * @param view
     * @param features
     */
    public ApplicationController(final AppView view, final Features features) {
        this.view = view;
        this.features = features;
    }

    /**
     * Handles the action event when the add-employee button is clicked.
     * Adds a new employee to the database.
     * @param event
     */
    @FXML
    void addEmployeeOnClick(final ActionEvent event) {
        features.addEmployee(
            employeeName.getText(),
            employeeSurname.getText(),
            employeeCF.getText(), 
            Float.parseFloat(employeeSalary.getText()),
            Utils.buildDate(hireDate.getText()).get());
        showEmployees(employeeView, features.viewAllEmployees());
        employeeName.clear();
        employeeSurname.clear();
        employeeCF.clear();
        employeeSalary.clear();
        hireDate.clear();
        this.initialize();
    }

    /**
     * Handles the action event when the add-supplier button is clicked.
     * Adds a new supplier to the database.
     * @param event
     */
    @FXML
    void addSupplierOnClick(final ActionEvent event) {
        features.addSupplier(supplierName.getText());
        showSuppliers(supplierView, features.viewAllSuppliers());
        supplierName.clear();
        this.initialize();
    }

    /**
     * Handles the action event when the add-treatment button is clicked.
     * Adds a new treatment to the database.
     * @param event
     */
    @FXML
    void addTreatmentOnClick(final ActionEvent event) {
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

    /**
     * Handles the action event when the apply-discount button is clicked.
     * Apply a discount on a certain type of plant.
     * @param event
     */
    @FXML
    void applyDiscountOnClick(final ActionEvent event) {
        features.applyDiscount(
                plantTypeID.getText(),
                Float.parseFloat(discount.getText()));
        plantTypeID.clear();
        discount.clear();
        this.initialize();
    }

    /**
     * Handles the action event when the view-best-sellers button is clicked.
     * Show the three best-selling plants.
     * @param event
     */
    @FXML
    void viewBestSellersOnClick(final ActionEvent event) {
        statView.getColumns().clear();
        final TableColumn<PlantSold, Integer> sold = new TableColumn<>("Nome");
        sold.setCellValueFactory(new PropertyValueFactory<>("name"));
        final TableColumn<PlantSold, String> quantity = new TableColumn<>("Unità vendute");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        statView.getColumns().addAll(sold, quantity);
        statView.setItems(features.viewBestSelling(statStartingDate.getText(), statEndDate.getText()));
    }

    /**
     * Handles the action event when the view-care-plan button is clicked.
     * Show the care plan for a certain plant.
     * @param event
     */
    @FXML
    void viewCarePlanOnClick(final ActionEvent event) {
        plantView.getColumns().clear();
        final TableColumn<CarePlan, Integer> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        final TableColumn<CarePlan, String> water = new TableColumn<>("Acqua");
        water.setCellValueFactory(new PropertyValueFactory<>("water"));
        final TableColumn<CarePlan, Integer> light = new TableColumn<>("Livello luce");
        light.setCellValueFactory(new PropertyValueFactory<>("light"));
        final TableColumn<CarePlan, String> fertilizer = new TableColumn<>("Concime");
        fertilizer.setCellValueFactory(new PropertyValueFactory<>("fertilizer"));
        final TableColumn<CarePlan, Integer> minTemp = new TableColumn<>("Temperatura min");
        minTemp.setCellValueFactory(new PropertyValueFactory<>("minTemp"));
        final TableColumn<CarePlan, String> maxTemp = new TableColumn<>("Temperatura max");
        maxTemp.setCellValueFactory(new PropertyValueFactory<>("maxTemp"));
        plantView.getColumns().addAll(id, water, light, fertilizer, minTemp, maxTemp);
        plantView.setItems(features.viewCarePlan(Integer.parseInt(plantToSearchID.getText())));
    }

    /**
     * Handles the action event when the view-employee button is clicked.
     * Shows all employees on shift.
     * @param event
     */
    @FXML
    void viewEmployeesOnShiftOnClick(final ActionEvent event) {
        showEmployees(employeeView, features.viewOnShift(
                shiftDate.getText(), 
                Integer.parseInt(shiftStartingTime.getText()), 
                Integer.parseInt(shiftEndTime.getText())));
    }

    /**
     * Handles the action event when the view-more-treated button is clicked.
     * Shows the plants that have been treated more than indicated in their care plan.
     * @param event
     */
    @FXML
    void viewMoreTreatedOnClick(final ActionEvent event) {
        final Optional<Date> start = Utils.buildDate(statStartingDate.getText());
        final Optional<Date> end = Utils.buildDate(statEndDate.getText());
        final var titles = List.of("Id", "Tipologia", "Giorni di cura", "Annaffiatura teorica",
                "Annaffiatura effettiva", "Concimazione teorica", "Concimazione effettiva");
        if (end.isPresent() && start.isPresent()) {
            final ObservableList<PlantCure> values = features.viewMoreTreated(start.get(), end.get());
            statView.setItems(values);
            statView.getColumns().clear(); // Clear existing columns before adding new ones
            for (int i = 0; i < titles.size(); i++) {
                final TableColumn<PlantCure, String> column = new TableColumn<>(titles.get(i));
                final int columnIndex = i;
                column.setCellValueFactory(cellData -> {
                    final PlantCure plantCure = cellData.getValue();
                    final String cellValue = switch (columnIndex) {
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

    /**
     * Handles the action event when the view-next-shift button is clicked.
     * Shows the next shift for the given employee.
     * @param event
     */
    @FXML
    void viewNextShiftOnClick(final ActionEvent event) {
        employeeView.getColumns().clear();
        final TableColumn<Shift, Integer> id = new TableColumn<>("Codice reparto");
        id.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        final TableColumn<Shift, String> date = new TableColumn<>("Data");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        final TableColumn<Shift, Integer> startingTime = new TableColumn<>("Ora inizio");
        startingTime.setCellValueFactory(new PropertyValueFactory<>("startingTime"));
        final TableColumn<Shift, String> endTime = new TableColumn<>("Ora fine");
        endTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        employeeView.getColumns().addAll(id, date, startingTime, endTime);
        employeeView.setItems(features.viewNextShift(Integer.parseInt(employeeID.getText())));
    }

    /**
     * Handles the action event when the view-products button is clicked.
     * Shows all the products the given supplier sells.
     * @param event
     */
    @FXML
    void viewProductsOnClick(final ActionEvent event) {
        supplierView.getColumns().clear();
        final TableColumn<SimpleType, String> name = new TableColumn<>("Nome prodotto");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        supplierView.getColumns().add(name);
        supplierView.setItems(features.viewProducts(Integer.parseInt(supplierID.getText())));
    }

    /**
     * Handles the action event when the view-supplier button is clicked.
     * Shows all the supplier for the given product.
     * @param event
     */
    @FXML
    void viewSuppliersOnClick(final ActionEvent event) {
        showSuppliers(plantView, features.viewSuppliers(productID.getText()));
    }

    /**
     * Handles the action event when the remove-supplier button is clicked.
     * Removes a supplier from the database.
     * @param event
     */
    @FXML
    void removeSupplierOnClick(final ActionEvent event) {
        features.removeSupplier(Integer.parseInt(supplierID.getText()));
        supplierID.clear();
        this.initialize();
    }

    /**
     * Handles the action event when the open-document-manager button is clicked.
     * Opens a new pop-up document management window.
     * @param event
     */
    @FXML
    void openDocumentManagerOnClick(final ActionEvent event) {
        view.openDocumentScene();
    }

    /** 
     * Method executed when the scene is loaded.
     * Loads all the starting database data on the tabs.
     */
    @FXML
    void initialize() {
        showSuppliers(supplierView, features.viewAllSuppliers());
        showEmployees(employeeView, features.viewAllEmployees());
        showProducts(plantView, features.viewAllProducts());
    }

    private void showSuppliers(final TableView view, final ObservableList<Supplier> data) {
        view.getColumns().clear();
        final TableColumn<Supplier, Integer> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        final TableColumn<Supplier, String> name = new TableColumn<>("Nome");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        view.getColumns().addAll(id, name);
        view.setItems(data);
    }

    private void showEmployees(final TableView view, final ObservableList<Employee> data) {
        view.getColumns().clear();
        final TableColumn<Employee, String> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        final TableColumn<Employee, Integer> name = new TableColumn<>("Nome");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        final TableColumn<Employee, String> surname = new TableColumn<>("Cognome");
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        final TableColumn<Employee, Integer> taxCode = new TableColumn<>("CF");
        taxCode.setCellValueFactory(new PropertyValueFactory<>("taxCode"));
        final TableColumn<Employee, String> salary = new TableColumn<>("Stipendio");
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        final TableColumn<Employee, Integer> employmentDate = new TableColumn<>("Data assunzione");
        employmentDate.setCellValueFactory(new PropertyValueFactory<>("employmentDate"));
        view.getColumns().addAll(id, name, surname, taxCode, salary, employmentDate);
        view.setItems(data);
    }

    private void showProducts(final TableView view, final ObservableList<Product> data) {
        view.getColumns().clear();
        final TableColumn<Product, String> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        final TableColumn<Product, Integer> description = new TableColumn<>("Descrizione");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        final TableColumn<Product, String> price = new TableColumn<>("Prezzo");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        final TableColumn<Product, Integer> type = new TableColumn<>("Tipologia");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        view.getColumns().addAll(id, description, price, type);
        view.setItems(data);
    }
}
