package it.unibo.nursery.view.impl;

import it.unibo.nursery.model.api.LoginLogics;
import it.unibo.nursery.model.impl.LoginLogicsImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    @FXML private Text errorMessage;
    @FXML private Button loginButton;
    @FXML private TextField password;
    @FXML private TextField username;

    private FxAppView view;
    private LoginLogics logics;

    public LoginController(FxAppView view) {
        this.view = view;
        logics = new LoginLogicsImpl();
    }

    @FXML
    void loginButtonOnClick(ActionEvent event) {
        if (logics.checkDatas(username.getText(), password.getText())) {
            view.setApplicationScene();
        } else {
            errorMessage.setOpacity(100);
        }
    }

    @FXML
    void initialize() {
        errorMessage.setOpacity(0);
    }

}
