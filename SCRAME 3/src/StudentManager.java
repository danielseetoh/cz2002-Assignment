/**
 * Created by danielseetoh on 4/14/2015.
 */
//done
public class StudentManager {

    private StudentDB studentDB = new StudentDB();



    //GET METHODS
    //get a string array of the student's names
    public String[] getStudentNameList(){
        String[] studentNameList = new String[studentDB.getStudentList().size()];
        for(int i = 0; i < studentDB.getStudentList().size(); i++){
            studentNameList[i] = studentDB.getStudentList().get(i).getStudentName();
        }
        return studentNameList;
    }

    //prints out the student IDs and student names
    public void printStudentList(){
        System.out.printf(" ID\tStudent Name\n");
        for(int i = 0; i<studentDB.getStudentList().size(); i++){
            System.out.printf("%3d\t%-30s\n", studentDB.getStudentList().get(i).getStudentID(), studentDB.getStudentList().get(i).getStudentName());
        }
    }

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
    //adds student into database
    public int addStudent(String name){
        studentDB.addStudent(name);
        int id = studentDB.getStudentList().size();
        return id;
    }



    //METHODS FOR CHECKING
    //checks if the given student ID already exists in the database
    public boolean isExistingStudentID(int studentID){
        boolean result = false;
        for(int i = 0; i<studentDB.getStudentList().size(); i++){
            if(studentDB.getStudentList().get(i).getStudentID() == studentID){
                result = true;
            }
        }
        return result;
    }

}
