package it.unibo.nursery.view;

import java.io.IOException;
import java.sql.Connection;

import it.unibo.nursery.model.Features;
import it.unibo.nursery.model.FeaturesImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FxAppView implements AppView {

    private Stage stage;
    private Features features;

    public FxAppView(Stage stage) {
        stage.setTitle("Plant Nursery Manager");
        stage.getIcons().add(new Image(ClassLoader.getSystemResource("images/icon.png").toString()));
        this.stage = stage;
        this.setLoginScene();
        stage.show();
    }

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

    @Override
    public void setApplicationScene() {
        try {
            final var loader = new FXMLLoader(ClassLoader.getSystemResource("layouts/Application.fxml"));
            loader.setController(new ApplicationController(this, features));
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openDocumentScene() {
        try {
            final var loader = new FXMLLoader(ClassLoader.getSystemResource("layouts/Document.fxml"));
            loader.setController(new DocumentController(features));
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            final Stage popUp = new Stage();
            popUp.setAlwaysOnTop(true);
            popUp.setScene(scene);
            popUp.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addConnection(Connection connection) {
        features = new FeaturesImpl(connection);
    }
    
}
