
import java.lang.Math;

public class Perceptron
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int x;
    
    private float[] weight;
    
    int sign(float n)
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
    
    public Perceptron()
    {
        weight = new float[2];
        for(int i = 0; i < 2; i++)
         {
             int random = (int) Math.round(Math.random() );
             weight[i] = random*-2+1; //( x * -2 +1) -> 1 / -1 bei random 0/1
             
            }
            
        
    }
    
    int guess(float input[])
    {
        float sum = 0;
        for(int i = 0; i < weight.length; i++)
        {
            sum += input[i]*weight[i];
        }
        
        return sign(sum);
    }
    
    
}
