import java.util.List;
import java.util.Scanner;

public class CourseManager {

    private int IDAssigner = 0;
    private CourseDB courseDB = new CourseDB();
    private Scanner sc = new Scanner(System.in);

    public void addCourse (int courseID, String courseName, int professorID, int numLectures, int numTutorials, int numLabs, int [] capacityLecture, int [] capacityTutorial, int [] capacityLaboratory) {

        Course course = new Course(IDAssigner, courseName,  professorID, numLectures, numTutorials, numLabs);
        IDAssigner++;

        course.addLecture(capacityLecture);
        course.addTutorial(capacityTutorial);
        course.addLab(capacityLab);

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
                course.setLectureVacancy(ID);
            break;

            case TUTORIAL:
                course.setTutorialVacancy(ID);
            break;

            case LAB:
                course.setLabVacancy(ID);
            break;
        }
    }

    public int getNumComponentsByCourseName(int courseID){
        return courseDB.getCourse(courseID).getNumCoursework();
    }

    public double getExamWeightByCourse(int courseID){
        return courseDB.getCourse(courseID).getExamWeight();
    }

    public double[] getCourseworkWeightByCourse(String courseName){
        return courseDB.getCourse(courseID).getCourseworkWeight();
    }

    public boolean verifyCourseID(int CourseID)
    {
        if(courseDB.getCourse(courseID) == -1) {
            return false;
        }
        return true;
    }

    public String [] getCourseIDList() {
        List<Course> courseList =  courseDB.getCourseList();
        String [] courseIDList = new String[courseList.size()];
        for(int i = 0; i < courseList.size(); i++) {
            courseIDList[i] = courseList.get(i).getCourseID();
        }

    }


    


}
