/**
 * Created by danielseetoh on 3/31/2015.yewlong.mel
.kkb */
import java.util.*;

public class Course {

    private String courseName;
    private Professor professorIC;
    private List<Student> studentList = new ArrayList<Student>();
    private CourseComponent exam;
    private List<CourseComponent> courseworkList = new ArrayList<CourseComponent>();
    private int vacancies;
    private int maxCapacity;
    private List<Lesson> lessonList = new ArrayList<Lesson>();
    private List<Tutorial> tutorialList = new ArrayList<Tutorial>();
    private List<Lecture> lectureList = new ArrayList<Lecture>();
    private List<Laboratory> labList = new ArrayList<Laboratory>();

    public Course(String courseName){
        this.courseName = courseName;
        createExam();
    }

    private void createExam(){
        exam = new CourseComponent("exam");
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setProfessor(Professor professorIC){
        this.professorIC = professorIC;
    }

    public Professor getProfessorIC() {
        return professorIC;
    }

    public List<Student> getStudentList(){
        return studentList;
    }

    public void printStats(){
        //print aggregated stats for the course
        System.out.println("The overall mark for this course is: " + getAvgMarks());
        System.out.println("The overall exam mark for this course is: " + getExamMarks());
        for(int i = 0; i<getCourseworkLength(); i++){
            System.out.println("The overall mark for " + courseworkList.get(i).getComponentName() + " is: "
                    + getCourseworkMarks()[i]);
        }
    }

    public int getVacancy(){
        updateVacancy();
        return vacancies;
    }

    private void updateVacancy(){  //new method
        this.vacancies = maxCapacity - studentList.size();
    }

    public void setMaxCapacity(int maxCapacity){//new function
        this.maxCapacity = maxCapacity;         //set max capacity
    }

    private double getAvgMarks(){
        double sum = 0;
        for(int i = 0; i < studentList.size(); i++) {
            sum += studentList.get(i).getRecordByCourseName(this.courseName).getOverallMarks();
        }
        return sum/studentList.size();
    }

    private double getExamMarks(){
        double sum = 0;
        for(int i = 0; i < studentList.size(); i++) {
            sum += studentList.get(i).getRecordByCourseName(this.courseName).getExamMarks();
        }
        return sum/studentList.size();
    }

    private double[] getCourseworkMarks(){
        double[] result = new double[this.courseworkList.size()];
        double sum;
        for(int i = 0; i < this.courseworkList.size(); i++){
            sum = 0;
            for(int j = 0; j < studentList.size(); j++) {
                sum += studentList.get(j).getRecordByCourseName(courseName).getCourseworkMarks()[i];
            }
            result[i] = sum/studentList.size();
        }
        return result;
    }

    public void addLecture(int lectureID, int capacity){ //edited
        Lecture lecture = new Lecture(lectureID, capacity);
        lessonList.add(lecture);
        lectureList.add(lecture);
    }

    public void addLectures(int numberOfLectures){
        Lecture newLecture;
        for(int i = 0; i < numberOfLectures; i++){
            if(i == 0){
                newLecture = new Lecture((i+1), this.maxCapacity/numberOfLectures + this.maxCapacity%numberOfLectures);
            } else {
                newLecture = new Lecture((i+1), this.maxCapacity/numberOfLectures);
            }
            lectureList.add(newLecture);
        }
    }

    public void addTutorial(int tutorialID, int capacity){
        Tutorial tutorial = new Tutorial(tutorialID, capacity);
        lessonList.add(tutorial);
        tutorialList.add(tutorial);
    }

    public void addTutorials(int numberOfTutorials){
        Tutorial newTutorial;
        for(int i = 0; i < numberOfTutorials; i++){
            if(i == 0){
                newTutorial = new Tutorial((i+1), this.maxCapacity/numberOfTutorials + this.maxCapacity%numberOfTutorials);
            } else {
                newTutorial = new Tutorial((i+1), this.maxCapacity/numberOfTutorials);
            }
            tutorialList.add(newTutorial);
        }
    }

    public void addLab(int labID, int capacity){
        Laboratory lab = new Laboratory(labID, capacity);
        lessonList.add(lab);
        labList.add(lab);
    }

    public void addLabs(int numberOfLabs){
        Laboratory newLab;
        for(int i = 0; i < numberOfLabs; i++){
            if(i == 0){
                newLab = new Laboratory((i+1), this.maxCapacity/numberOfLabs + this.maxCapacity%numberOfLabs);
            } else {
                newLab = new Laboratory((i+1), this.maxCapacity/numberOfLabs);
            }
            labList.add(newLab);
        }
    }

    public void enrollStudent(Student student){
        if(getVacancy()!=0){
            studentList.add(student);
            student.registerCourse(this);
            System.out.println("Student has been enrolled in the course.");
        }else{
            System.out.println("The course is currently full.");
        }
        updateVacancy();
    }

    public int getCourseworkLength(){
        return courseworkList.size();
    }

    public double getExamWeight(){ //new method
        return exam.getWeight();
    }

    public void setExamWeight(double weight){
        exam.setWeight(weight);
    }

    public double getCourseworkWeight(int i){ //new method
       return courseworkList.get(i).getWeight();
    }

    public void setCourseWorkWeight(int i, double weight){
        courseworkList.get(i).setWeight(weight);
    }

    public String getCourseName(){
        return courseName;
    }


    public CourseComponent createCourseComponent(String name){
        CourseComponent cc = new CourseComponent(name);
        courseworkList.add(cc);
        return cc;
    }

    public List getLectureList(){
        return lectureList;
    }

    public List getTutorialList(){
        return tutorialList;
    }

    public List getLabList(){
        return labList;
    }

    public List getLessonList(){
        return lessonList;
    }

    public Lecture getLecture(int i){
        return lectureList.get(i);
    }

    public Tutorial getTutorial(int i){
        return tutorialList.get(i);
    }

    public Laboratory getLab(int i){
        return labList.get(i);
    }

    public Lesson getLesson(int i){
        return lessonList.get(i);
    }

    public CourseComponent getCourseworkList(int i){
        return courseworkList.get(i);
    }
}
