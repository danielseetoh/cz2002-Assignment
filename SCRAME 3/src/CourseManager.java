import java.util.List;
import java.util.Scanner;

public class CourseManager {

    private int IDAssigner = 0;
    private CourseDB courseDB = new CourseDB();
    private Scanner sc = new Scanner(System.in);

    public void addCourse (int courseID, String courseName, int professorID, int [] capacityLecture, int [] capacityTutorial, int [] capacityLab) {

        Course course = new Course(IDAssigner, courseName,  professorID);
        IDAssigner++;

        course.addLecture(capacityLecture);
        course.addTutorial(capacityTutorial);
        course.addLab(capacityLab);

        courseDB.add(course);
    }

    public int [] getLessonVacancyByCourseID(int courseID, LessonOption option) {
        Course course = courseDB.getCourse(courseID);

        switch (option) {
            case LECTURE:
                List<Lecture> lectureList =  course.getLectureList();
                int [] lectureArray = new int[lectureList.size()];
                for(int i = 0; i < lectureList.size(); i++)
                    lectureArray[i] = lectureList.get(i).getVacancies();
                return lectureArray;

            case TUTORIAL:
                List<Tutorial> tutorialList =  course.getTutorialList();
                int [] tutorialArray = new int[tutorialList.size()];
                for(int i = 0; i < tutorialList.size(); i++)
                    tutorialArray[i] = tutorialList.get(i).getVacancies();
                return tutorialArray;

            case LAB:
                List<Lab> labList =  course.getLabList();
                int [] labArray= new int[labList.size()];
                for(int i = 0; i < labList.size(); i++)
                    labArray[i] = labList.get(i).getCapacity();
                return labArray;
        }

        return null;

    }

    public int [] getLessonCapacityByCourseID(int courseID, LessonOption option) {
        Course course = courseDB.getCourse(courseID);

        switch (option) {
            case LECTURE:
                List<Lecture> lectureList =  course.getLectureList();
                int [] lectureArray = new int[lectureList.size()];
                for(int i = 0; i < lectureList.size(); i++)
                    lectureArray[i] = lectureList.get(i).getCapacity();
                return lectureArray;

            case TUTORIAL:
                List<Tutorial> tutorialList =  course.getTutorialList();
                int [] tutorialArray = new int[tutorialList.size()];
                for(int i = 0; i < tutorialList.size(); i++)
                    tutorialArray[i] = tutorialList.get(i).getCapacity();
                return tutorialArray;

            case LAB:
                List<Lab> labList =  course.getLabList();
                int [] labArray= new int[labList.size()];
                for(int i = 0; i < labList.size(); i++)
                    labArray[i] = labList.get(i).getCapacity();
                return labArray;
        }

        return null;
    }

    public void setVacancyByCourseLesson(int courseID, LessonOption option, int ID) {
        Course course = courseDB.getCourse(courseID);

        switch (option) {
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

    public int getNumComponentsByCourseID(int courseID){
        return courseDB.getCourse(courseID).getNumCoursework();
    }

    public double getExamWeightByCourse(int courseID){
        return courseDB.getCourse(courseID).getExamWeight();
    }

    public double[] getCourseworkWeightByCourse(int courseID){
        return courseDB.getCourse(courseID).getCourseworkWeight();
    }

    public boolean isCourseReadyForRegistrationByID(int courseID)
    {
        if(courseDB.getCourse(courseID).getCourseID() == -1) {
            return false;
        }
        else return true;
    }

    public int [] getCourseIDList() {
        List<Course> courseList =  courseDB.getCourseList();
        int [] courseIDList = new int[courseList.size()];
        for(int i = 0; i < courseList.size(); i++)
            courseIDList[i] = courseList.get(i).getCourseID();
        return courseIDList;
    }

    public double getExamWeight(int courseID) {
        Course course = courseDB.getCourse(courseID);
        return course.getExamWeight();
    }

    public double [] getCourseworkWeight(int courseID) {
        Course course = courseDB.getCourse(courseID);
        return course.getCourseworkWeight();
    }

    public void setComponentWeightByCourseID(int courseID, double examWeight, double [] courseworkWeight) {
        Course course = courseDB.getCourse(courseID);
        course.setComponentWeightage(examWeight, courseworkWeight);
    }



}
