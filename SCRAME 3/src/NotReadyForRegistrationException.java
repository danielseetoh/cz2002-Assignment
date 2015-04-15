
public class NotReadyForRegistrationException extends Exception{

    public NotReadyForRegistrationException(String object){

        super( object + " is not ready for registration!");
    }
}
