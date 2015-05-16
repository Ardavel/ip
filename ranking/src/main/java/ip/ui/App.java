/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.ui;

import ip.obd.OBDData;
import ip.obd.OBDDataParser;
import ip.rest.OBDDataRestfulClient;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Wojciech Szałapski
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<OBDData> inputs = null;
        try {
            OBDDataRestfulClient obdDataRestfulClient = new OBDDataRestfulClient();
            JSONObject OBDJSONData = new JSONObject(obdDataRestfulClient.getRESTResponse(1431445243000L, 1431446143000L));
            inputs = OBDDataParser.parseOBDData(OBDJSONData);
        } catch (JSONException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (OBDData data : inputs) {
            double IMAP = OBDDataParser.calculateIMAP(data.getRPM(), data.getMAP(), data.getIAT());
            //DATA BELOW IS FOR: "The VE of my 1999 7.4L Chevy Suburban is about 65%."
            //Volumetric efficiency [percent]
            double VE = 0.65;
            //Engine displacement [liters]
            double ED = 7.4;
            double MAF = OBDDataParser.calculateMAF(IMAP, VE, ED);
            System.out.println("RPM = " + data.getRPM());
            System.out.println("IMAP = " + IMAP);
            System.out.println("MAF = " + MAF);
        }
        
        
        ApproximationDialog approximationDialog = new ApproximationDialog(null, true);
        WindowUtils.centerWindow(approximationDialog);
        approximationDialog.setVisible(true);

    }

}
