/**
 * Created by MelSng on 14/4/2015.
 */
public class RecordNotFoundException extends Exception{
    public RecordNotFoundException(){

        super("The Record is not found!");

    }

    public RecordNotFoundException(String object){

        super("The " + object + " Record is not found!");
    }


}
