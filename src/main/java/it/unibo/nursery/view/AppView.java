package it.unibo.nursery.view;

import java.sql.Connection;

/** Interface that models a scene and window manager for the application */
public interface AppView {

    /** Set the Login scene */
    void setLoginScene();

    /** Set the main application scene on stage and connect it to its controller */
    void setApplicationScene();

    /** Opens a new pop-up stage for the document manager scene and links it to its controller */
    void openDocumentScene();

    /** Add a connection to a DB */
    void addConnection(Connection connection);
}
