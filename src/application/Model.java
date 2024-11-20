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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Model {

    private ObservableList<Item> items = FXCollections.observableArrayList();  // List to store items

    // This method loads data (items) from a CSV file
    public void loadData() {
        String csvFile = "ItemsMaster.csv"; // path to your CSV file
        String line;
        String csvSeparator = ",";  // Comma as separator

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // Trim spaces and split by comma
                String[] fields = line.trim().split("\\s*,\\s*");

                if (fields.length >= 4) {  // Ensure that we have at least 4 fields (name, unit, quantity, price)
                    String name = fields[0];
                    String unit = fields[1];  // Unit field
                    int quantity = Integer.parseInt(fields[2]);  // Quantity field
                    double price = Double.parseDouble(fields[3]);  // Price field

                    // Create a new Item object and add it to the list
                    Item item = new Item(name, unit, quantity, price);
                    items.add(item);  // Add item to the observable list
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in the CSV file.");
        }
    }

    

    // Get the list of items
    public ObservableList<Item> getItems() {
        return items;
    }
}
