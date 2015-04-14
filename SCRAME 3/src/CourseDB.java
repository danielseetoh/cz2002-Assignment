import java.util.ArrayList;
import java.util.List;

public class CourseDB {

    private List<Course> courseList = new ArrayList<Course>();


    public List<Course> getCourseList() {

        return this.courseList;

    }

    public Course getCourse(int courseID) {

        int i = 0;

        do{

            if(courseList.get(i).getCourseID() == courseID)
                return courseList.get(i);

            i++;

        }while(i < courseList.size());

        return null;

    }

    public void addCourse(Course newCourse) {

        courseList.add(newCourse);

    }





}
