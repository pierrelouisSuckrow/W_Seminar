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
    x = rand.nextInt(GUI.getweight());
    y = rand1.nextInt(GUI.getheight());
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

