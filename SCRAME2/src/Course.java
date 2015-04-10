import java.util.ArrayList;
import java.util.List;

public class Course {

    // Single Responsibility: to store and retrieve course information

    private int courseID;
    private String courseName;
    private String professorName;
    private int capacity;
    private int vacancies;

    private double examWeight;
    private double[] courseworkWeight;
    private int numberOfCoursework;

    private int numLessonTypes;

    private int numLectures;
    private int numTutorials;
    private int numLabs;

    private List<Lesson[]> lessonList = new ArrayList<Lesson[]>();

    private boolean readyForRegistration = false;

    public Course (String courseName,  String professorName, int capacity, int numLectures, int numTutorials, int numLabs, int courseID) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.professorName = professorName;
        this.capacity = capacity;
        this.numLectures = numLectures;
        this.numTutorials = numTutorials;
        this.numLabs = numLabs;
        this.vacancies = capacity;
        this.numLessonTypes = 3;
        setLessons(numLectures, numTutorials, numLabs);
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

    public int[] getLectureVacancies (){
        int[] lectureVacancy = new int[numLectures];
        for(int i = 0; i < numLectures; i++){
            lectureVacancy[i] = lessonList.get(0)[i].getVacancies();
        }
        return lectureVacancy;
    }

    public int[] getTutorialVacancies (){
        int[] tutorialVacancy = new int[numTutorials];
        for(int i = 0; i < numTutorials; i++){
            tutorialVacancy[i] = lessonList.get(1)[i].getVacancies();
        }
        return tutorialVacancy;
    }

    public int[] getLabVacancies (){
        int[] labVacancy = new int[numLabs];
        for(int i = 0; i < numLabs; i++){
            labVacancy[i] = lessonList.get(2)[i].getVacancies();
        }
        return labVacancy;
    }

    public int getVacancies() {
        return vacancies;
    }

    public int getNumberOfCoursework() {
        return numberOfCoursework;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getLessonCapacity(int lessonType, int lessonID){
        return lessonList.get(lessonType)[lessonID].getCapacity();
    }

    public double getExamWeight() {
        return examWeight;
    }

    public double[] getCourseworkWeight() {
        return courseworkWeight;
    }






    //Setters
    public void setComponentWeightage(double examWeight, double[] courseworkWeight){
        this.examWeight = examWeight;
        this.courseworkWeight = courseworkWeight;
        this.numberOfCoursework = courseworkWeight.length;
    }

    private void setLessons(int numLectures, int numTutorials, int numLabs){

        int[] numLessons = {numLectures, numTutorials, numLabs};
        int lessonCapacity;
        int remainderCapacity;
        for(int i = 0; i < numLessonTypes; i++){
            if(numLessons[i] > 0) {
                lessonCapacity = this.capacity / numLessons[i];
                remainderCapacity = this.capacity % numLessons[i];
                lessonList.add(new Lesson[numLessons[i]]);
                for (int j = 0; j < numLessons[i]; j++) {
                    if (j == 0) {
                        lessonList.get(i)[j] = new Lesson(i, j, lessonCapacity + remainderCapacity);
                    } else {
                        lessonList.get(i)[j] = new Lesson(i, j, lessonCapacity);
                    }
                }
            }
        }
    }

    public void setVacancies(int vacancies){
        this.vacancies = vacancies;
    }

    public void setLessonVacancies(int lessonType, int lessonID, int vacancies){
        lessonList.get(lessonType)[lessonID].setVacancies(vacancies);
    }




    //Verifiers
    public boolean isReadyForRegistration(){
        readyForRegistration = isCourseComponentsValid();
        return readyForRegistration;
    }

    private boolean isCourseComponentsValid(){
        if(examWeight != 0 && courseworkWeight != null && numberOfCoursework >= 0){
            return true;
        } else {
            return false;
        }
    }
}
