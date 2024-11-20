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
import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;  // Define serialVersionUID to ensure compatibility across versions

    private String name;
    private String unit;  // Added unit field
    private double price;
    private int quantity;

    // Constructor adjusted for CSV data
    public Item(String name, String unit, int quantity, double price) {
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter methods
    public String getName() { return name; }
    public String getUnit() { return unit; }  // Getter for unit
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    // Setter for quantity
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return String.format("Item[name='%s', unit='%s', quantity=%d, price=%.2f]", name, unit, quantity, price);
    }
}
