package ip.obd;

import java.util.Date;

/**
 *
 * @author Wojciech Szałapski
 */
public class SummarizedRun {
    
    private double averageIMAP;
    
    private double safetyPenaltyPerKm;
    
    private Date endingTime;

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

    public Date getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }
}
