package it.unibo.nursery.logics.api;

import java.util.Date;
import java.util.List;

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
     * Process an invoice.
     */
    public void processInvoice(Date date, int id_imp, List<Integer> prod);

    /**
     * Issue a receipt.
     */
    public void issueReceipt();

    /**
     * Apply a discount on a type of plant.
     */
    public void applyDiscount();

    /**
     * View all suppliers for a given product.
     */
    public void viewSuppliers();

    /**
     * View all products available from a given supplier.
     */
    public void viewProducts();

    /**
     * View the care plan for a plant.
     */
    public void viewCarePlan();

    /**
     * View an employee's next shift.
     */
    public void viewNextShift();

    /**
     * View employees on shift.
     * @param date Date in format dd/mm/yyyy
     * @param startingTime
     * @param endTime
     */
    public void viewOnShift(String date, int startingTime, int endTime);

    /**
     * Add treatments performed on a plant.
     * @param plantID
     * @param employeeID
     * @param date Date in format dd/mm/yyyy
     * @param fertilizer True if the plant has been fertilized
     */
    public void addTreatment(int plantID, int employeeID, String date, boolean fertilizer);

    /**
     * Display the best-selling plants in a given period.
     */
    public void viewBestSelling();

    /**
     * View plants that have been treated more than indicated in their care plan.
     */
    public void viewMoreTreated();
}
