package br.notebook.comparer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static Connection connection = null;

    public static Connection getConnection() throws Exception {
       
    	if (connection != null)
            return connection;
        else {

                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/FTT";
                String user = "scott";
                String password = "tiger";
            	
            	Class.forName(driver); //verifica se o driver do bd está no class path...
                
                connection = DriverManager.getConnection(url, user, password);
                
                connection.setAutoCommit(true);
            
            
            return connection;
        }

    }
}