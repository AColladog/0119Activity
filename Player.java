import java.util.ArrayList;
import java.util.Stack;
/**
 * Esta clase nos deja refactorizar el código anterior, creando aquí todo lo necesario para el jugador
 * Ponemos en esta clase todo lo referente al jugador, liberando código
 * @author (ACG) 
 * @version (1.1)
 */
public class Player
{
    private ArrayList<Item> itemsPlayer;
    private static final double PESO_MAXIMO = 50;
    private Room currentRoom;
    private Item item;
    private Stack<Room> habitacionAnterior;

    /**
     * Constructor for objects of class Playeri
     */
    public Player()
    {
        itemsPlayer = new ArrayList<>();
        habitacionAnterior = new Stack<>();
        item = null;
    }

    /**
     * Seleccionamos la habitación que será la de partida
     */
    public void setCurrentRoom(Room room){
        currentRoom = room;
    }

    /**
     * Nos devuelve el ArrayList de items que tiene el jugador
     */
    public ArrayList<Item> getItemsPlayer(){
        return itemsPlayer;
    }

    /**
     * Nos devuelve el peso total que tiene el jugador en el carrito
     */
    private double pesoTotal(double pesoItem){
        double total = 0;
        for(Item a : itemsPlayer){
            total += a.getPeso();
        }
        return (total + pesoItem);
    }

    /**
     * Compara que el peso true si es menor del límite y false en caso contrario
     */public boolean comparaPesos(double pesoItem){
        boolean menor = false;
        if(pesoTotal(pesoItem) < PESO_MAXIMO){
            menor = true;
        }
        return menor;
    }

    public void addItemsPlayer(Item item){
        itemsPlayer.add(item);
    } 

    public void printItemsPlayer(){
        if(itemsPlayer.size() == 0){
            System.out.println("El jugador no lleva nada en el carrito");
        }else{
            System.out.println("El jugador lleva en el carrito: ");        
            for(Item a : itemsPlayer){
                System.out.println(a.getItemDescription() + " \tQue pesa: " + a.getPeso());
            }
            System.out.println("El jugador arrastra: " + pesoTotal(0) + "Kg\tDe un máximo que puede pujar de: " + PESO_MAXIMO);
        }
    }

    public void removeItemsPlayer(Item item){
        itemsPlayer.remove(item);
    }

    public void printLocationInfo(){
        System.out.println();
        System.out.println(currentRoom.getLongDescription()); 
    }

    public void goBack(){
        if(habitacionAnterior.empty()){
            System.out.println("No ha habido desplazamiento previo");
        }else{
            currentRoom = habitacionAnterior.pop();
            printLocationInfo();
        }
    }

    public void takeItem(Command command){
        boolean existe = false;
        for(Item a : currentRoom.getItems()){
            if(a.getItemDescription().equals(command.getSecondWord())){
                existe = true;
                if(a.getCanTake()){
                    if(comparaPesos(a.getPeso())){
                        addItemsPlayer(a);
                        currentRoom.getItems().remove(a);
                        return;
                    }else{
                        System.out.println("Sobrepasa los límites del peso");
                    }
                }else{
                    System.out.println("Este item no se puede mover, es fijo");
                }
            }
        }
        if(!existe){
            System.out.println("Este item no existe en la habitación");            
        }
    } 

    public void dropItem(Command command){
        boolean existe = false;
        for(Item a : getItemsPlayer()){
            if(a.getItemDescription().equals(command.getSecondWord())){
                existe = true;
                currentRoom.getItems().add(a);
                removeItemsPlayer(a);     
                return;
            }
        }
        if(getItemsPlayer().size() == 0){
            System.out.println("No hay nada que dejar");
        }else{
            if(!existe){
                System.out.println("Ese item no se encuentra en posesión");
            }
        }
    }

    public void look(){
        printLocationInfo();
        for(Item a : currentRoom.getItems()){
            a.printItemInfo();  
        }       
    }

    public void habitacionAnterior(Room previous, Room next){
        habitacionAnterior.push(previous);
        currentRoom = next;
        printLocationInfo();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        Room nextRoom = currentRoom.getExit(command.getSecondWord());

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            habitacionAnterior.push(currentRoom);
            currentRoom = nextRoom;
            habitacionAnterior(currentRoom, nextRoom);
        }

    }
}
