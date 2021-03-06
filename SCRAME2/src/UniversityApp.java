import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UniversityApp {

    private static Scanner sc = new Scanner(System.in);
    private static StudentDB studentDB = new StudentDB();
    private static CourseManager courseManager = new CourseManager();
    private static ProfessorDB professorDB = new ProfessorDB();
    private static RecordDB recordDB = new RecordDB();


    public static void main(String[] args){
        int choice = -1;
        while(choice != 0) {
            System.out.println("Admin Options: ");
            System.out.println("  1. Add a student");
            System.out.println("  2. Add a professor");
            System.out.println("  3. Add a course ");

            System.out.println("Student Options: ");
            System.out.println("  4. Register Student for a course ");
            System.out.println("  5. Print student transcript");

            System.out.println("Professor Options: ");
            System.out.println("  6. Enter course assessment components weightage");
            System.out.println("  7. Enter coursework mark - inclusive of its components");
            System.out.println("  8. Enter exam mark");

            System.out.println("Public Options: ");
            System.out.println("  9. Print student list by lecture, tutorial or laboratory session for a course");
            System.out.println(" 10. Check available slot in a class (vacancy in a class)");
            System.out.println(" 11. Print course statistics");
            System.out.println(" 12. Print student name list");
            System.out.println(" 13. Print course vacancy list");


            System.out.println("  0. Exit");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addProfessor();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    registerStudentForCourse();
                    break;
                case 5:
                    printStudentTranscript();
                    break;
                case 6:
                    setCourseComponentWeightage();
                    break;
                case 7:
                    setCourseworkMark();
                    break;
                case 8:
                    setExamMark();
                    break;
                case 9:
                    printStudentNameListByCourseLesson();
                    break;
                case 10:
                    checkLessonVacancies();
                    break;
                case 11:
                    printCourseStats();
                    break;
                case 12:
                    printStudentNameList();
                    break;
                case 13:
                    printCourseVacancy();
                    break;
                default:
                    break;
            }
        }
    }

    //TODO Check that input is valid
    private static void addStudent(){
        boolean success = false;
        while(!success){
            System.out.println("Enter name of student");
            String studentName = sc.next();
            // currently assume studentName has no space

            success = !studentDB.isExistingStudentName(studentName);

            if(!success){
                System.out.println("Student with name " + studentName + " already exists. Please try again with a different name.");
            } else {
                studentDB.addStudent(studentName);
                System.out.println("Student with name " + studentName + " is successfully registered with ID: " + studentDB.getStudentIDByName(studentName));
                break;
            }
        }
    }

    private static void addProfessor(){
        boolean success = false;
        while(!success){
            System.out.println("Enter name of professor");
            String professorName = sc.next();
            // currently assume professorName has no space

            success = !professorDB.isExistingProfessorName(professorName);
            if(!success){
                System.out.println("Professor with name " + professorName + " already exists. Please try again with a different name.");
            } else {
                professorDB.addProfessor(professorName);
                break;
            }
        }
    }

    private static void addCourse(){
        boolean success = false;
        while(!success){
            System.out.println("Enter name of course");
            String courseName = sc.next().toUpperCase();
            // currently assume courseName has no space

            System.out.println("Enter name of professor in charge");
            String professorName = sc.next();

            System.out.println("Enter number of lectures:");
            int numLectures = sc.nextInt();
            int[] lectureCapacity = new int[numLectures];
            for(int i = 0; i < numLectures; i++){
                System.out.println("Enter capacity of lecture "+(i+1)+" :");
                lectureCapacity[i] = sc.nextInt();
            }
            System.out.println("Enter number of tutorials:");
            int numTutorials = sc.nextInt();
            int[] tutorialCapacity = new int[numTutorials];
            for(int i = 0; i < numTutorials; i++){
                System.out.println("Enter capacity of tutorial "+(i+1)+" :");
                tutorialCapacity[i] = sc.nextInt();
            }
            System.out.println("Enter number of labs:");
            int numLabs = sc.nextInt();
            int[] labCapacity = new int[numLabs];
            for(int i = 0; i < numLabs; i++){
                System.out.println("Enter capacity of lab "+(i+1)+" :");
                labCapacity[i] = sc.nextInt();
            }

            List<int[]> lessonCapacity = new ArrayList<>();
            lessonCapacity.add(lectureCapacity);
            lessonCapacity.add(tutorialCapacity);
            lessonCapacity.add(labCapacity);


            success = professorDB.isExistingProfessorName(professorName) &&
                    !courseManager.isExistingCourseName(courseName) &&
                    numLectures >= 0 &&
                    numTutorials >= 0 &&
                    numLabs >= 0;

            if(!success){
                System.out.println("Error: Please try again.");
                //TODO: Specify type of error
            } else {
                courseManager.addCourse(courseName, professorName, lessonCapacity);
                break;
            }
        }
    }

    private static void registerStudentForCourse(){
        //TODO: Compute vacancy

        System.out.println("Enter name of student");
        String studentName = sc.next();

        System.out.println("Enter name of course");
        String courseName = sc.next();


        boolean success = courseManager.isCourseReadyForRegistrationByName(courseName) &&
                studentDB.isExistingStudentName(studentName);

        if(success){
            //register
            int numLessonTypes = Lesson.numLessonTypes;
            int[] lessonChoice = new int[numLessonTypes];
            for(int i = 0; i < numLessonTypes; i++){
                int numLessons = courseManager.getLessonCapacityByCourseName(courseName, i).length;
                if(numLessons > 0){
                    int[] lessonVacancy = courseManager.getLessonCapacityByCourseName(courseName, i);
                    System.out.println("Select a " + Lesson.getLessonName(i) + " ID");
                    System.out.println("ID\tVacancy");
                    for(int j = 0; j < lessonVacancy.length; j++){
                        if(lessonVacancy[j] > 0) {
                            System.out.printf("%2d\t%7d\n", j, lessonVacancy[j]);
                        }
                    }
                    lessonChoice[i] = sc.nextInt();
                } else {
                    lessonChoice[i] = -1;
                }
            }
            if(!recordDB.existingRecord(courseName, studentName)){
                int numComponents = courseDB.getNumComponentsByCourseName(courseName);
                double examWeight = courseDB.getExamWeightByCourse(courseName);
                double[] courseworkWeight = courseDB.getCourseworkWeightByCourse(courseName);
                recordDB.addRecord(courseName, studentName, lessonChoice, numComponents, examWeight, courseworkWeight);
                courseDB.setVacanciesByCourse(courseName, getVacanciesByCourse(courseName));
                for(int i = 0; i < numLessonTypes; i++){
                    if(lessonChoice[i] >= 0){
                        courseDB.setVacanciesByCourseLesson(courseName, i, lessonChoice[i], getVacanciesByCourseLesson(courseName, i, lessonChoice[i]));
                    }
                }
            } else {
                //Duplicate copy
            }

        } else {
            //cannot register
            System.out.println("Course is not open for registration");
        }
    }

    private static void printStudentTranscript(){
        System.out.println("Enter name of student");
        String studentName = sc.next();

        String[] courseNameList = recordDB.getCourseListByStudent(studentName);

        for(int i = 0; i < recordDB.getNumCourseByStudent(studentName); i++){
            System.out.printf("Course Name  : %s\n", courseNameList[i]);
            if(recordDB.isMarked(courseNameList[i], studentName)){
                System.out.printf("Grade        : %s\n", recordDB.getGradeByCourseStudent(courseNameList[i], studentName));
                System.out.printf("Overall Marks: %f\n", recordDB.getOverallMarksByCourseStudent(courseNameList[i], studentName));
                System.out.printf("Exam Marks   : %f\n", recordDB.getExamMarksByCourseStudent(courseNameList[i], studentName));
                System.out.printf("Exam Weight  : %f\n", courseDB.getExamWeightByCourse(courseNameList[i]));
                double[] courseworkMarks = recordDB.getCourseworkMarksByCourseStudent(courseNameList[i], studentName);
                double[] courseworkWeight = courseDB.getCourseworkWeightByCourse(courseNameList[i]);
                for(int j = 0; j < courseworkMarks.length; j++){
                    System.out.printf("Coursework[%d] Marks : %f\n", j, courseworkMarks[j]);
                    System.out.printf("Coursework[%d] Weight: %f\n", j, courseworkWeight[j]*(1-courseDB.getExamWeightByCourse(courseNameList[i])));
                }
                System.out.println();

            } else {
                System.out.println("Not Marked");
            }
        }
        System.out.println("End of Transcript");
    }

    private static void setCourseComponentWeightage(){
        System.out.println("Enter name of course:");
        String courseName = sc.next();
        System.out.println("Enter weightage of Exam component:");
        double examWeight = sc.nextDouble();
        System.out.println("Enter number of coursework components:");
        int numberOfCoursework = sc.nextInt();
        double[] courseworkWeight = new double[numberOfCoursework];
        for(int i = 0; i < numberOfCoursework; i++){
            System.out.println("Enter weightage of component[" + i + "]:");
            courseworkWeight[i] = sc.nextDouble();
        }
        courseDB.setComponentWeightByCourseName(courseName, examWeight, courseworkWeight);
    }

    private static void setCourseworkMark(){
        System.out.println("Enter name of student");
        String studentName = sc.next();

        System.out.println("Enter name of course");
        String courseName = sc.next();

        int numberOfComponents = courseDB.getNumComponentsByCourseName(courseName);
        double[] componentMarks = new double[numberOfComponents];
        for(int i = 0; i < numberOfComponents; i++){
            System.out.println("Enter marks for component["+i+"]:");
            componentMarks[i] = sc.nextDouble();
        }
        recordDB.setCourseworkComponentMarks(courseName, studentName, componentMarks);
    }

    private static void setExamMark() {
        System.out.println("Enter name of student");
        String studentName = sc.next();

        System.out.println("Enter name of course");
        String courseName = sc.next();

        System.out.println("Enter marks for exam:");
        double examMarks = sc.nextDouble();

        recordDB.setExamMarks(courseName, studentName, examMarks);
    }

    private static void printStudentNameListByCourseLesson(){
        System.out.println("Enter name of course");
        String courseName = sc.next();

        System.out.println("Enter lesson type:");
        System.out.println("1. Lecture");
        System.out.println("2. Tutorial");
        System.out.println("3. Lab");
        int lessonType = sc.nextInt() - 1;

        String[] studentNameList = null;
        int numLessons = courseDB.getNumLessonsByCourseName(courseName, lessonType);
        if(numLessons > 0){
            System.out.println("Select a " + Lesson.getLessonName(lessonType) + " ID");
            for(int i = 0; i < numLessons; i++){
                System.out.printf("%d\n", i);
            }
            int lessonID = sc.nextInt();
            studentNameList = recordDB.getStudentNameListByCourseLesson(courseName, lessonType, lessonID);
        }

        for(int i = 0; i < studentNameList.length; i++){
            System.out.println(studentNameList[i]);
        }
    }

    private static void checkLessonVacancies(){
        System.out.println("Enter name of course");
        String courseName = sc.next();

        System.out.println("Enter lesson type:");
        System.out.println("1. Lecture");
        System.out.println("2. Tutorial");
        System.out.println("3. Lab");
        int lessonType = sc.nextInt() - 1;

        int numLessons = courseDB.getNumLessonsByCourseName(courseName, lessonType);
        int[] lessonVacancies = courseDB.getLessonVacancyByCourseName(courseName, lessonType);
        if(numLessons > 0){
            System.out.println("ID\tVacancies");
            for(int i = 0; i < numLessons; i++){
                System.out.printf("%2d\t%9d\n", i, lessonVacancies[i]);
            }
        }
    }

    private static void printCourseStats(){
        System.out.println("Enter name of course");
        String courseName = sc.next();

        int numStudents = recordDB.getNumStudentsByCourse(courseName);
        System.out.printf("Number of students: %d\n", numStudents);

        double averageOverallMarks = recordDB.getAverageOverallMarksByCourse(courseName);
        System.out.printf("Average Overall Marks: %f\n", averageOverallMarks);

        double averageExamMarks = recordDB.getAverageExamMarksByCourse(courseName);
        System.out.printf("Average Exam Marks: %f\n", averageExamMarks);

        double averageTotalCourseworkMarks = recordDB.getAverageTotalCourseworkMarksByCourse(courseName);
        System.out.printf("Average Total Coursework Marks: %f\n", averageTotalCourseworkMarks);


    }

    private static void printStudentNameList(){
        String[] studentNameList = studentDB.getStudentNameList();
        for(int i = 0; i < studentNameList.length; i++){
            System.out.println(studentNameList[i]);
        }
    }

    private static void printCourseVacancy(){
        String[] courseNameList = courseDB.getCourseNameList();
        System.out.println("CourseName\tVacancy\tnumLectures\tnumTutorials\tnumLabs");
        for(int i = 0; i < courseNameList.length; i++){
            System.out.printf("%-10s\t%7d\t%11d\t%12d\t%7d\n",
                    courseNameList[i],
                    courseDB.getVacancyByCourseName(courseNameList[i]),
                    courseDB.getNumLessonsByCourseName(courseNameList[i],0),
                    courseDB.getNumLessonsByCourseName(courseNameList[i],1),
                    courseDB.getNumLessonsByCourseName(courseNameList[i],2));
        }
    }

    private static int getVacanciesByCourse(String courseName){
        return courseDB.getCapacityByCourse(courseName) - recordDB.getNumStudentsByCourse(courseName);
    }

    private static int getVacanciesByCourseLesson(String courseName, int lessonType, int lessonID){
        return courseDB.getLessonCapacityByCourseLesson(courseName, lessonType, lessonID) - recordDB.getNumStudentsByCourseLesson(courseName, lessonType, lessonID);
    }

}
