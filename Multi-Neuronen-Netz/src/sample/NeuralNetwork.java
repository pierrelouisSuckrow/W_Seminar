package sample;

import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.matrix.functor.MatrixFunction;
import org.la4j.vector.dense.BasicVector;
import org.la4j.vector.functor.VectorFunction;

import java.util.Random;


public class NeuralNetwork{


    private int inputs;
    private int layers; //Länge des Netzes + Output
    private int outputs; //Anzahl der Ouputs
    private int hiddenlayersrows; //Anzahl der Nodes im HiddenLayer
    static Basic2DMatrix[] weights; //Gewichtungen
    BasicVector[] bias; //Bias
    static Vector[] hidden; //HiddenLayers

    VectorFunction sigmoid = new VectorFunction() {
        @Override
        public double evaluate(int i, double value) {
            return 1/(1 + Math.exp(-value));
        }
    };

    VectorFunction cost = new VectorFunction() {
        @Override
        public double evaluate(int i, double value) {
            return value*value;
        }
    };



    /*
    MatrixFunction sigmoid = new MatrixFunction() {
        @Override
        public double evaluate(int i, int j, double value) {
            return 1/(1 + Math.exp(-value));
        }
    };
    */


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

                weights[i] = new Basic2DMatrix(hiddenlayersrows, outputs);
                bias[i] = new BasicVector(outputs);


            }else if(i == 0){


                weights[i] = new Basic2DMatrix(inputs, hiddenlayersrows);
                bias[i] = new BasicVector(hiddenlayersrows);


            }else{

                weights[i] = new Basic2DMatrix(hiddenlayersrows, hiddenlayersrows);
                bias[i] = new BasicVector(hiddenlayersrows);    

            }

                for(int z = 0; z < weights[i].rows(); z++)
                {
                    for(int r = 0; r < weights[i].columns(); r++){

                        double random = (Math.random()*0.12)-0.12;

                        weights[i].set(z, r, random);


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
        System.out.println("Inputs:");
        System.out.println(input);

        for(int i = 0; i < layers-1; i++) {

            if(i == 0){
                System.out.println("Calculating first Hiddenlayer");
                hidden[i] = input.multiply(weights[i]); // 1 Hiddenlayer mit Inputs
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
            Vector output = hidden[layers-2].multiply(weights[layers-1]);
            System.out.println("Calculating bias output");
            output.add(bias[layers-1]);
            System.out.println("Calculating sigmoid output");
            output.update(sigmoid);


            System.out.println("output:");
            System.out.println(output);




            return output;
        }catch (Exception e) {

            System.out.println(e);
            return null;
        }



    }

    public void train(Vector inputs, Vector knownanswer, double learningRate){

        Vector guess = feedforward(inputs);
        Vector[] errors = new Vector[layers];
        System.out.println("Calculating Output Error");
        errors[layers-1] = knownanswer.subtract(guess); //Output Error

        System.out.println("Calculating Hidden Errors");

        for (int i = layers-2; i >= 0; i--) {

            System.out.println("Calculating Hidden Error: h" + i );
            errors[i] =  weights[i+1].transpose().multiply(errors[i+1]);

        }

        System.out.println("Recalculating weights");

        for (int i = layers-1; i >=0; i--){

            System.out.println("Calculating LearningSlope");

            Matrix LearningSlope = errors[i].multiply()

            System.out.println("Calculating Weights: w" + i);

            weights[i] = weights[i];


        }
    }

}
