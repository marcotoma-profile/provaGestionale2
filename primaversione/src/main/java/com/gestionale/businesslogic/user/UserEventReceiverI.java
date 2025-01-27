package com.gestionale.businesslogic.user;

import java.util.Date;

public interface UserEventReceiverI {

    public void updateUserCreated(User user, String password);
    public void updateUserLoggedIn(User user, Date dt);

}
