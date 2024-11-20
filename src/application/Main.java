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
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        CartView view = new CartView();  // Create the view
        Model model = new Model();       // Create the model
        CartController controller = new CartController(view);  // Create the controller with view

        Scene scene = new Scene(view.getLayout(), 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Grocery Store Cart");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

