/**
 * Created by danielseetoh on 3/31/2015.
 */
public class Professor {

    private String professorName;
    private String professorID;
    private Course course; //new attribute, professor's course

    public Professor(String professorName, String professorID){
        this.professorName = professorName;
        this.professorID = professorID;
    }

    public String getName(){
        return professorName;
    }

    public String getProfessorID() {
        return professorID;
    }
}
