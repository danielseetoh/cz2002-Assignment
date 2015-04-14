import java.util.Scanner;

public class CourseManager {

    private int IDAssigner = 0;
    private CourseDB courseDB = new CourseDB();
    private Scanner sc = new Scanner(System.in);

    public void addCourse (int CourseID, String courseName, int professorID, int numLectures, int numTutorials, int numLabs, int [] capacityLecture, int [] capacityTutorial, int [] capacityLaboratory) {

        Course course = new Course(IDAssigner, courseName,  professorName, numLectures, numTutorials, numLabs);
        IDAssigner++;

        Course.addLecture(capacityLecture);
        Course.addTutorial(capacityTutorial);
        Course.addLaboratory(capacityLaboratory);

        courseDB.add(course);
    }

    public getLectureVacancy(int courseID, int ID) {
        Course course = courseDB.getCourse(courseID);
        return course.getLectureVacancy(ID);
    }

    public getTutorialVacancy(int courseID, int ID) {
        Course course = courseDB.getCourse(courseID);
        return course.getLectureVacancy(ID);
    }

    public getLaboratoryVacancy(int courseID, int ID) {
        Course course = courseDB.getCourse(courseID);
        return course.getLectureVacancy(ID);
    }

    public int getNumComponentsByCourseName(int courseID){
        return getCourse(courseID).getNumCoursework();
    }

    public double getExamWeightByCourse(int courseID){
        return getCourse(courseID).getExamWeight();
    }

    public double[] getCourseworkWeightByCourse(String courseName){
        return getCourse(courseID).getCourseworkWeight();
    }

    public boolean verifyCourseID(int CourseID)
    {
        if(courseDB.getCourse(courseID) == null) {
            return false;
        }
        return true;
    }

    


}
