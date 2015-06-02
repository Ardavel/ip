package ip.network.factory;

import ip.network.AbstractNetwork;
import ip.network.MultiLayerNetwork;
import ip.network.exceptions.CannotCreateNetworkException;
import ip.network.layer.NeuronLayer;
import ip.network.neuron.AbstractNeuron;
import ip.network.neuron.Neuron;
import ip.network.neuron.NeuronInput;
import ip.network.strategy.NeuronStrategy;
import ip.network.strategy.bp.BiasStrategyDecorator;
import java.util.Random;

/**
 *
 * @author Wojciech Szałapski
 */
public class MultiLayerNetworkFactory implements NetworkFactory {

    private final int[] neuronsNumberPerLayer;

    private final boolean useBias;

    private NeuronStrategy strategy;

    Random generator = new Random();

    public MultiLayerNetworkFactory(int[] neuronsNumberPerLayer, NeuronStrategy strategy, boolean useBias) {
        this.neuronsNumberPerLayer = neuronsNumberPerLayer;
        this.useBias = useBias;

        if (useBias) {
            strategy = new BiasStrategyDecorator(strategy);
        }
        this.strategy = strategy;
    }

    @Override
    public MultiLayerNetwork createNetwork() throws CannotCreateNetworkException {
        MultiLayerNetwork network = new MultiLayerNetwork();

        NeuronLayer inputLayer = createLayerWithNeurons(neuronsNumberPerLayer[0]);
        network.setInputLayer(inputLayer);

        int numberOfLayers = neuronsNumberPerLayer.length;
        for (int i = 1; i < numberOfLayers - 1; ++i) {
            network.addHiddenLayer(createLayerWithNeurons(neuronsNumberPerLayer[i]));
        }

        NeuronLayer outputLayer = createLayerWithNeurons(neuronsNumberPerLayer[numberOfLayers - 1]);
        network.setOutputLayer(outputLayer);

        network.connectAllLayers();
        
        generateStartingWeights(network);

        return network;
    }

    private NeuronLayer createLayerWithNeurons(int numberOfNeurons) {
        NeuronLayer layer = new NeuronLayer();
        for (int i = 0; i < numberOfNeurons; ++i) {
            AbstractNeuron neuron = new Neuron(strategy);
            if (useBias) {
                neuron.setBias(generator.nextDouble());
                neuron.setPreviousBias(neuron.getBias());
            }
            layer.addNeuron(neuron);
        }
        return layer;
    }

    private void generateStartingWeights(AbstractNetwork network) {
        Random random = new Random();
        generateStartingWeightsForLayer(network.getInputLayer(), random);

        network.getHiddenLayers().stream()
                .forEach((NeuronLayer hiddenLayer) -> generateStartingWeightsForLayer(hiddenLayer, random));

        generateStartingWeightsForLayer(network.getOutputLayer(), random);
    }

    private void generateStartingWeightsForLayer(NeuronLayer layer, Random random) {
        for (AbstractNeuron neuron : layer.getNeurons()) {
            neuron.getInputNeurons().stream()
                    .forEach((NeuronInput input) -> input.setWeight(random.nextDouble() - 0.5));
            neuron.setBias(random.nextDouble() - 0.5);
        }
    }

    public NeuronStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(NeuronStrategy strategy) {
        this.strategy = strategy;
    }
}
