import java.util.ArrayList;
import java.util.List;

/**
 * Handles the logic dealing with the course data
 */
public class CourseDB {

    /**
     * initialises a list
     */
    private List<Course> courseList = new ArrayList<Course>();

    /**
     * Retrieves the list of all the courses in the database
     * @return list of all courses in database
     */
    public List<Course> getCourseList() {
        return this.courseList;
    }

    /**
     * returns a Course object based on courseID
     * @param courseID identification number of the course
     * @return Course object based on courseID
     */
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

    /**
     * Add a new course into the database
     * @param newCourse course to be added into database
     */
    public void add(Course newCourse) {
        courseList.add(newCourse);
    }





}
