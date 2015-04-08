
/**
 * Created by danielseetoh on 3/31/2015.
 */
public class Record {

    // Single Responsibility: Store marks of student for a course

    private Student student;
    private String studentID;
    private Course course;
    private String courseName;
    private double examMarks;
    private double[] courseworkMarks;
    private double overallMarks;
    private String grade;
    private boolean isCourseworkInitialised = false;

    public Record(Student s, Course c){
        student = s;
        studentID = s.getStudentID();
        course = c;
        courseName = c.getCourseName();
        initCourseworkMarks();
    }

    private void updateGrade(){
        if(getOverallMarks()>=80){
            grade = "A";
        }else if(getOverallMarks()>=70){
            grade = "B";
        }else if(getOverallMarks()>=60){
            grade = "C";
        }else if(getOverallMarks()>=50){
            grade = "D";
        }else
            grade ="F";
    }

    public String getGrade(){
        updateGrade();
        return grade;
    }

    private void calculateOverallMarks(){
        double examWeightedMarks = examMarks*course.getExamWeight();
        double courseworkWeightedMarks = 0;
        for(int i = 0; i<courseworkMarks.length; i++) {
            if (courseworkMarks[i] != 0.0) {
                courseworkWeightedMarks += courseworkMarks[i] * course.getCourseworkWeight(i);
            }
        }
        overallMarks = examWeightedMarks+courseworkWeightedMarks*(1-course.getExamWeight());
    }

    public void setExamMarks(double marks){
        examMarks = marks;
        calculateOverallMarks();
    }

    public void setCourseworkMarks(int index, double marks){
        courseworkMarks[index] = marks;
        calculateOverallMarks();
    }

    private void initCourseworkMarks(){
        if(course.getCourseworkLength() > 1) {
            this.courseworkMarks = new double[course.getCourseworkLength()];
            for (int i = 0; i < courseworkMarks.length; i++) {
                courseworkMarks[i] = 0.0;
            }
            isCourseworkInitialised = true;
        } else {
            isCourseworkInitialised = false;
        }
    }

    public double getOverallMarks(){
        calculateOverallMarks();
        return overallMarks;
    }

    public double getExamMarks(){
        return examMarks;
    }

    public double[] getCourseworkMarks(){
        return courseworkMarks;
    }

    public Course getCourse(){
        return course;
    }

    public String getCourseName() {
        return courseName;
    }

    public Student getStudent() {
        return student;
    }

    public String getStudentID() {
        return studentID;
    }
}
