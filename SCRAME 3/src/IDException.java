
public class IDException extends Exception {

    public IDException(){

        super("The ID entered is not a valid ID!");

    }

    public IDException(String object){

        super("The " + object + " ID entered is not a valid ID!");
    }

}//this is an exception class to handle exceptions for any IDs in the program that is not yet in the database
