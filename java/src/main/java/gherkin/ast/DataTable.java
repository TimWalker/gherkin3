package gherkin.ast;

import java.util.List;

public class DataTable implements StepArgument, HasRows {
    private final String type = getClass().getSimpleName();
    private final List<TableRow> rows;

    public DataTable(List<TableRow> rows) {
        this.rows = rows;
    }

    @Override
    public List<TableRow> getRows() {
        return rows;
    }
}
