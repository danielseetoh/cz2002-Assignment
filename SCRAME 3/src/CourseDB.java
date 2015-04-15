import java.util.ArrayList;
import java.util.List;

public class CourseDB {

    private List<Course> courseList = new ArrayList<Course>();

    //return all the Course that are currently in the database
    public List<Course> getCourseList() {
        return this.courseList;
    }

    //returns a Course object based on courseID
    public Course getCourse(int courseID) {

        //return null if size of CourseList = 0
        if (courseList.size() == 0)
            return null;

        //loop through courseList to find Course with courseID
        for(int j = 0; j<courseList.size(); j++){
            if(courseList.get(j).getCourseID() == courseID)
                return courseList.get(j);
        }

        //if Course is not in CourseList, return null
        return null;
    }

    //add course into the database
    public void add(Course newCourse) {
        courseList.add(newCourse);
    }





}
