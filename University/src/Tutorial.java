/**
 * Created by danielseetoh on 3/31/2015.
 */
public class Tutorial extends Lesson{

    public Tutorial(int tutorialID, int maxCapacity){
        super.ID = tutorialID;
        super.maxCapacity = maxCapacity;
        super.vacancies = maxCapacity;
        super.lessonType = "Tutorial";
    }

}
