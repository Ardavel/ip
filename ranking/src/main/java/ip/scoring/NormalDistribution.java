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

    public NormalDistribution() {
    }
    
    public NormalDistribution(double mean, double standardDeviationFactor, int samples) {
        this.mean = mean;
        this.standardDeviationFactor = standardDeviationFactor;
        this.samples = samples;
        
        standardDeviation = Math.sqrt(standardDeviationFactor / samples);
    }
    
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
    
    public double mapPosition(double sample) {
        double minus3Sigma = mean - 3 * standardDeviation;
        double plus3Sigma = mean + 3 * standardDeviation;
        double span = plus3Sigma - minus3Sigma;
        
        return 1 - ((sample - minus3Sigma) / span);
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
