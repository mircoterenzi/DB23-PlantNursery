package it.unibo.nursery.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import it.unibo.nursery.db.Accessory;
import it.unibo.nursery.db.CarePlan;
import it.unibo.nursery.db.ConnectionProvider;
import it.unibo.nursery.db.Plant;
import it.unibo.nursery.db.PlantCure;
import it.unibo.nursery.db.Supplier;
import it.unibo.nursery.model.api.Features;
import it.unibo.nursery.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FeaturesImpl implements Features {

    private final Connection connection; 

    public FeaturesImpl(){
        String username = "root";
        String password = "Delta?Velorum1";

        String dbName = "plantnursery";
        ConnectionProvider prov = new ConnectionProvider( username, password, dbName);
        connection = prov.getMySQLConnection();
    }

    @Override
    public void addEmployee(String FirstName,String LastName,String CF,float income, Date employment_date) {
        final String query = "INSERT INTO Impiegato" +
                                "(nome, cognome, CF, stipendio, data_assunzione, id_imp)" + 
                                " VALUES (?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, FirstName);
            statement.setString(2, LastName);
            statement.setString(3, CF);
            statement.setFloat(4,income);
            statement.setDate(5, Utils.dateToSqlDate(employment_date));
            statement.setInt(6,this.getNext("Impiegato","id_imp"));
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void addSupplier(String name) {
        final String query = "INSERT INTO Fornitore (id_fornitore, nome) VALUES (?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1,this.getNext("Fornitore","id_fornitore"));
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public  void issueReceipt(Date date, int id_imp, Collection<Integer> prod) {
         final String query = "insert into scontrino (id_documento,data,impiegato) values (?,?,?)";
         int id_receipt = this.nextId_doc();
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1,id_receipt);
            statement.setDate(2, Utils.dateToSqlDate(date));
            statement.setInt(3, id_imp);
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        } 
        for (int id_prod : prod) {
            this.addReceiptToProduct(id_prod,id_receipt);
        }
    }

    private void addReceiptToProduct(int id_prod, int id_receipt) {
        final String query = "UPDATE accessorio SET id_scontrino = ? WHERE id_prodotto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id_receipt);
            statement.setInt(2, id_prod);
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        } 
    }

    @Override
    public void processInvoice(int id_supplier,Date date,Collection<Plant> plants, Collection<Accessory> accessories) {
        int id_invoice = this.nextId_doc();
        final String query = "insert into fattura(id_documento,data,id_fornitore) values(?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id_invoice);
            statement.setDate(2, Utils.dateToSqlDate(date));
            statement.setInt(3, id_supplier);
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        plants.forEach(p -> addPlant(p,id_invoice));
        accessories.forEach(p -> addAccessory(p,id_invoice));
    }

    private void addAccessory(Accessory p, int id_invoice) {
        final String query = "INSERT INTO Accessorio (id_prodotto, descrizione, id_fattura, id_scontrino, tipo)\n" + 
                "VALUES(?,?,?,NULL,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, this.nextId_prod());
            statement.setString(2, p.getDescription());
            statement.setInt(3, id_invoice);
            statement.setString(4, p.getType());
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void addPlant(Plant p, int id_invoice) {
        final String query = "INSERT INTO Pianta"+
                            "(id_prodotto, descrizione, larghezza_vaso, altezza, prezzo, id_fattura, id_scontrino, nome)"+
                            "VALUES(?,?,?,?,?,?,NULL,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, this.nextId_prod());
            statement.setString(2, p.getDescription());
            statement.setFloat(3, p.getLength());
            statement.setFloat(4, p.getHeight());
            statement.setFloat(5, p.getPrice());
            statement.setInt(6, id_invoice);
            statement.setString(7, p.getScientificName());
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void applyDiscount(String scientific_name, int discount) {

        if(scientific_name.length()>20 || discount >100 || discount<0 ){
            throw new IllegalArgumentException("invalid parameters for applyDiscount");
        }

        final String query = "UPDATE Pianta SET prezzo = prezzo *?" + 
                            "WHERE nome = '?' )";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, 1-discount/100);
            statement.setString(2, scientific_name);
            statement.executeUpdate();
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(e);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public ObservableList<Supplier> viewSuppliers(int id) {
        final String query = "SELECT DISTINCT F.* " + 
                "FROM Pianta P, Accessorio A, Fornitore F, Fattura Ft " + 
                "WHERE ((P.id_prodotto = ? AND P.id_fattura = Ft.id_documento) " + 
                "    OR (A.id_prodotto = ? AND A.id_fattura = Ft.id_documento)) " + 
                "AND Ft.id_fornitore = F.id_fornitore";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setInt(2, id);
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

    @Override
    public ObservableList<Supplier> viewProducts(int id) {
        final String query = "SELECT DISTINCT id_fornitore, nome " +
                "FROM Pianta, Fattura " +
                "WHERE id_fattura = id_documento " +
                "AND id_fornitore = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
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

    @Override
    public ObservableList<CarePlan> viewCarePlan(int id) {
        final String query = "SELECT Piano.* " + 
                "FROM Piano_di_Cura Piano, Pianta P, Tipo_pianta T " + 
                "WHERE ? = P.id_prodotto " +
                "AND P.nome = T.nome_scientifico " +
                "AND T.piano = id_piano";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
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

    @Override
    public void viewNextShift(int id) {
        final String query = "SELECT cod_reparto, data, ora_inizio, ora_fine " +
                "FROM Turno " +
                "WHERE id_imp = ? " +
                "AND data > ? " +
                "ORDER BY data, ora_inizio " +
                "LIMIT 1";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            final ResultSet result = statement.executeQuery();
            //TODO show the result on the side
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void viewOnShift(String date, int startingTime, int endTime) {
        final String query = "SELECT I.id_imp, nome, cognome, ora_inizio, ora_fine " + 
                "FROM Turno T, Impiegato I " + 
                "WHERE T.id_imp = I.id_imp " +
                "AND (ora_inizio >= ? OR ora_fine <= ?) " +
                "AND data = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, startingTime);
            statement.setInt(2, endTime);
            statement.setDate(3, Utils.dateToSqlDate(Utils.buildDate(date).get()));
            final ResultSet result = statement.executeQuery();
            //TODO show the result on the side
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void addTreatment(int plantID, int employeeID, String date, boolean fertilizer) {
        final String query = "INSERT INTO Cura (pianta, data, id_imp, concime) " + 
                "VALUES (?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, plantID);
            statement.setDate(2, Utils.dateToSqlDate(Utils.buildDate(date).get()));
            statement.setInt(3, employeeID);
            statement.setInt(4, fertilizer ? 0 : 1);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void viewBestSelling(String from, String to) {
        final String query = "SELECT nome, COUNT(*) AS num_piante " +
                "FROM Pianta, Scontrino " +
                "WHERE id_scontrino = id_documento " +
                "AND data BETWEEN ? AND ? " +
                "GROUP BY nome " +
                "ORDER BY num_piante " +
                "LIMIT 3";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(1, Utils.dateToSqlDate(Utils.buildDate(from).get()));
            statement.setDate(2, Utils.dateToSqlDate(Utils.buildDate(to).get()));
            final ResultSet result = statement.executeQuery();
            //TODO show the result on the side
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public ObservableList<PlantCure> viewMoreTreated(Date from, Date to) {
        this.createDatesView( from, to);
        this.createCureCountView(from, to);
        final String query = "SELECT P.id_prodotto, T.nome_scientifico, DATEDIFF(L.care_end, L.care_start) AS days_in_care, " +
               "FLOOR(DATEDIFF(L.care_end, L.care_start) / N.acqua) AS expected_acqua, C.water_count, " +
               "FLOOR(DATEDIFF(L.care_end, L.care_start) / N.concime) AS expected_concime, " +
               "C.concime_count " +
               "FROM Pianta P " +
               "JOIN Tipo_pianta T ON P.nome = T.nome_scientifico " +
               "JOIN Piano_di_cura N ON T.piano = N.id_piano " +
               "JOIN Plant_life L ON P.id_prodotto = L.id_prodotto " +
               "JOIN Num_cure C ON P.id_prodotto = C.id_prodotto " +
               "WHERE DATEDIFF(L.care_end, L.care_start) / N.acqua < C.water_count " +
               "OR DATEDIFF(L.care_end, L.care_start) / N.acqua < C.concime_count;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            ObservableList<PlantCure> list = FXCollections.observableList(new LinkedList<PlantCure>());
            while(result.next()){
                PlantCure plant = new PlantCure(result.getInt("id_prodotto"),
                                                    result.getString("nome_scientifico"),
                                                    result.getInt("days_in_care"),
                                                    result.getInt("expected_acqua"),
                                                    result.getInt("water_count"),
                                                    result.getInt("expected_concime"),
                                                    result.getInt("concime_count"));
                list.add(plant);
            }
            return list;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    

    private void createCureCountView(Date from, Date to) {
        final String query = "CREATE OR REPLACE VIEW Num_cure as " +
                            "Select P.id_prodotto, water_count,concime_count " +
                            "from Pianta P, (SELECT pianta, COUNT(*) AS water_count " +
                                            "FROM Cura where data between ? and ? " +
                                            "GROUP BY pianta) water_count, " + 
                                "(SELECT pianta, COUNT(*) AS concime_count "+ 
                                            "FROM Cura where concime = true and data between ? and ? " +
                                            "GROUP BY pianta) concime_count " +
                            "where P.id_prodotto = water_count.pianta and P.id_prodotto = concime_count.pianta ";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(1, Utils.dateToSqlDate(Utils.dateToSqlDate(from)));
            statement.setDate(2, Utils.dateToSqlDate(Utils.dateToSqlDate(to)));
            statement.setDate(3, Utils.dateToSqlDate(Utils.dateToSqlDate(from)));
            statement.setDate(4, Utils.dateToSqlDate(Utils.dateToSqlDate(to)));
            statement.executeUpdate();
            //TODO show the result on the side
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void createDatesView(Date from, Date to) {
        final String query = "CREATE OR REPLACE VIEW Plant_life AS " +
                                "SELECT p.id_prodotto, " +
                                "CASE WHEN f.data < ? THEN ? ELSE f.data END AS care_start, " +
                                "CASE WHEN s.data > ? THEN ? ELSE COALESCE(s.data, ?) END AS care_end " +
                                "FROM Pianta p LEFT JOIN Scontrino s ON p.id_scontrino = s.id_documento " +
                                "JOIN Fattura f ON p.id_fattura = f.id_documento";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(1, Utils.dateToSqlDate(Utils.dateToSqlDate(from)));
            statement.setDate(2, Utils.dateToSqlDate(Utils.dateToSqlDate(from)));
            statement.setDate(3, Utils.dateToSqlDate(Utils.dateToSqlDate(to)));
            statement.setDate(4, Utils.dateToSqlDate(Utils.dateToSqlDate(to)));
            statement.setDate(5, Utils.dateToSqlDate(Utils.dateToSqlDate(to)));
            statement.executeUpdate();
            //TODO show the result on the side
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private int getNext(String table_name, String column) {
        String query = "SELECT MAX("+ column + ") FROM " + table_name;
        System.out.println(query);
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) + 1;
            } else {
                return 1;
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private int nextId_prod(){
        String query = "SELECT MAX(id_prodotto) AS max_id_prodotto" + 
            "FROM ( SELECT id_prodotto FROM Pianta"+
            "UNION ALL" +
            "SELECT id_prodotto FROM Accessorio" +
            ") AS merged_tables";

            try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt("max_id_prodotto") + 1;
            } else {
                return 1;
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private int nextId_doc(){
        String query = "SELECT MAX(id_documento) AS max_id_documento" + 
            "FROM ( SELECT id_documento FROM Scontrino"+
            "UNION ALL" +
            "SELECT id_documento FROM Fattura" +
            ") AS merged_tables";

            try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt("max_id_documento") + 1;
            } else {
                return 1;
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
