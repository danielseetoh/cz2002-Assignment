
public class FullException extends Exception {

    public FullException(){

        super("This course is already full!");

    }

    public FullException(String message){

        super(message);

    }
}
