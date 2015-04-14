import java.util.ArrayList;
import java.util.List;

public class Course {

    // Single Responsibility: to store and retrieve course information

    private int courseID;
    private String courseName;
    private String professorName;

    private double examWeight;
    private double[] courseworkWeight;

    private List<Lesson[]> lessonList;

    public Course (String courseName,  String professorName, List<int[]> lessonCapacity, int courseID) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.professorName = professorName;
        for(int i = 0; i < lessonCapacity.size(); i++){
            lessonList.add(new Lesson[lessonCapacity.get(i).length]);
            for(int j = 0; j < lessonCapacity.get(i).length; j++){
                lessonList.get(i)[j] = new Lesson(i, j, lessonCapacity.get(i)[j]);
            }
        }
    }

    //Getters
    public String getCourseName() {
        return courseName;
    }

    public double getExamWeight() {
        return examWeight;
    }

    public double[] getCourseworkWeight() {
        return courseworkWeight;
    }

    public int[] getLessonCapacity(int lessonType){
        int numLessons = lessonList.get(lessonType).length;
        int[] lessonCapacity = new int[numLessons];
        for(int i = 0; i < numLessons; i++){
            lessonCapacity[i] = lessonList.get(lessonType)[i].getCapacity();
        }
        return lessonCapacity;
    }







    //Setters
    public void setComponentWeightage(double examWeight, double[] courseworkWeight){
        this.examWeight = examWeight;
        this.courseworkWeight = courseworkWeight;
    }

    public void setLessonVacancies(int lessonType, int lessonID, int vacancies){
    }




    //Verifiers
    public boolean isReadyForRegistration(){
        if(examWeight != 0 && courseworkWeight != null && courseworkWeight.length >= 0){
            return true;
        } else {
            return false;
        }
    }
}
