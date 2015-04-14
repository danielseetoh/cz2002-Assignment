/**
 * Created by danielseetoh on 4/14/2015.
 */
public class NotSufficientException extends Exception {

    public NotSufficientException(){

        super("The number entered is not sufficient.");

    }

    public NotSufficientException(String object){

        super("The number of " + object + " is not sufficient.");
    }
}
