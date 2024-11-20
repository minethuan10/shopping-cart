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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class CartView {
    private ComboBox<Item> itemsComboBox = new ComboBox<>();
    private TableView<Item> cartTableView = new TableView<>();
    private Slider quantitySlider = new Slider(1, 10, 1);
    private Button addToCartButton = new Button("Add to Cart");
    private Button removeFromCartButton = new Button("Remove from Cart");
    private Button saveCartButton = new Button("Save Cart");
    private Button checkOutButton = new Button("Check Out");
    private Button viewSavedCartsButton = new Button("View Saved Carts");

    private Label unitLabel = new Label("Unit: ");
    private Label purchaseUnitLabel = new Label("Purchase Unit: ");
    private Label pricePerUnitLabel = new Label("Price/Unit: $0.00");
    private Label totalLabel = new Label("$0.00");

    private BorderPane layout = new BorderPane();

    public CartView() {
        quantitySlider.setShowTickLabels(true);
        quantitySlider.setShowTickMarks(true);
        quantitySlider.setMajorTickUnit(1);
        quantitySlider.setSnapToTicks(true);

        // Configure ComboBox to display item names only
        itemsComboBox.setCellFactory(param -> new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getName());
            }
        });

        itemsComboBox.setButtonCell(new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getName());
            }
        });

        itemsComboBox.setConverter(new StringConverter<Item>() {
            @Override
            public String toString(Item item) {
                return item == null ? null : item.getName();
            }

            @Override
            public Item fromString(String string) {
                return null;
            }
        });

        // Display selected item details dynamically
        itemsComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                unitLabel.setText("Unit: " + newSelection.getUnit());
                purchaseUnitLabel.setText("Purchase Unit: " + (int) quantitySlider.getValue());
                pricePerUnitLabel.setText(String.format("Price/Unit: $%.2f", newSelection.getPrice()));
            }
        });

        // Configure TableView columns
        TableColumn<Item, String> nameColumn = new TableColumn<>("Item Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Item, String> unitColumn = new TableColumn<>("Unit");
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));

        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Item, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        cartTableView.getColumns().addAll(nameColumn, unitColumn, quantityColumn, priceColumn);
        // Arrange action buttons below the item selection and quantity controls
        HBox bottomButtons = new HBox(10, addToCartButton, removeFromCartButton, saveCartButton, checkOutButton, viewSavedCartsButton);
        // Layout configuration
        HBox itemDetails = new HBox(20, unitLabel, purchaseUnitLabel);
        VBox itemInfoBox = new VBox(5, itemDetails, pricePerUnitLabel, bottomButtons); // Place buttons below price label

        // Arrange item selection and quantity slider controls
        HBox itemControls = new HBox(10, itemsComboBox, quantitySlider);

       

        // Layout configuration with cart table at the bottom
        VBox bottomPane = new VBox(10, cartTableView, new Label("Total Amount:"), totalLabel);

        // Set layout positions
        layout.setTop(itemControls); // Item selection and quantity slider at the top
        layout.setCenter(itemInfoBox); // Item details and buttons in the center
        layout.setBottom(bottomPane); // Cart table and total amount label at the bottom
    }

    // Getters for the controller to access the UI components
    public ComboBox<Item> getItemsComboBox() { return itemsComboBox; }
    public TableView<Item> getCartTableView() { return cartTableView; }
    public Slider getQuantitySlider() { return quantitySlider; }
    public Button getAddToCartButton() { return addToCartButton; }
    public Button getRemoveFromCartButton() { return removeFromCartButton; }
    public Button getSaveCartButton() { return saveCartButton; }
    public Button getCheckOutButton() { return checkOutButton; }
    public Button getViewSavedCartsButton() { return viewSavedCartsButton; }
    public Label getTotalLabel() { return totalLabel; }

    public BorderPane getLayout() { return layout; }
}

