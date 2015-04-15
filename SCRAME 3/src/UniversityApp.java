import jdk.internal.util.xml.impl.Input;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.lang.Exception;
/**
 * Created by danielseetoh on 4/14/2015.
 */
public class UniversityApp {
    private static Scanner sc = new Scanner(System.in);
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
            System.out.println(" 12. Print student list");
            System.out.println(" 13. Print professor list");
            System.out.println(" 14. Print course list");

            System.out.println("  0. Exit");

            choice = sc.nextInt();

            switch(choice){
                case 0:
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    addStudent();
                    studentManager.printStudentList();
                    break;
                case 2:
                    addProfessor();
                    professorManager.printProfessorList();
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
                    studentManager.printStudentList();
                    break;
                case 13:
                    professorManager.printProfessorList();
                    break;
                case 14:
                    courseManager.printCourseList();
                    break;
                default:
                    System.out.println("That is not a valid choice.");
                    break;
            }

        }
    }

    private static void addStudent(){
        sc.nextLine();
        System.out.println("Enter the student's name.");
        String name = sc.nextLine();
        int ID = studentManager.addStudent(name);
        System.out.println("Student " + name + " has been registered with ID " + ID + ".");
    }

    private static void addProfessor(){
        sc.nextLine();
        System.out.println("Enter the professor's name. ");
        String name = sc.nextLine();
        int ID = professorManager.addProfessor(name);
        System.out.println("Professor " + name + " has been registered with ID " + ID + ".");
    }

    private static void addCourse(){
        try {
            if (professorManager.getProfessorNameList().length == 0)
                throw new NotSufficientException("professors");
        }catch(NotSufficientException e) {
            System.out.println("Please add a professor into the database before adding a course.");
            return;
        }
        boolean success = false;
        do{
            sc.nextLine();
            boolean succeed = false, succeed2 = false;
            int courseID = -1, professorID = -1, numLectures = -1, numTutorials = -1, numLabs = -1;
            int[] lectureCapacity = null;
            int[] tutorialCapacity = null;
            int[] labCapacity = null;

            System.out.println("Enter name of course");
            String courseName = sc.nextLine().toUpperCase();
            // currently assume courseName has no space

            do{
                try {
                    System.out.println("Enter ID of course");
                    courseID = sc.nextInt();
                    if (courseManager.isExistingCourse(courseID)) {
                        throw new IDException("Course");
                    }
                    succeed = true;
                }catch(InputMismatchException e){
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                }catch(IDException eID){
                    System.out.println(eID.getMessage());
                }
            }while(!succeed);
            succeed = false;

            do{
                try{
                    System.out.println("Enter ID of professor in charge");
                    professorID = sc.nextInt();
                    if (!professorManager.isExistingProfessorID(professorID)) {
                        throw new IDException("Professor");
                    }
                    succeed = true;
                }catch(InputMismatchException e){
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                }catch(IDException eID) {
                    System.out.println(eID.getMessage());
                }
            }while(!succeed);
            succeed = false;

            do {
                try {
                    System.out.println("Enter number of lectures (Minimum 1):");
                    numLectures = sc.nextInt();
                    if (numLectures < 1) {
                        throw new NotSufficientException("lectures");
                    }
                    lectureCapacity = new int[numLectures];
                    for (int i = 0; i < numLectures; i++) {
                        succeed2 = false;
                        do {
                            try {
                                System.out.println("Enter capacity of lecture " + (i + 1) + " :");
                                lectureCapacity[i] = sc.nextInt();
                                if (lectureCapacity[i] < 1) {
                                    throw new NotSufficientException("lecture slots");
                                }
                                succeed2 = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter an integer.");
                                sc.next();
                            } catch (NotSufficientException eID) {
                                System.out.println(eID.getMessage());
                            }
                        } while (!succeed2);
                    }
                    succeed = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                } catch (NotSufficientException eID) {
                    System.out.println(eID.getMessage());
                }
            }while (!succeed) ;
            succeed = false;
            succeed2 = false;

            do {
                try {
                    System.out.println("Enter number of tutorials:");
                    numTutorials = sc.nextInt();
                    tutorialCapacity = new int[numTutorials];
                    for (int i = 0; i < numTutorials; i++) {
                        succeed2 = false;
                        do {
                            try {
                                System.out.println("Enter capacity of tutorial " + (i + 1) + " :");
                                tutorialCapacity[i] = sc.nextInt();
                                if (tutorialCapacity[i] < 1) {
                                    throw new NotSufficientException("tutorial slots");
                                }
                                succeed2 = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter an integer.");
                                sc.next();
                            } catch (NotSufficientException eID) {
                                System.out.println(eID.getMessage());
                            }
                        } while (!succeed2);
                    }
                    succeed = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                }
            } while (!succeed);
            succeed = false;
            succeed2 = false;

            do {
                try {
                    System.out.println("Enter number of labs:");
                    numLabs = sc.nextInt();
                    labCapacity = new int[numLabs];
                    for (int i = 0; i < numLabs; i++) {
                        succeed2 = false;
                        do {
                            try {
                                System.out.println("Enter capacity of lab " + (i + 1) + " :");
                                labCapacity[i] = sc.nextInt();
                                if (labCapacity[i] < 1) {
                                    throw new NotSufficientException("lab slots");
                                }
                                succeed2 = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter an integer.");
                                sc.next();
                            } catch (NotSufficientException eID) {
                                System.out.println(eID.getMessage());
                            }
                        } while (!succeed2);
                    }
                    succeed = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                }
            } while (!succeed);
            succeed = false;
            succeed2 = false;

            success = professorManager.isExistingProfessorID(professorID) &&
                    numLectures >= 1 &&
                    numTutorials >= 0 &&
                    numLabs >= 0;

            if (!success) {
                System.out.println("Error: Please try again.");

                //TODO: Specify type of error
            } else {
                courseManager.addCourse(courseID, courseName, professorID, lectureCapacity, tutorialCapacity, labCapacity);
                System.out.println("Course " + courseName + " with ID " + courseID + " has been created.");
            }

        }while(!success);

    }

    private static void registerStudentForCourse(){

        // Function:
        // 1. To register a student (unique student ID) to a course (unique course ID)
        // 2. To register for the lessons (Lecture or Tutorial or Labs)

        try {
            if (courseManager.getCourseIDList().length == 0)
                throw new NotSufficientException("courses");
        } catch(NotSufficientException e) {
            System.out.println("Please add a course into the database before registration.");
            return;
        }
        try {
            if (studentManager.getStudentNameList().length == 0)
                throw new NotSufficientException("students");
        } catch(NotSufficientException e) {
            System.out.println("Please add a student into the database before registration.");
            return;
        }

                boolean success = false;
                int studentID = -1, courseID = -1;

                do {
                    try {
                        System.out.println("Enter ID of student");
                        studentID = sc.nextInt();
                        if (!studentManager.isExistingStudentID(studentID)) {
                            throw new IDException("Student");
                        }
                        success = true;
                    }catch(InputMismatchException e){
                        System.out.println("Please enter an integer.");
                        sc.nextLine();
                    }catch(IDException eID){
                        System.out.println(eID.getMessage());
                    }
                }while(!success);
                success = false;

                do {
                    try {
                        System.out.println("Enter ID of course");
                        courseID = sc.nextInt();
                        if (!courseManager.isCourseReadyForRegistrationByID(courseID)) {
                            throw new NotReadyForRegistrationException("Course ID " + courseID);
                        }
                        success = true;
                    }catch(InputMismatchException e){
                        System.out.println("Please enter an integer.");
                        sc.nextLine();
                    }catch(NotReadyForRegistrationException e){
                        System.out.println(e.getMessage());
                    }
                }while(!success);
                success = false;


                int numLessonTypes = LessonOption.getNumLessonType();
                int[] lessonChoice = new int[numLessonTypes];
                for (int i = 0; i < numLessonTypes; i++) {
                    LessonOption lessonOption = LessonOption.LECTURE;
                    if (i == 0)
                        lessonOption = LessonOption.LECTURE;
                    else if (i == 1)
                        lessonOption = LessonOption.TUTORIAL;
                    else if (i == 2)
                        lessonOption = LessonOption.LAB;

                    int numLessons = courseManager.getLessonCapacityByCourseID(courseID, lessonOption).length;
                    if (numLessons > 0) {
                        int[] lessonVacancy = courseManager.getLessonCapacityByCourseID(courseID, lessonOption);
                        do {
                            try {
                                System.out.println("Select a " + lessonOption.toString() + " ID");
                                System.out.println("ID\tVacancy");
                                for (int j = 0; j < lessonVacancy.length; j++) {
                                    if (lessonVacancy[j] > 0) {
                                        System.out.printf("%2d\t%7d\n", j, lessonVacancy[j]);
                                    }
                                }
                                lessonChoice[i] = sc.nextInt();
                                if (lessonChoice[i] >= 0 && lessonChoice[i] < numLessons) {
                                    throw new IDException("Lesson");
                                }
                            }catch(InputMismatchException e){
                                System.out.println("Please enter an integer.");
                                sc.nextLine();
                            }catch(IDException e){
                                System.out.println(e.getMessage());
                            }
                        }while(!success);
                        success = false;
                    } else {
                        lessonChoice[i] = -1;
                    }
                    try {
                        if (!recordManager.existingRecord(courseID, studentID)) {
                            int numComponents = courseManager.getNumComponentsByCourseID(courseID);
                            double examWeight = courseManager.getExamWeightByCourse(courseID);
                            double[] courseworkWeight = courseManager.getCourseworkWeightByCourse(courseID);
                            recordManager.addRecord(courseID, studentID, lessonChoice, numComponents, examWeight, courseworkWeight);
                            for (int j = 0; i < numLessonTypes; j++) {
                                if (j == 0)
                                    lessonOption = LessonOption.LECTURE;
                                else if (j == 1)
                                    lessonOption = LessonOption.TUTORIAL;
                                else if (j == 2)
                                    lessonOption = LessonOption.LAB;
                                if (lessonChoice[j] >= 0) {
                                    courseManager.setVacancyByCourseLesson(courseID, lessonOption, lessonChoice[j]);
                                }
                            }
                        } else {
                            throw new DuplicateException("Record");
                        }
                    }catch(DuplicateException e){
                        System.out.println(e.getMessage());
                    }

                }

    }

    private static void printStudentTranscript(){

        boolean succeed = false;
        int studentID = -1;
        int[] courseIDList = null;
        try{
            if(recordManager.getNumRecords() == 0){
                throw new NotSufficientException("records");
            }
        }catch(NotSufficientException e){
            System.out.println(e.getMessage());
        }

        do {
            try {
                System.out.println("Enter ID of student");
                studentID = sc.nextInt();
                if (!studentManager.isExistingStudentID(studentID)) {
                    throw new IDException("Student");
                }
                succeed = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
                sc.nextLine();
            }catch(IDException e){
                System.out.println(e.getMessage());
            }
        }while(!succeed);
        succeed = false;

        try {
            courseIDList = recordManager.getCourseIDByStudentID(studentID);
            if (courseIDList == null) {
                throw new RecordNotFoundException("Student ID " + studentID);
            }
        }catch(RecordNotFoundException e){
            System.out.println("There are no records for this student.");
        }

            for (int i = 0; i < recordManager.getNumCourseByStudentID(studentID); i++) {
                System.out.printf("Course Name  : %s\n", courseIDList[i]);
                if (recordManager.isMarked(courseIDList[i], studentID)) {
                    System.out.printf("Grade        : %s\n", recordManager.getGradeByCourseStudent(courseIDList[i], studentID));
                    System.out.printf("Overall Marks: %f\n", recordManager.getOverallMarksByCourseStudent(courseIDList[i], studentID));
                    System.out.printf("Exam Marks   : %f\n", recordManager.getExamMarksByCourseStudent(courseIDList[i], studentID));
                    System.out.printf("Exam Weight  : %f\n", courseManager.getExamWeightByCourse(courseIDList[i]));
                    double[] courseworkMarks = recordManager.getCourseworkMarksByCourseStudent(courseIDList[i], studentID);
                    double[] courseworkWeight = courseManager.getCourseworkWeightByCourse(courseIDList[i]);
                    for (int j = 0; j < courseworkMarks.length; j++) {
                        System.out.printf("Coursework[%d] Marks : %f\n", j, courseworkMarks[j]);
                        System.out.printf("Coursework[%d] Weight: %f\n", j, courseworkWeight[j] * (1 - courseManager.getExamWeightByCourse(courseIDList[i])));
                    }
                    System.out.println();

                } else {
                    System.out.println("Not Marked");
                }
            }
            System.out.println("End of Transcript");

    }

    private static void setCourseComponentWeightage(){

        boolean succeed = false;
        int numberOfCoursework = -1, courseID = -1;
        double examWeight = -1, counter = 0;
        double[] courseworkWeight = null;
        try{
            if(courseManager.getCourseIDList().length == 0){
                throw new NotSufficientException("courses");
            }
        }catch(NotSufficientException e){
            System.out.println(e.getMessage());
        }

        do {
            try {
                System.out.println("Enter ID of course:");
                courseID = sc.nextInt();
                if (!courseManager.isExistingCourse(courseID)) {
                    throw new IDException("course");
                }
                succeed = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
                sc.nextLine();
            }catch(IDException e){
                System.out.println(e.getMessage());
            }
        }while(!succeed);
        succeed = false;

        do {
            try {
                System.out.println("Enter weightage of Exam component (out of 100%):");
                examWeight = sc.nextDouble() / 100;
                if (examWeight < 0 || examWeight > 100) {
                    throw new InvalidValueException();
                }
                succeed = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter a number from 0 to 100.");
                sc.nextLine();
            }catch(InvalidValueException e){
                System.out.println("Please enter a number from 0 to 100.");
            }
        }while(!succeed);
        succeed = false;

        do {
            try {
                System.out.println("Enter number of coursework components:");
                numberOfCoursework = sc.nextInt();
                if (numberOfCoursework < 0 && examWeight != 100) {
                    throw new NotSufficientException();
                }
                succeed = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
                sc.nextLine();
            }catch(NotSufficientException e){
                System.out.println("Please enter a number larger than 0.");
            }
        }while(!succeed);
        succeed = false;



                courseworkWeight = new double[numberOfCoursework];
                for (int i = 0; i < numberOfCoursework; i++) {
                    do {
                        try {
                            System.out.println("Enter weightage of component[" + i + "] out of " + numberOfCoursework + ": (from 0% to " + (100-counter) + "%)");
                            courseworkWeight[i] = sc.nextDouble();
                            if (courseworkWeight[i] < 0.0 || courseworkWeight[i] > (100-counter)) {
                                throw new InvalidValueException();
                            }
                            counter+=courseworkWeight[i];
                            succeed = true;
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a number.");
                            sc.nextLine();
                        }catch(InvalidValueException e){
                            System.out.println(e.getMessage());
                        }
                    }while(!succeed);
                    succeed = false;
                }

            courseManager.setComponentWeightByCourseID(courseID, examWeight, courseworkWeight);

    }

    private static void setCourseworkMark(){

        int studentID = -1;
        int courseID = -1;
        boolean succeed = false;
        double[] componentMarks;

        do {
            try {
                System.out.println("Enter ID of student");
                studentID = sc.nextInt();
                if (!studentManager.isExistingStudentID(studentID))
                    throw new IDException("student");
                succeed = true;
            }catch(InputMismatchException e) {
                System.out.println("Wrong input type.");
                sc.nextLine();
            }catch(IDException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }while (!succeed);
        succeed = false;

        do {
            try {
                System.out.println("Enter ID of course");
                courseID = sc.nextInt();
                if (!courseManager.isExistingCourse(courseID))
                    throw new IDException("course");
                succeed = true;
            }catch(InputMismatchException e) {
                System.out.println("Wrong input type.");
                sc.nextLine();
            }catch (IDException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }while (!succeed);
        succeed = false;

        try {
            int numberOfComponents = courseManager.getNumComponentsByCourseID(courseID);
            if (numberOfComponents == -1)
                throw new IDException("Course");
            componentMarks = new double[numberOfComponents];
            for(int i = 0; i < numberOfComponents; i++){
                System.out.println("Enter marks for component["+i+"]:");
                componentMarks[i] = sc.nextDouble();
            }
            recordManager.setCourseworkComponentMarks(courseID, studentID, componentMarks);
        }catch(IDException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setExamMark() {
        int studentID = -1;
        int courseID = -1;
        boolean succeed = false;
        double examMarks = -1;

            do {
                try {
                    System.out.println("Enter ID of student");
                    studentID = sc.nextInt();
                    if (!studentManager.isExistingStudentID(studentID))
                        throw new IDException("student");
                    succeed = true;
                }catch(InputMismatchException e) {
                    System.out.println("Wrong input type.");
                    sc.nextLine();
                }catch (IDException e) {
                    System.out.println(e.getMessage());
                    sc.nextLine();
                }
            }while(!succeed);
            succeed = false;

            do {
                try {
                    System.out.println("Enter ID of course");
                    courseID = sc.nextInt();
                    if(!courseManager.isExistingCourse(courseID)){
                        throw new IDException("course");
                    }
                    succeed = true;
                }catch(InputMismatchException e) {
                    System.out.println("Wrong input type.");
                    sc.nextLine();
                }catch(IDException e) {
                    System.out.println(e.getMessage());
                    sc.nextLine(); 
                }
            }while(!succeed);
            succeed = false;

            do {
                try {
                    System.out.println("Enter marks for exam:");
                    examMarks = sc.nextDouble();
                    if (examMarks < 0 || examMarks > 100)
                        throw new InvalidValueException();
                    recordManager.setExamMarks(courseID, studentID, examMarks);
                    succeed = true;
                } catch (InputMismatchException e) {
                    System.out.println("Wrong input type.");
                    sc.nextLine();
                } catch (InvalidValueException e) {
                    System.out.println(e.getMessage());
                }
            }while(!succeed);
            succeed = false;
    }

    private static void printStudentNameListByCourseLesson(){
        LessonOption lessonOption = LessonOption.LECTURE;

        try {
            System.out.println("Enter ID of Course");
            int courseID = sc.nextInt();
            if (!courseManager.isExistingCourse(courseID)) {
                throw new IDException("course");
            }
            System.out.println("Enter lesson type:");
            System.out.println("1. Lecture");
            System.out.println("2. Tutorial");
            System.out.println("3. Lab");
            int lessonType = sc.nextInt() - 1;
            if (lessonType == 0)
                lessonOption = LessonOption.LECTURE;
            else if (lessonType == 1)
                lessonOption = LessonOption.TUTORIAL;
            else if (lessonType == 2)
                lessonOption = LessonOption.LAB;


            int[] studentNameList = null;
            int numLessons = courseManager.getLessonCapacityByCourseID(courseID, lessonOption).length;
            if (numLessons > 0) {
                System.out.println("Select a " + lessonOption.toString() + " ID");
                for (int i = 0; i < numLessons; i++) {
                    System.out.printf("%d\n", i);
                }
                int lessonID = sc.nextInt();
                studentNameList = recordManager.getStudentIDListByCourseLesson(courseID, lessonOption, lessonID);
            }

            for (int i = 0; i < studentNameList.length; i++) {
                System.out.println(studentNameList[i]);
            }
        }catch (IDException e) {
            System.out.println(e.getMessage());
        }
    }

    //method to check the vacancies of a particular lesson in a particular course identified by its courseID
    private static void checkLessonVacancies(){

        try {
            if (courseManager.getCourseIDList().length == 0)
                throw new NotSufficientException("courses");
        } catch(NotSufficientException e) {
            System.out.println("Please add a course into the database before registration.");
            return;
        }

        //initializing the variables in the method
        LessonOption lessonOption = LessonOption.LECTURE;

        //courseID has to be initialized to 0 first because the scanning of the courseID has to be in the try block and therefore might never reach the body of the function
        int courseID = 0;

        //initializing of the value of succeed so that it will make the function keep looping in the try block until a valid courseID is being entered
        boolean succeed = false;

        do {
            try {
                System.out.println("Enter ID of course");
                courseID = sc.nextInt();
                if (!courseManager.isExistingCourse(courseID)) {
                    throw new IDException("Course");
                }
                succeed = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
                sc.nextLine();
            }catch(IDException e){
                System.out.println(e.getMessage());
            }
        }while(!succeed);
        succeed = false;

        int lessonType = -1;

        //for user to choose what type of lesson's vacancies that he or she wants to check
        while(lessonType!=3) {
            System.out.println("Enter lesson type:");
            System.out.println("1. Lecture");
            System.out.println("2. Tutorial");
            System.out.println("3. Lab");
            System.out.println("4. Quit");

            do {
                try {
                    lessonType = sc.nextInt() - 1;
                    if (lessonType < 0 || lessonType > 3) {
                        throw new InvalidValueException();
                    }
                    succeed = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                } catch (InvalidValueException e) {
                    System.out.println(e.getMessage());
                }
            } while (!succeed);


            if (lessonType == 0)
                lessonOption = LessonOption.LECTURE;
            else if (lessonType == 1)
                lessonOption = LessonOption.TUTORIAL;
            else if (lessonType == 2)
                lessonOption = LessonOption.LAB;

            //getting the array of vacancies in the type of lesson selected in the particular course
            //array is used because there can be more than one lesson of that type in the course
            int[] lessonVacancies = courseManager.getLessonVacancyByCourseID(courseID, lessonOption);

            int numLessons = lessonVacancies.length;

            if(lessonType != 3) {
                //if the length of lessonVacancies is more than 0, it means that the lesson of that type is available in the particular course
                if (numLessons > 0) {
                    System.out.println("ID\tVacancies");
                    for (int i = 0; i < numLessons; i++) {
                        System.out.printf("%2d\t%9d\n", i, lessonVacancies[i]);
                    }
                }

                //if length of lessonVacancies is 0, then there are no such type of lessons in the particular course
                else
                    System.out.println("There are no " + lessonOption.toString() + " available!");
            }
        }
    }

    private static void printCourseStats(){
        try {
            if (courseManager.getCourseIDList().length == 0)
                throw new NotSufficientException("courses");
        } catch(NotSufficientException e) {
            System.out.println("Please add a course into the database before registration.");
            return;
        }

        //courseID has to be initialized to 0 first because the scanning of the courseID has to be in the try block and therefore might never reach the body of the function
        int courseID = 0;

        //initializing of the value of succeed so that it will make the function keep looping in the try block until a valid courseID is being entered
        boolean succeed = false;

        do {
            try {
                System.out.println("Enter ID of course");
                courseID = sc.nextInt();


                //if the courseID is not valid the function will throw the invalid ID exception


                if (courseManager.isExistingCourse(courseID)) {
                    throw new IDException("Course");
                }
                succeed = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
                sc.nextLine();
            }catch(IDException e){
                System.out.println(e.getMessage());
            }
        }while(!succeed);

        //getting the number of students in the course selected by the user
        int numStudents = recordManager.getNumStudentsByCourseID(courseID);
        System.out.printf("Number of students: %d\n", numStudents);

        //getting the average overall marks, of the course selected by the user
        double averageOverallMarks = recordManager.getAverageOverallMarksByCourseID(courseID);
        System.out.printf("Average Overall Marks: %f\n", averageOverallMarks);

        //getting the average exam marks of the course selected by the user
        double averageExamMarks = recordManager.getAverageExamMarksByCourseID(courseID);
        System.out.printf("Average Exam Marks: %f\n", averageExamMarks);

        //getting the average marks of all the course works of the course selected by the user
        double averageTotalCourseworkMarks = recordManager.getAverageTotalCourseworkMarksByCourseID(courseID);
        System.out.printf("Average Total Coursework Marks: %f\n", averageTotalCourseworkMarks);

    }
}




/*    function to load from file all objects

    private static void loadObjects(){

    }


}*/
