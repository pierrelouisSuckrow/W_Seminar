package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.vector.dense.BasicVector;

import java.util.Arrays;
import java.util.Random;

public class Main extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception{


    }




    public static void main(String[] args) {

        Reader Rinput = new Reader("Input3");
        double[][] input =  Rinput.newRead();
        Basic2DMatrix Minput = new Basic2DMatrix(input);
        Vector Vinput = Minput.toColumnVector();
        Basic2DMatrix target = new Basic2DMatrix(1,1);
        target.setAll(1);
        NeuralNetwork netz1 = new NeuralNetwork(Minput.rows(), 2, 60,1);
        netz1.feedforward(Vinput);
        //netz1.train(Minput, target);

        launch(args);
    }
}
