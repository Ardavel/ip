package ip.run;

import ip.entities.Architecture;
import ip.entities.Driver;
import ip.entities.Run;
import ip.facades.ArchitectureJpaController;
import ip.facades.DriverJpaController;
import ip.facades.RunJpaController;
import ip.obd.SummarizedRun;
import ip.scoring.NormalDistribution;
import ip.scoring.ScoreCalculator;
import java.util.logging.Logger;

/**
 *
 * @author Wojciech Szałapski
 */
public class RunHandler {

    private static final Logger logger = Logger.getLogger(RunHandler.class.getName());

    private final ScoreCalculator scoreCalculator = new ScoreCalculator();

    private final RunJpaController runFacade = new RunJpaController();

    private final DriverJpaController driverFacade = new DriverJpaController();

    private final ArchitectureJpaController architectureFacade = new ArchitectureJpaController();

    public void handleRun(SummarizedRun summarizedRun, Driver driver, NormalDistribution safeDrivingDistribution) {
        safeDrivingDistribution.processSample(summarizedRun.getSafetyPenaltyPerKm());
        double safetyPosition = safeDrivingDistribution.mapPosition(summarizedRun.getSafetyPenaltyPerKm());
        int safetyScore = (int) scoreCalculator.calculatePoints(safetyPosition);

        Architecture driverArchitecture = driver.getArchitecture();
        NormalDistribution ecoDrivingDistribution = new NormalDistribution(driverArchitecture.getMeanImap(), driverArchitecture.getSdFactorImap(), driverArchitecture.getRuns());
        ecoDrivingDistribution.processSample(summarizedRun.getAverageIMAP());
        double ecoPosition = ecoDrivingDistribution.mapPosition(summarizedRun.getAverageIMAP());
        int ecoScore = (int) scoreCalculator.calculatePoints(ecoPosition);

        Run run = summarizedRun.convertToRun();
        run.setArchitecture(driverArchitecture);

        runFacade.create(run);

        driver.setEcoPoints(ecoScore);
        driver.setSafePoints(safetyScore);

        try {
            driverFacade.edit(driver);
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }

        driverArchitecture.setMeanImap(ecoDrivingDistribution.getMean());
        driverArchitecture.setSdFactorImap(ecoDrivingDistribution.getStandardDeviationFactor());
        driverArchitecture.setRuns(ecoDrivingDistribution.getSamples());

        try {
            architectureFacade.edit(driverArchitecture);
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }
    }
}
