/**
 * Handles the logic dealing with the professor data
 */
public class ProfessorManager {

    private ProfessorDB professorDB = new ProfessorDB();

    //GET METHODS

    /**
     * Retrieves the names of all professors
     * @return string array of the names of professors in the database
     */
        public String[] getProfessorNameList(){
        String[] professorNameList = new String[professorDB.getProfessorList().size()];
        for(int i = 0; i<professorNameList.length; i++){
            professorNameList[i] = professorDB.getProfessorList().get(i).getProfessorName();
        }
        return professorNameList;
    }

    /**
     * print out the list of professor IDs and professor names
     */
    public void printProfessorList(){
        System.out.printf(" ID\tProfessor Name\n");
        for(int i = 0; i<professorDB.getProfessorList().size(); i++){
            System.out.printf("%3d\t%-30s\n",professorDB.getProfessorList().get(i).getProfessorID(), professorDB.getProfessorList().get(i).getProfessorName().toUpperCase());
        }
    }

    //SET METHODS

    /**
     * add a professor into the database
     * @param name name of the professor
     * @return the given identification number of the added professor
     */
    public int addProfessor(String name){
        professorDB.addProfessor(name);
        int id = professorDB.getProfessorList().size();
        return id;
    }

    //METHODS FOR CHECKING

    /**
     * checks if the given professorID already exists in the database
     * @param professorID Identification number of the professor
     * @return true if professorID already exists in database
     */
    public boolean isExistingProfessorID (int professorID){
        boolean result = false;
        for(int i = 0; i < professorDB.getProfessorList().size(); i++){
            if (professorDB.getProfessorList().get(i).getProfessorID() == professorID){
                result = true;
            }
        }
        return result;
    }

    /**
     * checks if the given professor name already exists in the database
     * @param professorName name of the professor
     * @return true if the professorName already exists in database
     */
    public boolean isExistingProfessorName (String professorName){
        for(int i = 0; i < getProfessorNameList().length; i++){
            if(getProfessorNameList()[i].equals(professorName))
                return true;
        }
        return false;
    }

}
