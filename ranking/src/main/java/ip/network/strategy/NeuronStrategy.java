package ip.network.strategy;

import ip.network.neuron.AbstractNeuron;
import ip.network.neuron.NeuronInput;
import java.util.Collection;

/**
 *
 * @author Wojciech Szałapski
 */
public interface NeuronStrategy {

    public double transfer(double netValue);

    public double calculateNetValue(Collection<NeuronInput> inputNeurons, double bias);

    public void updateBias(AbstractNeuron neuron, double delta, double momentumFactor);

    public void updateWeights(AbstractNeuron neuron, double delta, double momentumFactor);

    public void updateDelta(AbstractNeuron neuron, Double expectedOutput, double learningRate);
}
