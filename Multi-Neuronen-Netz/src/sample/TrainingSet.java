package sample;

import java.util.ArrayList;

public class TrainingSet {
    private int input_size;
    private int target_size;

    //0 = input , 1 = target
    private ArrayList<double[][]> training_data = new ArrayList<>();


    public TrainingSet(int input_size, int target_size)
    {
        this.input_size = input_size;
        this.target_size = target_size;
    }

    public  void add(double[] input, double[] target){
        if(input.length == input_size || target.length == target_size){
            training_data.add(new double[][]{input, target});

        }


    }

    public  TrainingSet getBatch(int size){

    }

    public  int get_size(){
        return training_data.size();
    }

    public double[] getInput(int i){

        return training_data.get(i)[0];
    }

    public double[] getOutput(int i){

        return training_data.get(i)[1];
    }

    public int getInput_size(){return input_size;}
    public int getTarget_size(){return target_size;}

}
