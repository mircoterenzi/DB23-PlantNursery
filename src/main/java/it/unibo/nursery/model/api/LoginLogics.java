package it.unibo.nursery.model.api;

public interface LoginLogics {

    /**
     * Checks if the username and password are correct.
     * @param username 
     * @param password
     * @return true if the combination of username and password is correct.
     */
    public boolean checkDatas(String username, String password);
}
