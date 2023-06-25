package it.unibo.nursery.model;

import java.util.Collection;
import java.util.Date;

import it.unibo.nursery.db.Accessory;
import it.unibo.nursery.db.CarePlan;
import it.unibo.nursery.db.Employee;
import it.unibo.nursery.db.Plant;
import it.unibo.nursery.db.PlantCure;
import it.unibo.nursery.db.PlantSold;
import it.unibo.nursery.db.Product;
import it.unibo.nursery.db.Shift;
import it.unibo.nursery.db.SimpleType;
import it.unibo.nursery.db.Supplier;
import javafx.collections.ObservableList;

public interface Features {

    /**
     * @return a list of Suppliers
     */
    ObservableList<Supplier> viewAllSuppliers();

    /**
     * @return a list of Products in stock
     */
    ObservableList<Product> viewAllProducts();

    /**
     * @return a list of Employees
     */
    ObservableList<Employee> viewAllEmployees();

    /**
     * remove a supplier from the database
     */
    void removeSupplier(int supplierId);
    /**
     * Insert an employee in the database.
     */
    void addEmployee(String firstName,String lastName,String CF,float income, Date employmentDate);

    /**
     * Insert a supplier in the database.
     */
    void addSupplier(String name);

    /**
     * Issue a receipt.
     */
    void issueReceipt(Date date, int id_imp, Collection<Integer> prod);

    /**
     * Process an invoice.
     */
    void processInvoice(int id_supplier,Date date,Collection<Plant> plants, Collection<Accessory> accessories);

    /**
     * Apply a discount on a type of plant.
     */
    void applyDiscount(String scientific_name, Float discount);

    /**
     * View all suppliers for a given product.
     */
    ObservableList<Supplier> viewSuppliers(String type);

    /**
     * View all products available from a given supplier.
     */
    ObservableList<SimpleType> viewProducts(int id);

    /**
     * View the care plan for a plant.
     */
    ObservableList<CarePlan> viewCarePlan(int id);

    /**
     * View an employee's next shift.
     */
    ObservableList<Shift> viewNextShift(int id);

    /**
     * View employees on shift.
     */
    ObservableList<Employee> viewOnShift(String date, int startingTime, int endTime);

    /**
     * Add treatments performed on a plant.
     */
    void addTreatment(int plantID, int employeeID, String date, boolean fertilizer);

    /**
     * Display the best-selling plants in a given period.
     */
    ObservableList<PlantSold> viewBestSelling(String from, String to);

     /**
     * View plants that have been treated more than indicated in their care plan 
     * in the range of dates considered or the time in storage during that timeframe.
     */
    ObservableList<PlantCure> viewMoreTreated(Date from, Date to);
}
