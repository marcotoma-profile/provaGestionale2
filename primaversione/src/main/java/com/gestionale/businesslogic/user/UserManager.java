package com.gestionale.businesslogic.user;

import java.util.ArrayList;

public class UserManager {
    
    private User currentUser;
    private ArrayList<UserEventReceiver> event_receivers;

    public UserManager() {
        this.event_receivers = new ArrayList<>();
    }

    public void addReceiver(UserEventReceiver receiver) {
        this.event_receivers.add(receiver);
    }

    public void removeReceiver(UserEventReceiver receiver) {
        if (this.event_receivers.contains(receiver)){
            this.event_receivers.remove(receiver);
        }
    }

    public User getCurrentUser() {
        return this.currentUser;
    }
}
