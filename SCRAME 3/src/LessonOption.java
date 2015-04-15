
//enum for different types of Lessons
public enum LessonOption {
    LECTURE {   //Lecture type

        public String toString() {
            return "Lecture";
        }
    },
    TUTORIAL {  //Tutorial type
        public String toString() {
            return "Tutorial";
        }
    },
    LAB {       //Lab type
        public String toString() {
            return "Lab";
        }
    };
    public static int getNumLessonType() {
        return 3;
    }
}
