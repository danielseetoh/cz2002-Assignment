/**
 * Created by danielseetoh on 4/14/2015.
 */

public class Professor {

    private int professorID;
    private String professorName;



    //CONSTRUCTOR
    public Professor (String professorName, int professorID) {
        this.professorName = professorName;
        this.professorID = professorID;
    }



    //GET METHODS
    //get professor's ID for this instance
    public int getProfessorID() {
        return professorID;
    }

    //get professor's name for this instance
    public String getProfessorName() {
        return professorName;
    }
}
