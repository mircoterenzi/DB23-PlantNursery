package it.unibo.nursery.model.api;

import java.util.Collection;
import java.util.Date;

import it.unibo.nursery.db.Accessory;
import it.unibo.nursery.db.Plant;

public interface Features {

    /**
     * Insert an employee in the database.
     */
    public void addEmployee(String FirstName,String LastName,String CF,float income, Date employment_date);

    /**
     * Insert a supplier in the database.
     */
    public void addSupplier(String name);

    /**
     * Issue a receipt.
     */
    public void issueReceipt(Date date, int id_imp, Collection<Integer> prod);

    /**
     * Process an invoice.
     */
    public void processInvoice(int id_supplier,Date date,Collection<Plant> plants, Collection<Accessory> accessories);

    /**
     * Apply a discount on a type of plant.
     */
    public void applyDiscount(String scientific_name, int discount);

    /**
     * View all suppliers for a given product.
     */
    public void viewSuppliers(int id);

    /**
     * View all products available from a given supplier.
     */
    public void viewProducts(int id);

    /**
     * View the care plan for a plant.
     */
    public void viewCarePlan(int id);

    /**
     * View an employee's next shift.
     */
    public void viewNextShift(int id);

    /**
     * View employees on shift.
     */
    public void viewOnShift(String date, int startingTime, int endTime);

    /**
     * Add treatments performed on a plant.
     */
    public void addTreatment(int plantID, int employeeID, String date, boolean fertilizer);

    /**
     * Display the best-selling plants in a given period.
     */
    public void viewBestSelling(String from, String to);

     /**
     * View plants that have been treated more than indicated in their care plan 
     * in the range of dates considered or the time in storage during that timeframe.
     */
    public void viewMoreTreated(Date from, Date to);
}