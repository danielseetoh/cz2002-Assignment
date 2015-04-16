/**
 * exception to handle invalid values
 */
public class NotSufficientException extends Exception {

    public NotSufficientException(){
        super("The amount entered is not sufficient");
    }

    public NotSufficientException(String object){
        super("The number of " + object + " is not sufficient.");
    }
}
