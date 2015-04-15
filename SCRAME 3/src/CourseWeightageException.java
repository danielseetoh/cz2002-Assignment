/**
 * Created by danielseetoh on 4/15/2015.
 */
public class CourseWeightageException extends Exception{

    public CourseWeightageException(){
        super("The course component weightage has already been set.");
    }
}
