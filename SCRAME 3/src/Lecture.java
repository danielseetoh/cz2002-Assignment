/**
 * class handling lecture ID and capacity
 */
public class Lecture extends Lesson {

    //constructor
    public Lecture(int ID, int capacity){
        super(ID,capacity);     //using Lesson's constructor
        this.name = "Lecture";
    }
}
