import java.util.ArrayList;
import java.util.List;

/**
 * Store and retrieves course information
 */
public class Course {

    private int courseID;
    private String courseName;
    private int professorID;

    private double examWeight;
    private double[] courseworkWeight;
    private int numCoursework;

    private List<Lecture> lectureList = new ArrayList<Lecture>();
    private List<Tutorial> tutorialList = new ArrayList<Tutorial>();
    private List<Lab> labList = new ArrayList<Lab>();

    /**
     * Default constructor for course
     * @param courseID identification number of the course
     * @param courseName name of the course
     * @param professorID identification number of the professor
     */

    public Course (int courseID, String courseName, int professorID) {

        this.courseName = courseName;
        this.courseID = courseID;
        this.professorID = professorID;

    }

    //GET METHODS

    /**
     * Retrieves the course ID
     * @return courseID
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * Retrieves the course name
     * @return courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Retrieves the professor ID
     * @return professor ID
     */
    public int getProfessorID() {
        return professorID;
    }

    /**
     * Retrieves the number of coursework for a particular course
     * @return number of coursework for a particular course
     */
    public int getNumCoursework() {
        return numCoursework;
    }

    /**
     * Retrieves the weightage of the exam component of a particular course
     * @return weightage of exam for a particular course
     */
    public double getExamWeight() {
        return examWeight;
    }

    /**
     * Retrieves the weightage of all the coursework components in the course
     * @return weightage of coursework
     */
    public double[] getCourseworkWeight() {

        return courseworkWeight;
    }

    /**
     * Retrieves the list of lectures of a particular course
     * @return list of lectures
     */
    public List<Lecture> getLectureList(){
        return this.lectureList;
    }

    //get the list of tutorials in a particular course

    /**
     * Retrieves the list of tutorials of a particular course
     * @return list of tutorials
     */
    public List<Tutorial> getTutorialList(){
        return this.tutorialList;
    }

    //get the list of labs in a particular course

    /**
     * Retrieves the list of labs of a particular course
     * @return list of labs
     */
    public List<Lab> getLabList(){
        return this.labList;
    }

    /**
     * Sets the component weightage
     * set method for courseManager to call upon to set the weightage of individual components in the course
     * @param examWeight weightage of exam
     * @param courseworkWeight weightage of coursework
     */
    public void setComponentWeightage(double examWeight, double[] courseworkWeight){
        this.examWeight = examWeight;
        this.courseworkWeight = courseworkWeight;
        this.numCoursework = courseworkWeight.length;
    }

    /**
     * method for addition of lectures into the course
     * @param capacity array that stores the number of students allowed to take each particular lecture
     */
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

    /**
     * method for addition of tutorials into the course
     * @param capacity array that stores the number of students allowed to take each particular tutorial
     */
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

    /**
     * method for addition of labs into the course
     * @param capacity array that stores the number of students allowed to take each particular lab
     */
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

    /**
     * method to set the vacancies of a particular lab in the lab list
     * @param ID Identification number of the particular lab
     */
    public void setLabVacancy(int ID){

        labList.get(ID).setVacancies();

    }

    /**
     * method to set the vacancies of a particular tutorial in the tutorial list
     * @param ID Identification number of the particular lab
     */
    public void setTutorialVacancy(int ID){

        tutorialList.get(ID).setVacancies();

    }

    /**
     * method to set the vacancies of a particular lecture in the lecture list
     * @param ID Identification number of the particular lab
     */
    public void setLectureVacancy(int ID){
        lectureList.get(ID).setVacancies();
    }

    //METHODS FOR CHECKING

    /**
     * Checks if the course component values are valid
     * @return true if the values are valid
     */
    public boolean isCourseComponentsValid(){
        if(examWeight != 0 && courseworkWeight != null && numCoursework >= 0){
            return true;
        } else {
            return false;
        }
    }
}
