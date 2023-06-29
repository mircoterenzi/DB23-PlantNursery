package it.unibo.nursery.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.Collection;

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
import it.unibo.nursery.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Implementation of all features required by the application. */
public class FeaturesImpl implements Features {

    private static final Date TODAY = Utils.buildDate("20/06/2023").get();
    private final Connection connection;

    /**
     * Class constructor.
     * @param connection The connection to the database
     */
    public FeaturesImpl(final Connection connection) {
        this.connection = connection;
    }

    /** {@inheritDoc} */
    @Override
    public void removeSupplier(final int supplierId) {
        if (supplierId == 1) {
            throw new IllegalArgumentException("cannot remove id = 1 ");
        }
        if (checkDependencies(supplierId)) {
            handleDependencies(supplierId);
        }
        final String query = "DELETE FROM Fornitore WHERE id_fornitore = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, supplierId);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean checkDependencies(final int supplierId) {
        final String query = "SELECT * FROM Fattura WHERE id_fornitore = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, supplierId);
            return statement.executeQuery().next();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void handleDependencies(final int supplierId) {
        final String query = "UPDATE Fattura SET id_fornitore = 1 "
                + "WHERE id_fornitore = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, supplierId);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void addEmployee(final String firstName, final String lastName, final String cf,
            final float income, final Date employmentDate) {
        final String query = "INSERT INTO Impiegato "
                + "(nome, cognome, CF, stipendio, data_assunzione, id_imp) "
                + " VALUES (?,?,?,?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, cf);
            statement.setFloat(4, income);
            statement.setDate(5, Utils.dateToSqlDate(employmentDate));
            statement.setInt(6, this.getNext("Impiegato", "id_imp"));
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void addSupplier(final String name) {
        final String query = "INSERT INTO Fornitore (id_fornitore, nome) VALUES (?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, this.getNext("Fornitore", "id_fornitore"));
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void issueReceipt(final Date date, final int idEmployee, final Collection<Integer> products) {
        final String query = "insert into scontrino (id_documento,data,impiegato) values (?,?,?)";
        final int idReceipt = this.nextIdDoc();
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, idReceipt);
            statement.setDate(2, Utils.dateToSqlDate(date));
            statement.setInt(3, idEmployee);
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        } 
        for (final int idProd : products) {
            this.addReceiptToProduct(idProd, idReceipt);
        }
    }

    private void addReceiptToProduct(final int idProduct, final int idReceipt) {
        final String queryAccessory = "UPDATE accessorio SET id_scontrino = ? WHERE id_prodotto = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(queryAccessory)) {
            statement.setInt(1, idReceipt);
            statement.setInt(2, idProduct);
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        } 
        final String queryPlant = "UPDATE pianta SET id_scontrino = ? WHERE id_prodotto = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(queryPlant)) {
            statement.setInt(1, idReceipt);
            statement.setInt(2, idProduct);
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        } 
    }

    /** {@inheritDoc} */
    @Override
    public void processInvoice(final int idSupplier, final Date date, final Collection<Plant> plants,
            final Collection<Accessory> accessories) {
        final int idInvoice = this.nextIdDoc();
        final String query = "insert into fattura(id_documento,data,id_fornitore) values(?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, idInvoice);
            statement.setDate(2, Utils.dateToSqlDate(date));
            statement.setInt(3, idSupplier);
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        plants.forEach(p -> addPlant(p, idInvoice));
        accessories.forEach(p -> addAccessory(p, idInvoice));
    }

    private void addAccessory(final Accessory accessory, final int idInvoice) {
        final String query = "INSERT INTO Accessorio (id_prodotto, descrizione, id_fattura, id_scontrino, tipo) "
                + "VALUES(?,?,?,NULL,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, this.nextIdProd());
            statement.setString(2, accessory.getDescription());
            statement.setInt(3, idInvoice);
            statement.setString(4, accessory.getType());
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void addPlant(final Plant plant, final int idInvoice) {
        final String query = "INSERT INTO Pianta "
                + "(id_prodotto, descrizione, larghezza_vaso, altezza, prezzo, id_fattura, id_scontrino, nome) "
                + "VALUES(?,?,?,?,?,?,NULL,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, this.nextIdProd());
            statement.setString(2, plant.getDescription());
            statement.setFloat(3, plant.getLength());
            statement.setFloat(4, plant.getHeight());
            statement.setFloat(5, plant.getPrice());
            statement.setInt(6, idInvoice);
            statement.setString(7, plant.getScientificName());
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void applyDiscount(final String scientificName, final Float discount) {
        if (scientificName.length() > 20 || discount > 100 || discount < 0) {
            throw new IllegalArgumentException("invalid parameters for applyDiscount");
        }
        final String query = "UPDATE Pianta SET prezzo = ( prezzo * ? )"
                + "WHERE nome = ? ";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            final float sconto = 1 - discount / 100;
            statement.setFloat(1, sconto);
            statement.setString(2, scientificName);
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ObservableList<Supplier> viewSuppliers(final String productName) {
        final String query = "SELECT DISTINCT F.* "
                + "FROM Fornitore F, Accessorio A, Pianta P, Fattura FT "
                + "WHERE F.id_fornitore = FT.id_fornitore "
                + "AND ((FT.id_documento = P.id_fattura AND P.nome = ?) "
                + "       OR (FT.id_documento = A.id_fattura AND A.tipo = ?))";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, productName);
            statement.setString(2, productName);
            final ResultSet result = statement.executeQuery();
            final ObservableList<Supplier> data = FXCollections.observableArrayList();
            while (result.next()) {
                data.add(new Supplier(result.getInt("id_fornitore"), result.getString("nome")));
            }
            return data;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ObservableList<SimpleType> viewProducts(final int id) {
        final String query = "SELECT DISTINCT nome "
                + "FROM Pianta P, Fattura FT "
                + "WHERE P.id_fattura = FT.id_documento "
                + "AND FT.id_fornitore = ? "
                + "UNION ALL "
                + "SELECT DISTINCT tipo AS nome "
                + "FROM Accessorio A, Fattura FT "
                + "WHERE A.id_fattura = FT.id_documento "
                + "AND FT.id_fornitore = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setInt(2, id);
            final ResultSet result = statement.executeQuery();
            final ObservableList<SimpleType> data = FXCollections.observableArrayList();
            while (result.next()) {
                data.add(new SimpleType(result.getString("nome")));
            }
            return data;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ObservableList<CarePlan> viewCarePlan(final int id) {
        final String query = "SELECT PCura.* "
                + "FROM Piano_di_Cura PCura, Pianta P, Tipo_pianta T "
                + "WHERE ? = P.id_prodotto "
                + "AND P.nome = T.nome_scientifico "
                + "AND T.piano = id_piano";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            final ResultSet result = statement.executeQuery();
            final ObservableList<CarePlan> data = FXCollections.observableArrayList();
            while (result.next()) {
                data.add(new CarePlan(
                        result.getInt("id_piano"),
                        result.getFloat("acqua"),
                        result.getString("livello_luce"),
                        result.getFloat("concime"),
                        result.getFloat("temp_min"),
                        result.getFloat("temp_max")
                ));
            }
            return data;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ObservableList<Shift> viewNextShift(final int id) {
        final String query = "SELECT * "
                + "FROM Turno "
                + "WHERE id_imp = ? "
                + "AND data > ? "
                + "ORDER BY data, ora_inizio "
                + "LIMIT 1";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setDate(2, Utils.dateToSqlDate(TODAY));
            final ResultSet result = statement.executeQuery();
            final ObservableList<Shift> data = FXCollections.observableArrayList();
            while (result.next()) {
                data.add(new Shift(
                        result.getInt("cod_reparto"),
                        Utils.sqlDateToDate(result.getDate("data")),
                        result.getInt("ora_inizio"),
                        result.getInt("ora_fine")
                ));
            }
            return data;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ObservableList<Employee> viewOnShift(final String date, final int startingTime, final int endTime) {
        final String query = "SELECT I.* "
                + "FROM Turno T, Impiegato I "
                + "WHERE T.id_imp = I.id_imp "
                + "AND data = ? "
                + "AND ((ora_inizio >= ? AND ora_inizio < ?) OR "
                + "    (ora_fine > ? AND ora_fine <= ?))";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(1, Utils.dateToSqlDate(Utils.buildDate(date).get()));
            statement.setInt(2, startingTime);
            statement.setInt(3, endTime);
            statement.setInt(4, startingTime);
            statement.setInt(5, endTime);
            final ResultSet result = statement.executeQuery();
            final ObservableList<Employee> data = FXCollections.observableArrayList();
            while (result.next()) {
                data.add(new Employee(
                        result.getString("nome"),
                        result.getString("cognome"),
                        result.getString("CF"),
                        result.getFloat("stipendio"),
                        Utils.sqlDateToDate(result.getDate("data_assunzione")),
                        result.getInt("id_imp")
                ));
            }
            return data;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void addTreatment(final int idPlant, final int idEmployee, final String date, final boolean fertilizer) {
        final String query = "INSERT INTO Cura "
                + "VALUES (?,?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, idPlant);
            statement.setDate(2, Utils.dateToSqlDate(Utils.buildDate(date).get()));
            statement.setInt(3, idEmployee);
            statement.setInt(4, fertilizer ? 0 : 1);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ObservableList<PlantSold> viewBestSelling(final String from, final String to) {
        final String query = "SELECT nome, COUNT(*) AS num_piante "
                + "FROM Pianta, Scontrino "
                + "WHERE id_scontrino = id_documento "
                + "AND data BETWEEN ? AND ? "
                + "GROUP BY nome "
                + "ORDER BY num_piante "
                + "LIMIT 3";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(1, Utils.dateToSqlDate(Utils.buildDate(from).get()));
            statement.setDate(2, Utils.dateToSqlDate(Utils.buildDate(to).get()));
            final ResultSet result = statement.executeQuery();
            final ObservableList<PlantSold> data = FXCollections.observableArrayList();
            while (result.next()) {
                data.add(new PlantSold(
                        result.getString("nome"),
                        result.getInt("num_piante")
                ));
            }
            return data;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ObservableList<PlantCure> viewMoreTreated(final Date from, final Date to) {
        this.createDatesView(from, to);
        this.createCureCountView(from, to);
        final String query = "SELECT P.id_prodotto, T.nome_scientifico, DATEDIFF(L.care_end, L.care_start) AS days_in_care, "
               + "FLOOR(DATEDIFF(L.care_end, L.care_start) / N.acqua) AS expected_acqua, C.water_count, "
               + "FLOOR(DATEDIFF(L.care_end, L.care_start) / N.concime) AS expected_concime, "
               + "C.concime_count "
               + "FROM Pianta P "
               + "JOIN Tipo_pianta T ON P.nome = T.nome_scientifico "
               + "JOIN Piano_di_cura N ON T.piano = N.id_piano "
               + "JOIN Plant_life L ON P.id_prodotto = L.id_prodotto "
               + "JOIN Num_cure C ON P.id_prodotto = C.id_prodotto "
               + "WHERE DATEDIFF(L.care_end, L.care_start) / N.acqua < C.water_count "
               + "OR DATEDIFF(L.care_end, L.care_start) / N.acqua < C.concime_count";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            final ObservableList<PlantCure> list = FXCollections.observableList(new LinkedList<PlantCure>());
            while (result.next()) {
                list.add(new PlantCure(result.getInt("id_prodotto"),
                        result.getString("nome_scientifico"),
                        result.getInt("days_in_care"),
                        result.getInt("expected_acqua"),
                        result.getInt("water_count"),
                        result.getInt("expected_concime"),
                        result.getInt("concime_count")));
            }
            return list;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void createCureCountView(final Date from, final Date to) {
        final String query = "CREATE OR REPLACE VIEW Num_cure as "
                            + "Select P.id_prodotto, water_count,concime_count "
                            + "from Pianta P, (SELECT pianta, COUNT(*) AS water_count "
                            + "                 FROM Cura where data between ? and ? "
                            + "                 GROUP BY pianta) water_count, "
                            + "             (SELECT pianta, COUNT(*) AS concime_count "
                            + "                 FROM Cura where concime = true and data between ? and ? "
                            + "                 GROUP BY pianta) concime_count "
                            + "where P.id_prodotto = water_count.pianta and P.id_prodotto = concime_count.pianta ";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(1, Utils.dateToSqlDate(Utils.dateToSqlDate(from)));
            statement.setDate(2, Utils.dateToSqlDate(Utils.dateToSqlDate(to)));
            statement.setDate(3, Utils.dateToSqlDate(Utils.dateToSqlDate(from)));
            statement.setDate(4, Utils.dateToSqlDate(Utils.dateToSqlDate(to)));
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void createDatesView(final Date from, final Date to) {
        final String query = "CREATE OR REPLACE VIEW Plant_life AS "
                + "SELECT p.id_prodotto, "
                + "CASE WHEN f.data < ? THEN ? ELSE f.data END AS care_start, "
                + "CASE WHEN s.data > ? THEN ? ELSE COALESCE(s.data, ?) END AS care_end "
                + "FROM Pianta p LEFT JOIN Scontrino s ON p.id_scontrino = s.id_documento "
                + "JOIN Fattura f ON p.id_fattura = f.id_documento";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(1, Utils.dateToSqlDate(Utils.dateToSqlDate(from)));
            statement.setDate(2, Utils.dateToSqlDate(Utils.dateToSqlDate(from)));
            statement.setDate(3, Utils.dateToSqlDate(Utils.dateToSqlDate(to)));
            statement.setDate(4, Utils.dateToSqlDate(Utils.dateToSqlDate(to)));
            statement.setDate(5, Utils.dateToSqlDate(Utils.dateToSqlDate(to)));
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private int getNext(final String tableName, final String column) {
        final String query = "SELECT MAX(" + column + ") FROM " + tableName;
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            return resultSet.next() ? resultSet.getInt(1) + 1 : 1;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    private int nextIdProd() {
        final String query = "SELECT MAX(id_prodotto) AS max_id_prodotto "
                + "FROM ( SELECT id_prodotto FROM Pianta "
                + "UNION ALL "
                + "SELECT id_prodotto FROM Accessorio "
                + ") AS merged_tables";

            try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            return resultSet.next() ? resultSet.getInt("max_id_prodotto") + 1 : 1;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private int nextIdDoc() {
        final String query = "SELECT MAX(id_documento) AS max_id_documento "
                + "FROM ( SELECT id_documento FROM Scontrino "
                + "UNION ALL "
                + "SELECT id_documento FROM Fattura "
                + ") AS merged_tables";
            try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            return resultSet.next() ? resultSet.getInt("max_id_documento") + 1 : 1;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ObservableList<Supplier> viewAllSuppliers() {
       final String query = "SELECT * from Fornitore";
        try (Statement statement = connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final ObservableList<Supplier> list = FXCollections.observableArrayList();
            while (result.next()) {
                list.add(new Supplier(result.getInt("id_fornitore"), result.getString("nome")));
            }
            return list;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ObservableList<Product> viewAllProducts() {
        final String query = "SELECT id_prodotto, nome AS tipo, "
                + "prezzo, descrizione "
                + "FROM Pianta "
                + "WHERE id_scontrino IS NULL "
                + "UNION ALL "
                + "SELECT A.id_prodotto, A.tipo, "
                + "T.prezzo, A.descrizione "
                + "FROM Accessorio A, Tipo_accessorio T "
                + "WHERE A.tipo = T.nome_tipo AND id_scontrino IS NULL";
        try (Statement statement = connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final ObservableList<Product> list = FXCollections.observableArrayList();
            while (result.next()) {
               list.add(new Product(result.getInt("id_prodotto"),
                        result.getString("tipo"),
                        result.getFloat("prezzo"),
                        result.getString("descrizione")));
            }
            return list;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ObservableList<Employee> viewAllEmployees() {
        final String query = "SELECT * from Impiegato";
        try (Statement statement = connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final ObservableList<Employee> list = FXCollections.observableArrayList();
            while (result.next()) {
               list.add(new Employee(result.getString("nome"),
                        result.getString("cognome"),
                        result.getString("cf"),
                        result.getFloat("stipendio"),
                        Utils.sqlDateToDate(result.getDate("data_assunzione")),
                        result.getInt("id_imp")));
            }
            return list;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
