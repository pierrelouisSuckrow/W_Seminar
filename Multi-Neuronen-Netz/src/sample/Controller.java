package sample;

import com.intellij.util.ui.UIUtil;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    public NeuralNetworkGenerator Net;
    public Canvas canvas_paint;
    public Button button_clear;
    public Button button_guess;


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



        GraphicsContext g = canvas_paint.getGraphicsContext2D();
        //g.setFill(WHITE);
        //g.fillRect(0,0, canvas_paint.getWidth(), canvas_paint.getHeight());

        canvas_paint.setOnMouseDragged(e -> {
            double size = 10;
            double x = e.getX() - size/2;
            double y = e.getY() - size/2;

            g.setFill(BLACK);
           g.fillRect(x, y, size, size);

        });

    }



    public void GenerateNetwork(ActionEvent actionEvent) {
        Net = new NeuralNetworkGenerator(Integer.parseInt(input_number_layers_.getText()), Integer.parseInt(input_length_layer_hidden.getText()));

    }

    public void TrainNetwork(ActionEvent actionEvent) {
        TrainingSet tset = Net.createTrainSet("trainImage.idx3-ubyte", "trainLabel.idx1-ubyte",0,60000-1);
        //Net.trainData(tset,100,50,1000 );
        Net.trainRaw(tset);

    }

    public void clear_canvas(ActionEvent actionEvent) {
        GraphicsContext g = canvas_paint.getGraphicsContext2D();
        g.setFill(WHITE);
        g.fillRect(0,0, canvas_paint.getWidth(), canvas_paint.getHeight());


    }

    public void TestNetwork(ActionEvent actionEvent) {
        TrainingSet testSet = Net.createTrainSet("t10k-images.idx3-ubyte", "t10k-labels.idx1-ubyte",0,10000);
        Net.testTrainSet(testSet, 10);
    }

    public void guess(ActionEvent actionEvent) {
        /*
        WritableImage writableImage = new WritableImage(280, 280);
        canvas_paint.snapshot(null, writableImage);
        BufferedImage myBI = new BufferedImage(280, 280, BufferedImage.TYPE_BYTE_GRAY);
        */

        /*
        BufferedImage img = MnistImageLoader.resize(getImage(), 28, 28);
        //img = GrayScale(img);
        int image_array[][] = MnistImageLoader.bufferedImageToArray(img);
        double image[] = MnistImageLoader.intArrayToDoubleArray(image_array);
        //double[] output = Net.guess(image);
        for (int[] arr : image_array) {
            System.out.println(Arrays.toString(arr));
        }
        //System.out.println(Arrays.deepToString(image_array));
        System.out.println(Arrays.toString(image));
        File outputfile = new File("ddddddddddddd.png");
        try {
            ImageIO.write(img, "png", outputfile);
        }catch (Exception e){

        }
        */
        getImage();
        BufferedImage img = null;
        try {


        img = MnistImageLoader.loadImage("input.png");}

        catch (Exception e){

        }
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



    }
    /*
    private BufferedImage getScaledImage() {
        // for a better recognition we should improve this part of how we retrieve the image from the canvas
        WritableImage writableImage = new WritableImage(280, 280);
        canvas_paint.snapshot(null, writableImage);
        Image tmp = SwingFXUtils.fromFXImage(writableImage, null).getScaledInstance(28, 28, Image.SCALE_SMOOTH);
        BufferedImage scaledImg = new BufferedImage(28, 28, BufferedImage.TYPE_BYTE_GRAY);
        Graphics graphics = scaledImg.getGraphics();
        graphics.drawImage(tmp, 0, 0, null);
        graphics.dispose();
        return scaledImg;
    }*/

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
    public BufferedImage GrayScale(BufferedImage img) {

        try {
            int width = img.getWidth();
            int height = img.getHeight();

            for(int i=0; i<height; i++) {

                for(int j=0; j<width; j++) {

                    Color c = new Color(img.getRGB(j, i));
                    int red = (int)(c.getRed() * 0.299);
                    int green = (int)(c.getGreen() * 0.587);
                    int blue = (int)(c.getBlue() *0.114);
                    Color newColor = new Color(red+green+blue,

                            red+green+blue,red+green+blue);

                    img.setRGB(j,i,newColor.getRGB());
                }
            }

            return img;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
