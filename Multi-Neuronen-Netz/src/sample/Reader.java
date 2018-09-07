package sample;
import java.io.BufferedReader;
import java.io.FileReader;

public class Reader {


private String Dateiname;
private BufferedReader reader;

    public Reader(String Dateiname){

        this.Dateiname = Dateiname;




    }


    public float[][] newRead() {




        try {
            reader = new BufferedReader(new FileReader(Dateiname + ".txt"));
            System.out.println("Datei wurde gelesen.");

            String StrAnzahlSynapsen;
            String StrAnzahlInputs;

            StrAnzahlSynapsen = reader.readLine();//Ließt die Erste Zeile
            int IntAnzahlSynapsen = StringtoInt(StrAnzahlSynapsen);//Konvertierung zu Int
            StrAnzahlInputs = reader.readLine();
            int IntAnzahlInputs = StringtoInt(StrAnzahlInputs); //Konvertierung zu Int
            float[][] newMatrix = new float[IntAnzahlSynapsen][IntAnzahlInputs];

            for(int i = 0; i < IntAnzahlSynapsen; i++)
            {
                String reihe = reader.readLine();
                String[] arr=reihe.split(" ");
                for(int q=0;q<arr.length;q++)
                {
                    float currentNum = StringtoFloat(arr[q]);
                    newMatrix[i][q] = currentNum;
                }


            }

            /*
            while() != null){ //ReadLine gibt die aktuelle Zeile aus und geht zur nÃ¤chsten.
                String nachname = reader.readLine(); // Zweite Zeile
                String alter = reader.readLine(); // Dritte Zeile
                System.out.println(vorname + " " + nachname + " " + alter); // Ausgabe
                reader.readLine(); // Die leere Zeile

            }
*/
            reader.close();
            return newMatrix;
        } catch (Exception ex){ // Sollte etwas schief gehen wird dieser Teil ausgeführt
            System.out.println(ex.getMessage());
            return null;
        }








    }



   private float StringtoFloat(String numberStr){

        float numberFl;


            numberFl = Float.parseFloat(numberStr);

            return numberFl;

    }

    private int StringtoInt(String numberStr){

        int numberInt;

        numberInt = Integer.parseInt(numberStr);

        return numberInt;
    }

}



