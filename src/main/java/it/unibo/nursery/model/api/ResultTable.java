package it.unibo.nursery.model.api;

import java.util.List;

public interface ResultTable {
    
    /**
     * Add a column of datas to the table.
     */
    public void addLine(List<String> line);

    /**
     * Returns a table in string format.
     */
    public String getTableToString();

}
