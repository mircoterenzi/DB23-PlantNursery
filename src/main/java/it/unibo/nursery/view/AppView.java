package it.unibo.nursery.view;

import java.sql.Connection;

public interface AppView {

    /**
     * Set the Login scene.
     */
    public void setLoginScene();

    /**
     * Set the Application scene.
     */
    public void setApplicationScene();

    /*
     * Opens a pop-up window for the Document scene
     */
    public void openDocumentScene();

    /**
     * Add a connection to a DB.
     */
    public void addConnection(Connection connection);
}
