import java.util.ArrayList;
import java.util.List;

public class CourseDB {

    private List<Course> courseList = new ArrayList<Course>();


    public List<Course> getCourseList() {

        return this.courseList;

    }// this is a method that is being called by courseManager to return all the courses that are currently in the database

    public Course getCourse(int courseID) {

        int i = 0;

        if (courseList.size() == 0)
            return null;

        for(int j = 0; j<courseList.size(); j++){

            if(courseList.get(i).getCourseID() == courseID)
                return courseList.get(i);

            i++;

        }

        return null;

    }

    public void add(Course newCourse) {

        courseList.add(newCourse);

    }// method that is being called by the courseManager to add course into the database





}
