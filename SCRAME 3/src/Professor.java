/**
 *Store and retrieves professor information
 */
public class Professor {

    private int professorID;
    private String professorName;



    //CONSTRUCTOR

    /**
     * Constructor for professor
     * @param professorName name of professor
     * @param professorID identification number of professor
     */
    public Professor (String professorName, int professorID) {
        this.professorName = professorName;
        this.professorID = professorID;
    }

    //GET METHODS

    /**
     * Retrieves identification number of professor
     * @return professor identification number
     */
    public int getProfessorID() {
        return professorID;
    }

    /**
     * Retrieves professor name
     * @return professor name
     */
    public String getProfessorName() {
        return professorName;
    }
}
