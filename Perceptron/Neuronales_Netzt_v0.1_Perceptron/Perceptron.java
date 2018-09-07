
import java.lang.Math;

public class Perceptron
{
       public Points[] points = new Points[100];
        
    private float[] weight;
    
    int sign(float n) // Die signumfuntkion
    {
        if( n >= 0)
        {
            return 1;
        }
        else 
        {
            return -1;
        }
    }
    
    public Perceptron() // Eine einzelne Synapse 
    {
        weight = new float[2];
        for(int i = 0; i < 2; i++)
         {
             int random = (int) Math.round(Math.random() ); // Zufallszahl 0/1
             weight[i] = random*-2+1; //( x * -2 +1) -> 1 / -1 bei random 0/1
             
            }
            
        
    }
    
    public int guess(float input[]) //liefert einen Anahme über das Ergebnis aus der Summe aller Inputs * der Gewichtung
    {
        float sum = 0;
        for(int i = 0; i < weight.length; i++)
        {
            sum += input[i]*weight[i];
        }
        
        return sign(sum);
    }
    
    
}
