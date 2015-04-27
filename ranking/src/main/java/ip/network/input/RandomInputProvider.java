/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.network.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Lukasz Cyran
 */
public class RandomInputProvider implements InputProvider {

    protected List<InputRow> dataset;

    int nextRow = 0;
    
    private int inputSize = 14;

    Random random = new Random();

    protected RandomInputProvider() {
    }

    public RandomInputProvider(int dataSize) {
        dataset = new ArrayList<>(generateRandomInput(dataSize));
    }

    private List<InputRow> generateRandomInput(int dataSize) {
        List<InputRow> randomDataset = new ArrayList<>();
        Double[] randomInputs = new Double[inputSize];
        double[] randomOutputs = new double[1];
        for (int i = 0; i < dataSize; i++) {
            //Gwałtowne hamowania i przyspieszenia na minutę (0-10)
            for(int j=0; j<2; j++){
                randomInputs[j] = 1d * random.nextInt(11);
                System.out.println(j + " " + randomInputs[j]);
            }
            Double[] tempRandomInputs = new Double[inputSize - 2];
            double tempSum = 0;
            //Procentowy czas przebywania w każdym z przedziałów rpm
            for(int j=2; j<inputSize; j++){
                tempRandomInputs[j-2] = random.nextDouble();
                tempSum += tempRandomInputs[j-2];
            }
            for(int j=2; j<inputSize; j++){
                randomInputs[j] = tempRandomInputs[j-2]/tempSum;
            }
            
            Arrays.sort(randomInputs, 3, inputSize, Collections.reverseOrder());
            
            double[] finalRandomInputs = new double[inputSize];
            
            for(int j=0; j<inputSize; j++){
                finalRandomInputs[j] = randomInputs[j].doubleValue();
                System.out.println(j + " " + finalRandomInputs[j]);
            }
            
            randomOutputs[0] = Double.NaN;
            randomDataset.add(new InputRow(finalRandomInputs, randomOutputs));
        }
        return randomDataset;
    }

    @Override
    public boolean hasMoreRows() {
        return (nextRow < dataset.size());
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
