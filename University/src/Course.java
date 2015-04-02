/**
 * Created by danielseetoh on 3/31/2015.
 */
import java.util.*;

public class Course {

    private String courseName;
    private Professor professorIC;
    private Student[] studentList;
    private CourseComponent exam;
    private List<CourseComponent> coursework = new ArrayList<CourseComponent>();
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

    public void setProfessor(Professor professorIC){ //new method
        this.professorIC=professorIC;
    }

    public Student[] getStudentList(){
        return studentList;
    }

    public void printStats(){
        //print aggregated stats for the course
        System.out.println("The overall mark for this course is: " + getAvgMarks());
        System.out.println("The overall exam mark for this course is: " + getExamMarks());
        for(int i = 0; i<getCourseworkLength(); i++){
            System.out.println("The overall mark for " + coursework.get(i).getComponentName() + " is: "
                    + getCourseworkMarks()[i]);
        }
    }

    public int getVacancy(){
        setVacancy();
        return vacancies;
    }

    private void setVacancy(){  //new method
        int v = 0;
        for(int i = 0; i<studentList.length; i++){
            if(studentList[i]==null){
                v++;
            }
        }
        this.vacancies = v;
    }

    public void setMaxCapacity(int maxCapacity){//new function
        this.maxCapacity = maxCapacity;         //set max capacity
        studentList = new Student[maxCapacity];
    }

    private double getAvgMarks(){
        int i = 0;
        double sum = 0;
        double counter = 0;
        while(studentList[i]!=null){
            for(int j = 0; j<studentList[i].getWholeRecordList().size(); j++){
                if(studentList[i].getRecordList(j).getCourse() == this){   //need to trim this
                    sum+= studentList[i].getRecordList(j).getOverallMarks();
                    counter++;
                }
            }
        }
        return (sum/counter);
    }

    private double getExamMarks(){
        int i = 0;
        double sum = 0;
        double counter = 0;
        while(studentList[i]!=null){
            for(int j = 0; j<studentList[i].getWholeRecordList().size(); j++){
                if(studentList[i].getRecordList(j).getCourse() == this){   //need to trim this
                    sum+= studentList[i].getRecordList(j).getExamMarks();
                    counter++;
                }
            }
        }
        return (sum/counter);
    }

    private double[] getCourseworkMarks(){
        double[] courseworkList = new double[getCourseworkLength()];
        for(int k = 0; k<courseworkList.length; k++){
            int i = 0;
            double sum = 0;
            double counter = 0;
            while(studentList[i]!=null){
                for(int j = 0; j<studentList[i].getWholeRecordList().size(); j++){
                    if(studentList[i].getRecordList(j).getCourse()== this){   //need to trim this
                        if(studentList[i].getRecordList(j).getCourseworkMarks()[k]!=-1) {
                            sum += studentList[i].getRecordList(j).getCourseworkMarks()[k];
                            counter++;
                        }
                    }
                }
            }
            courseworkList[k] = sum/counter;
        }
        return courseworkList;
    }

    public void addLecture(int lectureID, int capacity){ //edited
        Lecture lecture = new Lecture(lectureID, capacity);
        lessonList.add(lecture);
        lectureList.add(lecture);
    }

    public void addTutorial(int tutorialID, int capacity){
        Tutorial tutorial = new Tutorial(tutorialID, capacity);
        lessonList.add(tutorial);
        tutorialList.add(tutorial);
    }

    public void addLab(int labID, int capacity){
        Laboratory lab = new Laboratory(labID, capacity);
        lessonList.add(lab);
        labList.add(lab);
    }

    public void enrollStudent(Student student){
        if(getVacancy()!=0){
            for(int i = 0; i<studentList.length; i++){
                if(studentList[i]==null){
                    studentList[i] = student;
                    System.out.println("Student has been enrolled in the course.");
                    student.registerCourse(this);
                    break;
                }
            }
        }else{
            System.out.println("The course is currently full.");
        }
        setVacancy();
    }

    public int getCourseworkLength(){
        return coursework.size();
    }

    public double getExamWeight(){ //new method
        return exam.getWeight();
    }

    public void setExamWeight(double weight){
        exam.setWeight(weight);
    }

    public double getCourseworkWeight(int i){ //new method
       return coursework.get(i).getWeight();
    }

    public void setCourseWorkWeight(int i, double weight){
        coursework.get(i).setWeight(weight);
    }

    public String getCourseName(){
        return courseName;
    }

    private void createExam(){
        exam = new CourseComponent("exam");
    }

    public void createCourseComponent(String name){
        CourseComponent cc = new CourseComponent(name);
        coursework.add(cc);
    }

    public CourseComponent getCoursework(int i){
        return coursework.get(i);
    }

    public List getWholeLectureList(){
        return lectureList;
    }

    public List getWholeTutorialList(){
        return tutorialList;
    }

    public List getWholeLabList(){
        return labList;
    }

    public List getWholeLessonList(){
        return lessonList;
    }
    public Lecture getLectureList(int i){
        return lectureList.get(i);
    }

    public Tutorial getTutorialList(int i){
        return tutorialList.get(i);
    }

    public Laboratory getLabList(int i){
        return labList.get(i);
    }

    public Lesson getLessonList(int i){
        return lessonList.get(i);
    }
}
