
//enumeration for different types of Lessons
public enum LessonOption {
    LECTURE {   //Lecture type

        @Override   //return "Lecture" String
        public String toString() {
            return "Lecture";
        }
    },
    TUTORIAL {  //Tutorial type

        @Override   //return "Tutorial" String
        public String toString() {
            return "Tutorial";
        }
    },
    LAB {       //Lab type

        @Override   //return "Lab" String
        public String toString() {
            return "Lab";
        }
    };

    //return number of lesson types
    public static int getNumLessonType() {
        return 3;
    }
}
