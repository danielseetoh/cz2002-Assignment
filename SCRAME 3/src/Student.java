/**
 * Created by danielseetoh on 4/14/2015.
 */

public class Student {

    // Single Responsibility: To store and retrieve student information

    private int studentID;
    private String studentName;

    public Student (String studentName, int studentID) {
        this.studentName = studentName;
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }
}
