package it.unibo.nursery.logics.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.nursery.logics.api.LoginLogics;
import javafx.util.Pair;

public class LoginLogicsImpl implements LoginLogics {

    private List<Pair<String, String>> combination = new ArrayList<>();

    public LoginLogicsImpl() {
        combination.add(new Pair<String, String>("admin", "admin"));    //at the moment only admin-admin let open the app
    }

    @Override
    public boolean checkDatas(String username, String password) {
        return combination.contains(new Pair<>(username,password));
    }
    
}
