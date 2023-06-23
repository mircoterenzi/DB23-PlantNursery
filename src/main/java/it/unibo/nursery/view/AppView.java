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
    public void setApplicationScene(Connection connection);
}
