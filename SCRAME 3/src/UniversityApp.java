import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by danielseetoh on 4/14/2015.
 */
public class UniversityApp {
    private static Scanner sc;
    private static StudentManager studentManager = new StudentManager();
    private static ProfessorManager professorManager = new ProfessorManager();
    private static CourseManager courseManager = new CourseManager();
    private static RecordManager recordManager = new RecordManager();


    public static void main(String[] args) {

        int choice = -1;
        while (choice != 0) {
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

            switch(choice){
                case 0:
                    System.out.println("Goodbye!");
                    break;
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
                    setCourseComponentWeightage();
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
                    System.out.println("That is not a valid choice.");
                    break;
            }

        }
    }

    private static void addStudent(){
        System.out.println("Enter the student's name.");
        String name = sc.nextLine();
        int ID = studentManager.addStudent(name);
        System.out.println("Student " + name + " has been registered with ID " + ID + ".");
    }

    private static void addProfessor(){
        System.out.println("Enter the professor's name. ");
        String name = sc.nextLine();
        int ID = professorManager.addProfessor(name);
        System.out.println("Professor " + name + " has been registered with ID " + ID + ".");
    }

    private static void addCourse(){
        boolean success = false;
        while(!success){
            System.out.println("Enter name of course");
            String courseName = sc.next().toUpperCase();
            // currently assume courseName has no space

            System.out.println("Enter ID of course");
            int courseID = sc.nextInt();

            System.out.println("Enter ID of professor in charge");
            int professorID = sc.nextInt();

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


            success = professorManager.isExistingProfessorID(professorID) &&
                    numLectures >= 1 &&
                    numTutorials >= 0 &&
                    numLabs >= 0;

            if(!success){
                System.out.println("Error: Please try again.");
                //TODO: Specify type of error
            } else {
                courseManager.addCourse(courseID, courseName, professorID, numLectures, numTutorials, numLabs, lectureCapacity, tutorialCapacity, labCapacity);
                break;
            }
        }
    }
//appv
    private static void registerStudentForCourse(){
        //TODO: Compute vacancy

        System.out.println("Enter ID of student");
        int studentID = sc.nextInt();

        System.out.println("Enter ID of course");
        int courseID = sc.nextInt();


        boolean success = courseManager.isCourseReadyForRegistrationByID(courseID) &&
                studentManager.isExistingStudentID(studentID);

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
                        courseDB.setVacanciesByCourseLesson(courseName, i, lessonChoice[i]);
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
        System.out.println("Enter ID of student");
        int studentID = sc.nextInt();

        System.out.println("Enter ID of course");
        int courseID = sc.nextInt();

        int numberOfComponents = courseManager.getNumComponentsByCourseID(courseID);
        double[] componentMarks = new double[numberOfComponents];
        for(int i = 0; i < numberOfComponents; i++){
            System.out.println("Enter marks for component["+i+"]:");
            componentMarks[i] = sc.nextDouble();
        }
        recordManager.setCourseworkComponentMarks(courseID, studentID, componentMarks);
    }

    private static void setExamMark() {
        System.out.println("Enter ID of student");
        int studentID = sc.nextInt();

        System.out.println("Enter ID of course");
        String courseID = sc.next();

        System.out.println("Enter marks for exam:");
        double examMarks = sc.nextDouble();

        recordManager.setExamMarks(courseID, studentID, examMarks);
    }

    private static void printStudentNameListByCourseLesson(){
        System.out.println("Enter ID of Course");
        int courseID = sc.nextInt();

        System.out.println("Enter lesson type:");
        System.out.println("1. Lecture");
        System.out.println("2. Tutorial");
        System.out.println("3. Lab");
        int lessonType = sc.nextInt() - 1;

        String[] studentNameList = null;
        //lesson?
        int numLessons = courseManager.getNumLessonsByCourseID(courseID, lessonType);
        if(numLessons > 0){
            System.out.println("Select a " + Lesson.getLessonName(lessonType) + " ID");
            for(int i = 0; i < numLessons; i++){
                System.out.printf("%d\n", i);
            }
            int lessonID = sc.nextInt();
            studentNameList = recordManager.getStudentNameListByCourseLesson(courseID, lessonType, lessonID);
        }

        for(int i = 0; i < studentNameList.length; i++){
            System.out.println(studentNameList[i]);
        }
    }

    private static void checkLessonVacancies(){
        System.out.println("Enter ID of course");
        int courseID = sc.nextInt();

        System.out.println("Enter lesson type:");
        System.out.println("1. Lecture");
        System.out.println("2. Tutorial");
        System.out.println("3. Lab");
        int lessonType = sc.nextInt() - 1;

        int numLessons = courseDB.getNumLessonsByCourseID(courseID, lessonType);
        int[] lessonVacancies = courseDB.getLessonVacancyByCourseID(courseID, lessonType);
        if(numLessons > 0){
            System.out.println("ID\tVacancies");
            for(int i = 0; i < numLessons; i++){
                System.out.printf("%2d\t%9d\n", i, lessonVacancies[i]);
            }
        }
    }

    private static void printCourseStats(){
        System.out.println("Enter ID of course");
        int courseID = sc.nextInt();

        int numStudents = recordDB.getNumStudentsByCourseID(courseID);
        System.out.printf("Number of students: %d\n", numStudents);

        double averageOverallMarks = recordDB.getAverageOverallMarksByCourseID(courseID);
        System.out.printf("Average Overall Marks: %f\n", averageOverallMarks);

        double averageExamMarks = recordDB.getAverageExamMarksByCourseID(courseID);
        System.out.printf("Average Exam Marks: %f\n", averageExamMarks);

        double averageTotalCourseworkMarks = recordDB.getAverageTotalCourseworkMarksByCourseID(courseID);
        System.out.printf("Average Total Coursework Marks: %f\n", averageTotalCourseworkMarks);


    }

    private static void printStudentNameList(){
        String[] studentNameList = studentManager.getStudentNameList();
        for(int i = 0; i < studentNameList.length; i++){
            System.out.println(studentNameList[i]);
        }
    }

    private static void printCourseVacancy(){
        int[] courseIDList = courseManager.getCourseIDList();
        System.out.println("CourseName\tVacancy\tnumLectures\tnumTutorials\tnumLabs");
        for(int i = 0; i < courseIDList.length; i++){
            System.out.printf("%-10s\t%7d\t%11d\t%12d\t%7d\n",
                    courseIDList[i],
                    courseManager.getVacancyByCourseID(courseIDList[i]),
                    courseManager.getNumLessonsByCourseID(courseIDList[i],0),
                    courseManager.getNumLessonsByCourseID(courseIDList[i],1),
                    courseManager.getNumLessonsByCourseID(courseIDList[i],2));
        }
    }

/*    function to load from file all objects

    private static void loadObjects(){

    }

    */
}
