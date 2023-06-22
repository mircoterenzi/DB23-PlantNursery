package it.unibo.nursery.model.impl;

import java.util.ArrayList;
import java.util.List;
import it.unibo.nursery.model.api.ResultTable;

public class ResultTableImpl implements ResultTable {

    private List<List<String>> columns;

    public ResultTableImpl() {
        columns = new ArrayList<>();
    }

    @Override
    public void addColumn(String title, List<String> data) {
        final List<String> column = new ArrayList<>();
        column.add(title);
        column.addAll(data);
        columns.add(column);
    }

    @Override
    public String getTableToString() {
        int width = columns.size();
        int lenght = columns.get(0).size();
        String text = "";
        int y = 0;
        while (y < lenght) {
            String row = "";
            int x = 0;
            while (x < width) {
                row += columns.get(x).get(y) + "\t\t";
                x++;
            }
            y++;
            text += row + "\n";
        }
        return text;
    }
}
