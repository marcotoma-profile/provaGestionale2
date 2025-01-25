package com.gestionale.businesslogic.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestionale.businesslogic.GlobalManager;
import com.gestionale.businesslogic.persistence.PersistenceManager;
import com.gestionale.businesslogic.persistence.ResultHandler;

public class User {

    private String name;
    private String email;
    private boolean is_admin;

    public boolean isAdmin(){
        return this.is_admin;
    }

    public static User userLogIn(String email, String password) {
        password = GlobalManager.getInstance().getPasswordUtils().hashPassword(password);
        String query = "SELECT name, is_admin FROM Users WHERE email=\'" + email + "\' AND password=\'" + password + "\'";

        User ret = new User();

        PersistenceManager.executeQuery(query, new ResultHandler() {
        
            @Override
            public void handle(ResultSet rs) throws SQLException {
                ret.email = email;
                ret.is_admin = rs.getBoolean("is_admin");
                ret.name = rs.getString("user_name");
            }
        });

        return ret;
    }
}
