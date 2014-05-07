import java.util.ArrayList;
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
    
    private double pesoTotal(double pesoItem){
        double total = 0;
        for(Item a : itemsPlayer){
            total += a.getPeso();
        }
        return (total + pesoItem);
    }
    
    public boolean comparaPesos(double pesoItem){
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
        System.out.println("El jugador porta: ");
        for(Item a : itemsPlayer){
            System.out.println(a.getItemDescription() + " \tQue pesa: " + a.getPeso());
        }
        System.out.println("El jugador arrastra: " + pesoTotal(0) + "Kg\tDe un máximo que puede pujar de: " + PESO_MAXIMO);
    }
    
    public void removeItemsPlayer(Item item){
        itemsPlayer.remove(item);
    }
}
