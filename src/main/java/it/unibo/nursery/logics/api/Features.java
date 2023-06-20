package it.unibo.nursery.logics.api;

public interface Features {

    /**
     * Insert an employee in the database.
     */
    public void addEmployee();

    /**
     * Insert a supplier in the database.
     */
    public void addSupplier();

    /**
     * Process an invoice.
     */
    public void processInvoice();

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
     */
    public void viewOnShift();

    /**
     * Add treatments performed on a plant.
     */
    public void addTreatment();

    /**
     * Display the best-selling plants in a given period.
     */
    public void viewBestSelling();

    /**
     * View plants that have been treated more than indicated in their care plan.
     */
    public void viewMoreTreated();
}
