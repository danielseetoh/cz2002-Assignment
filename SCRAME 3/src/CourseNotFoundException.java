/**
 * Created by KianBoon on 14/4/2015.
 */
public class CourseNotFoundException extends Exception{
    public CourseNotFoundException(){

        super("The Course is not found!");

    }

    public CourseNotFoundException(String object){

        super("The " + object + " Course is not found!");
    }


}