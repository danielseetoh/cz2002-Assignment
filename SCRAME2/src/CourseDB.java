import java.util.ArrayList;
import java.util.List;

public class CourseDB {
    private List<Course> courseList = new ArrayList<Course>();

    //Getters
    private Course getCourseByName (String courseName) {
        for(int i = 0; i < courseList.size(); i++){
            Course currentCourse = courseList.get(i);
            if(currentCourse.getCourseName().equals(courseName)){
                return currentCourse;
            }
        }
        return null;
    }

    public String[] getCourseNameList(){
        String[] courseNameList = new String[courseList.size()];
        for(int i = 0; i < courseList.size(); i++){
            courseNameList[i] = courseList.get(i).getCourseName();
        }
        return courseNameList;
    }

    public int getVacancyByCourseName(String courseName){
        return getCourseByName(courseName).getVacancies();
    }

    public int getNumLecturesByCourseName (String courseName){
        return getCourseByName(courseName).getNumLectures();
    }

    public int getNumTutorialsByCourseName (String courseName){
        return getCourseByName(courseName).getNumTutorials();
    }

    public int getNumLabsByCourseName (String courseName){
        return getCourseByName(courseName).getNumLabs();
    }

    public int[] getLectureVacancyByCourseName (String courseName){
        Course currentCourse = getCourseByName(courseName);
        int[] lectureVacancy = currentCourse.getLectureVacancies();
        return lectureVacancy;
    }

    public int[] getTutorialVacancyByCourseName (String courseName){
        Course currentCourse = getCourseByName(courseName);
        int[] tutorialVacancy = currentCourse.getTutorialVacancies();
        return tutorialVacancy;
    }

    public int[] getLabVacancyByCourseName (String courseName){
        Course currentCourse = getCourseByName(courseName);
        int[] labVacancy = currentCourse.getLabVacancies();
        return labVacancy;
    }

    public int getNumLessonsByCourseName (String courseName, int lessonType){
        switch(lessonType){
            case 0:
                return getNumLecturesByCourseName(courseName);
            case 1:
                return getNumTutorialsByCourseName(courseName);
            case 2:
                return getNumLabsByCourseName(courseName);
            default:
                return -1;
        }
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
        return getCourseByName(courseName).getNumberOfCoursework();
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







    //Setters
    public void addCourse (String courseName, String professorName, int capacity,  int numLectures, int numTutorials, int numLabs) {
        int newID = courseList.size();
        courseList.add(new Course(courseName, professorName, capacity, numLectures, numTutorials, numLabs, newID));
    }

    public void setComponentWeightByCourseName(String courseName, double examWeight, double[] courseworkWeight){
        getCourseByName(courseName).setComponentWeightage(examWeight, courseworkWeight);
    }

    public void setVacanciesByCourse(String courseName, int vacancies){
        getCourseByName(courseName).setVacancies(vacancies);
    }

    public void setVacanciesByCourseLesson(String courseName, int lessonType, int lessonID, int vacancies){
        getCourseByName(courseName).setLessonVacancies(lessonType, lessonID, vacancies);
    }


    //Verifiers
    public boolean isExistingCourseName (String courseName) {
        boolean result = false;
        for(int i = 0; i < courseList.size(); i++){
            if(courseList.get(i).getCourseName().equals(courseName)){
                result = true;
            }
        }
        return result;
    }

    public boolean isCourseReadyForRegistrationByName(String courseName){
        return getCourseByName(courseName).isReadyForRegistration();
    }
}
