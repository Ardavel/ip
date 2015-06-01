package ip.network.input;

import ip.entities.Run;
import ip.facades.RunJpaController;
import ip.run.RunHandler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class DatabaseInputProvider implements InputProvider {

    private RunJpaController runJpaController;

    private int nextRow = 0;

    private List<InputRow> dataset;

    private RunHandler handler = new RunHandler();

    public DatabaseInputProvider() {
        List<Run> runs = runJpaController.findRunEntities();
        dataset = convertToInputRows(runs);
    }

    private List<InputRow> convertToInputRows(List<Run> runs) {
        List<InputRow> rows = new ArrayList<>(runs.size());
        for (Run run : runs) {
            //handler.
        }
        return rows;
    }

    @Override
    public boolean hasMoreRows() {
        return nextRow < dataset.size();
    }

    @Override
    public InputRow provideInputRow() {
        return dataset.get(nextRow++);
    }

    @Override
    public List<InputRow> provideAllRows() {
        List<InputRow> result = new ArrayList<>();

        while (nextRow < dataset.size()) {
            result.add(dataset.get(nextRow++));
        }

        return result;
    }
}
