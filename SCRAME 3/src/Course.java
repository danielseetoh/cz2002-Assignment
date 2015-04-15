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

    private List<Lecture> lectureList = new ArrayList<Lecture>();
    private List<Tutorial> tutorialList = new ArrayList<Tutorial>();
    private List<Lab> labList = new ArrayList<Lab>();


    private boolean readyForRegistration = false;

    //CONSTRUCTOR
    public Course (int courseID, String courseName, int professorID) {

        this.courseName = courseName;
        this.courseID = courseID;
        this.professorID = professorID;

    }

    //GET METHODS
    public int getCourseID() {
        return courseID;
    }// return courseID of a course

    public String getCourseName() {
        return courseName;
    }

    public int getProfessorID() {
        return professorID;
    }

    /*public int getNumLectures() {
        return numLectures;
    }

    public int getNumTutorials() {
        return numTutorials;
    }

    public int getNumLabs() {
        return numLabs;
    }*/

    //return the number of coursework for a particular course
    public int getNumCoursework() {
        return numCoursework;
    }

    //get the weightage of the exam component in a particular course
    public double getExamWeight() {
        return examWeight;
    }

    //get the weightage of all the coursework components in the course and returned through an array of weightage
    public double[] getCourseworkWeight() {

        return courseworkWeight;
    }

    //get the list of lectures in a particular course
    public List<Lecture> getLectureList(){


        return this.lectureList;

    }

    //get the list of tutorials in a particular course
    public List<Tutorial> getTutorialList(){

        return this.tutorialList;

    }

    //get the list of labs in a particular course
    public List<Lab> getLabList(){

        return this.labList;

    }






    //SET METHODS
    //set method for courseManager to call upon to set the weightage of individual components in the course
    public void setComponentWeightage(double examWeight, double[] courseworkWeight){
        this.examWeight = examWeight;
        this.courseworkWeight = courseworkWeight;
        this.numCoursework = courseworkWeight.length;
    }

    //method for addition of lectures into the course
    public void addLecture(int[] capacity){

        //initializing i to 0 so that it can be used to cycle through the capacity array to know how many lectures to be added
        int i = 0;

        //cycling through the capacity array to know the capacities of the individual lecture and then adding it into the particular lecture with ID i
        while(i < capacity.length){

            //instantiating a new lecture and adding it into the lecture list
            Lecture newLecture = new Lecture(i, capacity[i]);
            lectureList.add(newLecture);
            i++;

        }

    }

    //method for addition of tutorials into the course
    public void addTutorial(int[] capacity){

        //initializing i to 0 so that it can be used to cycle through the capacity array to know how many tutorials to be added
        int i = 0;

        //cycling through the capacity array to know the capacities of the individual tutorials and then adding it into the particular tutorial with ID i
        while(i < capacity.length){

            //instantiating a new tutorial and adding it into the tutorial list
            Tutorial newTutorial = new Tutorial(i, capacity[i]);
            tutorialList.add(newTutorial);
            i++;

        }


    }

    //method for addition of labs into the course
    public void addLab(int[] capacity){

        //initializing i to 0 so that it can be used to cycle through the capacity array to know how many labs to be added
        int i = 0;

        //cycling through the capacity array to know the capacities of the individual labs and then adding it into the particular lab with ID i
        while(i < capacity.length){

            //instantiating a new lab and adding it into the lab list
            Lab newLab = new Lab(i, capacity[i]);
            labList.add(newLab);
            i++;

        }

    }

    //method to set the vacancies of a particular lab in the lab list
    public void setLabVacancy(int ID){

        labList.get(ID).setVacancies();

    }

    //method to set the vacancies of a particular tutorial in the tutorial list
    public void setTutorialVacancy(int ID){

        tutorialList.get(ID).setVacancies();

    }

    //method to set the vacancies of a particular lecture in the lecture list
    public void setLectureVacancy(int ID){

        lectureList.get(ID).setVacancies();

    }

    //Verifiers
    /*public boolean isReadyForRegistration(){
        readyForRegistration = isCourseComponentsValid();
        return readyForRegistration;
    }*/
    public boolean isCourseComponentsValid(){
        if(examWeight != 0 && courseworkWeight != null && numCoursework >= 0){
            return true;
        } else {
            return false;
        }
    }
}
