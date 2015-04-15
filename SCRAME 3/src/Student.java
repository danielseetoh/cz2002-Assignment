/**
 * Created by danielseetoh on 4/14/2015.
 */

public class Student {

    private int studentID;
    private String studentName;


    //CONSTRUCTOR
    public Student (String studentName, int studentID) {
        this.studentName = studentName;
        this.studentID = studentID;
    }



    //GET METHODS
    //get the student's ID for this instance
    public int getStudentID() {
        return studentID;
    }

    //get the student's name for this instance
    public String getStudentName() {
        return studentName;
    }


}
