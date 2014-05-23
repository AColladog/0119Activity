/**
 * Aquí crearemos una clase donde instanciaremos los items 
 * @author ACG 
 * @version 1.1
 */
public class Item
{
    private double peso;
    private String itemDescription;
    private boolean canTake;
    private boolean magic;

    /**
     * Constructor for objects of class Item
     */
    public Item(String itemDescription, double peso, boolean canTake, boolean magic)
    {
        // initialise instance variables
        this.itemDescription = itemDescription;
        this.peso = peso;
        this.canTake = canTake;
        this.magic = magic;
    }

    /**
     * @return  el String del item 
     */
    public String getItemDescription()
    {   
        return itemDescription;
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
    
    public void printItemInfo(){        
        System.out.println("Localizada: " + getItemDescription() + " \tQue pesa: " + getPeso() + "Kg\tTransportable: " + getCanTake()); 
    }
    
    /**
     * @return si es un objeto magico, TRUE
     */
    public boolean getIsMagic(){
        return magic;
    }
}
