/**
 * Created by danielseetoh on 3/31/2015.
 */
public abstract class Lesson {

    private int vacancies;
    private int maxCapacity;
    private Student[] studentList;
    private String type;

    public int getVacancies(){
        return vacancies;
    }

    public int getMaxCapacity(){
        return maxCapacity;
    }

    public void enrollLesson(Student student){
        if(vacancies!=0){
            for(int i = 0; i<studentList.length; i++){
                if(studentList[i]==null){
                    studentList[i] = student;
                    System.out.println("Student has been enrolled in the course.");
                    break;
                }
            }
        }else{
            System.out.println("The lecture is currently full.");
        }
    }

    private void setVacancy(){  //new method
        int v = 0;
        for(int i = 0; i<studentList.length; i++){
            if(studentList[i]==null){
                v++;
            }
        }
        this.vacancies = v;
    }

    public Student[] getStudentList(){
        return studentList;
    }

    public abstract int getID();

    public String getType(){
        return type;
    }
}
