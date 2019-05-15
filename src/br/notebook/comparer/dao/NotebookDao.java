package br.notebook.comparer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.notebook.comparer.model.Notebook;
import br.notebook.comparer.util.DbUtil;

public class NotebookDao implements Dao {

    private Connection connection;

    public NotebookDao() {
    	
        try {
			connection = DbUtil.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ArithmeticException("NotebookDao: DB Connect: " + e.getMessage());
		}
    
    } //NotebookDao

    public void addNotebook(Notebook Notebook) {
        
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO Notebook (NAME, EMAIL, DOB, COLOR, CARDTYPE, GENDER, PERIOD, VALUE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, Notebook.getName());
            preparedStatement.setString(2, Notebook.getEmail());
            preparedStatement.setDate(3, new java.sql.Date(Notebook.getDob().getTime())); //java.sql.Date não tem time zone...
            preparedStatement.setString(4, Notebook.getColor());
            preparedStatement.setString(5, Notebook.getCardType());
            preparedStatement.setString(6, Notebook.getGender());
            preparedStatement.setString(7, Notebook.getPeriod());
            preparedStatement.setFloat(8, Notebook.getValue());
            
            preparedStatement.executeUpdate();
            

        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("NotebookDao: addNotebook: " + e.getMessage()); 
        }
    } //addNotebook

    public void deleteNotebook(long id) {
        try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM PEOPOLE WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //deleteNotebook

    public void updateNotebook(Notebook Notebook) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE USERS SET NAME=?, " 
                    		                          + "EMAIL=?, " 
                    		                          + "DOB=?, " 
                    		                          + "COLOR=?, " 
                    		                          + "CARDTYPE=?, " 
                    		                          + "GENDER=?, " 
                    		                          + "PERIOD=? " 
                    		                          + "VALUE=? " 
                                               + "WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setString(1, Notebook.getName());
            preparedStatement.setString(2, Notebook.getEmail());
            preparedStatement.setDate(3, (java.sql.Date)Notebook.getDob());
            preparedStatement.setString(4, Notebook.getColor());
            preparedStatement.setString(5, Notebook.getCardType());
            preparedStatement.setString(6, Notebook.getGender());
            preparedStatement.setString(7, Notebook.getPeriod());
            preparedStatement.setFloat(8, Notebook.getValue());
            
            preparedStatement.setLong(9, Notebook.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //updateNotebook

    public List<Notebook> getAllNotebook() {
        
    	List<Notebook> p = new ArrayList<Notebook>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Notebook");
            while (rs.next()) {
                
            	Notebook Notebook = new Notebook();
                
            	Notebook.setId(rs.getLong("ID"));
                Notebook.setName(rs.getString("NAME"));
                Notebook.setEmail(rs.getString("EMAIL"));
                Notebook.setDob(rs.getDate("DOB"));
                Notebook.setValue(rs.getFloat("VALUE"));
                Notebook.setValue(rs.getString("COLOR"));
                Notebook.setValue(rs.getString("CARDTYPE"));
                Notebook.setValue(rs.getString("GENDER"));
                Notebook.setValue(rs.getString("PERIOD"));

                p.add(Notebook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    } //getAllNotebook

    public Notebook getUserById(long id) {

    	Notebook p = new Notebook();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from Notebook WHERE ID=?");
            
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p.setEmail(rs.getString("EMAIL"));
                p.setDob(rs.getDate("DOB"));
                p.setValue(rs.getFloat("VALUE"));
                p.setColor(rs.getString("COLOR"));
                p.setColor(rs.getString("CARD"));
                p.setColor(rs.getString("PERIOD"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    } //getUserById
    
} //NotebookDao