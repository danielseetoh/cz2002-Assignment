/**
 * Created by danielseetoh on 4/14/2015.
 */
import java.util.ArrayList;
import java.util.List;

public class StudentDB {

    private List<Student> studentList = new ArrayList<Student>();

    //GET METHODS
    //get the student object list
    public List<Student> getStudentList(){
        return studentList;
    }

    //get the student by the name
    private Student getStudentByName (String studentName) {
        for(int i = 0; i < studentList.size(); i++){
            Student currentStudent = studentList.get(i);
            if(currentStudent.getStudentName().equals(studentName)){
                return currentStudent;
            }
        }
        return null;
    }

    //get the student by the ID
    public int getStudentIDByName (String studentName){
        return getStudentByName(studentName).getStudentID();
    }



    //SET METHODS
    //add a student into the student object list
    public void addStudent (String studentName) {
        int newID = studentList.size()+1;
        studentList.add(new Student(studentName, newID));
    }

}

