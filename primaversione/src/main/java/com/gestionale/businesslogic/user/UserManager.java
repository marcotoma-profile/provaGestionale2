package com.gestionale.businesslogic.user;

import java.util.ArrayList;
import java.util.Date;

import com.gestionale.businesslogic.GlobalManager;

public class UserManager {
    
    private User currentUser;
    private ArrayList<UserEventReceiverI> event_receivers;

    public UserManager() {
        this.event_receivers = new ArrayList<>();
    }

    public void addReceiver(UserEventReceiverI receiver) {
        this.event_receivers.add(receiver);
    }

    public void removeReceiver(UserEventReceiverI receiver) {
        if (this.event_receivers.contains(receiver)){
            this.event_receivers.remove(receiver);
        }
    }

    public void createNewUser(String name, String email, boolean is_admin, String password) {
        if (!this.currentUser.isAdmin()){
            // error: current user not an admin
        }

        if (User.userAlreadyPresent(email)) {
            // error: user already present
            return;
        }

        this.currentUser = new User(name, email, is_admin);

        this.notifyUserCreated(currentUser, password);
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public User doLogin(String email, String password) {
        password = GlobalManager.getInstance().getPasswordUtils().hashPassword(password);
        this.currentUser = User.userLogIn(email, password);
        
        this.notifyUserLoggedIn(this.currentUser);
        return this.currentUser;
    }

    private void notifyUserCreated(User user, String password) {
        for (UserEventReceiverI iter : this.event_receivers){
            iter.updateUserCreated(user, password);
        }
    }

    private void notifyUserLoggedIn(User user) {
        for (UserEventReceiverI iter : this.event_receivers) {
            iter.updateUserLoggedIn(user, new Date());
        }
    }
}
