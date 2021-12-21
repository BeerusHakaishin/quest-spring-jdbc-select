package com.wildcodeschool.wildandwizard.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wildcodeschool.wildandwizard.entity.School;

public class SchoolRepository {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/spring_jdbc_quest?serverTimezone=GMT";
    private static final String DB_USER = "h4rryp0tt3r";
    private static final String DB_PASSWORD = "Horcrux4life!";

    public List<School> findAll() {
    	   Connection connection = null;
           PreparedStatement statement = null;
           ResultSet resultSet = null;
         
           try {
               connection = DriverManager.getConnection(
                       DB_URL, DB_USER, DB_PASSWORD
               );
               statement = connection.prepareStatement(
                    "SELECT * FROM school;"
            );
            resultSet = statement.executeQuery();
            List<School> schools = new ArrayList<>();
            
          
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Long capacity = resultSet.getLong("capacity");
                String country = resultSet.getString("country");
                schools.add(new School(id, name, capacity, country));
            }
            return schools;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public School findById(Long id) {
    	
    	 Connection connection = null;
         PreparedStatement statement = null;
         ResultSet resultSet = null;

        try {
             connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM school WHERE id = ?;"
            );
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Long capacity = resultSet.getLong("capacity");
                String country = resultSet.getString("country");
                return new School(id, name, capacity, country);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<School> findByCountry(String country) {
    	
    	 Connection connection = null;
         PreparedStatement statement = null;
         ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM school WHERE country = ?;"
            );
            statement.setString(1, country);
            List<School> schools = new ArrayList<>();
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Long capacity = resultSet.getLong("capacity");
                schools.add(new School(id, name, capacity, country));
            }
            return schools;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
