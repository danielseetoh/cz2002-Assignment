/**
 * Created by danielseetoh on 4/14/2015.
 */
import java.util.ArrayList;
import java.util.List;

public class ProfessorDB {

    private List<Professor> professorList = new ArrayList<Professor>();

    public List<Professor> getProfessorList(){
        return professorList;
    }

    //Setter
    public void addProfessor (String professorName) {
        int newID = professorList.size()+1;
        professorList.add(new Professor(professorName, newID));
    }



    //Verifier
    public boolean isExistingProfessorName (String professorName){
        boolean result = false;
        for (int i = 0; i < professorList.size(); i++) {
            if (professorList.get(i).getProfessorName().equals(professorName)) {
                result = true;
            }
        }
        return result;
    }
}
