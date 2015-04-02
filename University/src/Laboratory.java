/**
 * Created by danielseetoh on 3/31/2015.
 */
public class Laboratory extends Lesson{

    private int labID;
    private int maxCapacity;
    private String type = "Laboratory";

    public Laboratory(int labID, int maxCapacity){
        this.labID = labID;
        this.maxCapacity = maxCapacity;
    }

    public int getID(){
        return labID;
    }

}
