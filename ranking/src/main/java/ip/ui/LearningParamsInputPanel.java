/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.ui;

import ip.ui.exceptions.EmptyInputFieldException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author PiotrGrzelak
 */
public class LearningParamsInputPanel extends javax.swing.JPanel {

    /**
     * Creates new form LearningParamsInputPanel
     */
    public LearningParamsInputPanel() {
        initComponents();
    }

    public void setDefaultLearningRate(double learningRate) {
        learningRateInput.setText(String.valueOf(learningRate));
    }
    
    public void setDefaultMomentum(double momentum) {
        momentumFactorInput.setText(String.valueOf(momentum));
    }
    
    public void setDefaultError(double error) {
        errorThresholdInput.setText(String.valueOf(error));
    }
    
    public void setDefaultEpochNum(double epochNum) {
        maxEpochNumInput.setText(String.valueOf(epochNum));
    }
    
    public double getLearningRate() throws EmptyInputFieldException {
        String learningRateParam = learningRateInput.getText();
        if (StringUtils.isEmpty(learningRateParam)) {
            throw new EmptyInputFieldException(learningRateLabel.getText());
        }

        return Double.parseDouble(learningRateParam);
    }

    public double getMomentumFactor() throws EmptyInputFieldException {
        String momentumFactorParam = momentumFactorInput.getText();
        if (StringUtils.isEmpty(momentumFactorParam)) {
            throw new EmptyInputFieldException(momentumFactorLabel.getText());
        }

        return Double.parseDouble(momentumFactorParam);
    }

    public double getErrorThreshold() throws EmptyInputFieldException {
        String errorThresholdParam = errorThresholdInput.getText();
        if (StringUtils.isEmpty(errorThresholdParam)) {
            throw new EmptyInputFieldException(errorThresholdLabel.getText());
        }

        return Double.parseDouble(errorThresholdParam);
    }

    public int getMaximumEpochNumber() throws EmptyInputFieldException {
        String maxEpochNumParam = maxEpochNumInput.getText();
        if (StringUtils.isEmpty(maxEpochNumParam)) {
            throw new EmptyInputFieldException(maxEpochNumLabel.getText());
        }

        return Integer.parseInt(maxEpochNumParam);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        learningRateLabel = new javax.swing.JLabel();
        momentumFactorLabel = new javax.swing.JLabel();
        maxEpochNumLabel = new javax.swing.JLabel();
        errorThresholdLabel = new javax.swing.JLabel();
        momentumFactorInput = new javax.swing.JTextField();
        learningRateInput = new javax.swing.JTextField();
        maxEpochNumInput = new javax.swing.JTextField();
        errorThresholdInput = new javax.swing.JTextField();

        learningRateLabel.setText("Współczynnik nauki:");

        momentumFactorLabel.setText("Współczynnik momentum:");

        maxEpochNumLabel.setText("Maksymalna liczba epok:");

        errorThresholdLabel.setText("Docelowy błąd:");

        momentumFactorInput.setText("0.8");

        learningRateInput.setText("0.1");

        maxEpochNumInput.setText("1000");

        errorThresholdInput.setText("0.001");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(momentumFactorLabel)
                            .addComponent(learningRateLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(learningRateInput, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(momentumFactorInput)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maxEpochNumLabel)
                            .addComponent(errorThresholdLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maxEpochNumInput, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(errorThresholdInput))))
                .addContainerGap(202, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(learningRateLabel)
                    .addComponent(learningRateInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(momentumFactorLabel)
                    .addComponent(momentumFactorInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxEpochNumLabel)
                    .addComponent(maxEpochNumInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(errorThresholdLabel)
                    .addComponent(errorThresholdInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField errorThresholdInput;
    private javax.swing.JLabel errorThresholdLabel;
    private javax.swing.JTextField learningRateInput;
    private javax.swing.JLabel learningRateLabel;
    private javax.swing.JTextField maxEpochNumInput;
    private javax.swing.JLabel maxEpochNumLabel;
    private javax.swing.JTextField momentumFactorInput;
    private javax.swing.JLabel momentumFactorLabel;
    // End of variables declaration//GEN-END:variables
}
