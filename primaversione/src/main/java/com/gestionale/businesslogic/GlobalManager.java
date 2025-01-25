package com.gestionale.businesslogic;

import com.gestionale.businesslogic.security.PasswordUtils;

public class GlobalManager {

    private PasswordUtils passwordUtils;

    private GlobalManager() {
        this.passwordUtils = new PasswordUtils();
    }

    private static GlobalManager manager;

    public static GlobalManager getInstance() {
        if (GlobalManager.manager == null) {
            GlobalManager.manager = new GlobalManager();
        }
        return GlobalManager.manager;
    }

    public PasswordUtils getPasswordUtils() {
        return this.passwordUtils;
    }
}
