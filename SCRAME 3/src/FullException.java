/**
 * Exception class for when course is full
 */
public class FullException extends Exception {

    public FullException(){

        super("This course is already full!");

    }

}
