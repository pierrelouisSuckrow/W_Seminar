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

        if(size > 0 && size <= this.get_size()) {
            TrainingSet set = new TrainingSet(input_size, target_size);
            Integer[] ids = randomValues(0,this.get_size() - 1, size);
            for(Integer i:ids) {
                set.add(this.getInput(i),this.getTarget(i));
            }
            return set;
        }else return this;

    }

    public  int get_size(){
        return training_data.size();
    }

    public double[] getInput(int i){

        return training_data.get(i)[0];
    }

    public double[] getTarget(int i){

        return training_data.get(i)[1];
    }

    public int getInput_size(){return input_size;}
    public int getTarget_size(){return target_size;}

    public static Integer[] randomValues(int lowerBound, int upperBound, int amount) {

        lowerBound --;

        if(amount > (upperBound-lowerBound)){
            return null;
        }

        Integer[] values = new Integer[amount];
        for(int i = 0; i< amount; i++){
            int n = (int)(Math.random() * (upperBound-lowerBound+1) + lowerBound);
            while(containsValue(values, n)){
                n = (int)(Math.random() * (upperBound-lowerBound+1) + lowerBound);
            }
            values[i] = n;
        }
        return values;
    }
    public static <T extends Comparable<T>> boolean containsValue(T[] ar, T value){
        for(int i = 0; i < ar.length; i++){
            if(ar[i] != null){
                if(value.compareTo(ar[i]) == 0){
                    return true;
                }
            }

        }
        return false;
    }

}
