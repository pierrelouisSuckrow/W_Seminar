package sample;

import com.intellij.util.ui.UIUtil;
import com.sun.jna.platform.win32.WinUser;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import sample.mnist.MnistImageLoader;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.WHITE;

public class Controller implements Initializable {


    public Button ButtonNetworkGenerator;
    public Button ButtonTrainNetwork;

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
    public NeuralNetworkGenerator Net;
    public Canvas canvas_paint;
    public Button button_clear;
    public Button button_guess;
    public TextField nummber_loops;


    @Override
    public void initialize(URL location, ResourceBundle resources) {



        GraphicsContext g = canvas_paint.getGraphicsContext2D();
        g.setFill(WHITE);
        g.fillRect(0,0, canvas_paint.getWidth(), canvas_paint.getHeight());

        canvas_paint.setOnMouseDragged(e -> {
            double size = 12;
            double x = e.getX() - size/2;
            double y = e.getY() - size/2;

            g.setFill(BLACK);
           g.fillRect(x, y, size, size);

        });

    }



    public void GenerateNetwork(ActionEvent actionEvent) {
        Net = new NeuralNetworkGenerator(Integer.parseInt(input_number_layers_.getText()), Integer.parseInt(input_length_layer_hidden.getText()));
        showAlertWithHeaderText("Neuronales Netz", "Netz wurde erfolgreich generiert");

    }

    public void TrainNetwork(ActionEvent actionEvent) {
        TrainingSet tset = Net.createTrainSet("trainImage.idx3-ubyte", "trainLabel.idx1-ubyte",0,60000-1);
        //Net.trainData(tset,100,50,1000 );
        Net.trainRaw(tset, Integer.parseInt(nummber_loops.getText()));
        showAlertWithHeaderText("Neuronales Netz", "Netz wurde erfolgreich trainiert");

    }

    public void clear_canvas(ActionEvent actionEvent) {
        GraphicsContext g = canvas_paint.getGraphicsContext2D();
        g.setFill(WHITE);
        g.fillRect(0,0, canvas_paint.getWidth(), canvas_paint.getHeight());


    }

    public void TestNetwork(ActionEvent actionEvent) {
        TrainingSet testSet = Net.createTrainSet("t10k-images.idx3-ubyte", "t10k-labels.idx1-ubyte",0,10000);
        percentage_accuracy.setText(String.valueOf(Net.testTrainSet(testSet, 10)));
        showAlertWithHeaderText("Neuronales Netz", "Netz wurde erfolgreich getestet");
    }

    public void guess(ActionEvent actionEvent) {

        BufferedImage img = getImage();
        img = MnistImageLoader.resize(img, 28,28);

        int image_array[][] = MnistImageLoader.bufferedImageRedToArray(img);
        double input[] = MnistImageLoader.intArrayToDoubleArray(image_array);
        input = MnistImageLoader.invert(input);
        for(int i = 0; i < input.length; i++)
        {
            if(input[i] < 0.005)
            {
                input[i] = 0;
            }
        }
        System.out.println(Arrays.toString(input));
        double[] output = Net.guess(input);

            for(int i = 0; i < 10; i++){
                System.out.println(  (double) Math.round(output[i]*100)/100);
        }

                percentage_0.setText(String.valueOf((double) Math.round(output[0]*100)/100));
                percentage_1.setText(String.valueOf((double) Math.round(output[1]*100)/100));
                percentage_2.setText(String.valueOf((double) Math.round(output[2]*100)/100));
                percentage_3.setText(String.valueOf((double) Math.round(output[3]*100)/100));
                percentage_4.setText(String.valueOf((double) Math.round(output[4]*100)/100));
                percentage_5.setText(String.valueOf((double) Math.round(output[5]*100)/100));
                percentage_6.setText(String.valueOf((double) Math.round(output[6]*100)/100));
                percentage_7.setText(String.valueOf((double) Math.round(output[7]*100)/100));
                percentage_8.setText(String.valueOf((double) Math.round(output[8]*100)/100));
                percentage_9.setText(String.valueOf((double) Math.round(output[9]*100)/100));


    }

    private BufferedImage getImage() {


        WritableImage writableImage =  canvas_paint.snapshot(null,null);

        BufferedImage img = SwingFXUtils.fromFXImage(writableImage, null);
        try {
            File outputfile = new File("input.png");
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", outputfile);

        } catch (final IOException e) {

        }


        return  img;
    }

    private void showAlertWithHeaderText(String titel, String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titel);
        alert.setHeaderText(info);


        alert.showAndWait();
    }

}
