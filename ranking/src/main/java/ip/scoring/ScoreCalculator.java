package ip.scoring;

/**
 *
 * @author Wojciech Szałapski
 */
public class ScoreCalculator {

    private final static double THRESHOLD = 0.2;
    
    private final static double SCALING_FACTOR = 17.2;
    
    private final static double EXPONENT = 2.4;
    
    public double calculatePoints(double position) {
        if (position <= THRESHOLD) {
            return 0;
        } else {
            return SCALING_FACTOR * (Math.exp(EXPONENT * (position - THRESHOLD)) - 1);
        }
    }
}
