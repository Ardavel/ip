package ip.obd;

import ip.entities.Run;
import java.util.Date;

/**
 *
 * @author Wojciech Szałapski
 */
public class SummarizedRun {
    
    private double averageIMAP;
    
    private double safetyPenaltyPerKm;
    
    private double distance;
    
    private Date endingTime;

    public Run convertToRun() {
        Run run = new Run();
        run.setAvgImap(averageIMAP);
        run.setEndingTime(endingTime);
        return run;
    }
    
    public double getAverageIMAP() {
        return averageIMAP;
    }

    public void setAverageIMAP(double averageIMAP) {
        this.averageIMAP = averageIMAP;
    }

    public double getSafetyPenaltyPerKm() {
        return safetyPenaltyPerKm;
    }

    public void setSafetyPenaltyPerKm(double safetyPenaltyPerKm) {
        this.safetyPenaltyPerKm = safetyPenaltyPerKm;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Date getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }
}
