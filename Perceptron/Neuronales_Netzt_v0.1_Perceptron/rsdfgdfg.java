import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 15.07.2018
 * @author 
 */

public class rsdfgdfg extends Application {
  // Anfang Attribute
  private Circle circle1 = new Circle();
  // Ende Attribute
  
  public void start(Stage primaryStage) { 
    Pane root = new Pane();
    Scene scene = new Scene(root, 284, 262);
    // Anfang Komponenten
    
    circle1.setCenterX(139);
    circle1.setCenterY(89);
    circle1.setRadius(50);
    root.getChildren().add(circle1);
    // Ende Komponenten
    
    primaryStage.setOnCloseRequest(e -> System.exit(0));
    primaryStage.setTitle("rsdfgdfg");
    primaryStage.setScene(scene);
    primaryStage.show();
  } // end of public rsdfgdfg
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    launch(args);
  } // end of main
  
  // Ende Methoden
} // end of class rsdfgdfg

