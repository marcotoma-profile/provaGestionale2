package com.gestionale.businesslogic.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestionale.businesslogic.persistence.BatchUpdateHandler;
import com.gestionale.businesslogic.persistence.PersistenceManager;

public class User {

    private String id;
    private String name;
    private String email;
    private boolean is_admin;

    public User(String name, String email, boolean is_admin) {
        this.id = "";
        this.name = name;
        this.email = email;
        this.is_admin = is_admin;
    }

    private User (){
        this.id = "";
        this.name = "";
        this.email = "";
        this.is_admin = false;
    }

    public boolean isAdmin(){
        return this.is_admin;
    }

    public static boolean userAlreadyPresent(String email) {
        String query = "SELECT user_name FROM Users WHERE email=\'" + email +"\'";
        User ret = new User();

        PersistenceManager.executeQuery(query, (ResultSet rs) -> {
            ret.name = rs.getString("user_name");
        });

        return !ret.name.equals("");
    }

    public static User userLogIn(String email, String password) {
        String query = "SELECT name, is_admin FROM Users WHERE email=\'" + email + "\' AND password=\'" + password + "\'";

        User ret = new User();

        PersistenceManager.executeQuery(query, (ResultSet rs) -> {
            ret.email = email;
            ret.is_admin = rs.getBoolean("is_admin");
            ret.name = rs.getString("user_name");
        });

        return ret;
    }

    public static void createNewUser(User user, String password){
        String query = "INSERT INTO Users (email, user_name, password, dt_user_added) VALUES (?, ?, ?, ?)";

        int[] result = PersistenceManager.executeBatchUpdate(query, 1, new BatchUpdateHandler() {
            
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setString(1, user.email);
                ps.setString(2, user.name);
                ps.setString(3, password);
                ps.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
            }
            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // l'utente creato dovrebbe essere solamente uno
                if (count == 0) {
                    user.id = rs.getString(1);
                }
            }
        });
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\nEmail: " + this.email + "\nId: " + this.id;
    }
}
