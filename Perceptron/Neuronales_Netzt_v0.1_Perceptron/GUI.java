import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.paint.*;    

public class GUI extends Application {
  // Anfang Attribute  
  private static int height = 800;
  private static int weight = 800;
  // Ende Attribute
  
  public void start(Stage primaryStage) { 
    Pane root = new Pane();
    Scene scene = new Scene(root, height, weight);

    
    primaryStage.setOnCloseRequest(e -> System.exit(0));
    primaryStage.setTitle("GUI");
    primaryStage.setScene(scene);
    primaryStage.show();
  } // end of public GUI
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    launch(args);
    Ellipse e2 = new Ellipse( 100, 100, 40, 40);
    e2.setFill(Color.RED);
    
    /*
    Perceptron perceptron = new Perceptron();
    for (int i = 0;i < perceptron.points.length  ; i++ ) {
      if(perceptron.points[i].getState() == 1){
        System.out.println("test");   
        Ellipse e1 = new Ellipse();
        e1.setCenterX(perceptron.points[i].getX()); 
        e1.setCenterY(perceptron.points[i].getY());
        e1.setRadiusX(8);
        e1.setRadiusY(8);
        }
          
    } // end of for
    */
    
  } // end of main

  public static int getheight(){ 
  
  return height;  
  }
  
  public static int getweight(){ 
  
  return weight;  
  }
    
  
  // Ende Methoden
} // end of class GUI

