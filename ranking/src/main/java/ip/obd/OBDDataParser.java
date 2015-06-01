/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.obd;

import ip.ui.App;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Lukasz Cyran
 */
public class OBDDataParser {

    public static List<OBDData> parseOBDData(JSONObject OBDJSONData) {
        List<OBDData> inputs = new ArrayList<>();
        try {
            JSONArray OBDJSONRows = OBDJSONData.getJSONArray("rows");
            for (int i = 0; i < OBDJSONRows.length(); i++) {
                JSONObject row = OBDJSONRows.getJSONObject(i);

                Double RPM = Double.parseDouble(row.getString("MDI_OBD_RPM"));
//                String FuelType = row.getString("MDI_OBD_PID_1"); //No data
//                String MAF = row.getString("MDI_OBD_PID_2"); //No data
                byte[] MAPBytes = Base64.decodeBase64(row.getString("MDI_OBD_PID_3"));
                byte[] IATBytes = Base64.decodeBase64(row.getString("MDI_OBD_PID_4"));
                byte[] MAPFullBytes = {0, 0, 0, MAPBytes[0]};
                byte[] IATFullBytes = {0, 0, 0, IATBytes[0]};
                int MAP = ByteBuffer.wrap(MAPFullBytes).getInt();
                int IAT = ByteBuffer.wrap(IATFullBytes).getInt();

                OBDData obdData = new OBDData(RPM, 0, 0, MAP, IAT);
                inputs.add(obdData);

                System.out.println("RPM = " + RPM);
//                System.out.println("Fuel Type = " + FuelType);
//                System.out.println("MAF = " + MAF);
                System.out.println("MAP = " + MAP);
                System.out.println("IAT = " + IAT);
            }
        } catch (JSONException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inputs;
    }

    /**
     * Calculates IMAP
     *
     * @param RPM - Revolutions per minute
     * @param MAP - Manifold absolute pressure [kPa]
     * @param IAT - Intake air temperature [Kelvin]
     * @return IMAP - Intake (?) manifold absolute pressure
     */
    public static double calculateIMAP(double RPM, int MAP, int IAT) {
        return RPM * MAP / IAT;
    }

    /**
     * Calculates MAF
     *
     * @param IMAP - Intake (?) manifold absolute pressure
     * @param VE - Volumetric efficiency
     * @param ED - Engine displacement
     * @return MAF - Mass air-flow [grams/second]
     */
    public static double calculateMAF(double IMAP, double VE, double ED) {
        //Average molecular mass of air
        double MM = 28.97;
        //Gas constant [J/°K/mole]
        double R = 8.314;
        return IMAP / 120 * VE / 100 * ED * MM / R;
    }
}
