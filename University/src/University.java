/**
 * Created by danielseetoh on 3/31/2015.
 */
import java.util.*;

public class University {

    public List<Course> courseList = new ArrayList<Course>();
    public List<Student> studentList = new ArrayList<Student>();
    public List<Professor> professorList = new ArrayList<Professor>();

    public void addStudent(String studentName, String StudentID){
        Student student = new Student(studentName, StudentID);
        studentList.add(student);
    }

    public void addCourse(String courseName, int maxCapacity){
        Course course = new Course(courseName);
        course.setMaxCapacity(maxCapacity);
        courseList.add(course);
    }

    public Student getStudentByID(String studentID){
        Student result = null;
        for(int i = 0; i < studentList.size(); i++){
            if(studentList.get(i).getStudentID().equals(studentID))
                result = studentList.get(i);
        }
        return result;
    }

    public Course getCourseByName(String courseName){ //not using sem and year
        Course result = null;
        for(int i = 0; i<courseList.size(); i++){
            if(courseList.get(i).getCourseName().equals(courseName)){
                result = courseList.get(i);
            }
        }
        return result;
    }

    public void addProfessor(String professorName, String professorID){
        Professor professor = new Professor(professorName, professorID);
        professorList.add(professor);
    }

    public Professor getProfessorByName(String professorName){
        Professor result = null;
        for(int i = 0; i < professorList.size(); i++){
            if(professorList.get(i).getName().equals(professorName)){
                result = professorList.get(i);
            }
        }
        return result;
    }

}
