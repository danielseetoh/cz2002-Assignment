
public class IDException extends Exception {

    public IDException(){

        super("The ID entered is not a valid ID!");

    }

    public IDException(String message){

        super(message);
    }

}
