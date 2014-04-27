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
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room northeastExit;
    private Room southwestExit;
    private Room southeastExit;
    private Room northwestExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * @param northeast The northeast exit.
     * @param southwest The southwest exit.
     * @param southeast The southeast exit.
     * @param northwest The northwest exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room northeast, Room southwest, Room southeast, Room northwest) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(northeast != null)
            northeastExit = northeast;
        if(southwest != null)
            southwestExit = southwest;
        if(southeast != null)
            southeastExit = southeast;
        if(northwest != null)
            northwestExit = northwest;
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
        if(direccion.equals("north")){
            out = northExit;
        }
        if(direccion.equals("east")){
            out = eastExit;
        }
        if(direccion.equals("south")){
            out = southExit;
        }
        if(direccion.equals("west")){
            out = westExit;
        }
        if(direccion.equals("northeast")){
            out = northeastExit;
        }
        if(direccion.equals("southwest")){
            out = southwestExit;
        }
        if(direccion.equals("southeast")){
            out = southeastExit;
        }
        if(direccion.equals("northwest")){
            out = northwestExit;
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
        String localizacion = "Exits: ";
        if(northExit != null) {
            localizacion = localizacion + " north ";
        }
        if(eastExit != null) {
            localizacion = localizacion + " east ";
        }
        if(southExit != null) {
            localizacion = localizacion + " south ";
        }
        if(westExit != null) {
            localizacion = localizacion + " west ";
        }
        if(northeastExit != null) {
            localizacion = localizacion + " northeast ";
        }
        if(southwestExit != null) {
            localizacion = localizacion + " southwest ";
        }
        if(southeastExit != null) {
            localizacion = localizacion + " southeast ";
        }
        if(northwestExit != null) {
            localizacion = localizacion + " northwest ";
        }
        return localizacion; 
    }
}
