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


        double [][] testa = {
                {1, 2, 3},
                {4, 5, 6},
        };

        Matrix test = new Basic2DMatrix(testa);
        System.out.println(test.toString());
        Matrix test2 = new Basic2DMatrix(2,3);
        test2.setAll(1);
        System.out.println(test2.toString());


        Reader Rinput = new Reader("Input1");
        double[][] input =  Rinput.newRead();
        Basic2DMatrix Minput = new Basic2DMatrix(input);
        Vector Vinput = Minput.toColumnVector();
        Vector target = new BasicVector(1);
        target.setAll(1);
        NeuralNetwork netz1 = new NeuralNetwork(Minput.rows(), 3, 3,1);//Layers immer mit outputlayer angeben
        //netz1.feedforward(Vinput);
        netz1.train(Vinput, target, 0.5);
        /*
        Reader Rinput2 = new Reader("Input3");
        double[][] input2 =  Rinput2.newRead();
        Basic2DMatrix Minput2 = new Basic2DMatrix(input2);
        Vector Vinput2 = Minput2.toColumnVector();




        System.out.println("TeSt4343");

        System.out.println(netz1.learningSlope(Vinput, Vinput2, Vinput2).toString());
        System.out.println(netz1.learningSlope(Vinput, Vinput2, Vinput2).multiply(2).toString());

*/
        launch(args);
    }
}
