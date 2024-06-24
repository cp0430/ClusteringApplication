import javax.swing.*;
import weka.core.Instances;
import weka.gui.visualize.VisualizePanel;
import java.awt.*;

public class DataVisualization {

    public static void visualizeData(JTextArea txtData, JProgressBar progressBar) {
        progressBar.setValue(0);
        try {
            Instances data = HelperUtilities.getInstancesFromTextArea(txtData.getText());

            // create a plot for visualization
            VisualizePanel vp = new VisualizePanel();
            vp.setName(data.relationName());
            vp.setInstances(data);
            JFrame plotFrame = new JFrame("Data Visualization");
            plotFrame.setSize(800, 600);
            plotFrame.getContentPane().setLayout(new BorderLayout());
            plotFrame.getContentPane().add(vp, BorderLayout.CENTER);
            plotFrame.setVisible(true);
        } catch (Exception ex) {
            HelperUtilities.showError("Error visualizing data: " + ex.getMessage(), txtData);
        }
        progressBar.setValue(100);
    }
}
