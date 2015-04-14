import java.util.ArrayList;
import java.util.List;

public class CourseDB {
    private List<Course> courseList = new ArrayList<Course>();

    //Getters
    public Course getCourseByName (String courseName) {
        for(int i = 0; i < courseList.size(); i++){
            Course currentCourse = courseList.get(i);
            if(currentCourse.getCourseName().equals(courseName)){
                return currentCourse;
            }
        }
        return null;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    //Setters
    public void setComponentWeightByCourseName(String courseName, double examWeight, double[] courseworkWeight){
        getCourseByName(courseName).setComponentWeightage(examWeight, courseworkWeight);
    }



    //Verifiers

}
