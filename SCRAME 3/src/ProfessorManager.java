/**
 * Created by danielseetoh on 4/14/2015.
 */
//done
public class ProfessorManager {

    private ProfessorDB professorDB = new ProfessorDB();



    //GET METHODS
    //get a string array of the names of professors in the database
    public String[] getProfessorNameList(){
        String[] professorNameList = new String[professorDB.getProfessorList().size()];
        for(int i = 0; i<professorNameList.length; i++){
            professorNameList[i] = professorDB.getProfessorList().get(i).getProfessorName();
        }
        return professorNameList;
    }

    //print out the list of professor IDs and professor names
    public void printProfessorList(){
        System.out.printf("Professor ID\tProfessor Name");
        for(int i = 0; i<professorDB.getProfessorList().size(); i++){
            System.out.printf("%12d\t%-30s",professorDB.getProfessorList().get(i).getProfessorID(), professorDB.getProfessorList().get(i).getProfessorName());
        }
    }



    //SET METHODS
    //add a professor into the database
    public int addProfessor(String name){
        professorDB.addProfessor(name);
        int id = professorDB.getProfessorList().size();
        return id;
    }



    //METHODS FOR CHECKING
    //checks if the given professorID is already existing in the database
    public boolean isExistingProfessorID (int professorID){
        boolean result = false;
        for(int i = 0; i < professorDB.getProfessorList().size(); i++){
            if (professorDB.getProfessorList().get(i).getProfessorID() == professorID){
                result = true;
            }
        }
        return result;
    }

}
