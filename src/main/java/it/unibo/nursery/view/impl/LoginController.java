package it.unibo.nursery.view.impl;

import java.sql.Connection;

import it.unibo.nursery.db.ConnectionProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    private static final String DB_NAME = "plantnursery";

    @FXML private Text errorMessage;
    @FXML private Button loginButton;
    @FXML private TextField password;
    @FXML private TextField username;

    private FxAppView view;

    public LoginController(FxAppView view) {
        this.view = view;
    }

    @FXML
    void loginButtonOnClick(ActionEvent event) {
        try {
            ConnectionProvider prov = new ConnectionProvider( username.getText(), password.getText(), DB_NAME);
            Connection connection = prov.getMySQLConnection();
            view.setApplicationScene(connection);
        } catch (Exception exception) {
            errorMessage.setOpacity(100);
        }
    }

    @FXML
    void initialize() {
        errorMessage.setOpacity(0);
    }

}
