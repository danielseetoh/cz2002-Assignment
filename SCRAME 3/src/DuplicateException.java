/**
 * Created by MelSng on 14/4/2015.
 */
public class DuplicateException extends Exception{
    public DuplicateException(String object){

        super( object + " is a duplicate!");
    }
}
