module ShoppingCart {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens application to javafx.base;  // This line allows javafx.base to access your application package

    exports application;
}
