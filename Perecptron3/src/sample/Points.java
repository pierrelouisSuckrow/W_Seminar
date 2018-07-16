package sample;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 15.07.2018
 * @author
 */
import java.util.Random;


public class Points {

    // Anfang Attribute
    private float x;
    private float y;
    private int state;
    // Ende Attribute

    public Points() {
        Random rand = new Random();
        Random rand1 = new Random();
        x = rand.nextInt(Main.getweight()) + 1;
        y = rand1.nextInt(Main.getheight()) + 1;
        if(x > y){
            state = 1;
        }else{
            state = -1;
        }
    }

    // Anfang Methoden
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getState() {
        return state;
    }

    // Ende Methoden
} // end of Points


