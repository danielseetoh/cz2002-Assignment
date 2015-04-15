
public class DuplicateException extends Exception{
    public DuplicateException(String object){

        super( object + " is a duplicate!");
    }
}
