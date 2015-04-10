
public class Lesson {
    private int lessonType;
    private int lessonID;

    private int capacity;
    private int vacancies;

    public Lesson(int lessonType, int lessonID, int capacity){
        this.lessonType = lessonType;
        this.lessonID = lessonID;
        this.capacity = capacity;
        this.vacancies = capacity;
    }

    //Getters
    public int getLessonType() {
        return lessonType;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getLessonID() {
        return lessonID;
    }

    public int getVacancies() {
        return vacancies;
    }

    public static int getNumLessonTypes(){
        return 3;
    }

    public static String getLessonName(int lessonType){
        switch (lessonType){
            case 0:
                return "lecture";
            case 1:
                return "tutorial";
            case 2:
                return "lab";
            default:
                return null;
        }
    }


    //Setters
    public void fillVacancy (){
        this.vacancies--;
    }



    //Verifiers
    public boolean canRegister (){
        if(vacancies > 0){
            return true;
        } else {
            return false;
        }
    }
}
