package ip.mock;

import ip.obd.SummarizedRun;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class RandomRunsGenerator {

    private final static double MIN_IMAP = 250;
    
    private final static double MAX_IMAP = 2500;
    
    private final static double IMAP_SPAN = MAX_IMAP - MIN_IMAP;
    
    private final static double MAX_SAFETY_PENALTY_PER_KM = 7.5;
    
    private final static double DISTANCE = 200;
    
    public List<SummarizedRun> generateRandomSummarizedRuns(int runsCount) {
        List<SummarizedRun> result = new ArrayList<>(runsCount);
        
        for (int i = 0; i < runsCount; ++i) {
            SummarizedRun run = new SummarizedRun();
            run.setAverageIMAP(generateAverageIMAP());
            run.setEndingTime(new Date());
            run.setSafetyPenaltyPerKm(generateSafetyPentaltyPerKm());
            run.setDistance(Math.random() * DISTANCE);
            result.add(run);
        }
        
        return result;
    }
    
    private double generateAverageIMAP() {
        return Math.random() * IMAP_SPAN + MIN_IMAP;
    }
    
    private double generateSafetyPentaltyPerKm() {
        return Math.random() * MAX_SAFETY_PENALTY_PER_KM;
    }
}
