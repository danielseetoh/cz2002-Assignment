public enum LessonOption {
    LECTURE {
        public String toString() {
            return "Lecture";
        }
    },
    TUTORIAL {
        public String toString() {
            return "Tutorial";
        }
    },
    LAB {
        public String toString() {
            return "Lab";
        }
    };
    public int getNumLessonType() {
        return 3;
    }
}
