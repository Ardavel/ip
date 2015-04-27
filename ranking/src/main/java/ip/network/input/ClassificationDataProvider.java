package ip.network.input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Wojciech Szałapski
 */
public class ClassificationDataProvider extends TrainingDataProvider {

    public ClassificationDataProvider(File inputFile, int outputs, String separator, int[] inputsNumbers) throws IOException {
        Scanner sc = new Scanner(inputFile);

        dataset = new ArrayList<>();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] stringNums = line.split(separator);

            double[] inputValues = new double[inputsNumbers.length];
            for (int i = 0; i < inputsNumbers.length; ++i) {
                inputValues[i] = Double.parseDouble(stringNums[inputsNumbers[i]]);
            }

            double[] outputValues = new double[outputs];
            int group = Integer.parseInt(stringNums[stringNums.length - 1]);
            outputValues[group - 1] = 1;

            dataset.add(new InputRow(inputValues, outputValues));
        }
    }
}
