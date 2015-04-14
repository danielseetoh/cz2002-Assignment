/**
 * Created by MelSng on 14/4/2015.
 */
public class NotReadyForRegistrationException extends Exception{

    public NotReadyForRegistrationException(String object){

        super( object + " is not ready for registration!");
    }
}
