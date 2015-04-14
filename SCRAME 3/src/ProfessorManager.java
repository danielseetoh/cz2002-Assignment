/**
 * Created by danielseetoh on 4/14/2015.
 */
public class ProfessorManager {

    private ProfessorDB professorDB = new ProfessorDB();

    public String[] getProfessorNameList(){
        String[] professorNameList = new String[professorDB.getProfessorList().size()];
        for(int i = 0; i<professorNameList.length; i++){
            professorNameList[i] = professorDB.getProfessorList().get(i).getProfessorName();
        }
        return professorNameList;
    }

    public void addProfessor(String name){
        professorDB.addProfessor(name);
    }

}
