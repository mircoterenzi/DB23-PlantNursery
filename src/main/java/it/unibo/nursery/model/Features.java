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

/** Interface that contains all features required by the application. */
public interface Features {

    /**
     * @return a list of suppliers
     */
    ObservableList<Supplier> viewAllSuppliers();

    /**
     * @return a list of products in stock
     */
    ObservableList<Product> viewAllProducts();

    /**
     * @return a list of employees
     */
    ObservableList<Employee> viewAllEmployees();

    /**
     * Remove a supplier from the database.
     * @param supplierId The id of the supplier to remove
     */
    void removeSupplier(int supplierId);

    /**
     * Insert an employee in the database.
     * @param firstName 
     * @param lastName
     * @param cf Tax code 
     * @param income Annual income
     * @param employmentDate
     */
    void addEmployee(String firstName, String lastName, String cf, float income, Date employmentDate);

    /**
     * Insert a supplier in the database.
     * @param name
     */
    void addSupplier(String name);

    /**
     * Issue a receipt.
     * @param date
     * @param idEmployee The id of the employee that sold the items
     * @param products All the products that has been sold
     */
    void issueReceipt(Date date, int idEmployee, Collection<Integer> products);

    /**
     * Process an invoice.
     * @param idSupplier The id of the supplier the products were purchased from
     * @param date
     * @param plants All the plants purchased
     * @param accessories All the accessories purchased
     */
    void processInvoice(int idSupplier, Date date, Collection<Plant> plants, Collection<Accessory> accessories);

    /**
     * Apply a discount on a type of plant.
     * @param scientificName
     * @param discount Amount of discount
     */
    void applyDiscount(String scientificName, Float discount);

    /**
     * View all suppliers for a given product.
     * @param productName
     * @return All the suppliers that sells the given product
     */
    ObservableList<Supplier> viewSuppliers(String productName);

    /**
     * View all products available from a given supplier.
     * @param id The supplier id
     * @return All the products the given supplier sells
     */
    ObservableList<SimpleType> viewProducts(int id);

    /**
     * View the care plan for a plant.
     * @param id The plant id
     * @return The care plan for that type of plant
     */
    ObservableList<CarePlan> viewCarePlan(int id);

    /**
     * View an employee's next shift.
     * @param id The employee id
     * @return The next shift for that employee
     */
    ObservableList<Shift> viewNextShift(int id);

    /**
     * View employees on shift.
     * @param date
     * @param startingTime
     * @param endTime
     * @return All the employees working in the given period of time
     */
    ObservableList<Employee> viewOnShift(String date, int startingTime, int endTime);

    /**
     * Add treatments performed on a plant.
     * @param idPlant
     * @param idEmployee
     * @param date
     * @param fertilizer True if the plant has been fertilised
     */
    void addTreatment(int idPlant, int idEmployee, String date, boolean fertilizer);

    /**
     * Display the best-selling plants in a given period.
     * @param from 
     * @param to End date
     * @return The top three best-selling plant
     */
    ObservableList<PlantSold> viewBestSelling(String from, String to);

     /**
      * View plants that have been treated more than indicated in their care plan 
      * in the range of dates considered or the time in storage during that timeframe.
      * @param from Starting date
      * @param to End date
      * @return The plat that has been treated more then expected
      */
    ObservableList<PlantCure> viewMoreTreated(Date from, Date to);
}
