import java.util.ArrayList;
import java.util.List;

public class University {

    // Single Responsibility: to store and retrieve student data, course data, record data

    private List<Student> studentList = new ArrayList<Student>();
    private List<Course> courseList = new ArrayList<Course>();
    private List<Record> recordList = new ArrayList<Record>();
    private List<Professor> professorList = new ArrayList<Professor>();


    //Getters
    public String[] getStudentNameList(){
        String[] studentNameList = new String[studentList.size()];
        for(int i = 0; i < studentList.size(); i++){
            studentNameList[i] = studentList.get(i).getStudentName();
        }
        return studentNameList;
    }

    public Course getCourseByName (String courseName) {
        for(int i = 0; i < courseList.size(); i++){
            Course currentCourse = courseList.get(i);
            if(currentCourse.getCourseName().equals(courseName)){
                return currentCourse;
            }
        }
        return null;
    }

    public Student getStudentByName (String studentName) {
        for(int i = 0; i < studentList.size(); i++){
            Student currentStudent = studentList.get(i);
            if(currentStudent.getStudentName().equals(studentName)){
                return currentStudent;
            }
        }
        return null;
    }

    public Record[] getRecordsByStudentName(String studentName){
        int counter = 0;
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentName().equals(studentName)){
                counter++;
            }
        }
        Record[] records = new Record[counter];
        counter = 0;
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentName().equals(studentName)){
                records[counter] = currentRecord;
                counter++;
            }
        }
        return records;
    }

    public Record getRecord (String courseName, String studentName){
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentName().equals(studentName) && currentRecord.getCourseName().equals(courseName)){
                return currentRecord;
            }
        }
        return null;
    }


    //Setters
    public boolean addStudent (String studentName) {
        if(existingStudentName(studentName)){
            return false;
        } else {
            int newID = studentList.size();
            studentList.add(new Student(studentName, newID));
            return true;
        }
    }

    public boolean addCourse (String courseName, String professorName, int capacity,  int numLectures, int numTutorials, int numLabs) {
        if(existingCourseName(courseName)){
            return false;
        } else if(!existingProfessorName(professorName)){
            return false;
        } else {
            int newID = courseList.size();
            courseList.add(new Course(courseName, professorName, capacity, numLectures, numTutorials, numLabs, newID));
            return true;
        }
    }

    public boolean addRecord (String courseName, String studentName, int lectureChoice, int tutorialChoice, int labChoice) {
        if(existingRecord(courseName, studentName)){
            return false;
        } else {
            recordList.add(new Record(courseName, studentName, lectureChoice, tutorialChoice, labChoice));
            return true;
        }
    }

    public boolean addProfessor (String professorName) {
        if(existingProfessorName(professorName)){
            return false;
        } else {
            int newID = professorList.size();
            professorList.add(new Professor(professorName, newID));
            return true;
        }
    }



    //private methods
    private boolean existingStudentName (String studentName){
        boolean result = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentName().equals(studentName)) {
                result = true;
            }
        }
        return result;
    }

    private boolean existingCourseName (String courseName) {
        boolean result = false;
        for(int i = 0; i < courseList.size(); i++){
            if(courseList.get(i).getCourseName().equals(courseName)){
                result = true;
            }
        }
        return result;
    }

    private boolean existingRecord (String courseName, String studentName) {
        boolean result = false;
        for (int i = 0; i < recordList.size(); i++) {
            Record currentRecord = recordList.get(i);
            if (currentRecord.getCourseName().equals(courseName) && currentRecord.getStudentName().equals(studentName)) {
                result = true;
            }
        }
        return result;
    }

    private boolean existingProfessorName (String professorName){
        boolean result = false;
        for (int i = 0; i < professorList.size(); i++) {
            if (professorList.get(i).getProfessorName().equals(professorName)) {
                result = true;
            }
        }
        return result;
    }

}
