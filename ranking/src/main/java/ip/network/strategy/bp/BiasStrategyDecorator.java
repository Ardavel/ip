/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.network.strategy.bp;

import ip.network.neuron.AbstractNeuron;
import ip.network.neuron.NeuronInput;
import ip.network.strategy.NeuronStrategy;
import java.util.Collection;

/**
 *
 * @author PiotrGrzelak
 */
public class BiasStrategyDecorator implements NeuronStrategy {

    private NeuronStrategy decorated;

    public BiasStrategyDecorator(NeuronStrategy strategy) {
        decorated = strategy;
    }

    @Override
    public void updateBias(AbstractNeuron neuron, double delta, double momentumFactor) {
        neuron.setBias(neuron.getBias() + delta
                + momentumFactor * (neuron.getBias() - neuron.getPreviousBias()));
    }

    @Override
    public double transfer(double netValue) {
        return decorated.transfer(netValue);
    }

    @Override
    public void updateDelta(AbstractNeuron neuron, Double expectedOutput, double learningRate) {
        decorated.updateDelta(neuron, expectedOutput, learningRate);
    }

    @Override
    public double calculateNetValue(Collection<NeuronInput> inputNeurons, double bias) {
        return decorated.calculateNetValue(inputNeurons, bias);
    }

    @Override
    public void updateWeights(AbstractNeuron neuron, double delta, double momentumFactor) {
        decorated.updateWeights(neuron, delta, momentumFactor);
    }
}
