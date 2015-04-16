/**
 * Stores and retrieves record data
 */
public class Record {

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

    /**
     * constructor for record
     * @param courseID identification number of the courses the student has registered
     * @param studentID identification number of the student
     * @param lessonChoice identification number of the lab,tutorial or lab the student is registered to
     * @param numComponents coursework marks of the various components of coursework
     * @param examWeight weightage of the exam marks
     * @param courseworkWeight weightage of coursework marks
     */
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

    /**
     * Gets course ID
     * @return courseID
     */
    public int getCourseID() { return courseID;}

    /**
     * Gets student ID
     * @return studentID
     */
    public int getStudentID() {return studentID;}



    /**
     *Gets exam marks
     * @return exam marks
     */
    public double getExamMarks() {
        return examMarks;
    }

    /**
     * Retrieves the mark of each coursework component
     * @return array of double with the coursework marks of each coursework component
     */
    public double[] getCourseworkComponentMarks() {
        return courseworkMarks;
    }

    /**
     * Gets overall marks
     * @return overall marks
     */
    public double getOverallMarks() {
        return overallMarks;
    }



    /**
     * Retrieves grade
     * @return grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Gets the ID of the lecture chosen by the student
     * @return identification number of the lecture chosen by student
     */
    public int getLectureChoice() {
        return lectureChoice;
    }

    /** Gets the ID of the tutorial chosen by the student
     * @return identification number of the tutorial chosen by student
     */
    public int getTutorialChoice() {
        return tutorialChoice;
    }

    /** Gets the ID of the lab chosen by the student
     * @return identification number of the lab chosen by student
     */
    public int getLabChoice() {
        return labChoice;
    }

    /** Gets the total coursework marks as a double data type
     * @return sum of the marks of coursework components
     */
    public double getTotalCourseworkMarks() {
        return totalCourseworkMarks;
    }

    //SET METHODS

    /**
     * Sets the exam marks of the record
     * @param examMarks exam marks of student
     */
    public void setExamMarks(double examMarks) {
        this.examMarks = examMarks;
        this.examMarked = true;
        setOverallMarked();
    }

    /**
     * Sets the coursework marks of the record
     * @param courseworkComponentMarks coursework component marks of the student
     */
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

    /**
     * Sets the record to be marked if exam and coursework has both been marked
     */
    private void setOverallMarked(){
        this.overallMarked = this.examMarked && this.componentMarked;
        if(overallMarked){
            setOverallMarks();
            setGrade();
        }
    }

    /**
     * Sets the grade if the record has been marked
     */
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

    /**
     * Sets the overall marks by calculation from exam marks and coursework marks
     */
    private void setOverallMarks(){
        overallMarks = examMarks*examWeight + totalCourseworkMarks*(1-examWeight);
    }

    /**
     * Sets the total coursework marks by calculation from the individual coursework component marks
     */
    private void setTotalCourseworkMarks(){
        double result = 0;
        for(int i = 0; i < courseworkMarks.length; i++){
            result += (courseworkMarks[i]*courseworkWeight[i]);
        }
        totalCourseworkMarks = result;
    }



    //METHODS FOR CHECKING

    /**
     * Checks if record is marked
     * @return true if marked
     */
    public boolean isMarked() {
        return overallMarked;
    }
}
