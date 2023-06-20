package it.unibo.nursery.view.impl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    private FxAppView view;

    public LoginController(FxAppView view) {
        this.view = view;
    }

    @FXML
    void loginButtonOnClick(ActionEvent event) {
        //TODO: controllo d'accesso per future implementazioni
        view.setApplicationScene();
    }

}
