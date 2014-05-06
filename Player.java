import java.util.ArrayList;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private ArrayList<Item> itemsPlayer;
    private static final double PESO_MAXIMO = 50;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        itemsPlayer = new ArrayList<>();
    }
    
    public ArrayList<Item> getItemsPlayer(){
        return itemsPlayer;
    }
}
