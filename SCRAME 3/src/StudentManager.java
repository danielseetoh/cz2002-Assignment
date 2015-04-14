/**
 * Created by danielseetoh on 4/14/2015.
 */
//done
public class StudentManager {

    private StudentDB studentDB = new StudentDB();


    //getters
    public String[] getStudentNameList(){
        String[] studentNameList = new String[studentDB.getStudentList().size()];
        for(int i = 0; i < studentDB.getStudentList().size(); i++){
            studentNameList[i] = studentDB.getStudentList().get(i).getStudentName();
        }
        return studentNameList;
    }

    // adds student into database
    public int addStudent(String name){
        studentDB.addStudent(name);
        int id = studentDB.getStudentList().size();
        return id;
    }

    public void printStudentList(){
        for(int i = 0; i<studentDB.getStudentList().size(); i++){
            System.out.println("ID: " + studentDB.getStudentList().get(i).getStudentID() + " Name: " + studentDB.getStudentList().get(i).getStudentName());
        }
    }

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
