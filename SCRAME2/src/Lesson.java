
public abstract class Lesson {
    private int lessonID;
    private int capacity;
    public static final int numLessonTypes = 3;

    public Lesson(int lessonID, int capacity){
        this.capacity = capacity;
        this.lessonID = lessonID;
    }

    public int getCapacity() {
        return capacity;
    }

    public abstract String getName();
}
