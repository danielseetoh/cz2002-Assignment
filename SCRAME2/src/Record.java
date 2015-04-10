
public class Record {

    //Single Responsibility: To store and retrieve marks

    private String courseName;
    private String studentName;

    private int lectureChoice;
    private int tutorialChoice;
    private int labChoice;

    private double examMarks;
    private double[] courseworkComponentMarks;
    private double overallMarks;
    private String grade;

    private boolean marked = false;

    public Record (String courseName, String studentName, int lectureChoice, int tutorialChoice, int labChoice) {
        this.courseName = courseName;
        this.studentName = studentName;
        this.lectureChoice = lectureChoice;
        this.tutorialChoice = tutorialChoice;
        this.labChoice = labChoice;
    }

    public Record (String courseName, String studentName, int[] lessonChoice) {
        this.courseName = courseName;
        this.studentName = studentName;
        this.lectureChoice = lessonChoice[0];
        this.tutorialChoice = lessonChoice[1];
        this.labChoice = lessonChoice[2];
    }




    //Getters

    public String getCourseName() {
        return courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getExamMarks() {
        return examMarks;
    }

    public double[] getCourseworkComponentMarks() {
        return courseworkComponentMarks;
    }

    public int getNumberOfCourseworkComponents () {
        return this.courseworkComponentMarks.length;
    }

    public double getOverallMarks() {
        return overallMarks;
    }

    public String getGrade() {
        return grade;
    }

    public int getLectureChoice() {
        return lectureChoice;
    }

    public int getTutorialChoice() {
        return tutorialChoice;
    }

    public int getLabChoice() {
        return labChoice;
    }





    //Setters
    public void setExamMarks(double examMarks) {
        this.examMarks = examMarks;
    }

    public void setCourseworkComponentMarks (double[] courseworkComponentMarks){
        if(courseworkComponentMarks.length == this.courseworkComponentMarks.length){
            for(int i = 0; i < courseworkComponentMarks.length; i++){
                this.courseworkComponentMarks[i] = courseworkComponentMarks[i];
            }
        }
    }





    //Verifiers
    public boolean isMarked() {
        return marked;
    }
}
