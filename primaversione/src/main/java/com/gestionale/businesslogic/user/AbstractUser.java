package com.gestionale.businesslogic.user;

public abstract class AbstractUser {
    private String name;
    private String email;
    private boolean is_admin;

    public abstract String getName();
    public abstract String getEmail();
    public abstract String getRole();
    

}
