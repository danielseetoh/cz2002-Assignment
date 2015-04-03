/**
 * Created by danielseetoh on 3/31/2015.
 */
public class Tutorial extends Lesson{

    private int tutorialID;

    public Tutorial(int tutorialID, int maxCapacity){
        this.tutorialID = tutorialID;
        super.maxCapacity = maxCapacity;
        super.vacancies = maxCapacity;
        super.lessonType = "Tutorial";
    }
    public int getID(){
        return tutorialID;
    }
}
