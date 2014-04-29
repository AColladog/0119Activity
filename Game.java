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

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
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
        conserjeria = new Room("conserjeria"); //outside
        cnp = new Room("comisaria"); //theater
        profes = new Room("profesores"); //pub
        fp2 = new Room("fp2");//lab
        fp1 = new Room("fp1");
        primero = new Room("primero");
        segundo = new Room("segundo");
        bachiller1 = new Room("bachiller1");
        bachiller2 = new Room("bachiller2");
        patio = new Room("patio");

        // initialise room exits
        // conserjeria n, e, s, w, ne, sw, se, nw
        conserjeria.setExit("southExit", cnp);
        conserjeria.setExit("northeastExit", profes);
        conserjeria.setExit("southwestExit", patio);
        //cnp
        cnp.setExit("northExit", conserjeria);
        //primero
        primero.setExit("northExit", segundo);
        primero.setExit("eastExit", fp2);
        primero.setExit("southExit", profes);
        primero.setExit("westExit", bachiller2);
        //fp2
        fp2.setExit("westExit", primero);
        //bachiller2
        bachiller2.setExit("eastExit", primero);
        //segundo
        segundo.setExit("eastExit", fp1);
        segundo.setExit("southExit", primero);
        segundo.setExit("westExit", bachiller1);
        //fp1
        fp1.setExit("westExit", segundo);
        //bachiller1
        bachiller1.setExit("eastExit", segundo);
        //patio
        patio.setExit("northwestExit", conserjeria);
        //profes
        profes.setExit("northExit", primero);
        profes.setExit("southwestExit", conserjeria);
        
        /**
         * cnp.setExits(conserjeria, null, null, null, null, null, null, null);
        profes.setExits(primero, null, null,null, null, conserjeria, null, null);
        primero.setExits(segundo, fp2, profes, bachiller2, null, null, null, null);
        fp2.setExits(null, null, null, primero, null, null, null, null);
        bachiller2.setExits(null, primero, null, null, null, null, null, null);
        segundo.setExits(null, fp1, primero, bachiller1, null, null, null, null);
        fp1.setExits(null, null, null, segundo, null, null, null, null);
        bachiller1.setExits(null, segundo, null, null, null, null, null, null);
        patio.setExits(null,null, null, null, null, null, null, conserjeria);
        
         */
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
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
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
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
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
        //System.out.println("You are " + currentRoom.getDescription());
        
        //System.out.println(currentRoom.getExitString());
        System.out.println(currentRoom.getLongDescription());
    }
}
