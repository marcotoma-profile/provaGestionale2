package com.gestionale.businesslogic;

import com.gestionale.businesslogic.observer.UserEventReceiver;
import com.gestionale.businesslogic.security.PasswordUtils;
import com.gestionale.businesslogic.user.UserManager;

public class GlobalManager {

    private PasswordUtils passwordUtils;
    private UserManager userManager;

    private GlobalManager() {
        this.passwordUtils = new PasswordUtils();
        this.userManager = new UserManager();
        this.userManager.addReceiver(new UserEventReceiver());
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

    public UserManager getUserManager() {
        return this.userManager;
    }
}
