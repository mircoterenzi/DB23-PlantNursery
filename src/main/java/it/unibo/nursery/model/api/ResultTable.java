package it.unibo.nursery.model.api;

import java.util.List;

public interface ResultTable {
    
    /**
     * Add a column of datas to the table.
     */
    public void addColumn(String title, List<String> data);

    /**
     * Returns a table in string format.
     */
    public String getTableToString();

}
