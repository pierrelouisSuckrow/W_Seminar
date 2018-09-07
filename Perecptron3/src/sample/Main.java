package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.input.MouseEvent;

import java.util.*;

import java.awt.*;

public class Main extends Application {

    private static Points[] points = new Points[80];
    private static int height = 800;
    private static int weight = 800;
    static Perceptron perceptron = new Perceptron();
    private Line line = new Line(0,0, weight, height);
    private int klickz = 0;




    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Darstellung");
        Parent root = new Pane();
        ((Pane) root).getChildren().add(line);
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        Scene scene = new Scene(root, weight, height);
        primaryStage.setScene(scene);



        for (int i = 0;i < points.length  ; i++ ) {
            Circle[] circles = new Circle[points.length];
            Circle circle = new Circle();
            circle.setCenterX(points[i].getX());
            circle.setCenterY(points[i].getY());
            circle.setRadius(8);
            circles[i] = circle;
            ((Pane) root).getChildren().add(circles[i]);
            if(points[i].getState() == 1){

            circles[i].setFill(Color.PURPLE);

            }else{
                circles[i].setFill(javafx.scene.paint.Color.ORANGE);
            }

        } // end of for



        primaryStage.show();



        //System.out.println("hallo");
        ///Trainieren
        scene.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                klickz++;
                System.out.println(klickz);
                for (int i = 0;i < points.length  ; i++ ) {

                    float[] inputs = {points[i].getX(), points[i].getY()};
                    perceptron.train(inputs, points[i].getState());
                    //System.out.println("test");
                    int guess = perceptron.guess(inputs);
                    Circle[] circles = new Circle[points.length];
                    Circle circle = new Circle();
                    circle.setCenterX(points[i].getX());
                    circle.setCenterY(points[i].getY());
                    circle.setRadius(5);
                    circles[i] = circle;
                    ((Pane) root).getChildren().add(circles[i]);
                    if(guess == points[i].getState())
                    {
                        circles[i].setFill(javafx.scene.paint.Color.GREEN);
                    }else{

                        circles[i].setFill(javafx.scene.paint.Color.RED);
                    }

                }

            }
        });

        primaryStage.show();
        }


    public static void main(String[] args) {
        for(int i = 0; i < points.length; i++) {

            points[i] = new Points();

        }
        System.out.println("nice");
        launch(args);}

    public static int getheight(){

        return height;
    }

    public static int getweight(){

        return weight;
    }
}
