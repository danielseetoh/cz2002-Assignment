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

    public int addProfessor(String name){
        professorDB.addProfessor(name);
        int id = professorDB.getProfessorList().size();
        return id;
    }

    public void printProfessorList(){
        for(int i = 0; i<professorDB.getProfessorList().size(); i++){
            System.out.println("ID: " + professorDB.getProfessorList().get(i).getProfessorID() + " Name: "
                    + professorDB.getProfessorList().get(i).getProfessorName());
        }
    }

}
