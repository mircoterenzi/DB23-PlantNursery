package it.unibo.nursery.logics.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import it.unibo.nursery.logics.api.Features;
import it.unibo.nursery.db.ConnectionProvider;
import it.unibo.nursery.utils.Utils;

public class FeaturesImpl implements Features {

    private final Connection connection; 

    public FeaturesImpl(){
        String username = "root";
        String password = "";

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

    public  void issueReceipt(Date date, int id_imp, List<Integer> prod) {
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
    public void processInvoice() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'issueReceipt'");
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
    public void viewSuppliers(int id) {
        final String query = "SELECT DISTINCT P.* " + 
                "FROM Pianta P, Accessorio A, Fornitore F, Fattura Ft " + 
                "WHERE ((P.id_prodotto = 31 AND P.id_fattura = Ft.id_documento)  " + 
                "    OR (A.id_prodotto = 31 AND A.id_fattura = Ft.id_documento)) " + 
                "AND Ft.id_fornitore = F.id_fornitore";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            //statement.setInt(1, id);
            final ResultSet result = statement.executeQuery();
            //TODO show the result on the side
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void viewProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewProducts'");
    }

    @Override
    public void viewCarePlan(int id) {
        final String query = "SELECT Piano.* " + 
                "FROM Piano_di_Cura Piano, Pianta P, Tipo_pianta T " + 
                "WHERE ? = P.id_prodotto " +
                "AND P.nome = T.nome_scientifico " +
                "AND T.piano = id_piano";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            final ResultSet result = statement.executeQuery();
            //TODO show the result on the side
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void viewNextShift() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewNextShift'");
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
    public void viewMoreTreated() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewMoreTreated'");
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
