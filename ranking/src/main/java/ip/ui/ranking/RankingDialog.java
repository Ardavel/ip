package ip.ui.ranking;

import ip.facades.DriverJpaController;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Wojciech Szałapski
 */
public class RankingDialog extends javax.swing.JDialog {

    private final DriverJpaController driverFacade = new DriverJpaController();

    public RankingDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setTitle("Ranking");

        JScrollPane ecoDrivingScrollPane = new JScrollPane();
        JPanel ecoDrivingPanel = new JPanel();
        ecoDrivingPanel.setLayout(new BorderLayout());
        JTable ecoRankingTable = new JTable(new RankingTableModel(driverFacade.findDriverEntities(), true));
        ecoDrivingPanel.add(ecoRankingTable);
        ecoDrivingScrollPane.setViewportView(ecoDrivingPanel);

        JScrollPane safeDrivingScrollPane = new JScrollPane();
        JPanel safeDrivingPanel = new JPanel();
        safeDrivingPanel.setLayout(new BorderLayout());
        JTable safeRankingTable = new JTable(new RankingTableModel(driverFacade.findDriverEntities(), false));
        safeDrivingPanel.add(safeRankingTable);
        safeDrivingScrollPane.setViewportView(safeDrivingPanel);

        rankingTabbedPane.add("Eco driving", ecoDrivingScrollPane);
        rankingTabbedPane.add("Safe driving", safeDrivingScrollPane);

        pack();
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rankingTabbedPane = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rankingTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rankingTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane rankingTabbedPane;
    // End of variables declaration//GEN-END:variables
}
