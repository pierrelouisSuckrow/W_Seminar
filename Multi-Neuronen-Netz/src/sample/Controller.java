package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    public Button ButtonNetworkGenerator;
    public Button ButtonTrainNetwork;
    public Label[] percentage_guess = new Label[10];
    public Label percentage_0;
    public Label percentage_1;
    public Label percentage_2;
    public Label percentage_3;
    public Label percentage_4;
    public Label percentage_5;
    public Label percentage_6;
    public Label percentage_7;
    public Label percentage_8;
    public Label percentage_9;
    public Label percentage_accuracy;
    public TextField input_trainig_set;
    public TextField input_testing_set;
    public TextField input_number_layers_;
    public TextField input_length_layer_hidden;
    public NeuralNetwork Net;

    public Controller(){


        percentage_guess[0] = percentage_0;
        percentage_guess[1] = percentage_1;
        percentage_guess[2] = percentage_2;
        percentage_guess[3] = percentage_3;
        percentage_guess[4] = percentage_4;
        percentage_guess[5] = percentage_5;
        percentage_guess[6] = percentage_6;
        percentage_guess[7] = percentage_7;
        percentage_guess[8] = percentage_8;
        percentage_guess[9] = percentage_9;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }



    public void GenerateNetwork(ActionEvent actionEvent) {

        Net = new NeuralNetwork(23,Integer.parseInt(input_number_layers_.getText()),Integer.parseInt(input_length_layer_hidden.getText()),10);
    }

    public void TrainNetwork(ActionEvent actionEvent) {
    }
}
