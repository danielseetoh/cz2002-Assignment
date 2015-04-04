/**
 * Created by danielseetoh on 3/31/2015.
 */
public class Laboratory extends Lesson{

    public Laboratory(int labID, int maxCapacity){
        super.ID = labID;
        super.maxCapacity = maxCapacity;
        super.vacancies = maxCapacity;
        super.lessonType = "Lab";
    }

}
