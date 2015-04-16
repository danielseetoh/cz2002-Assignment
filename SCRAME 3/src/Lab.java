/**
 * class handling lab ID and capacity
 */
public class Lab extends Lesson{

    //constructor
    public Lab(int ID, int capacity){
        super(ID,capacity);      //using Lesson's constructor
        this.name = "Lab";
    }
}
