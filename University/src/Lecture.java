/**
 * Created by danielseetoh on 3/31/2015.
 */
public class Lecture extends Lesson{

    public Lecture(int lectureID, int maxCapacity){
        this.ID = lectureID;
        super.maxCapacity = maxCapacity;
        super.vacancies = maxCapacity;
        super.lessonType = "Lecture";
    }

}
