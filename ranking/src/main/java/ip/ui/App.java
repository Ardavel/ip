/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Wojciech Szałapski
 */
public class App {

    private final static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        NetworkDialog approximationDialog = new NetworkDialog(null, true);
        WindowUtils.centerWindow(approximationDialog);
        approximationDialog.setVisible(true);

    }

//        List<OBDData> inputs = null;
//        try {
//            OBDDataRestfulClient obdDataRestfulClient = new OBDDataRestfulClient();
//            JSONObject OBDJSONData = new JSONObject(obdDataRestfulClient.getRESTResponse(1431445243000L, 1431446143000L));
//            inputs = OBDDataParser.parseOBDData(OBDJSONData);
//        } catch (JSONException ex) {
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        for (OBDData data : inputs) {
//            double IMAP = OBDDataParser.calculateIMAP(data.getRPM(), data.getMAP(), data.getIAT());
//            //DATA BELOW IS FOR: "The VE of my 1999 7.4L Chevy Suburban is about 65%."
//            //Volumetric efficiency [percent]
//            double VE = 0.65;
//            //Engine displacement [liters]
//            double ED = 7.4;
//            double MAF = OBDDataParser.calculateMAF(IMAP, VE, ED);
//            System.out.println("RPM = " + data.getRPM());
//            System.out.println("IMAP = " + IMAP);
//            System.out.println("MAF = " + MAF);
//        }
}
