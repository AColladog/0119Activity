import java.util.ArrayList;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    private Option option;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {

    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString){        
        for(Option key : option.values()) {
            if(key.getCommand().equals(aString))
                if(key != Option.UNKNOWN){
                    return true;
            }
        }
        return false;
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        System.out.println("Valid command words: ");
        for(Option command : option.values()){
            if(command != Option.UNKNOWN){
                System.out.print(command.getCommand() + " ");
            }
        }        
    }

    /**
     * Return the Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return The Option correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public Option getCommandWord(String commandWord){
        Option wordOption = Option.UNKNOWN;
        if(isCommand(commandWord)){
            for(Option key : option.values()) {
                if(key.getCommand().equals(commandWord)){
                    wordOption = key;
                }
            }
        }
        return wordOption;
    }
}
