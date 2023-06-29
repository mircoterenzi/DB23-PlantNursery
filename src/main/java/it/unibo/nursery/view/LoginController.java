package it.unibo.nursery.view;

import java.sql.Connection;

import it.unibo.nursery.db.ConnectionProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/** The LoginController class is responsible for handling the title login scene and related actions. */
public class LoginController {

    /** The database name used to login. */
    private static final String DB_NAME = "plantnursery";

    @FXML private Text errorMessage;
    @FXML private Button loginButton;
    @FXML private PasswordField password;
    @FXML private TextField username;

    private final FxAppView view;

    /**
     * Constructor for LoginController class.
     * @param view
     */
    public LoginController(final FxAppView view) {
        this.view = view;
    }

    /**
     * Handles the action event when the login button is clicked.
     * Use the user name and password given by the user and try to connect with the database.
     * In the event of incorrect data, it displays an error message.
     * @param event
     */
    @FXML
    void loginButtonOnClick(final ActionEvent event) {
        try {
            final ConnectionProvider prov = new ConnectionProvider(username.getText(), password.getText(), DB_NAME);
            final Connection connection = prov.getMySQLConnection();
            view.addConnection(connection);
            view.setApplicationScene();
        } catch (IllegalStateException exception) {
            errorMessage.setOpacity(100);
        }
    }

    /** 
     * Method executed when the scene is loaded.
     * Set the opacity of the error message to zero.
     */
    @FXML
    void initialize() {
        errorMessage.setOpacity(0);
    }

}
