/**********************************************
Workshop #
Course:<subject type> - Semester
Last Name:Tran
First Name:Vu Duc Thuan 
ID:121804223
Section:APD
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date:2024-11-7
**********************************************/
package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;

public class SavedCartsView {
    private TableView<File> savedCartsTable = new TableView<>();
    private Button loadCartButton = new Button("Load Cart");

    public SavedCartsView(Stage stage) {
        // Set up the layout
        BorderPane layout = new BorderPane();
        layout.setCenter(savedCartsTable);
        layout.setBottom(loadCartButton);

        // Create a column for displaying the file name
        TableColumn<File, String> fileNameColumn = new TableColumn<>("Saved Cart");
        fileNameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        savedCartsTable.getColumns().add(fileNameColumn);

        // Set up the scene
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Saved Carts");
    }

    public TableView<File> getSavedCartsTable() {
        return savedCartsTable;
    }

    public Button getLoadCartButton() {
        return loadCartButton;
    }
}
