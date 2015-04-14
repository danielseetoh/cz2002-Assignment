import java.util.ArrayList;
import java.util.List;

public class CourseDB {

    private List<Course> courseList = new ArrayList<Course>();


    public List<Course> getCourseList() {

        return this.courseList;

    }

    public Course getCourse(int courseID) {

        int i = 0;

        for(int j = 0; j<courseList.size(); j++){

            if(courseList.get(i).getCourseID() == courseID)
                return courseList.get(i);

            i++;

        }

        return null;

    }

    public void add(Course newCourse) {

        courseList.add(newCourse);

    }





}
