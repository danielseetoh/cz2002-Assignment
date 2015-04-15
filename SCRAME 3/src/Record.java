
public class Record {

    //Single Responsibility: To store and retrieve marks

    private int courseID;
    private int studentID;

    private int lectureChoice;
    private int tutorialChoice;
    private int labChoice;

    private double examWeight;
    private double[] courseworkWeight;

    private double examMarks;
    private double[] courseworkMarks;
    private double totalCourseworkMarks;
    private double overallMarks;
    private String grade;

    private boolean examMarked = false;
    private boolean componentMarked = false;
    private boolean overallMarked = false;

    // CONSTRUCTOR
    public Record (int courseID,int studentID, int[] lessonChoice, int numComponents, double examWeight, double[] courseworkWeight) {
        this.courseID = courseID;
        this.studentID = studentID;
        this.lectureChoice = lessonChoice[0];
        this.tutorialChoice = lessonChoice[1];
        this.labChoice = lessonChoice[2];
        this.courseworkMarks = new double[numComponents];
        this.examWeight = examWeight;
        this.courseworkWeight = courseworkWeight;
    }



    // GET METHODS
    // Gets course ID
    public int getCourseID() { return courseID;}

    // Gets student ID
    public int getStudentID() {return studentID;}

    // Gets exam marks
    public double getExamMarks() {
        return examMarks;
    }

    // Gets an array of double with the coursework marks of each coursework component
    public double[] getCourseworkComponentMarks() {
        return courseworkMarks;
    }

    // Gets overall marks
    public double getOverallMarks() {
        return overallMarks;
    }

    // Gets the grade
    public String getGrade() {
        return grade;
    }

    //
    public int getLectureChoice() {
        return lectureChoice;
    }

    public int getTutorialChoice() {
        return tutorialChoice;
    }

    public int getLabChoice() {
        return labChoice;
    }

    public double getTotalCourseworkMarks() {
        return totalCourseworkMarks;
    }

    //Setters
    public void setExamMarks(double examMarks) {
        this.examMarks = examMarks;
        this.examMarked = true;
        setOverallMarked();
    }

    public void setCourseworkComponentMarks (double[] courseworkComponentMarks){
        if(courseworkComponentMarks.length == this.courseworkMarks.length){
            for(int i = 0; i < courseworkComponentMarks.length; i++){
                this.courseworkMarks[i] = courseworkComponentMarks[i];
            }
            this.componentMarked = true;
        }
        setTotalCourseworkMarks();
        setOverallMarked();
    }

    private void setOverallMarked(){
        this.overallMarked = this.examMarked && this.componentMarked;
        if(overallMarked){
            setOverallMarks();
            setGrade();
        }
    }

    private void setGrade() {
        if(getOverallMarks()>=80){
            grade = "A";
        } else if(getOverallMarks()>=70){
            grade = "B";
        } else if(getOverallMarks()>=60){
            grade = "C";
        } else if(getOverallMarks()>=50){
            grade = "D";
        } else {
            grade = "F";
        }
    }

    private void setOverallMarks(){
        overallMarks = examMarks*examWeight + totalCourseworkMarks*(1-examWeight);
    }

    private void setTotalCourseworkMarks(){
        double result = 0;
        for(int i = 0; i < courseworkMarks.length; i++){
            result += (courseworkMarks[i]*courseworkWeight[i]);
        }
        totalCourseworkMarks = result;
    }


    //Verifiers
    public boolean isMarked() {
        return overallMarked;
    }
}
