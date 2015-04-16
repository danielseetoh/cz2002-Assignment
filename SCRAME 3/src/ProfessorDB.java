
import java.util.ArrayList;
import java.util.List;

/**
 * handles the professor information
 */
public class ProfessorDB {

    private List<Professor> professorList = new ArrayList<Professor>();



    //GET METHODS

    /**
     * Retrieves list of professors
     * @return list of professors
     */
    public List<Professor> getProfessorList(){
        return professorList;
    }

    //SET METHODS

    /**
     * add professor to professorList
     * @param professorName name of the professor
     */
    public void addProfessor (String professorName) {
        int newID = professorList.size()+1;
        professorList.add(new Professor(professorName, newID));
    }

}
