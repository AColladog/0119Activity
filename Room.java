import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> location;
    private ArrayList<Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        location = new HashMap<>();
        items = new ArrayList<>();

    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor){
        location.put(direction, neighbor);
    }
    
    /**
     * creamos el ArrayList de items
     */
    public void addItem(String item, double peso, boolean canTake){
        items.add(new Item(item, peso, canTake));
    }
    
    /**
     * @return la colección de items
     */
    public ArrayList<Item> getItems(){
        return items;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }    

    /**
     * Toma como parámetro una cadena que representa una dirección.
     * @return el objeto de la clase Room asociado a esa salida o null si no hay salida.
     */
    public Room getExit(String direccion){        
        Room out = null;
        for(String a : location.keySet()){
            if(direccion.equals(a))
            {
                out = location.get(a);
            }
        }
        return out;
    }
    
    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String out = "";
        for(String a : location.keySet()){
            out += a + "  ";
        } 
        return out; 
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        String longDescription = "You are in the " + description +"\nExits: "+ getExitString();
        return longDescription;
    }   
}
