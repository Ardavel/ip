package ip.obd;

/**
 *
 * @author Lukasz Cyran
 */
public class OBDData {

    private double RPM;
    private int fuelType;
    private int MAF;
    private int MAP;
    private int IAT;

    public OBDData(double RPM, int fuelType, int MAF, int MAP, int IAT) {
        this.RPM = RPM;
        this.fuelType = fuelType;
        this.MAF = MAF;
        this.MAP = MAP;
        this.IAT = IAT;
    }

    public double getRPM() {
        return RPM;
    }

    public void setRPM(double RPM) {
        this.RPM = RPM;
    }

    public int getFuelType() {
        return fuelType;
    }

    public void setFuelType(int fuelType) {
        this.fuelType = fuelType;
    }

    public int getMAF() {
        return MAF;
    }

    public void setMAF(int MAF) {
        this.MAF = MAF;
    }

    public int getMAP() {
        return MAP;
    }

    public void setMAP(int MAP) {
        this.MAP = MAP;
    }

    public int getIAT() {
        return IAT;
    }

    public void setIAT(int IAT) {
        this.IAT = IAT;
    }

}
