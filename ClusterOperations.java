import javax.swing.*;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

public class ClusterOperations {

    public static void clusterData(JTextArea txtData, JTextField txtNumClusters, JTextArea txtSummary, JProgressBar progressBar) {
        progressBar.setValue(0);
        try {
            int numClusters = Integer.parseInt(txtNumClusters.getText());
            SimpleKMeans kMeans = new SimpleKMeans();
            kMeans.setNumClusters(numClusters);
            Instances data = HelperUtilities.getInstancesFromTextArea(txtData.getText());
            kMeans.buildClusterer(data);

            // evaluate the clusterer and display results
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(kMeans);
            eval.evaluateClusterer(data);
            txtData.setText(eval.clusterResultsToString());
            txtSummary.setText("Clustering completed successfully.");
        } catch (Exception ex) {
            HelperUtilities.showError("Error clustering data: " + ex.getMessage(), txtData);
        }
        progressBar.setValue(100);
    }
}
