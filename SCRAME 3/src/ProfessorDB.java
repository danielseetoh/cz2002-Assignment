
import java.util.ArrayList;
import java.util.List;

public class ProfessorDB {

    private List<Professor> professorList = new ArrayList<Professor>();



    //GET METHODS
    //get professorList
    public List<Professor> getProfessorList(){
        return professorList;
    }



    //SET METHODS
    //add professor to professorList
    public void addProfessor (String professorName) {
        int newID = professorList.size()+1;
        professorList.add(new Professor(professorName, newID));
    }

}
