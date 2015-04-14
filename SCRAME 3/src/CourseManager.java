import java.util.Scanner;

public enum LessonOption {LECTURE, TUTORIAL, LAB };     

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

    public int getVacancy(int courseID, LessonOption option, int ID) {
        Course course = courseDB.getCourse(courseID);

        switch (option ) {
            case LECTURE:
                return course.getLectureVacancy(ID);
            break;

            case TUTORIAL:
                return course.getTutorialVacancy(ID);
            break;

            case LAB:
                return course.getLabVacancy(ID);
            break;
        }
    }

    public void setVacancy(int courseID, LessonOption option, int ID) {
        Course course = courseDB.getCourse(courseID);

        switch (option ) {
            case LECTURE:
                return course.setLectureVacancy(ID);
            break;

            case TUTORIAL:
                return course.setTutorialVacancy(ID);
            break;

            case LAB:
                return course.setLabVacancy(ID);
            break;
        }
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
        if(courseDB.getCourse(courseID) == -1) {
            return false;
        }
        return true;
    }


    


}
