package com.gestionale.businesslogic.observer;

import java.util.Date;

import com.gestionale.businesslogic.user.User;
import com.gestionale.businesslogic.user.UserEventReceiverI;

public class UserEventReceiver implements UserEventReceiverI {

    @Override
    public void updateUserCreated(User user, String password) {
        User.createNewUser(user, password);
    }

    @Override
    public void updateUserLoggedIn(User user, Date dt) {
        
    }
}
