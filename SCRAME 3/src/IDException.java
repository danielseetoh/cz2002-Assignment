
public class IDException extends Exception {

    public IDException(){

        super("The ID entered is not a valid ID!");

    }

    public IDException(String object){

        super("The " + object + " ID entered is not a valid ID!");
    }

}
