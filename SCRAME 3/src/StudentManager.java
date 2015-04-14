/**
 * Created by danielseetoh on 4/14/2015.
 */
public class StudentManager {

    private StudentDB studentDB = new StudentDB();

    public String[] getStudentNameList(){
        String[] studentNameList = new String[studentDB.getStudentList().size()];
        for(int i = 0; i < studentDB.getStudentList().size(); i++){
            studentNameList[i] = studentDB.getStudentList().get(i).getStudentName();
        }
        return studentNameList;
    }

    public void addStudent(String name){
        studentDB.addStudent(name);
    }
}
