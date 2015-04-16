/**
 * Stores and retrieves student data
 */
public class Student {

    private int studentID;
    private String studentName;


    //CONSTRUCTOR

    /**
     * constructor for student
     * @param studentName name of the student
     * @param studentID identification number of the student
     */
    public Student (String studentName, int studentID) {
        this.studentName = studentName;
        this.studentID = studentID;
    }

    //GET METHODS

    /**
     * Retrieves student identification number
     * @return studentID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Retrieves the student's name for this instance
     * @return name of the student
     */
    public String getStudentName() {
        return studentName;
    }


}
