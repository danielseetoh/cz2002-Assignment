/**
 * Exception message for when object already exists in database
 */
public class DuplicateException extends Exception{
    public DuplicateException(String object){

        super( object + " is a duplicate!");
    }
}
