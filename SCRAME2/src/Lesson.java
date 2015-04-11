
public class Lesson extends Slots{
    private int lessonType;
    private int lessonID;

    public Lesson(int lessonType, int lessonID, int capacity){
        super(capacity);
        this.lessonType = lessonType;
        this.lessonID = lessonID;
    }

    //Getters
    public int getLessonType() {
        return lessonType;
    }

    public int getLessonID() {
        return lessonID;
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
}
