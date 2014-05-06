import java.util.Stack;
import java.util.ArrayList;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> habitacionAnterior;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        habitacionAnterior = new Stack<>();
        itemsPlayer = new ArrayList<>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room conserjeria, cnp, profes, primero, segundo, fp2, fp1, bachiller1, bachiller2, patio;

        // create the rooms
        conserjeria = new Room("conserjeria");         
        cnp = new Room("comisaria"); 
        profes = new Room("profesores"); 
        fp2 = new Room("fp2");
        fp1 = new Room("fp1");
        primero = new Room("primero");
        segundo = new Room("segundo");
        bachiller1 = new Room("bachiller1");
        bachiller2 = new Room("bachiller2");
        patio = new Room("patio");
        // damos items a las rooms
        conserjeria.addItem("fotocopiadora", 45, true);
        cnp.addItem("cartera", 1.5, true);
        cnp.addItem("navaja", 0.3, true);
        profes.addItem("mesa", 80, false);
        fp2.addItem("silla", 7, true);
        fp2.addItem("tijera", 7, true);
        fp1.addItem("pda", 2, true);
        primero.addItem("maceta", 5, true);
        segundo.addItem("papelera", 0.5, true);
        bachiller1.addItem("tiza", 0.04, true);
        bachiller1.addItem("pizarra", 30, false);
        bachiller2.addItem("grapadora", 0.6, true);
        patio.addItem("porteria", 60, false);
        patio.addItem("canasta", 300, false);

        // initialise room exits
        // conserjeria n, e, s, w, ne, sw, se, nw
        conserjeria.setExit("south", cnp);
        conserjeria.setExit("northeast", profes);
        conserjeria.setExit("southwest", patio);
        //cnp
        cnp.setExit("north", conserjeria);
        //primero
        primero.setExit("north", segundo);
        primero.setExit("east", fp2);
        primero.setExit("south", profes);
        primero.setExit("west", bachiller2);
        //fp2
        fp2.setExit("west", primero);
        //bachiller2
        bachiller2.setExit("east", primero);
        //segundo
        segundo.setExit("east", fp1);
        segundo.setExit("south", primero);
        segundo.setExit("west", bachiller1);
        //fp1
        fp1.setExit("west", segundo);
        //bachiller1
        bachiller1.setExit("east", segundo);
        //patio
        patio.setExit("northwest", conserjeria);
        //profes
        profes.setExit("north", primero);
        profes.setExit("southwest", conserjeria);
        
        currentRoom = cnp;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();        
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            System.out.println();
            printLocationInfo();            
            printItemInfo();            
        }
        else if (commandWord.equals("eat")) {
            System.out.println("You have eaten now and you are not hungry any more");
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("back")) {
            goBack();
        }
        else if(commandWord.equals("take")){
            takeItem(command);
        }
        else if(commandWord.equals("items")){
            System.out.println();
            printItemsPlayer();
        }
        else if(commandWord.equals("drop")){
            dropItem(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        parser.imprimeComandos();
        System.out.println();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
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
            printLocationInfo();
        }
         
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
    }
    
    private void printItemInfo(){
        for(Item a : currentRoom.getItems()){
            System.out.println("Localizada: " + a.getItem() + " \tQue pesa: " + a.getPeso() + "Kg\tTransportable: " + a.getCanTake());
        }
        //System.out.println("Localizada: " + currentRoom.getItem() + " \tQue pesa: " + currentRoom.getPeso() + "Kg");
    }
    
    private void goBack(){
        if(habitacionAnterior.empty()){
            System.out.println("No ha habido desplazamiento previo");
        }else{
            currentRoom = habitacionAnterior.pop();
            printLocationInfo();
        }
    }
    
    private void takeItem(Command command){
        boolean existe = false;
        for(Item a : currentRoom.getItems()){
            if(a.getItem().equals(command.getSecondWord())){
                existe = true;
                if(a.getCanTake()){
                    if(pesoTotal(a.getPeso()) < PESO_MAXIMO){
                        itemsPlayer.add(a);
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
    
    private double pesoTotal(double pesoItem){
        double total = 0;
        for(Item a : itemsPlayer){
            total += a.getPeso();
        }
        return (total + pesoItem);
    }
    
    private void printItemsPlayer(){
        System.out.println("El jugador porta: ");
        for(Item a : itemsPlayer){
            System.out.println(a.getItem() + " \tQue pesa: " + a.getPeso());
        }
        System.out.println("El jugador arrastra: " + pesoTotal(0) + "Kg\tDe un máximo que puede pujar de: " + PESO_MAXIMO);
    }    
    
    private void dropItem(Command command){
        boolean existe = false;
        int count = 0;
        for(Item a : itemsPlayer){
            count++;
            if(a.getItem().equals(command.getSecondWord())){
                existe = true;
                currentRoom.getItems().add(a);
                itemsPlayer.remove(a);     
                return;
            }
        }
        if(count < 1){
            System.out.println("No hay nada que dejar");
        }else{
            if(!existe){
                System.out.println("Ese item no se encuentra en posesión");
            }
        }
    }
}
