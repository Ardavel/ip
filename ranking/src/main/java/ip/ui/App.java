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

        MainWindow mainWindow = new MainWindow();
        WindowUtils.centerWindow(mainWindow);
        mainWindow.setVisible(true);

    }
}
