package ip.run;

import ip.entities.Driver;
import ip.obd.SummarizedRun;

/**
 *
 * @author Wojciech Szałapski
 */
public class RunHandler {

    public void handleRun(SummarizedRun run, Driver driver) {
        // give points to driver and persist run in database
        // user convertToRun method in SummarizedRun and don't forget to assign 
        // driver to newly created Run
    }
}
