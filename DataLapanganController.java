package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DataLapangan; // Import the model class


public class DataLapanganController implements Initializable {

    @FXML
    private Button tambah;
    @FXML
    private Button edit;
    @FXML
    private Button hapus;
    @FXML
    private TextField nmLapangan;
    @FXML
    private TextField hari;
    @FXML
    private TextField txtbooking;
    
    // Change TableColumn types to DataLapangan and String (matching the model's properties)
    @FXML
    private TableColumn<DataLapangan, String> nama;
    @FXML
    private TableColumn<DataLapangan, String> tanggal;
    @FXML
    private TableColumn<DataLapangan, String> booking;
    
    // Change TableView type to DataLapangan
    @FXML
    private TableView<DataLapangan> tvLapangan;
    
    // Data storage for the TableView
    private ObservableList<DataLapangan> dataLapanganList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the ObservableList
        dataLapanganList = FXCollections.observableArrayList();
        
        // 1. Set up the table columns to map to the model's properties
        // The string in PropertyValueFactory must exactly match the property name (getNmLapangan -> "nmLapangan")
        nama.setCellValueFactory(new PropertyValueFactory<>("nmLapangan"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("Hari")); // Matches getHari()
        booking.setCellValueFactory(new PropertyValueFactory<>("booking"));
        
        // 2. Load the data into the TableView
        tvLapangan.setItems(dataLapanganList);
        
        // --- Optional: Load initial dummy data ---
        dataLapanganList.add(new DataLapangan("Lapangan A", "Senin", "Iqbal"));
        dataLapanganList.add(new DataLapangan("Lapangan B", "Selasa", "Vanza"));
        
        // 3. Add listener for selection change to populate text fields for editing/deleting
        tvLapangan.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showLapanganDetails(newValue));
    }
    
    /**
     * Populates the text fields with the details of the selected DataLapangan.
     * @param lapangan The selected DataLapangan object (can be null).
     */
    private void showLapanganDetails(DataLapangan lapangan) {
        if (lapangan != null) {
            nmLapangan.setText(lapangan.getNmLapangan());
            hari.setText(lapangan.getHari());
            txtbooking.setText(lapangan.getBooking());
        } else {
            // Clear all text fields if nothing is selected
            clearFields();
        }
    }
    
    /**
     * Clears all input text fields.
     */
    private void clearFields() {
        nmLapangan.setText("");
        hari.setText("");
        txtbooking.setText("");
        tvLapangan.getSelectionModel().clearSelection();
    }

    // --- CRUD Operations ---

    /**
     * Adds a new DataLapangan record.
     * Maps to the 'tambah' button.
     */
    @FXML
    private void tambah(ActionEvent event) {
        String newNmLapangan = nmLapangan.getText();
        String newHari = hari.getText();
        String newBooking = txtbooking.getText();
        
        if (!newNmLapangan.isEmpty() && !newHari.isEmpty() && !newBooking.isEmpty()) {
            DataLapangan newLap = new DataLapangan(newNmLapangan, newHari, newBooking);
            dataLapanganList.add(newLap);
            clearFields();
            // Optional: Select the newly added item
            tvLapangan.getSelectionModel().select(newLap);
        } else {
            // In a real application, you'd show an alert/error message
            System.out.println("Error: All fields must be filled for adding.");
        }
    }

    /**
     * Edits the selected DataLapangan record.
     * Maps to the 'edit' button.
     */
    @FXML
    private void edit(ActionEvent event) {
        DataLapangan selectedLap = tvLapangan.getSelectionModel().getSelectedItem();
        
        if (selectedLap != null) {
            // Update the selected object's properties
            selectedLap.setNmLapangan(nmLapangan.getText());
            selectedLap.setHari(hari.getText());
            selectedLap.setBooking(txtbooking.getText());
            
            // Refresh the table view to show the changes
            tvLapangan.refresh();
            clearFields();
        } else {
            System.out.println("Error: No item selected for editing.");
        }
    }

    /**
     * Deletes the selected DataLapangan record.
     * Maps to the 'hapus' button.
     */
    @FXML
    private void hapus(ActionEvent event) {
        int selectedIndex = tvLapangan.getSelectionModel().getSelectedIndex();
        
        if (selectedIndex >= 0) {
            // Remove the item from the ObservableList, which automatically updates the TableView
            dataLapanganList.remove(selectedIndex);
            clearFields();
        } else {
            // In a real application, you'd show a confirmation/error dialog
            System.out.println("Error: No item selected for deletion.");
        }
    }
}