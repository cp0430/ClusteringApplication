import java.io.BufferedReader;
import java.io.StringReader;
import javax.swing.*;
import weka.core.Attribute;
import weka.core.Instances;

public class HelperUtilities {

    public static Instances getInstancesFromTextArea(String text) throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(text));
        Instances data = new Instances(reader);
        reader.close();
        return data;
    }

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void showError(String message, JTextArea txtData) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        txtData.append("\n" + message);
    }

    public static void displaySummaryStatistics(Instances data, JTextArea txtSummary) {
        txtSummary.setText("Summary Statistics:\n\n");
        txtSummary.append("Number of attributes: " + data.numAttributes() + "\n");
        txtSummary.append("Number of instances: " + data.numInstances() + "\n");

        // display detailed statistics for each attribute
        for (int i = 0; i < data.numAttributes(); i++) {
            Attribute attribute = data.attribute(i);
            txtSummary.append("\nAttribute " + (i + 1) + ": " + attribute.name() + "\n");
            txtSummary.append("Type: " + Attribute.typeToString(attribute.type()) + "\n");
            if (attribute.isNumeric()) {
                txtSummary.append("Mean: " + data.meanOrMode(i) + "\n");
                txtSummary.append("StdDev: " + Math.sqrt(data.variance(i)) + "\n");
            }
        }
    }
}
