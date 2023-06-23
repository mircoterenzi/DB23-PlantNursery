package it.unibo.nursery.view.impl;

import java.io.IOException;
import java.sql.Connection;

import it.unibo.nursery.view.api.AppView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FxAppView implements AppView {

    private Stage stage;

    public FxAppView(Stage stage) {
        stage.setTitle("Plant Nursery Manager");
        stage.getIcons().add(new Image(ClassLoader.getSystemResource("images/icon.png").toString()));
        this.stage = stage;
        this.setLoginScene();
        stage.show();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void setLoginScene() {
        try {
            final var loader = new FXMLLoader(ClassLoader.getSystemResource("layouts/Login.fxml"));
            loader.setController(new LoginController(this));
            Parent root = loader.load();
            final Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void setApplicationScene(Connection connection) {
        try {
            final var loader = new FXMLLoader(ClassLoader.getSystemResource("layouts/Application.fxml"));
            loader.setController(new ApplicationController(connection));
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
