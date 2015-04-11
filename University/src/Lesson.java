import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielseetoh on 3/31/2015.
 */
public abstract class Lesson {

    protected int vacancies;
    protected int maxCapacity;
    protected List<Student> studentList = new ArrayList<Student>();
    protected String lessonType;
    protected int ID;

    public int getVacancy(){
        updateVacancy();
        return vacancies;
    }

    public int getMaxCapacity(){
        return maxCapacity;
    }

    public void enrollLesson(Student student){
        if(getVacancy()!=0){
            studentList.add(student);
            System.out.println("Student has been enrolled in the Lesson.");
        }else{
            System.out.println("The Lesson is currently full.");
        }
        updateVacancy();
    }

    private void updateVacancy(){  //new method
        this.vacancies = maxCapacity - studentList.size();
    }

    public List<Student> getStudentList(){
        return studentList;
    }

    public int getID(){
        return ID;
    }

    public String getLessonType(){
        return lessonType;
    }
}
