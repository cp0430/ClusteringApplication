import java.io.*;
import javax.swing.*;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class FileOperations {

    public static void loadARFF(File inputFile, JTextArea txtData, JTextArea txtSummary, JProgressBar progressBar) {
        progressBar.setValue(0);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            Instances data = new Instances(reader);
            reader.close();
            txtData.setText(data.toString());
            HelperUtilities.displaySummaryStatistics(data, txtSummary);
        } catch (FileNotFoundException ex) {
            HelperUtilities.showError("Error: File not found.", txtData);
        } catch (IOException ex) {
            HelperUtilities.showError("Error reading file: " + ex.getMessage(), txtData);
        } catch (Exception ex) {
            HelperUtilities.showError("Error loading ARFF file: " + ex.getMessage(), txtData);
        }
        progressBar.setValue(100);
    }

    public static void saveARFF(File outputFile, JTextArea txtData, JProgressBar progressBar) {
        progressBar.setValue(0);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(txtData.getText());
            writer.close();
            HelperUtilities.showMessage("ARFF file saved successfully.");
        } catch (IOException ex) {
            HelperUtilities.showError("Error saving file: " + ex.getMessage(), txtData);
        } catch (Exception ex) {
            HelperUtilities.showError("Error saving ARFF file: " + ex.getMessage(), txtData);
        }
        progressBar.setValue(100);
    }

    public static void loadCSV(File inputFile, JTextArea txtData, JTextArea txtSummary, JProgressBar progressBar) {
        progressBar.setValue(0);
        try {
            CSVLoader loader = new CSVLoader();
            loader.setSource(inputFile);
            Instances data = loader.getDataSet();
            txtData.setText(data.toString());
            HelperUtilities.displaySummaryStatistics(data, txtSummary);
        } catch (Exception ex) {
            HelperUtilities.showError("Error loading CSV file: " + ex.getMessage(), txtData);
        }
        progressBar.setValue(100);
    }

    public static void saveCSV(File outputFile, JTextArea txtData, JProgressBar progressBar) {
        progressBar.setValue(0);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(txtData.getText());
            writer.close();
            HelperUtilities.showMessage("CSV file saved successfully.");
        } catch (Exception ex) {
            HelperUtilities.showError("Error saving CSV file: " + ex.getMessage(), txtData);
        }
        progressBar.setValue(100);
    }
}
