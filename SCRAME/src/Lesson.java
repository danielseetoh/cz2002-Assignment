
public class Lesson {
    private int lessonID;
    private int capacity;
    private int vacancies;

    public Lesson(int lessonID, int capacity){
        this.lessonID = lessonID;
        this.capacity = capacity;
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
}
