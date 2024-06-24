import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarculaLaf;

public class ClusteringApplication extends JFrame implements ActionListener {
    private JFileChooser fileChooser;
    private File inputFile;
    private File outputFile;
    private JButton btnLoadARFF;
    private JButton btnSaveARFF;
    private JButton btnLoadCSV;
    private JButton btnSaveCSV;
    private JButton btnFilterAttributes;
    private JButton btnClusterData;
    private JButton btnVisualizeData;
    private JTextArea txtData;
    private JTextArea txtSummary;
    private JProgressBar progressBar;
    private JTextField txtNumClusters;

    public ClusteringApplication() {
        setTitle("Clustering Application");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set FlatLaf look and feel
        FlatDarculaLaf.setup();

        // Set custom fonts
        Font customFont = new Font("Arial", Font.PLAIN, 14);

        // create file chooser
        fileChooser = new JFileChooser();

        // create buttons
        btnLoadARFF = createButton("Load ARFF");
        btnSaveARFF = createButton("Save ARFF");
        btnLoadCSV = createButton("Load CSV");
        btnSaveCSV = createButton("Save CSV");
        btnFilterAttributes = createButton("Filter Attributes");
        btnClusterData = createButton("Cluster Data");
        btnVisualizeData = createButton("Visualize Data");

        // add action listeners
        btnLoadARFF.addActionListener(this);
        btnSaveARFF.addActionListener(this);
        btnLoadCSV.addActionListener(this);
        btnSaveCSV.addActionListener(this);
        btnFilterAttributes.addActionListener(this);
        btnClusterData.addActionListener(this);
        btnVisualizeData.addActionListener(this);

        // create text areas
        txtData = new JTextArea();
        txtData.setFont(customFont);
        txtData.setEditable(false);
        txtData.setBorder(new EmptyBorder(10, 10, 10, 10));
        txtSummary = new JTextArea();
        txtSummary.setFont(customFont);
        txtSummary.setEditable(false);
        txtSummary.setBorder(new EmptyBorder(10, 10, 10, 10));

        // create scroll panes for text areas
        JScrollPane scrollPaneData = new JScrollPane(txtData);
        JScrollPane scrollPaneSummary = new JScrollPane(txtSummary);

        // create panel for buttons and inputs using GridBagLayout
        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // add padding

        // add buttons to control panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        controlPanel.add(btnLoadARFF, gbc);

        gbc.gridy++;
        controlPanel.add(btnSaveARFF, gbc);

        gbc.gridy++;
        controlPanel.add(btnLoadCSV, gbc);

        gbc.gridy++;
        controlPanel.add(btnSaveCSV, gbc);

        gbc.gridy++;
        controlPanel.add(btnFilterAttributes, gbc);

        gbc.gridy++;
        controlPanel.add(btnClusterData, gbc);

        gbc.gridy++;
        controlPanel.add(btnVisualizeData, gbc);

        // create cluster number input
        JPanel clusterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblNumClusters = new JLabel("Number of Clusters:");
        lblNumClusters.setFont(customFont);
        txtNumClusters = new JTextField("3", 5);
        txtNumClusters.setFont(customFont);
        clusterPanel.add(lblNumClusters);
        clusterPanel.add(txtNumClusters);

        // add cluster panel to control panel
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        controlPanel.add(clusterPanel, gbc);

        // add components to frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPaneData, scrollPaneSummary);
        splitPane.setDividerLocation(400);
        add(splitPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.WEST);

        // create and add progress bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setFont(customFont);
        progressBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(progressBar, BorderLayout.NORTH);

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(150, 40)); // Set a preferred size for buttons
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLoadARFF) {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                inputFile = fileChooser.getSelectedFile();
                new Thread(() -> FileOperations.loadARFF(inputFile, txtData, txtSummary, progressBar)).start();
            }
        } else if (e.getSource() == btnSaveARFF) {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                outputFile = fileChooser.getSelectedFile();
                new Thread(() -> FileOperations.saveARFF(outputFile, txtData, progressBar)).start();
            }
        } else if (e.getSource() == btnLoadCSV) {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                inputFile = fileChooser.getSelectedFile();
                new Thread(() -> FileOperations.loadCSV(inputFile, txtData, txtSummary, progressBar)).start();
            }
        } else if (e.getSource() == btnSaveCSV) {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                outputFile = fileChooser.getSelectedFile();
                new Thread(() -> FileOperations.saveCSV(outputFile, txtData, progressBar)).start();
            }
        } else if (e.getSource() == btnFilterAttributes) {
            new Thread(() -> DataFilter.filterAttributes(txtData, txtSummary, progressBar)).start();
        } else if (e.getSource() == btnClusterData) {
            new Thread(() -> ClusterOperations.clusterData(txtData, txtNumClusters, txtSummary, progressBar)).start();
        } else if (e.getSource() == btnVisualizeData) {
            new Thread(() -> DataVisualization.visualizeData(txtData, progressBar)).start();
        }
    }

    public static void main(String[] args) {
        new ClusteringApplication();
    }
}
