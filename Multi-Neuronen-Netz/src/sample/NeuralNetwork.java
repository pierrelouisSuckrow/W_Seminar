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

    PrintStream originalStream = System.out;

    PrintStream dummyStream = new PrintStream(new OutputStream(){
        public void write(int b) {
            // NO-OP
        }
    });

    private int inputs;
    private int layers; //Länge des Netzes + Output
    private int outputs; //Anzahl der Ouputs
    private int hiddenlayersrows; //Anzahl der Nodes im HiddenLayer
    static Matrix[] weights; //Gewichtungen
    BasicVector[] bias; //Bias
    static Vector[] hidden; //HiddenLayers

    VectorFunction sigmoid = new VectorFunction() {
        @Override
        public double evaluate(int i, double value) {
            return 1/(1 + Math.exp(-value));
        }
    };

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

                        double random = (Math.random()*0.12)-0.12;

                        //weights[i].set(z, r, random);
                        weights[i].set(z, r, z*r);


                    }
                }
            bias[i].setAll(1);
            System.out.println("Weigths" + i);
            System.out.println(weights[i].toString());
            System.out.println("Bias" + i);
            System.out.println(bias[i].toString());

            }

        }



    public Vector feedforward(Vector input){
        System.setOut(dummyStream);
        System.out.println("Inputs:");
        System.out.println(input);

        for(int i = 0; i < layers-1; i++) {

            if(i == 0){
                System.out.println("Calculating first Hiddenlayer");
                hidden[i] = weights[i].multiply(input); // 1 Hiddenlayer mit Inputs
                System.out.println("Hidden 0 without Bias or Sig");
                System.out.println(hidden[i].toString());

            }else{ // Hidden mit Hidden
                System.out.println("Hiddenlayer " + i);
                hidden[i] = hidden[i-1].multiply(weights[i]);
            }
            System.out.println("Calculating bias");
            hidden[i].add(bias[i]);
            System.out.println("Calculating sigmoid");
            hidden[i].update(sigmoid);

            System.out.println("hidden" + i);
            System.out.println(hidden[i].toString());

        }
        System.out.println("Output calculation");
        try {

            Vector output = weights[layers-1].multiply(hidden[layers-2]);
            System.out.println("Calculating bias output");
            output.add(bias[layers-1]);
            System.out.println("Calculating sigmoid output");
            output.update(sigmoid);


            System.out.println("output:");
            System.out.println(output);


            System.setOut(originalStream);

            return output;
        }catch (Exception e) {

            System.out.println(e);
            return null;
        }



    }

    public void train(Vector inputs, Vector knownanswer, double learningRate){
        System.setOut(dummyStream);
        Vector guess = feedforward(inputs);
        if(guess.length() == knownanswer.length()) {
            Vector[] errors = new Vector[layers];
            System.out.println("Calculating Output Error");
            errors[layers - 1] = knownanswer.subtract(guess); //Output Error

            System.out.println("Calculating Hidden Errors");

            for (int i = layers - 2; i >= 0; i--) {

                System.out.println("    Calculating Hidden Error: h" + i);
                errors[i] = weights[i + 1].transpose().multiply(errors[i + 1]);
                System.out.println(errors[i].toString());

            }
            System.out.println("Recalculating weights output");

            weights[layers - 1] = weights[layers - 1].add(learningSlope(errors[layers - 1], guess, hidden[layers - 2]).multiply(learningRate));

            System.out.println("Recalculating weights hidden");

            for (int i = layers - 2; i >= 0; i--) {


                System.out.println("    Calculating Weights hidden: w" + i);
                if (i == 0) {

                    weights[i] = weights[i].add(learningSlope(errors[i], hidden[i], inputs).multiply(learningRate));
                } else {


                    weights[i] = weights[i].add(learningSlope(errors[i], hidden[i], hidden[i - 1]).multiply(learningRate));
                }

            }
        }else{
            System.out.println("KnownAnswer size does not equal calculated output");
        }
        System.setOut(originalStream);
    }

    public Matrix learningSlope(Vector Error, Vector Output, Vector Outputbevore)
    {
        System.out.println("        Calculating Learning Slope:");
        Matrix OutputbevoreM = Outputbevore.toRowMatrix();
        System.out.println("            1");
        Vector product1 = Error.hadamardProduct(Output);
        System.out.println("            2");
        Vector product2 = Output.subtract(1);
        System.out.println("            3");
        product2.update(minus);
        System.out.println("            4");
        product1 = product1.hadamardProduct(product2);
        System.out.println("            5");

        Matrix MatrixColumn1 = product1.toColumnMatrix();
        System.out.println("            6");
        Matrix LS = MatrixColumn1.multiply(OutputbevoreM);

        return LS;

    }

}
