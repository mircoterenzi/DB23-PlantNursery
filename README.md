# DB23-PlantNursery 🪴
The project represents a database management application for an imaginary plant nursery, the implemented functionalities are:
1. Insert a person (employee or supplier)
2. Process an invoice
3. Issue a receipt
4. Apply a discount on a type of plant
5. View all suppliers for a given product
6. View all products available from a given supplier
7. View the care plan for a plant
8. View an employee's next shift
9. View employees on shift
10.	Update (add) treatments performed on a plant
11.	Display the best-selling plants in a given period
12.	View plants that have been treated more than indicated in their care plan
13. Remove a supplier

The repository contains a report (in italian) describing all the steps and analyses done during the creation of this database.

# How to use
Once the repository is downloaded, the database name set by default is *plantnursery*, in case it needs to be changed you can set it in the *src/main/java/it/unibo/nursery/view/LoginController.java* file.
````
private static final String DB_NAME = "plantnursery";
````
Username and password will be asked for in the application's login screen.
Start the application by running the main method in the *src/main/java/it/unibo/nursery/PlantNursery.java* file.