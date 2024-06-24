import javax.swing.*;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class DataFilter {

    public static void filterAttributes(JTextArea txtData, JTextArea txtSummary, JProgressBar progressBar) {
        progressBar.setValue(0);
        try {
            Instances data = HelperUtilities.getInstancesFromTextArea(txtData.getText());

            // apply Remove filter to remove attributes 1 to 3
            Remove remove = new Remove();
            remove.setAttributeIndices("1-3");
            remove.setInputFormat(data);
            Instances filteredData = Filter.useFilter(data, remove);

            // display filter output
            txtData.setText(filteredData.toString());

            // display filter summary
            String[] options = remove.getOptions();
            txtSummary.setText("Filter summary:\n\n");
            txtSummary.append("Filter type: " + remove.getClass().getSimpleName() + "\n");
            txtSummary.append("Attribute indices to remove: " + options[1] + "\n");
            txtSummary.append("Number of attributes removed: " + (data.numAttributes() - filteredData.numAttributes()) + "\n");
            txtSummary.append("Number of instances remaining: " + filteredData.numInstances() + "\n");
        } catch (Exception ex) {
            HelperUtilities.showError("Error filtering attributes: " + ex.getMessage(), txtData);
        }
        progressBar.setValue(100);
    }
}
