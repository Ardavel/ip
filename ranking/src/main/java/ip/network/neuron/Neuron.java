package ip.network.neuron;

import ip.network.strategy.NeuronStrategy;

/**
 *
 * @author Wojciech Szałapski
 */
public class Neuron extends AbstractNeuron {

    public Neuron(NeuronStrategy strategy) {
        this.strategy = strategy;
    }
}
