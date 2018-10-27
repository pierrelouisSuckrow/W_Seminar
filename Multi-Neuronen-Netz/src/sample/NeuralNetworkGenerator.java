package sample;

import org.la4j.Vector;
import org.la4j.vector.dense.BasicVector;
import java.io.File;
import  sample.mnist.*;


public class NeuralNetworkGenerator {

    //Konstanten

    private int inputs_length = 784; // 28*28
    private int output_length = 10; // 0 - 9
    private double learning_rate = 0.3;



    NeuralNetwork Net;



    public NeuralNetworkGenerator(int number_layers, int layers_hidden_length)
    {
        Net = new NeuralNetwork(inputs_length,number_layers, layers_hidden_length,output_length);


    }



    public void train_batch(TrainingSet set, int loops, int batch_size){
        if(inputs_length == set.getInput_size() || output_length == set.getTarget_size())
        for(int i = 0; i < loops; i++){
            TrainingSet batch = set.getBatch(batch_size);
            for(int z = 0; i < batch_size;i++){
                Vector input = new BasicVector(batch.getInput(z));
                Vector target = new BasicVector(batch.getTarget(z));
                Net.train(input, target, learning_rate);

            }
        }

    }
    //static ????????
    public TrainingSet createTrainSet(int start, int end) {

        TrainingSet set = new TrainingSet(inputs_length, output_length);

        try {

            String path = new File("").getAbsolutePath();

            MnistImageFile m = new MnistImageFile(path + "/resources/trainImage.idx3-ubyte", "rw");
            MnistLabelFile l = new MnistLabelFile(path + "/resources/trainLabel.idx1-ubyte", "rw");

            for(int i = start; i <= end; i++) {
                if(i % 100 ==  0){
                    System.out.println("prepared: " + i);
                }

                double[] input = new double[inputs_length];
                double[] output = new double[output_length];

                output[l.readLabel()] = 1d;
                for(int j = 0; j < 28*28; j++){
                    input[j] = (double)m.read() / (double)256;
                }

                set.add(input, output);
                m.next();
                l.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return set;
    }

    public void trainData(TrainingSet set, int epochs, int loops, int batch_size) {
        for(int e = 0; e < epochs;e++) {
            train_batch(set, loops, batch_size);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>   "+ e+ "   <<<<<<<<<<<<<<<<<<<<<<<<<<");
        }
    }

    /*
    public static void testTrainSet(TrainingSet set, int printSteps) {
        int correct = 0;
        for(int i = 0; i < set.get_size(); i++) {

            double highest = NetworkTools.indexOfHighestValue(net.calculate(set.getInput(i)));
            double actualHighest = NetworkTools.indexOfHighestValue(set.getOutput(i));
            if(highest == actualHighest) {

                correct ++ ;
            }
            if(i % printSteps == 0) {
                System.out.println(i + ": " + (double)correct / (double) (i + 1));
            }
        }
        System.out.println("Testing finished, RESULT: " + correct + " / " + set.size()+ "  -> " + (double)correct / (double)set.size() +" %");
    }
    */

}
