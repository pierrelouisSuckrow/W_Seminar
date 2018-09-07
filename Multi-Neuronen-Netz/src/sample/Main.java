package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;

public class Main extends Application {


    float[][] weights = new int[][];


    @Override
    public void start(Stage primaryStage) throws Exception{


    }




    public static void main(String[] args) {


        Basic2DMatrix matrix = new Basic2DMatrix(9,9);

        for(int i = 0; i < 4; i++)

        launch(args);
    }
}
