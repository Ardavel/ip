package ip.network.normalization;

import ip.network.input.InputRow;

/**
 *
 * @author Wojciech Szałapski
 */
public class MinMaxNormalizer implements InputNormalizer {

    private final static int AVERAGE_IMAP = 0;
    
    private final static int FUEL_RATIO = 1;
    
    private final static int FUEL_DENSITY = 2;
    
    private final static int VEHICLE_MASS = 3;
    
    private final static int ENGINE_POWER = 4;
    
    private final static int ENGINE_VOLUME = 5;
    
    private final double[] min = new double[]{250, 5, 0.4, 500, 20, 500};
    
    private final double[] max = new double[]{3000, 20, 0.8, 10000, 1500, 5000};

    private final double[] span;
    
    public MinMaxNormalizer() {
        span = new double[min.length];
        for (int i = 0; i < span.length; ++i) {
            span[i] = max[i] - min[i];
        }
    }
    
    @Override
    public void normalizeInput(InputRow input) {
        double[] values = input.getValues();
        
        for (int i = 0; i < values.length; ++i) {
            values[i] = (values[i] - min[i]) / span[i];
        }
    }
}
