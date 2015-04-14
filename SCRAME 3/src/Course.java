import java.util.ArrayList;
import java.util.List;

public class Course {

    // Single Responsibility: to store and retrieve course information

    private int courseID;
    private String courseName;
    private int professorID;

    private double examWeight;
    private double[] courseworkWeight;
    private int numCoursework;

    private int numLectures;
    private int numTutorials;
    private int numLabs;
    private List<Lecture> lectureList = new ArrayList<Lecture>();
    private List<Tutorial> tutorialList = new ArrayList<Tutorial>();
    private List<Lab> labList = new ArrayList<Lab>();


    private boolean readyForRegistration = false;

    public Course (int courseID, String courseName, int professorID, int numLectures, int numTutorials, int numLabs) {


        this.courseName = courseName;
        this.courseID = courseID;
        this.professorID = professorID;
        this.numLectures = numLectures;
        this.numTutorials = numTutorials;
        this.numLabs = numLabs;

    }

    //Getters
    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getProfessorID() {
        return professorID;
    }

    public int getNumLectures() {
        return numLectures;
    }

    public int getNumTutorials() {
        return numTutorials;
    }

    public int getNumLabs() {
        return numLabs;
    }

    public int getNumCoursework() {
        return numCoursework;
    }

    public double getExamWeight() {
        return examWeight;
    }

    public double[] getCourseworkWeight() {
        return courseworkWeight;
    }

    public List<Lecture> getLectureVacancy(){


        return this.lectureList;

    }

    public List<Tutorial> getTutorialVacancy(){

        return this.tutorialList;

    }

    public List<Lab> getLabVacancy(){

        return this.labList;

    }

    public List<Lecture> getLectureCapacity(){

        return this.lectureList;

    }

    public List<Tutorial> getTutorialCapacity(){

        return this.tutorialList;

    }

    public List<Lab> getLabCapacity(){

        return this.labList;

    }








    //Setters
    public void setComponentWeightage(double examWeight, double[] courseworkWeight){
        this.examWeight = examWeight;
        this.courseworkWeight = courseworkWeight;
        this.numCoursework = courseworkWeight.length;
    }

    public void addLecture(int[] capacity){

        int i = 0;

        while(i < capacity.length){

            Lecture newLecture = new Lecture(i, capacity[i]);
            lectureList.add(newLecture);
            i++;

        }

    }

    public void addTutorial(int[] capacity){

        int i = 0;

        while(i < capacity.length){

            Tutorial newTutorial = new Tutorial(i, capacity[i]);
            tutorialList.add(newTutorial);
            i++;

        }


    }

    public void addLab(int[] capacity){

        int i = 0;

        while(i < capacity.length){

            Lab newLab = new Lab(i, capacity[i]);
            labList.add(newLab);
            i++;

        }

    }

    public void setLabVacancy(int ID){

        labList.get(ID).setVacancies();

    }

    public void setTutorialVacancy(int ID){

        tutorialList.get(ID).setVacancies();

    }

    public void setLectureVacancy(int ID){

        lectureList.get(ID).setVacancies();

    }


    //Verifiers
    public boolean isReadyForRegistration(){
        readyForRegistration = isCourseComponentsValid();
        return readyForRegistration;
    }

    private boolean isCourseComponentsValid(){
        if(examWeight != 0 && courseworkWeight != null && numCoursework >= 0){
            return true;
        } else {
            return false;
        }
    }
}
