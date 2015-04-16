/**
 * handles the logic dealing with student data
 */
public class StudentManager {

    /**
     * TODO: javadoc
     */
    private StudentDB studentDB = new StudentDB();

    //GET METHODS

    /**
     * Retrieves list of all student names
     * @return string array of the student's names
     */
    public String[] getStudentNameList(){
        String[] studentNameList = new String[studentDB.getStudentList().size()];
        for(int i = 0; i < studentDB.getStudentList().size(); i++){
            studentNameList[i] = studentDB.getStudentList().get(i).getStudentName();
        }
        return studentNameList;
    }

    /**
     * prints out the student IDs and student names
     */
    public void printStudentList(){
        System.out.printf(" ID\tStudent Name\n");
        for(int i = 0; i<studentDB.getStudentList().size(); i++){
            System.out.printf("%3d\t%-30s\n", studentDB.getStudentList().get(i).getStudentID(), studentDB.getStudentList().get(i).getStudentName().toUpperCase());
        }
    }

    /**
     * Retrieves student name using identification number
     * @param studentID identification number of student
     * @return student name
     */
    public String getStudentNameByID(int studentID){
        if(studentDB.getStudentList() == null){
            return null;
        } else if (studentDB.getStudentList().get(studentID-1) == null){
            return null;
        } else {
            return studentDB.getStudentList().get(studentID - 1).getStudentName();
        }
    }



    //SET METHODS

    /**
     * adds student into database
     * @param name student name
     * @return identification number of student
     */
    public int addStudent(String name){
        studentDB.addStudent(name);
        int id = studentDB.getStudentList().size();
        return id;
    }

    //METHODS FOR CHECKING

    /**
     * checks if the given student ID already exists in the database
     * @param studentID identification number of student
     * @return true if student ID already exists in database
     */
    public boolean isExistingStudentID(int studentID){
        boolean result = false;
        for(int i = 0; i<studentDB.getStudentList().size(); i++){
            if(studentDB.getStudentList().get(i).getStudentID() == studentID){
                result = true;
            }
        }
        return result;
    }

    /**
     * checks if the given student name already exists in the database
     * @param studentName student name
     * @return true if name already exists in database
     */
    public boolean isExistingStudentName(String studentName){
        if(studentDB.getStudentIDByName(studentName) <= 0){
            return false;
        } else {
            return true;
        }
    }
}
