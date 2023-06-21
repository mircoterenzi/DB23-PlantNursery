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
        String password = "Delta_Velorum1";

        String dbName = "plantnursery";
        ConnectionProvider prov = new ConnectionProvider( username, password, dbName);
        connection = prov.getMySQLConnection();
    }

    @Override
    public void addEmployee(String FirstName,String LastName,String CF,float income, Date employment_date) {
        final String query = "INSERT INTO Impiegato (nome, cognome, CF, stipendio, data_assunzione, id_imp) VALUES (?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, FirstName);
            statement.setString(2, LastName);
            statement.setString(3, CF);
            statement.setFloat(4,income);
            statement.setDate(5, Utils.dateToSqlDate(employment_date));
            statement.setInt(6,this.getNext("Impiegati","id_imp"));
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

    @Override
    public void processInvoice(Date date, int id_imp, List<Integer> prod) {
        
    }

    @Override
    public void issueReceipt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'issueReceipt'");
    }

    @Override
    public void applyDiscount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyDiscount'");
    }

    @Override
    public void viewSuppliers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewSuppliers'");
    }

    @Override
    public void viewProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewProducts'");
    }

    @Override
    public void viewCarePlan() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewCarePlan'");
    }

    @Override
    public void viewNextShift() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewNextShift'");
    }

    @Override
    public void viewOnShift() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewOnShift'");
    }

    @Override
    public void addTreatment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTreatment'");
    }

    @Override
    public void viewBestSelling() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewBestSelling'");
    }

    @Override
    public void viewMoreTreated() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewMoreTreated'");
    }
    

    private int getNext(String table_name, String column) {
        String query = "SELECT MAX("+ column + ") FROM " + table_name;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(column) + 1;
            } else {
                return 1;
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
