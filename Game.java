//import java.util.Stack;
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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();       
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
        conserjeria.addItem("fotocopiadora", 45, true, false);
        cnp.addItem("cartera", 1.5, true, false);
        cnp.addItem("navaja", 0.3, true, false);
        profes.addItem("mesa", 80, false, false);
        fp2.addItem("silla", 7, true, false);
        fp2.addItem("tijera", 7, true, false);
        fp1.addItem("pda", 2, true, false);
        primero.addItem("maceta", 5, true, false);
        segundo.addItem("papelera", 0.5, true, false);
        bachiller1.addItem("tiza", 0.04, true, false);
        bachiller1.addItem("pizarra", 30, false, false);
        bachiller2.addItem("grapadora", 0.6, true, false);
        patio.addItem("porteria", 60, false, false);
        patio.addItem("canasta", 300, false, false);
        Room[] habitaciones = {conserjeria, cnp, profes, primero, segundo, fp2, fp1, bachiller1, bachiller2, patio};
        for(int i = 0; i < habitaciones.length; i++){
            habitaciones[i].addItem("magicCard", 0.001, false, true);
        }

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
        
        player.setCurrentRoom(cnp);  // start game outside
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
        player.printLocationInfo();
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

        Option commandWord = command.getCommandWord();
        switch (commandWord){
        case HELP:
             printHelp();
             break;
        case GO:
             player.goRoom(command);
             break;
        case LOOK:
             player.look();
             break;
        case EAT:
             System.out.println("You have eaten now and you are not hungry any more");
             break;
        case BACK:
             player.goBack();
             break;
        case ITEMS:
             System.out.println();
             player.printItemsPlayer();
             break;
        case TAKE:
             player.takeItem(command);
             break;
        case QUIT:
             wantToQuit = quit(command);
             break;
        case DROP:
             player.dropItem(command);
             break;
        }
        return wantToQuit;
    }
    
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
}
