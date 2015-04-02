/**
 * Created by danielseetoh on 3/31/2015.
 */
public class Tutorial extends Lesson{

    private int tutorialID;
    private int maxCapacity;
    private String type = "Tutorial";

    public Tutorial(int tutorialID, int maxCapacity){
        this.tutorialID = tutorialID;
        this.maxCapacity = maxCapacity;
    }

    public int getID(){
        return tutorialID;
    }


}
