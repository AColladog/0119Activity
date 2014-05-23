
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option{
    GO("ir"), QUIT("cerrar"), HELP("ayuda"), LOOK("mirar"), EAT("comer"), BACK("atras"), ITEMS("objetos"),
    TAKE("coger"), DROP("dejar"), UNKNOWN("");
    
    private String command;
    
    /**
     * constructor
     * @param command un String, que inicializa el atributo command
     */
    Option(String command){
        this.command = command;
    }
    
    /**
     * return el String de la clase enum
     */
    public String getCommand(){
        return command;
    }
}
