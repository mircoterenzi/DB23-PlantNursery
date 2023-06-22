package it.unibo.nursery.model.impl;

import java.util.ArrayList;
import java.util.List;
import it.unibo.nursery.model.api.ResultTable;

public class ResultTableImpl implements ResultTable {
    private List<String> columnTitles;
    private List<List<String>> rows;

    public ResultTableImpl(List<String> title) {
        columnTitles = new ArrayList<>(title);
        rows = new ArrayList<>();
    }

    @Override
    public void addLine(List<String> line) {
        rows.add(new ArrayList<>(line));
    }

    @Override
    public String getTableToString() {
        StringBuilder tableBuilder = new StringBuilder();

        // Calculate the variable column widths
        List<Integer> columnWidths = calculateColumnWidths();

        // Append column titles
        appendRow(tableBuilder, columnTitles, columnWidths);

        // Append rows
        for (List<String> row : rows) {
            appendRow(tableBuilder, row, columnWidths);
        }

        return tableBuilder.toString();
    }

    private List<Integer> calculateColumnWidths() {
        List<Integer> columnWidths = new ArrayList<>(columnTitles.size());
        int margin = 2; // Additional margin for each column

        // Initialize column widths with title lengths
        for (String title : columnTitles) {
            columnWidths.add(title.length() + margin);
        }

        // Update column widths based on row data
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                String cell = row.get(i);
                int cellLength = cell.length();
                int currentWidth = columnWidths.get(i);
                if (cellLength + margin > currentWidth) {
                    columnWidths.set(i, cellLength + margin);
                }
            }
        }

        return columnWidths;
    }

    private void appendRow(StringBuilder tableBuilder, List<String> row, List<Integer> columnWidths) {
        for (int i = 0; i < row.size(); i++) {
            String cell = row.get(i);
            int cellLength = cell.length();
            int columnWidth = columnWidths.get(i);
            int padding = columnWidth - cellLength;
            tableBuilder.append(cell);
            if (padding >= 4){
                tableBuilder.append("\t");
                padding =padding-4;
            }
            for (int j = 0; j < padding; j++) {
                tableBuilder.append(" ");
            }
            tableBuilder.append("\t");
        }
        tableBuilder.append("\n");
    }

}
