
public class Professor {

    //Single Responsibility: To store and retrieve professor information

    private int professorID;
    private String professorName;

    public Professor (String professorName, int professorID) {
        this.professorName = professorName;
        this.professorID = professorID;
    }

    public int getProfessorID() {
        return professorID;
    }

    public String getProfessorName() {
        return professorName;
    }
}
