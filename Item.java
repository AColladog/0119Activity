import java.util.ArrayList;
/**
 * Aquí crearemos una clase donde instanciaremos los items 
 * @author ACG 
 * @version 1.1
 */
public class Item
{
    private double peso;
    private String item;
    private boolean canTake;

    /**
     * Constructor for objects of class Item
     */
    public Item(String item, double peso, boolean canTake)
    {
        // initialise instance variables
        this.item = item;
        this.peso = peso;
        this.canTake = canTake;
    }

    /**
     * @return  el String del item 
     */
    public String getItem()
    {   
        return item;
    }
    
    /**
     * @return el valor del peso en Kg
     */
    public double getPeso(){
        return peso;
    }
    
    /**
     * @return si se puede coger, TRUE
     */
    public boolean getCanTake(){
        return canTake;
    }
}
