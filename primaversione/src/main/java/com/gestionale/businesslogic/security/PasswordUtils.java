package com.gestionale.businesslogic.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
