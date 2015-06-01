package ip.network.input;

import ip.entities.Architecture;
import ip.entities.Run;
import ip.facades.RunJpaController;
import ip.network.normalization.InputNormalizer;
import ip.network.normalization.MinMaxNormalizer;
import ip.run.RunHandler;
import ip.scoring.NormalDistribution;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class DatabaseInputProvider implements InputProvider {

    private static final int INPUTS_NUM = 4;

    private static final int OUTPUTS_NUM = 1;

    private RunJpaController runJpaController;

    private int nextRow = 0;

    private List<InputRow> dataset;

    private RunHandler handler = new RunHandler();
    
    private InputNormalizer normalizer = new MinMaxNormalizer();

    public DatabaseInputProvider() {
        List<Run> runs = runJpaController.findRunEntities();
        dataset = convertToInputRows(runs);
    }

    private List<InputRow> convertToInputRows(List<Run> runs) {
        List<InputRow> rows = new ArrayList<>(runs.size());
        for (Run run : runs) {
            Architecture arch = run.getArchitecture();
            NormalDistribution distribution = new NormalDistribution(arch.getMeanImap(), arch.getSdFactorImap(), arch.getRuns());
            double[] values = new double[INPUTS_NUM];
            double[] expectedOutput = new double[OUTPUTS_NUM];
            values[0] = arch.getVehicle().getMass();
            values[1] = arch.getFuel().getRatio();
            values[2] = arch.getFuel().getDensity();
            values[3] = run.getAvgImap();

            expectedOutput[0] = distribution.mapPosition(run.getAvgImap());

            InputRow row = new InputRow(values, expectedOutput);
            rows.add(row);
        }
        return rows;
    }

    @Override
    public boolean hasMoreRows() {
        return nextRow < dataset.size();
    }

    @Override
    public InputRow provideInputRow() {
        normalizer.normalizeInput(dataset.get(nextRow));
        return dataset.get(nextRow++);
    }

    @Override
    public List<InputRow> provideAllRows() {
        List<InputRow> result = new ArrayList<>();

        while (nextRow < dataset.size()) {
            result.add(provideInputRow());
        }

        return result;
    }
}
