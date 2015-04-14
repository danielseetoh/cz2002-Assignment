import java.util.ArrayList;
import java.util.List;

public class Course {

    // Single Responsibility: to store and retrieve course information

    private int courseID;
    private String courseName;
    private String professorName;

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

    public Course (int courseID, String courseName, String professorName, int numLectures, int numTutorials, int numLabs) {


        this.courseName = courseName;
        this.courseID = courseID;
        this.professorName = professorName;
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

    public String getProfessorName() {
        return professorName;
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

    public int getLectureVacancy(int lectureID){

        int i = 0;

        do {

            if(lectureList.get(i).getID() == lectureID){
                int vacancies = lectureList.get(lectureID).getVacancies();
                return vacancies;}

            i++;

        }while(i < lectureList.size());

        return -1;

    }

    public int getTutorialVacancy(int tutorialID){

        int i = 0;

        do {

            if(tutorialList.get(i).getID() == tutorialID){
                int vacancies = labList.get(tutorialID).getVacancies();
                return vacancies;}

            i++;

        }while(i < tutorialList.size());

        return -1;

    }

    public int getLabVacancy(int lectureID){

        int i = 0;

        do {

            if(labList.get(i).getID() == lectureID){
            int vacancies = labList.get(lectureID).getVacancies();
            return vacancies;}

            i++;

        }while(i < labList.size());

        return -1;

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

    public void addLab(int labID, int[] capacity){

        int i = 0;

        while(i < capacity.length){

            Lab newLab = new Lab(i, capacity[i]);
            labList.add(newLab);
            i++;

        }

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
