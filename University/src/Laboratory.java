/**
 * Created by danielseetoh on 3/31/2015.
 */
public class Laboratory extends Lesson{

    private int labID;

    public Laboratory(int labID, int maxCapacity){
        this.labID = labID;
        super.maxCapacity = maxCapacity;
        super.vacancies = maxCapacity;
        super.lessonType = "Lab";
    }
    public int getID(){
        return labID;
    }

}
