/**
 * Created by danielseetoh on 3/31/2015.
 */
import java.util.*;

public class University {

    public List<Course> courseList = new ArrayList<Course>();
    public List<Student> studentList = new ArrayList<Student>();
    public List<Professor> professorList = new ArrayList<Professor>();

    public void addStudent(String studentName, int StudentID){
        Student student = new Student(studentName, StudentID);
        studentList.add(student);
    }

    public void addCourse(String courseName){
        Course course = new Course(courseName);
        courseList.add(course);
    }

    public Student getStudentByID(int studentID){
        for(int i = 0; i<studentList.size(); i++){
            if(studentList.get(i).getID() == studentID){
                return studentList.get(i);
            }
        }
        return null;
    }

    public Course getCourseByName(String courseName){ //not using sem and year
        for(int i = 0; i<courseList.size(); i++){
            if(courseList.get(i).getCourseName() == courseName){
                return courseList.get(i);
            }
        }

        return null;
    }

    public void addProfessor(String professorName, String professorID){
        Professor professor = new Professor(professorName, professorID);
        professorList.add(professor);
    }

    public Professor getProfessorByName(String professorName){
        for(int i = 0; i<professorList.size(); i++){
            if(professorList.get(i).getName()==professorName){
                return professorList.get(i);
            }
        }

        return null;
    }

}
