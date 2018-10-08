package sample;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Reader {


private String Dateiname;
private BufferedReader reader;

    public Reader(String Dateiname){

        this.Dateiname = Dateiname;




    }

    public void test(){}


    public double[][] newRead() {




        try {
            System.out.println("Suchen");
            reader = new BufferedReader(new FileReader("src/sample/resources/" + Dateiname + ".txt"));
            System.out.println("Datei wurde gelesen.");

            String StrAnzahlSynapsen;
            String StrAnzahlInputs;

            StrAnzahlSynapsen = reader.readLine();//Ließt die Erste Zeile
            int IntAnzahlSynapsen = StringtoInt(StrAnzahlSynapsen);//Konvertierung zu Int
            StrAnzahlInputs = reader.readLine();
            int IntAnzahlInputs = StringtoInt(StrAnzahlInputs); //Konvertierung zu Int
            double[][] newMatrix = new double[IntAnzahlSynapsen][IntAnzahlInputs];

            for(int i = 0; i < IntAnzahlSynapsen; i++)
            {
                String reihe = reader.readLine();
                String[] arr=reihe.split(" ");
                for(int q=0;q<arr.length;q++)
                {
                    double currentNum = StringtoDouble(arr[q]);
                    newMatrix[i][q] = currentNum;
                }


            }

            reader.close();
            return newMatrix;
        } catch (Exception ex){ // Sollte etwas schief gehen wird dieser Teil ausgeführt
            System.out.println("Error Reader");
            System.out.println(ex.getMessage());
            return null;
        }








    }



   private double StringtoDouble(String numberStr){

        double numberFl;


            numberFl = Double.parseDouble(numberStr);

            return numberFl;

    }

    private int StringtoInt(String numberStr){

        int numberInt;

        numberInt = Integer.parseInt(numberStr);

        return numberInt;
    }

}



