package ip.scoring;

import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class NormalDistribution {

    private double mean = 0;
    
    private double standardDeviationFactor = 0;
    
    private double standardDeviation = 0;
    
    private int samples = 0;
    
    public void processSample(double sample) {
        ++samples;
        double oldMean = mean;
        mean += (sample - mean) / samples;
        standardDeviationFactor += (sample - oldMean) * (sample - mean);
        standardDeviation = Math.sqrt(standardDeviationFactor / samples);
    }
    
    public void initializeFromScratch(List<Double> samplesList) {
        mean = 0;
        samples = samplesList.size();
        for (Double sample : samplesList) {
            mean += sample;
        }
        mean /= samples;
        
        standardDeviation = 0;
        for (Double sample : samplesList) {
            standardDeviation += Math.pow(sample - mean, 2);
        }
        standardDeviation /= samples;
        
        standardDeviationFactor = standardDeviation * standardDeviation * samples;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStandardDeviationFactor() {
        return standardDeviationFactor;
    }

    public void setStandardDeviationFactor(double standardDeviationFactor) {
        this.standardDeviationFactor = standardDeviationFactor;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public int getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }
}
