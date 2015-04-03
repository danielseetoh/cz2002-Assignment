/**
 * Created by danielseetoh on 3/31/2015.
 */
public class Lecture extends Lesson{

    private int lectureID;

    public Lecture(int lectureID, int maxCapacity){
        this.lectureID = lectureID;
        super.maxCapacity = maxCapacity;
        super.vacancies = maxCapacity;
        super.lessonType = "Lecture";
    }
    public int getID(){
        return lectureID;
    }
}
