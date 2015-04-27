/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.ui;

import ip.network.MultiLayerNetwork;
import ip.network.exceptions.CannotCreateNetworkException;
import ip.network.factory.MultiLayerNetworkFactory;
import ip.network.input.ClassificationDataProvider;
import ip.network.input.InputRow;
import ip.network.input.RandomInputProvider;
import ip.network.input.TrainingDataProvider;
import ip.network.neuron.AbstractNeuron;
import ip.network.strategy.bp.BackPropagationStrategy;
import ip.network.strategy.bp.IdentityActivationBPS;
import ip.network.training.ThresholdEpochNetworkTrainer;
import ip.ui.exceptions.EmptyInputFieldException;
import ip.ui.plot.PlotGenerator;
import ip.ui.plot.PlotNamer;
import ip.ui.plot.ResultsPlotData;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author PiotrGrzelak
 */
public class ApproximationDialog extends javax.swing.JDialog {

    private PlotGenerator generator;

    private MultiLayerNetwork network;

    private int hiddenNeurons;

    private int inputNeurons;

    private int outputNeurons;

    /**
     * Creates new form ApproximationDialog
     */
    public ApproximationDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        generator = new PlotGenerator();
        initComponents();

        networkCreationParamsPanel.fixNetworkInputsField(1);
        networkCreationParamsPanel.fixNetworkOutputField(1);
        learningParamsInputPanel.setDefaultLearningRate(0.4);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        headerSeparator = new javax.swing.JSeparator();
        downSeparator = new javax.swing.JSeparator();
        buttonPanel = new javax.swing.JPanel();
        trainNetworkButton = new javax.swing.JButton();
        testNetworkButton = new javax.swing.JButton();
        networkCreationParamsPanel = new ip.ui.NetworkCreationParamsPanel();
        createNetworkPanel = new javax.swing.JPanel();
        createNetworkButton = new javax.swing.JButton();
        networkCreationSeparator = new javax.swing.JSeparator();
        learningParamsInputPanel = new ip.ui.LearningParamsInputPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aproksymacja");

        headerLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        headerLabel.setText("Aproksymacja");
        headerPanel.add(headerLabel);

        trainNetworkButton.setText("Trenuj sieć");
        trainNetworkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainNetworkButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(trainNetworkButton);

        testNetworkButton.setText("Testuj sieć");
        testNetworkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testNetworkButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(testNetworkButton);

        createNetworkButton.setText("Stwórz sieć");
        createNetworkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNetworkButtonActionPerformed(evt);
            }
        });
        createNetworkPanel.add(createNetworkButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(headerSeparator)
            .addComponent(downSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(learningParamsInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(networkCreationParamsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createNetworkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(networkCreationSeparator)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headerSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(networkCreationParamsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createNetworkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(networkCreationSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(learningParamsInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(downSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void trainNetworkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainNetworkButtonActionPerformed
        if (network == null) {
            return;
        }

        try {
            JFileChooser trainingDataFileChooser = new JFileChooser(".");
            int result = trainingDataFileChooser.showOpenDialog(this);

            if (result == JFileChooser.CANCEL_OPTION) {
                return;
            }

            File chosenFile = trainingDataFileChooser.getSelectedFile();

            int maxEpochNum = learningParamsInputPanel.getMaximumEpochNumber();
            double learningRate = learningParamsInputPanel.getLearningRate();
            double momentumFactor = learningParamsInputPanel.getMomentumFactor();
            double error = learningParamsInputPanel.getErrorThreshold();

            TrainingDataProvider provider = new TrainingDataProvider(
                    chosenFile, inputNeurons, outputNeurons, " ");
            List<InputRow> trainingData = provider.provideAllRows();

            ThresholdEpochNetworkTrainer trainer
                    = new ThresholdEpochNetworkTrainer(maxEpochNum, error, learningRate, momentumFactor);
            List<Double> meanSquaredError = trainer.trainNetwork(network, trainingData);

            String plotFileName = new PlotNamer().setBaseName("error").setEpochs(meanSquaredError.size()).setHiddenNeurons(hiddenNeurons)
                    .setLearningRate(learningRate).setMomentumFactor(momentumFactor)
                    .generateName();

            generator.generateErrorChart(meanSquaredError, plotFileName);

            List<double[]> networkResults = new ArrayList<>(trainingData.size());
            trainingData.stream().forEach(
                    (InputRow row) -> networkResults.add(network.runNetwork(row.getValues()))
            );

            ResultsPlotData resultsPlotData = new ResultsPlotData();
            resultsPlotData.setInputs(trainingData);
            resultsPlotData.setOutputs(networkResults);
            resultsPlotData.setPlotName("Approximation");

            plotFileName = new PlotNamer().setBaseName("trainingResult")
                    .setEpochs(meanSquaredError.size())
                    .setHiddenNeurons(hiddenNeurons)
                    .setLearningRate(learningRate)
                    .setMomentumFactor(momentumFactor)
                    .generateName();

            generator.generateResultsChart(resultsPlotData, plotFileName);

            ResultsDialog results = new ResultsDialog((Frame) this.getParent(), trainingData, networkResults);
            results.setVisible(true);
        } catch (EmptyInputFieldException | IOException ex) {
            Logger.getLogger(ClassificationDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_trainNetworkButtonActionPerformed

    private void testNetworkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testNetworkButtonActionPerformed
        try {
            if (network == null) {
                return;
            }

            JFileChooser trainingDataFileChooser = new JFileChooser(".");
            int result = trainingDataFileChooser.showOpenDialog(this);

            if (result == JFileChooser.CANCEL_OPTION) {
                return;
            }

            File chosenFile = trainingDataFileChooser.getSelectedFile();

            TrainingDataProvider provider = new TrainingDataProvider(
                    chosenFile, inputNeurons, outputNeurons, " ");
            List<InputRow> trainingData = provider.provideAllRows();

            List<double[]> networkResults = new ArrayList<>(trainingData.size());
            trainingData.stream().forEach(
                    (InputRow row) -> networkResults.add(network.runNetwork(row.getValues()))
            );

            ResultsPlotData resultsPlotData = new ResultsPlotData();
            resultsPlotData.setInputs(trainingData);
            resultsPlotData.setOutputs(networkResults);
            resultsPlotData.setxAxisLabel("arguments");
            resultsPlotData.setyAxisLabel("function values");
            resultsPlotData.setPlotName("Approximation");

            generator.generateResultsChart(resultsPlotData);

            ResultsDialog results = new ResultsDialog((Frame) this.getParent(), trainingData, networkResults);
            results.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ApproximationDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_testNetworkButtonActionPerformed

    private void createNetworkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNetworkButtonActionPerformed
        try {
            inputNeurons = networkCreationParamsPanel.getNetworkInputsNum();
            outputNeurons = networkCreationParamsPanel.getNetworkOutputsNum();
            hiddenNeurons = networkCreationParamsPanel.getHiddenNeuronsNum();

            BackPropagationStrategy strategy = BackPropagationStrategy.getInstance();
            IdentityActivationBPS identityStrategy = IdentityActivationBPS.getInstance();
            MultiLayerNetworkFactory factory = new MultiLayerNetworkFactory(
                    new int[]{inputNeurons, hiddenNeurons, outputNeurons}, strategy, true);
            network = factory.createNetwork();
            network.getOutputLayer().getNeurons().stream().forEach((AbstractNeuron n) -> (n.setStrategy(identityStrategy)));

            JOptionPane.showMessageDialog(this, "Tworzenie sieci zakończone sukcesem", "Sukces",
                    JOptionPane.INFORMATION_MESSAGE);

            trainNetworkButton.setEnabled(true);
            testNetworkButton.setEnabled(true);
        } catch (EmptyInputFieldException | CannotCreateNetworkException ex) {
            Logger.getLogger(TransformationDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
        new RandomInputProvider(100);
    }//GEN-LAST:event_createNetworkButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton createNetworkButton;
    private javax.swing.JPanel createNetworkPanel;
    private javax.swing.JSeparator downSeparator;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JSeparator headerSeparator;
    private ip.ui.LearningParamsInputPanel learningParamsInputPanel;
    private ip.ui.NetworkCreationParamsPanel networkCreationParamsPanel;
    private javax.swing.JSeparator networkCreationSeparator;
    private javax.swing.JButton testNetworkButton;
    private javax.swing.JButton trainNetworkButton;
    // End of variables declaration//GEN-END:variables
}