/**
 * Created by danielseetoh on 3/31/2015.
 */
public class Lecture extends Lesson{

    private int lectureID;
    private int maxCapacity;
    private String type = "Lecture";

    public Lecture(int lectureID, int maxCapacity){
        this.lectureID = lectureID;
        this.maxCapacity = maxCapacity;
    }

    public int getID(){
        return lectureID;
    }


}
