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

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartController {

    private CartView view;
    private Model model = new Model();
    private ObservableList<Item> cartItems = FXCollections.observableArrayList();

    public CartController(CartView view) {
        this.view = view;
        attachHandlers();
        model.loadData();
        view.getItemsComboBox().setItems(model.getItems());
        view.getCartTableView().setItems(cartItems);
        setupBindings();
    }

    private void attachHandlers() {
        view.getAddToCartButton().setOnAction(e -> addToCart());
        view.getRemoveFromCartButton().setOnAction(e -> removeFromCart());
        view.getSaveCartButton().setOnAction(e -> saveCart());
        view.getCheckOutButton().setOnAction(e -> checkOut());
        view.getViewSavedCartsButton().setOnAction(e -> openSavedCartsWindow());
    }

    private void setupBindings() {
        view.getTotalLabel().textProperty().bind(Bindings.createStringBinding(() ->
                String.format("$%.2f", cartItems.stream()
                        .mapToDouble(item -> item.getPrice() * item.getQuantity())
                        .sum()), cartItems));
    }

    private void addToCart() {
        Item selectedItem = view.getItemsComboBox().getSelectionModel().getSelectedItem();
        int quantity = (int) view.getQuantitySlider().getValue();

        if (selectedItem == null || quantity <= 0) {
            showMessage("Please select an item and quantity.");
            return;
        }

        Item cartItem = new Item(selectedItem.getName(), selectedItem.getUnit(), quantity, selectedItem.getPrice());
        cartItems.add(cartItem);
        showMessage("Item added to cart!");
    }

    private void removeFromCart() {
        int selectedIndex = view.getCartTableView().getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            cartItems.remove(selectedIndex);
            showMessage("Item removed from cart.");
        }
    }

    private void saveCart() {
        if (cartItems.isEmpty()) {
            showMessage("The cart is empty. Nothing to save.");
            return;
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cart_" + System.currentTimeMillis() + ".ser"))) {
            out.writeObject(new ArrayList<>(cartItems));
            showMessage("Cart saved successfully!");
        } catch (IOException e) {
            showError("Failed to save cart.");
        }
    }

    private void checkOut() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you done with the grocery?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Check Out Confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            cartItems.clear();
            showMessage("Checkout complete!");
        }
    }

    private void openSavedCartsWindow() {
        Stage savedCartsStage = new Stage();
        SavedCartsView savedCartsView = new SavedCartsView(savedCartsStage);

        // Load saved carts list for display
        File folder = new File(".");
        File[] cartFiles = folder.listFiles((dir, name) -> name.startsWith("cart_") && name.endsWith(".ser"));

        if (cartFiles != null && cartFiles.length > 0) {
            savedCartsView.getSavedCartsTable().getItems().addAll(cartFiles);
        } else {
            showMessage("No saved carts found.");
        }

        // Set action to load selected cart
        savedCartsView.getLoadCartButton().setOnAction(e -> {
            File selectedFile = savedCartsView.getSavedCartsTable().getSelectionModel().getSelectedItem();
            if (selectedFile != null) {
                loadCart(selectedFile);
                savedCartsStage.close();
            } else {
                showMessage("Please select a saved cart to load.");
            }
        });

        savedCartsStage.show();
    }

    private void loadCart(File file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            List<Item> loadedCartItems = (List<Item>) in.readObject();
            cartItems.setAll(loadedCartItems);  // Update the cartItems list with the loaded items
            showMessage("Cart loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            showError("Failed to load cart.");
        }
    }


    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}

