# Clustering Application

## Overview

The Clustering Application is a Java-based GUI application designed to facilitate data loading, filtering, clustering, and visualization. Utilizing the WEKA machine learning library, this application supports both ARFF and CSV file formats, providing a user-friendly interface for performing clustering analysis.

## Project Structure

This project contains the following Java source files:

1. **ClusteringApplication.java**: The main class containing the GUI setup and event handling for the application.
2. **DataFilter.java**: Contains methods for filtering attributes in the dataset.
3. **DataVisualization.java**: Contains methods for visualizing data using the WEKA VisualizePanel.
4. **FileOperations.java**: Contains methods for loading and saving ARFF and CSV files.
5. **HelperUtilities.java**: Contains utility methods for common tasks such as displaying messages and setting progress.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- WEKA library
- FlatLaf Look and Feel library

### Installation

1. **Clone the repository**:
   ```
   git clone https://github.com/yourusername/ClusteringApplication.git
   cd ClusteringApplication
   ```

2. **Add WEKA and FlatLaf libraries to your project**:
   - Download the WEKA library from the [WEKA website](https://www.cs.waikato.ac.nz/ml/weka/downloading.html).
   - Download the FlatLaf library from the [Maven repository](https://mvnrepository.com/artifact/com.formdev/flatlaf).

3. **Add the libraries to your project build path**:
   - In your IDE, add the WEKA and FlatLaf JAR files to your project build path.

### Running the Application

To run the application, execute the `main` method in the `ClusteringApplication.java` file:

```java
public static void main(String[] args) {
    new Clustering_Application();
}
```

## Features

### 1. Load ARFF/CSV Files
Load data from ARFF or CSV files into the application.

### 2. Save ARFF/CSV Files
Save the processed data back to ARFF or CSV files.

### 3. Filter Attributes
Remove specified attributes from the dataset using the WEKA `Remove` filter.

### 4. Cluster Data
Cluster the data using the WEKA `SimpleKMeans` algorithm. Specify the number of clusters to generate.

### 5. Visualize Data
Visualize the dataset using WEKA's `VisualizePanel`.

### 6. Progress and Summary
Display progress of operations and summary statistics of the dataset in the GUI.

## Usage

### Loading Data
- Click "Load ARFF" or "Load CSV" to load data from a file.
- The loaded data will be displayed in the data text area.

### Saving Data
- Click "Save ARFF" or "Save CSV" to save the current data to a file.

### Filtering Attributes
- Click "Filter Attributes" to remove specified attributes from the data.

### Clustering Data
- Enter the desired number of clusters in the "Number of Clusters" text field.
- Click "Cluster Data" to perform clustering on the data.

### Visualizing Data
- Click "Visualize Data" to open a new window with a plot of the data.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue to discuss changes.

## Contact

For any inquiries or feedback, please contact [patilchirag0430@gmail.com](mailto:patilchirag0430@gmail.com).

---

Thank you for using the Clustering Application! We hope it helps you with your data clustering needs.
