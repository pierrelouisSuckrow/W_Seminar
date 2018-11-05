package sample;

import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.matrix.functor.MatrixFunction;
import org.la4j.operation.VectorOperation;
import org.la4j.vector.DenseVector;
import org.la4j.vector.SparseVector;
import org.la4j.vector.dense.BasicVector;
import org.la4j.vector.functor.VectorFunction;
import org.la4j.vector.functor.VectorProcedure;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;


public class NeuralNetwork{

    private int inputs; //Anzahl Inputs
    private int layers; //Länge des Netzes + Output
    private int outputs; //Anzahl der Ouputs
    private int hiddenlayersrows; //Anzahl der Nodes im HiddenLayer
    static Matrix[] weights; //Gewichtungen
    BasicVector[] bias; //Bias
    static Vector[] hidden; //HiddenLayers

    //Sigmoid Funktion
    VectorFunction sigmoid = new VectorFunction() {
        @Override
        public double evaluate(int i, double value) {
            return 1/(1 + Math.exp(-value));
        }
    };

    // ändert das Vorzeichen
    VectorFunction minus = new VectorFunction() {
        @Override
        public double evaluate(int i, double value) {
            return value*(-1);
        }
    };

    public NeuralNetwork(int inputs, int layers, int hiddenlayersrows, int outputs){

        this.inputs = inputs;
        this.layers = layers;
        this.outputs = outputs;
        this.hiddenlayersrows = hiddenlayersrows;
        weights = new Basic2DMatrix[layers];
        bias = new BasicVector[layers];
        hidden = new Vector[layers-1];
        for(int i = 0; i < layers; i++){


            if(i == layers-1) { //Für Outputs

                weights[i] = new Basic2DMatrix(outputs, hiddenlayersrows);
                bias[i] = new BasicVector(outputs);


            }else if(i == 0){

                weights[i] = new Basic2DMatrix(hiddenlayersrows, inputs);
                bias[i] = new BasicVector(hiddenlayersrows);

            }else{
                weights[i] = new Basic2DMatrix(hiddenlayersrows, hiddenlayersrows);
                bias[i] = new BasicVector(hiddenlayersrows);    

            }

            for(int z = 0; z < weights[i].rows(); z++)
                {
                    for(int r = 0; r < weights[i].columns(); r++){

                        double random = (Math.random()*1.8)-0.9;

                        weights[i].set(z, r, random);


                    }
                }

            for(int z = 0; z < bias[i].length(); z++)
            {
                double random = (Math.random()*1.2)-0.5;
                bias[i].set(z, random);
            }

            }

        }


//Berechnet die Outputs des Netzes
    public Vector feedforward(Vector input){

        //Kalkuliert die Hiddenlayers
        for(int i = 0; i < layers-1; i++) {

            if(i == 0){
                hidden[i] = weights[i].multiply(input); // 1 Hiddenlayer mit Inputs


            }else{ // Hidden mit Hidden

                hidden[i] = hidden[i-1].multiply(weights[i]);
            }
            //Fügt BIAS hinzu
            hidden[i].add(bias[i]);
            //Aktivierungsfunktion
            hidden[i].update(sigmoid);



        }

        //Kalkuliert den Output

            Vector output = weights[layers-1].multiply(hidden[layers-2]);
            //Fügt Bias hinzu
            output.add(bias[layers-1]);
            //Aktvierungsfunktion
            output.update(sigmoid);


            System.out.println("output:");
            System.out.println(output);


            return output;



    }

    //Trainiert einmal das Netz

    public void train(Vector inputs, Vector knownanswer, double learningRate){

        System.out.println("target:");
        System.out.println(knownanswer.toString());
        //Feedforward der Inputs
        Vector guess = feedforward(inputs);
        //output und target gleiche Größe
        if(guess.length() == knownanswer.length()) {
            //Array aus Vektoren für die Fehler
            Vector[] errors = new Vector[layers];
            //=(target-output)
            errors[layers - 1] = knownanswer.subtract(guess); //Output Error

            //Hiddenerror:

            for (int i = layers - 2; i >= 0; i--) {

                // = Transponierte Gewichtung * error davor
                errors[i] = weights[i + 1].transpose().multiply(errors[i + 1]);


            }
            //Gewichtungsupdate für Output Gewichtungen
            // = weights + learningRate * Steigung

            weights[layers - 1] = weights[layers - 1].add(learningSlope(errors[layers - 1], guess, hidden[layers - 2]).multiply(learningRate));



            for (int i = layers - 2; i >= 0; i--) {


                if (i == 0) {
                    //Gewichtungsupdate für Input Gewichtungen
                    weights[i] = weights[i].add(learningSlope(errors[i], hidden[i], inputs).multiply(learningRate));
                } else {

                    //Gewichtungsupdate für Hidden Gewichtungen
                    weights[i] = weights[i].add(learningSlope(errors[i], hidden[i], hidden[i - 1]).multiply(learningRate));
                }

            }
        }else{
            System.out.println("KnownAnswer size does not equal calculated output");
        }

    }


    //Kalkuliert die Tangente
    public Matrix learningSlope(Vector Error, Vector Output, Vector Outputbevore)
    {
        //Konvertiert Vektor zu 1D Matrix und Transposiert diese
        Matrix OutputbevoreM = Outputbevore.toRowMatrix();

        // = (E*O)
        Vector product1 = Error.hadamardProduct(Output);
        // = (1-O)
        Vector product2 = Output.subtract(1);
        product2.update(minus);

        // = (E*O)*(1-O)
        product1 = product1.hadamardProduct(product2);

        //Konvertiert Vektor zu 1D Matrix
        Matrix MatrixColumn1 = product1.toColumnMatrix();

        // = (E*O*(1-O) * Output von dem Layer davor
        Matrix LS = MatrixColumn1.multiply(OutputbevoreM);

        return LS;

    }

}
