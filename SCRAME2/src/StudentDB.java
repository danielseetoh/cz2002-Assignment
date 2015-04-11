import java.util.ArrayList;
import java.util.List;

public class StudentDB {
    private List<Student> studentList = new ArrayList<Student>();

    //Getters
    public String[] getStudentNameList(){
        String[] studentNameList = new String[studentList.size()];
        for(int i = 0; i < studentList.size(); i++){
            studentNameList[i] = studentList.get(i).getStudentName();
        }
        return studentNameList;
    }

    private Student getStudentByName (String studentName) {
        for(int i = 0; i < studentList.size(); i++){
            Student currentStudent = studentList.get(i);
            if(currentStudent.getStudentName().equals(studentName)){
                return currentStudent;
            }
        }
        return null;
    }

    public int getStudentIDByName (String studentName){
        return getStudentByName(studentName).getStudentID();
    }

    //Setters
    public void addStudent (String studentName) {
        int newID = studentList.size();
        studentList.add(new Student(studentName, newID));
    }




    //Verifiers
    public boolean isExistingStudentName (String studentName){
        boolean result = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentName().equals(studentName)) {
                result = true;
            }
        }
        return result;
    }
}
