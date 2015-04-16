
import java.util.ArrayList;
import java.util.List;

/**
 * handles student data
 */
public class StudentDB {

    private List<Student> studentList = new ArrayList<Student>();

    //GET METHODS

    /**
     * Retrieve the list of all students
     * @return student object list
     */
    public List<Student> getStudentList(){
        return studentList;
    }

    /**
     * Retrieve student information by name
     * @param studentName student name
     * @return student name
     */
    private Student getStudentByName (String studentName) {
        for(int i = 0; i < studentList.size(); i++){
            Student currentStudent = studentList.get(i);
            if(currentStudent.getStudentName().equals(studentName)){
                return currentStudent;
            }
        }
        return null;
    }

    /**
     * Retrieve student information by identification number
     * @param studentName student name
     * @return student identification number
     */
    public int getStudentIDByName (String studentName){
        if(getStudentByName(studentName) == null){
            return -1;
        }
        return getStudentByName(studentName).getStudentID();
    }

    //SET METHODS

    /**
     * add a student into the student object list
     * @param studentName student name
     */
    public void addStudent (String studentName) {
        int newID = studentList.size()+1;
        studentList.add(new Student(studentName, newID));
    }

}

