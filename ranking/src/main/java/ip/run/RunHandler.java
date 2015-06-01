package ip.run;

import ip.entities.Driver;
import ip.entities.Run;
import ip.obd.SummarizedRun;
import ip.scoring.NormalDistribution;
import ip.scoring.ScoreCalculator;

/**
 *
 * @author Wojciech Szałapski
 */
public class RunHandler {

    private final ScoreCalculator scoreCalculator = new ScoreCalculator();
    
    public void handleRun(SummarizedRun summarizedRun, Driver driver, NormalDistribution safeDrivingDistribution) {
        safeDrivingDistribution.processSample(summarizedRun.getSafetyPenaltyPerKm());
        double safetyPosition = safeDrivingDistribution.mapPosition(summarizedRun.getSafetyPenaltyPerKm());
        double safetyScore = scoreCalculator.calculatePoints(safetyPosition);
        
        // get driver's architecture and create NormalDistribution from it
        // there is a constructor in NormalDistribution for data available in DB
        // user the same formula as above but with eco distribution and IMAP
        // instead of safety penalty

        Run run = summarizedRun.convertToRun();
        run.setArchitecture(driver.getArchitecture());
        // update driver's eco and safety points in DB
        // persist run in the database
        // in ideal case it should be one transaction but for now it can be in
        // two separate transactions
    }
}
