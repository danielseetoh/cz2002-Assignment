import java.util.List;

/**
 * Created by MelSng on 13/4/2015.
 */
public class CourseManager {

    private CourseDB courseDB = new CourseDB();

    //Setter
    public void addCourse (String courseName, String professorName, List<int[]> lessonCapacity) {
        List<Course> courseList = courseDB.getCourseList();
        int newID = courseList.size();
        courseList.add(new Course(courseName, professorName, lessonCapacity, newID));
    }

    public int[] getLessonCapacityByCourseName (String courseName, int lessonType){
        return courseDB.getCourseByName(courseName).getLessonCapacity(lessonType);
    }

    public boolean isExistingCourseName (String courseName) {
        boolean result = false;
        List<Course> courseList = courseDB.getCourseList();
        for(int i = 0; i < courseList.size(); i++){
            if(courseList.get(i).getCourseName().equals(courseName)){
                result = true;
            }
        }
        return result;
    }

    public boolean isCourseReadyForRegistrationByName(String courseName){
        return courseDB.getCourseByName(courseName).isReadyForRegistration();
    }

/*
    public String[] getCourseNameList(){
        List<Course> courseList = courseDB.getCourseList();
        String[] courseNameList = new String[courseList.size()];
        for(int i = 0; i < courseList.size(); i++){
            courseNameList[i] = courseList.get(i).getCourseName();
        }
        return courseNameList;
    }

    private int getNumLecturesByCourseName (String courseName){
        return courseDB.getCourseByName(courseName).getNumLectures();
    }

    private int getNumTutorialsByCourseName (String courseName) {
        return getCourseByName(courseName).getNumTutorials();
    }

    private int getNumLabsByCourseName (String courseName){
        return getCourseByName(courseName).getNumLabs();
    }

    private int[] getLectureVacancyByCourseName (String courseName){
        Course currentCourse = getCourseByName(courseName);
        int[] lectureVacancy = currentCourse.getLectureVacancies();
        return lectureVacancy;
    }

    private int[] getTutorialVacancyByCourseName (String courseName){
        Course currentCourse = getCourseByName(courseName);
        int[] tutorialVacancy = currentCourse.getTutorialVacancies();
        return tutorialVacancy;
    }

    private int[] getLabVacancyByCourseName (String courseName){
        Course currentCourse = getCourseByName(courseName);
        int[] labVacancy = currentCourse.getLabVacancies();
        return labVacancy;
    }



    public int[] getLessonVacancyByCourseName (String courseName, int lessonType){
        switch(lessonType){
            case 0:
                return getLectureVacancyByCourseName(courseName);
            case 1:
                return getTutorialVacancyByCourseName(courseName);
            case 2:
                return getLabVacancyByCourseName(courseName);
            default:
                return null;
        }
    }

    public int getNumComponentsByCourseName(String courseName){
        return getCourseByName(courseName).getNumCoursework();
    }

    public int getCapacityByCourse(String courseName){
        return getCourseByName(courseName).getCapacity();
    }

    public int getLessonCapacityByCourseLesson(String courseName, int lessonType, int lessonID){
        return getCourseByName(courseName).getLessonCapacity(lessonType, lessonID);
    }

    public double getExamWeightByCourse(String courseName){
        return getCourseByName(courseName).getExamWeight();
    }

    public double[] getCourseworkWeightByCourse(String courseName){
        return getCourseByName(courseName).getCourseworkWeight();
    }
    */
}
