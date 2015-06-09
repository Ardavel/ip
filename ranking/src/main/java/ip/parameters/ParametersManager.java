package ip.parameters;

import ip.network.MultiLayerNetwork;
import ip.network.neuron.AbstractNeuron;
import ip.network.neuron.NeuronInput;
import ip.scoring.NormalDistribution;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author Wojciech Szałapski
 */
public class ParametersManager {

    private final String FILE_NAME = "parameters.txt";

    private double safeDrivingMean;

    private double safeDrivingSDFactor;

    private int samples;

    private int inputNeuronsNumber;

    private int hiddenNeuronsNumber;

    private double[][] weights;

    private ParametersManager() {
    }

    public void getParametersFromSafetyDistribution(NormalDistribution safetyDistribution) {
        safeDrivingMean = safetyDistribution.getMean();
        safeDrivingSDFactor = safetyDistribution.getStandardDeviationFactor();
        samples = safetyDistribution.getSamples();
    }

    public void setParametersInSafetyDistribution(NormalDistribution safetyDistribution) {
        safetyDistribution.setMean(safeDrivingMean);
        safetyDistribution.setStandardDeviationFactor(safeDrivingSDFactor);
        safetyDistribution.setSamples(samples);
        safetyDistribution.updateStandardDeviation();
    }

    public void getParametersFromNeuralNetwork(MultiLayerNetwork network) {
        List<AbstractNeuron> hiddenNeurons = network.getHiddenLayers().get(0).getNeurons();
        hiddenNeuronsNumber = hiddenNeurons.size();
        inputNeuronsNumber = hiddenNeurons.get(0).getInputNeurons().size();

        weights = new double[hiddenNeuronsNumber + 1][];

        for (int i = 0; i < hiddenNeuronsNumber; ++i) {
            AbstractNeuron neuron = hiddenNeurons.get(i);
            List<NeuronInput> inputNeurons = neuron.getInputNeurons();
            double[] neuronWeights = new double[inputNeuronsNumber + 1];

            for (int j = 0; j < inputNeuronsNumber; ++j) {
                neuronWeights[j] = inputNeurons.get(j).getWeight();
            }
            neuronWeights[inputNeurons.size()] = neuron.getBias();

            weights[i] = neuronWeights;
        }

        double[] outputWeights = new double[hiddenNeuronsNumber + 1];
        AbstractNeuron outputNeuron = network.getOutputLayer().getNeurons().get(0);
        for (int i = 0; i < hiddenNeuronsNumber; ++i) {
            outputWeights[i] = outputNeuron.getInputNeurons().get(i).getWeight();
        }
        outputWeights[hiddenNeuronsNumber] = outputNeuron.getBias();

        weights[hiddenNeuronsNumber] = outputWeights;
    }

    public void setParametersInNeuralNetwork(MultiLayerNetwork network) {
        List<AbstractNeuron> hiddenNeurons = network.getHiddenLayers().get(0).getNeurons();
        for (int i = 0; i < hiddenNeuronsNumber; ++i) {
            List<NeuronInput> inputNeurons = hiddenNeurons.get(i).getInputNeurons();
            for (int j = 0; j < inputNeuronsNumber; ++j) {
                inputNeurons.get(j).setWeight(weights[i][j]);
                inputNeurons.get(j).setPreviousWeight(weights[i][j]);
            }
            hiddenNeurons.get(i).setBias(weights[i][inputNeuronsNumber]);
            hiddenNeurons.get(i).setPreviousBias(weights[i][inputNeuronsNumber]);
        }

        AbstractNeuron outputNeuron = network.getOutputLayer().getNeurons().get(0);
        List<NeuronInput> inputNeurons = outputNeuron.getInputNeurons();
        for (int i = 0; i < hiddenNeuronsNumber; ++i) {
            inputNeurons.get(i).setWeight(weights[hiddenNeuronsNumber][i]);
            inputNeurons.get(i).setPreviousWeight(weights[hiddenNeuronsNumber][i]);
        }
        outputNeuron.setBias(weights[hiddenNeuronsNumber][hiddenNeuronsNumber]);
        outputNeuron.setPreviousBias(weights[hiddenNeuronsNumber][hiddenNeuronsNumber]);
    }

    public void deserializeParameters() throws Exception {
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            safeDrivingMean = Double.parseDouble(scanner.next());
            safeDrivingSDFactor = Double.parseDouble(scanner.next());
            samples = scanner.nextInt();

            inputNeuronsNumber = scanner.nextInt();
            hiddenNeuronsNumber = scanner.nextInt();
            weights = new double[hiddenNeuronsNumber + 1][];

            for (int i = 0; i < hiddenNeuronsNumber; ++i) {
                weights[i] = new double[inputNeuronsNumber + 1];
                for (int j = 0; j <= inputNeuronsNumber; ++j) {
                    weights[i][j] = Double.parseDouble(scanner.next());
                }
            }

            weights[hiddenNeuronsNumber] = new double[hiddenNeuronsNumber + 1];
            for (int i = 0; i <= hiddenNeuronsNumber; ++i) {
                weights[hiddenNeuronsNumber][i] = Double.parseDouble(scanner.next());
            }
        }
    }

    public void serializeParameters() throws IOException {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(safeDrivingMean + " " + safeDrivingSDFactor + " " + samples + "\n");
            writer.write(inputNeuronsNumber + " " + hiddenNeuronsNumber + "\n");
            for (int i = 0; i < weights.length; ++i) {
                for (int j = 0; j < weights[i].length; ++j) {
                    writer.write(weights[i][j] + " ");
                }
                writer.write("\n");
            }
        }
    }

    public static ParametersManager getInstance() {
        return ParametersManagerHolder.INSTANCE;
    }

    private static class ParametersManagerHolder {

        private static final ParametersManager INSTANCE = new ParametersManager();
    }
}
