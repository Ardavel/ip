/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.network.output;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PiotrGrzelak
 */
public class ClassificationMatrixGenerator {
    
    public int[][] generateClassificationMatrix(List<double[]> expectedOutputs, List<double[]> networkOutputs, int classesNum) {
        int[][] matrix = new int[classesNum][classesNum];
        for (int i = 0; i < expectedOutputs.size(); ++i) {
            int expectedClassIdx = transformOutputVectorToClassIdx(expectedOutputs.get(i));
            int calculatedClassIdx = transformOutputVectorToClassIdx(networkOutputs.get(i));
            
            matrix[expectedClassIdx][calculatedClassIdx]++;
        }
        return matrix;
    }
    
    public void saveMatrixToFile(String fileName, int[][] matrix) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (int[] row : matrix) {
                StringBuilder builder = new StringBuilder();
                for (int cell : row) {
                    builder.append(cell).append(" ");
                }
                writer.println(builder.toString());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClassificationMatrixGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int transformOutputVectorToClassIdx(double[] vector) {
        int max = 0;
        for (int i = 0; i < vector.length; ++i) {
            if (vector[max] < vector[i]) {
                max = i;
            }
        }
        
        return max;
    }
}
