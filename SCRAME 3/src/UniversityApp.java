import jdk.internal.util.xml.impl.Input;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.lang.Exception;
/**
 * Created by danielseetoh on 4/14/2015.
 */

/**
 * main class
 */
public class UniversityApp {
    private static Scanner sc = new Scanner(System.in);
    private static StudentManager studentManager = new StudentManager();
    private static ProfessorManager professorManager = new ProfessorManager();
    private static CourseManager courseManager = new CourseManager();
    private static RecordManager recordManager = new RecordManager();

    /**
     * initialises application
     * @param args TODO:
     */
    public static void main(String[] args) {
        //load students, professors and courses into program
        loadStudents();
        loadProfessors();
        loadCourses();
        int choice = -1;
        while (choice != 0) {
            System.out.println();
            System.out.println("     MAIN MENU");
            System.out.println("     =========");

            System.out.println("  1. Add a student");
            System.out.println("  2. Add a professor");
            System.out.println("  3. Add a course ");
            System.out.println("  4. Enter course assessment components weightage");
            System.out.println("  5. Register Student for a course ");
            System.out.println("  6. Print student list by lecture, tutorial or laboratory session for a course");
            System.out.println("  7. Check available slot in a class (vacancy in a class)");
            System.out.println("  8. Enter coursework mark - inclusive of its components");
            System.out.println("  9. Enter exam mark");
            System.out.println(" 10. Print student transcript");
            System.out.println(" 11. Print course statistics");

            System.out.println(" 12. Print student list");
            System.out.println(" 13. Print professor list");
            System.out.println(" 14. Print course list");

            System.out.println("  0. Exit");
            System.out.println();
            System.out.print("Option: ");

            boolean succeed = false;
            do {
                try {
                    choice = sc.nextInt();
                    succeed = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                }
            } while(!succeed);

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
                    setCourseComponentWeightage();
                    break;
                case 5:
                    registerStudentForCourse();
                    break;
                case 6:
                    printStudentNameListByCourseLesson();
                    break;
                case 7:
                    checkLessonVacancies();
                    break;
                case 8:
                    setCourseworkMark();
                    break;
                case 9:
                    setExamMark();
                    break;
                case 10:
                    printStudentTranscript();
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

    /**
     * adds a student after checking for duplicates and invalid entries
     */
    private static void addStudent(){
        sc.nextLine();
        System.out.println("Enter the student's name.");
        String name = null;
        boolean succeed = false;

        do {
            try {
                //check that names can only be alphabetical
                name = sc.nextLine();
                if (name.matches((".*\\d+.*"))) {
                    throw new InvalidValueException("alphabets only");
                }
                succeed = true;
            } catch (InvalidValueException e) {
                System.out.println(e.getMessage());
            }
        }while(!succeed);
        succeed = false;

        //handles duplicate names, allow user to confirm adding in
        if(studentManager.isExistingStudentName(name)){
            System.out.println("The name you have entered already exists. Are you sure you want to add this student?");
            System.out.println(" 1. Yes\t\t2. No");
            do {
                try {
                    int addStudent = sc.nextInt();
                    if(addStudent != 1 && addStudent != 2){
                        throw new InvalidValueException("1 or 2");
                    } else if(addStudent == 1){
                        succeed = true;
                    } else {
                        return;
                    }
                }catch (InputMismatchException e){
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                }catch (InvalidValueException e){
                    System.out.println(e.getMessage());
                }
            }while(!succeed);
        }

        //add student into database
        int ID = studentManager.addStudent(name);
        System.out.println("Student " + name + " has been registered with ID " + ID + ".");
        studentManager.printStudentList();

    }

    /**
     * adds professor after checking for duplicates and invalid entries
     */
    private static void addProfessor(){
        sc.nextLine();
        System.out.println("Enter the professor's name. ");
        String name = null;
        boolean succeed = false;


        do {
            try {
                //check that name can only be alphabetical
                name = sc.nextLine();
                if (name.matches((".*\\d+.*"))) {
                    throw new InvalidValueException("alphabets only");
                }
                succeed = true;
            } catch (InvalidValueException e) {
                System.out.println(e.getMessage());
            }
        }while(!succeed);
        succeed = false;

        //handle duplicate names, allows user to confirm adding in
        if(professorManager.isExistingProfessorName(name)){
            System.out.println("The name you have entered already exists. Are you sure you want to add this professor?");
            System.out.println(" 1. Yes\t\t2. No");
            do {
                try {
                    int addProf = sc.nextInt();
                    if(addProf != 1 && addProf != 2){
                        throw new InvalidValueException("1 or 2");
                    } else if(addProf == 1){
                        succeed = true;
                    } else {
                        return;
                    }
                }catch (InputMismatchException e){
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                }catch (InvalidValueException e){
                    System.out.println(e.getMessage());
                }
            }while(!succeed);
        }

        int ID = professorManager.addProfessor(name);
        System.out.println("Professor " + name + " has been registered with ID " + ID + ".");
        professorManager.printProfessorList();
    }

    /**
     * adds a course after checking for duplicates and invalid entries
     */
    private static void addCourse(){

        //adding a course requires a professor
        //this checks if there are professors already registered into the database
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

            //get the course ID
            do{
                try {
                    System.out.println("Enter ID of course below:");
                    courseManager.printCourseList();
                    courseID = sc.nextInt();
                    //check that the course ID does not already exist in the database
                    if (courseManager.isExistingCourse(courseID)) {
                        throw new DuplicateException("Course");
                    }
                    succeed = true;
                }catch(InputMismatchException e){
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                }catch(DuplicateException eID){
                    System.out.println(eID.getMessage());
                }
            }while(!succeed);
            succeed = false;

            //get the professor's ID
            do{
                try{
                    System.out.println("Select ID from professors below:");
                    professorManager.printProfessorList();
                    professorID = sc.nextInt();
                    //check that the professor ID does not already exist in the database
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

            //enter the number of lectures
            do {
                try {
                    System.out.println("Enter number of lectures (Minimum 1):");
                    numLectures = sc.nextInt();
                    //minimum number of lectures is 1
                    if (numLectures < 1) {
                        throw new NotSufficientException("lectures");
                    }
                    //enter the lecture capacity for each lecture
                    lectureCapacity = new int[numLectures];
                    for (int i = 0; i < numLectures; i++) {
                        succeed2 = false;
                        do {
                            try {
                                System.out.println("Enter capacity of lecture " + (i + 1) + " :");
                                lectureCapacity[i] = sc.nextInt();
                                //lecture capacity cannot be 0 or less
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

            //enter the number of tutorials
            do {
                try {
                    System.out.println("Enter number of tutorials:");
                    numTutorials = sc.nextInt();
                    //enter the tutorial capacity for each tutorial
                    tutorialCapacity = new int[numTutorials];
                    for (int i = 0; i < numTutorials; i++) {
                        succeed2 = false;
                        do {
                            try {
                                System.out.println("Enter capacity of tutorial " + (i + 1) + " :");
                                tutorialCapacity[i] = sc.nextInt();
                                //tutorial capacity cannot be 0 or less
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

            //enter the number of labs
            do {
                try {
                    System.out.println("Enter number of labs:");
                    numLabs = sc.nextInt();
                    //enter the lab capacity for each lab
                    labCapacity = new int[numLabs];
                    for (int i = 0; i < numLabs; i++) {
                        succeed2 = false;
                        do {
                            try {
                                System.out.println("Enter capacity of lab " + (i + 1) + " :");
                                labCapacity[i] = sc.nextInt();
                                //lab capacity cannot be 0 or less
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

            //checks that all pre-requisites are met before adding course
            success = professorManager.isExistingProfessorID(professorID) &&
                    numLectures >= 1 &&
                    numTutorials >= 0 &&
                    numLabs >= 0;

            if (!success) {
                System.out.println("Error: Please try again.");

            } else {
                courseManager.addCourse(courseID, courseName, professorID, lectureCapacity, tutorialCapacity, labCapacity);
                System.out.println("Course " + courseName + " with ID " + courseID + " has been created.");
            }

        }while(!success);

    }


    /**
     * sets course component weightage after checking for invalid entries
     */
    private static void setCourseComponentWeightage(){

        boolean succeed = false, succeed2 = false;
        int numberOfCoursework = -1, courseID = -1;
        double examWeight = -1, counter = 0;
        double[] courseworkWeight = null;

        //check that there are courses existing in the database before allowing this method to continue
        try{
            if(courseManager.getCourseIDList().length == 0){
                throw new NotSufficientException("courses");
            }
        }catch(NotSufficientException e){
            System.out.println(e.getMessage());
            return;
        }

        //enter the course ID
        do {
            try {
                System.out.println("Select ID from courses below:");
                courseManager.printCourseList();
                courseID = sc.nextInt();
                //checks if the course ID exists in the database
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

        //if there are students already registered for this course, the course component weightage cannot be chaanged
        try {
            if (recordManager.getNumStudentsByCourseID(courseID) > 0) {
                throw new CourseWeightageException();
            }
        }catch(CourseWeightageException e){
            System.out.println(e.getMessage());
            return;
        }

        //get the weightage of the exam component
        do {
            try {
                System.out.println("Enter weightage of Exam component (out of 100%):");
                examWeight = sc.nextDouble() / 100;
                //checks that the weightage is not out of bounds
                if (examWeight < 0 || examWeight > 1) {
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

        //get number of coursework components
        do {
            try {
                System.out.println("Enter number of coursework components:");
                numberOfCoursework = sc.nextInt();
                //number of coursework cannot be less than 1 if examWeight is not 100%
                if (numberOfCoursework < 1 && examWeight != 1) {
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

        //get weightage of each course component
        do {
            counter = 0;
            courseworkWeight = new double[numberOfCoursework];
            for (int i = 0; i < numberOfCoursework; i++) {
                do {
                    try {
                        //sets the last coursework component weightage immediately
                        if(i==numberOfCoursework-1){
                            System.out.println("Weightage of component[" + (i+1) + "] is " + (100-counter) + ".");
                            courseworkWeight[i] = (100-counter)/100;
                        }else {
                            //allows the user to set the coursework component for all except the last component, which will be set automatically
                            System.out.println("Enter weightage of component[" + (i + 1) + "] out of " + numberOfCoursework + ": (from 0% to " + (100 - counter) + "%)");
                            courseworkWeight[i] = sc.nextDouble()/100;
                            if (courseworkWeight[i] < 0.0 || courseworkWeight[i] > (100 - counter)) {
                                throw new InvalidValueException();
                            }
                        }
                        counter += courseworkWeight[i]*100;
                        succeed = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number.");
                        sc.nextLine();
                    } catch (InvalidValueException e) {
                        System.out.println(e.getMessage());
                    }
                } while (!succeed);
                succeed = false;
                succeed2 = true;
            }
        }while(!succeed2);

        courseManager.setComponentWeightByCourseID(courseID, examWeight, courseworkWeight);

    }

    /**
     * registers a student for a course and its component lesson types. checks if course exists first.
     */
    private static void registerStudentForCourse(){

        //checks if there exist courses in the database
        try {
            if (courseManager.getCourseIDList().length == 0)
                throw new NotSufficientException("courses");
        } catch(NotSufficientException e) {
            System.out.println("Please add a course into the database before registration.");
            return;
        }

        //checks if there exist students in the database
        try {
            if (studentManager.getStudentNameList().length == 0)
                throw new NotSufficientException("students");
        } catch(NotSufficientException e) {
            System.out.println("Please add a student into the database before registration.");
            return;
        }

        boolean success = false;
        int studentID = -1, courseID = -1;

        //enter the student's ID
        do {
            try {
                System.out.println("Enter ID of student below:");
                studentManager.printStudentList();
                studentID = sc.nextInt();
                //checks if the student ID exists
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

        //enter the course ID
        do {
            try {
                System.out.println("Select ID from courses below:");
                courseManager.printCourseList();
                courseID = sc.nextInt();
                //checks if the course ID exists
                if(!courseManager.isExistingCourse(courseID)){
                    throw new IDException("Course");
                }
                //checks if this course is ready for registration (which requires coursework weightage to be set)
                if (!courseManager.isCourseReadyForRegistrationByID(courseID)) {
                    throw new NotReadyForRegistrationException("Course ID " + courseID);
                }
                success = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
                sc.nextLine();
            }catch(NotReadyForRegistrationException e){
                System.out.println(e.getMessage());
                return;
            }catch (IDException e){
                System.out.println(e.getMessage());
                return;
            }
        }while(!success);
        success = false;

        //checks if student has already registered for the course
        try {
            if(recordManager.existingRecord(courseID, studentID)){
                throw new DuplicateException("Record");
            }
        } catch (DuplicateException e){
            System.out.println(e.getMessage());
            return;
        }

        //select which lecture, tutorial and/or lab to register in
        LessonOption lessonOption = LessonOption.LECTURE;
        int numLessonTypes = LessonOption.getNumLessonType();
        int[] lessonChoice = new int[numLessonTypes];
        for (int i = 0; i < numLessonTypes; i++) {

            if (i == 0)
                lessonOption = LessonOption.LECTURE;
            else if (i == 1)
                lessonOption = LessonOption.TUTORIAL;
            else if (i == 2)
                lessonOption = LessonOption.LAB;

            int numLessons = courseManager.getLessonCapacityByCourseID(courseID, lessonOption).length;
            if (numLessons > 0) {
                int[] lessonVacancy = courseManager.getLessonVacancyByCourseID(courseID, lessonOption);
                do {
                    try {
                        int totalVacanciesLeft = 0;
                        for(int k = 0; k < lessonVacancy.length; k++){
                            totalVacanciesLeft += lessonVacancy[k];
                        }
                        if(totalVacanciesLeft <= 0){
                            throw new FullException();
                        }
                        System.out.println("Select a " + lessonOption.toString() + " ID");
                        System.out.println("ID\tVacancy");
                        for (int j = 0; j < lessonVacancy.length; j++) {
                            if (lessonVacancy[j] >= 0) {
                                System.out.printf("%2d\t%7d\n", j, lessonVacancy[j]);
                            }
                        }
                        lessonChoice[i] = sc.nextInt();
                        if (lessonChoice[i] < 0 || lessonChoice[i] >= lessonVacancy.length || lessonVacancy[lessonChoice[i]] == 0) {
                            throw new IDException("Lesson");
                        }
                        success = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter an integer.");
                        sc.nextLine();
                    } catch (IDException e) {
                        System.out.println(e.getMessage());
                    }catch (FullException e){
                        System.out.println(e.getMessage());
                        return;
                    }
                } while (!success);
                success = false;
            } else {
                lessonChoice[i] = -1;
            }
        }

        //add the record into the record manager for the specified student and course
        try {
            if (!recordManager.existingRecord(courseID, studentID)) {
                int numComponents = courseManager.getNumComponentsByCourseID(courseID);
                double examWeight = courseManager.getExamWeightByCourse(courseID);
                double[] courseworkWeight = courseManager.getCourseworkWeightByCourse(courseID);
                recordManager.addRecord(courseID, studentID, lessonChoice, numComponents, examWeight, courseworkWeight);
                System.out.println("Student ID " + studentID + " has successfully registered in Course ID " + courseID);
                for (int j = 0; j < numLessonTypes; j++) {
                    if (j == 0) {
                        lessonOption = LessonOption.LECTURE;
                    } else if (j == 1) {
                        lessonOption = LessonOption.TUTORIAL;
                    } else if (j == 2) {
                        lessonOption = LessonOption.LAB;
                    }
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

    /**
     * prints a list of all students in a particular course and lesson type
     */
    private static void printStudentNameListByCourseLesson(){
        LessonOption lessonOption = LessonOption.LECTURE;
        int courseID = -1;
        boolean succeed = false;
        int lessonType = -1;
        int lessonID = -1;


        try {

            //enter the course ID
            do {
                try {
                    System.out.println("Select ID from courses below:");
                    courseManager.printCourseList();
                    courseID = sc.nextInt();
                    //check if course exists
                    if (!courseManager.isExistingCourse(courseID)) {
                        throw new IDException("course");
                    }
                    succeed = true;
                }catch (InputMismatchException e) {
                    System.out.println("Wrong input type.");
                    sc.nextLine();
                }
            }while(!succeed);
            succeed = false;

            //enter lesson type
            do {
                try{
                    System.out.println("Enter lesson type:");
                    System.out.println("1. Lecture");
                    System.out.println("2. Tutorial");
                    System.out.println("3. Lab");
                    lessonType = sc.nextInt() - 1;
                    if (lessonType!=0 && lessonType!=1 && lessonType !=2 )
                        throw new InvalidValueException();
                    if (lessonType == 0)
                        lessonOption = LessonOption.LECTURE;
                    else if (lessonType == 1) {
                        lessonOption = LessonOption.TUTORIAL;
                    }
                    else if (lessonType == 2) {
                        lessonOption = LessonOption.LAB;
                    }
                    if(courseManager.getLessonCapacityByCourseID(courseID, lessonOption).length == 0){
                        throw new NotSufficientException();
                    }
                    succeed = true;
                }catch (InputMismatchException e) {
                    System.out.println("Wrong input type.");
                    sc.nextLine();
                }catch(InvalidValueException e){
                    System.out.println(e.getMessage());
                    sc.nextLine();
                }catch (NotSufficientException e){
                    System.out.println("There are no lessons of this lesson type.");
                }
            }while(!succeed);
            succeed = false;

            //enter the lesson ID after selecting the lesson type
            int[] studentIDList = null;
            int numLessons = courseManager.getLessonCapacityByCourseID(courseID, lessonOption).length;
            if (numLessons > 0) {
                do{
                    try {
                        System.out.println("Select a " + lessonOption.toString() + " ID");
                        for (int i = 0; i < numLessons; i++) {
                            System.out.printf("%d\n", i);
                        }
                        lessonID = sc.nextInt();
                        succeed = true;
                    }catch(InputMismatchException e) {
                        System.out.println(e.getMessage());
                        sc.nextLine();
                    }
                }while(!succeed);
                succeed = false;

            }
            //get student ID list and print it out
            studentIDList = recordManager.getStudentIDListByCourseLesson(courseID, lessonOption, lessonID);
            if(studentIDList.length>0) {
                System.out.printf(" ID\tStudent Name\n");
                for (int i = 0; i < studentIDList.length; i++) {
                    System.out.printf("%3d\t%-30s\n", studentIDList[i], studentManager.getStudentNameByID(studentIDList[i]));
                }
            } else {
                System.out.println("There are no students in this lesson");
            }
        }catch (IDException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * check for vacancies in the particular lesson type
     */
    private static void checkLessonVacancies(){

        LessonOption lessonOption = LessonOption.LECTURE;
        int courseID = 0;
        boolean succeed = false;

        //check that there exist courses in the database before proceeding
        try {
            if (courseManager.getCourseIDList().length == 0)
                throw new NotSufficientException("courses");
        } catch(NotSufficientException e) {
            System.out.println("Please add a course into the database before registration.");
            return;
        }

        //enter the course ID
        do {
            try {
                System.out.println("Select ID from courses below:");
                courseManager.printCourseList();
                courseID = sc.nextInt();
                //check that the course exists
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

        //choose the type of lesson
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


            //lesson type conversion to enum
            if (lessonType == 0)
                lessonOption = LessonOption.LECTURE;
            else if (lessonType == 1)
                lessonOption = LessonOption.TUTORIAL;
            else if (lessonType == 2)
                lessonOption = LessonOption.LAB;

            //getting the array of vacancies in the type of lesson selected in the particular course
            //array is used because there can be more than one lesson of that type in the course
            int[] lessonVacancies = courseManager.getLessonVacancyByCourseID(courseID, lessonOption);
            int[] lessonCapacity =  courseManager.getLessonCapacityByCourseID(courseID, lessonOption);

            int numLessons = lessonVacancies.length;

            if(lessonType != 3) {
                //if the length of lessonVacancies is more than 0, it means that the lesson of that type is available in the particular course
                if (numLessons > 0) {
                    System.out.println("ID\tVacancies");
                    for (int i = 0; i < numLessons; i++) {
                        System.out.printf("%2d\t%-2d/%-5d\n", i, lessonVacancies[i], lessonCapacity[i]);
                    }
                }

                //if length of lessonVacancies is 0, then there are no such type of lessons in the particular course
                else
                    System.out.println("There are no " + lessonOption.toString() + " available!");
            }
        }
    }

    /**
     * set coursework marks
     */
    private static void setCourseworkMark(){

        //check that there are courses in the database before proceeding
        try {
            if (courseManager.getCourseIDList().length == 0)
                throw new NotSufficientException("courses");
        } catch(NotSufficientException e) {
            System.out.println("Please add a course into the database before registration.");
            return;
        }

        //check that there are students in the database before proceeding
        try {
            if (studentManager.getStudentNameList().length == 0)
                throw new NotSufficientException("students");
        } catch(NotSufficientException e) {
            System.out.println("Please add a student into the database before registration.");
            return;
        }

        //check that there are records in the database before proceeding
        try {
            if (recordManager.getNumRecords() == 0)
                throw new NotSufficientException("records");
        } catch(NotSufficientException e) {
            System.out.println("There are no records in the database.");
            return;
        }

        int studentID = -1;
        int courseID = -1;
        boolean succeed = false;
        double[] componentMarks;

        //enter the student's ID
        do {
            try {
                System.out.println("Enter ID of student below:");
                studentManager.printStudentList();
                studentID = sc.nextInt();
                //check that the student ID exists
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

        //enter the course ID
        do {
            try {
                System.out.println("Select ID from courses below:");
                courseManager.printCourseList();
                courseID = sc.nextInt();
                //check that the course ID exists
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

        //set the marks for each component in this course for the student
        do {
            try {
                int numberOfComponents = courseManager.getNumComponentsByCourseID(courseID);
                if (numberOfComponents == -1)
                    throw new IDException("Course");
                componentMarks = new double[numberOfComponents];
                //loop through the components and enter the marks
                for (int i = 0; i < numberOfComponents; i++) {
                    System.out.println("Enter marks for component[" + i + "]:");
                    componentMarks[i] = sc.nextDouble();
                    if (componentMarks[i] < 0 || componentMarks[i] > 100) {
                        throw new InvalidValueException();
                    }
                }
                succeed = true;
                recordManager.setCourseworkComponentMarks(courseID, studentID, componentMarks);
            } catch (IDException e) {
                System.out.println(e.getMessage());
            } catch (InvalidValueException e) {
                System.out.println("Please enter a number from 0 to 100.");
            }
        } while (!succeed);
    }

    /**
     * set exam marks
     */
    private static void setExamMark() {
        int studentID = -1;
        int courseID = -1;
        boolean succeed = false;
        double examMarks = -1;

        //check if there exists records in the database before proceeding
        try{
            if(recordManager.getNumRecords() == 0){
                throw new NotSufficientException("students registered for courses ");
            }
        }catch(NotSufficientException e){
            System.out.println(e.getMessage());
            return;
        }

        //enter the student's ID
        do {
            try {
                System.out.println("Enter ID of student below:");
                studentManager.printStudentList();
                studentID = sc.nextInt();
                //check that the student ID exists
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

        //enter the course ID
        do {
            try {
                System.out.println("Select ID from courses below:");
                courseManager.printCourseList();
                courseID = sc.nextInt();
                //check that the course ID exists
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

        //set the marks for exam
        do {
            try {
                System.out.println("Enter marks for exam:");
                examMarks = sc.nextDouble();
                //check that the exam marks is valid
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

    /**
     * print all records associated with a particular student
     */
    private static void printStudentTranscript(){

        boolean succeed = false;
        int studentID = -1;
        int[] courseIDList = null;

        //check that there exist records in the database before proceeding
        try{
            if(recordManager.getNumRecords() == 0){
                throw new NotSufficientException("records");
            }
        }catch(NotSufficientException e){
            System.out.println(e.getMessage());
            return;
        }

        //enter the student's ID
        do {
            try {
                System.out.println("Enter ID of student");
                studentID = sc.nextInt();
                //check that the student's ID exists
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

        //check that the student has records for at least 1 course
        try {
            courseIDList = recordManager.getCourseIDByStudentID(studentID);
            if (courseIDList == null) {
                throw new RecordNotFoundException("Student ID " + studentID);
            }
        }catch(RecordNotFoundException e){
            System.out.println("There are no records for this student.");
            return;
        }

        //loop through the record and print out the statistics
        for (int i = 0; i < recordManager.getNumCourseByStudentID(studentID); i++) {
            System.out.printf("Course Name  : %s\n", courseIDList[i]);
            if (recordManager.isMarked(courseIDList[i], studentID)) {
                System.out.printf("Grade        : %s\n", recordManager.getGradeByCourseStudent(courseIDList[i], studentID));
                System.out.printf("Overall Marks: %.1f\n", recordManager.getOverallMarksByCourseStudent(courseIDList[i], studentID));
                System.out.printf("Exam Marks   : %.1f\n", recordManager.getExamMarksByCourseStudent(courseIDList[i], studentID));
                System.out.printf("Exam Weight  : %.1f percent\n", courseManager.getExamWeightByCourse(courseIDList[i])*100);
                double[] courseworkMarks = recordManager.getCourseworkMarksByCourseStudent(courseIDList[i], studentID);
                double[] courseworkWeight = courseManager.getCourseworkWeightByCourse(courseIDList[i]);
                for (int j = 0; j < courseworkMarks.length; j++) {
                    System.out.printf("Coursework[%d] Marks : %.1f\n", j+1, courseworkMarks[j]);
                    System.out.printf("Coursework[%d] Weight: %.1f percent\n", j+1, courseworkWeight[j] * (1 - courseManager.getExamWeightByCourse(courseIDList[i])) * 100);
                }
                System.out.println();

            } else {
                System.out.println("Not Marked");
            }
        }
        System.out.println("End of Transcript");

    }

    /**
     * print number of students, average overall grade,average exam mark and total coursework marks associated with a particular course
     */
    private static void printCourseStats(){

        //check that there exist courses in the database before proceeding
        try {
            if (courseManager.getCourseIDList().length == 0)
                throw new NotSufficientException("courses");
        } catch(NotSufficientException e) {
            System.out.println("Please add a course into the database before registration.");
            return;
        }

        int courseID = 0;
        boolean succeed = false;

        //enter the course ID
        do {
            try {
                System.out.println("Select ID from courses below:");
                courseManager.printCourseList();
                courseID = sc.nextInt();
                //if the courseID is not valid the function will throw the invalid ID exception
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

        //getting the number of students in the course selected by the user
        int numStudents = recordManager.getNumStudentsByCourseID(courseID);
        System.out.printf("Number of students: %d\n", numStudents);

        //getting the average overall marks, of the course selected by the user
        double averageOverallMarks = recordManager.getAverageOverallMarksByCourseID(courseID);
        System.out.printf("Average Overall Marks: %.1f\n", averageOverallMarks);

        //getting the average exam marks of the course selected by the user
        double averageExamMarks = recordManager.getAverageExamMarksByCourseID(courseID);
        System.out.printf("Average Exam Marks: %.1f\n", averageExamMarks);

        //getting the average marks of all the course works of the course selected by the user
        double averageTotalCourseworkMarks = recordManager.getAverageTotalCourseworkMarksByCourseID(courseID);
        System.out.printf("Average Total Coursework Marks: %.1f\n", averageTotalCourseworkMarks);

    }

    /**
     * initialises a group of pre-determined students
     */
    private static void loadStudents(){
        List<String> students = new ArrayList<String>();
        Scanner sc;

        //scan from students.txt into List students
        try{
            sc = new Scanner(new File("students.txt"));
            while(sc.hasNextLine()){
                students.add(sc.nextLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        //add students into the database
        for(int i = 0; i<students.size(); i++) {
            studentManager.addStudent(students.get(i));
        }
    }

    /*
    initialises a group of pre-determined professors
     */
    private static void loadProfessors(){
        List<String> professors = new ArrayList<String>();
        Scanner sc;

        //scan from professors.txt into List professors
        try{
            sc = new Scanner(new File("professors.txt"));
            while(sc.hasNextLine()){
                professors.add(sc.nextLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        //add professors into the database
        for(int i = 0; i<professors.size(); i++){
            professorManager.addProfessor(professors.get(i));
        }
    }

    /**
     * initialises a group of pre-determined courses
     */
    private static void loadCourses(){
        List<String[]> courses = new ArrayList<>();
        Scanner sc;
        String line;
        String delimiter = "[ ]+";

        try{
            //scan from courses.txt line by line into String line
            //split each line into token based on spaces and store into List courses
            sc = new Scanner(new File("courses.txt"));
            while(sc.hasNextLine()){
                line = sc.nextLine();
                courses.add(line.split(delimiter));
            }
            for(int i = 0; i<courses.size(); i++){

                //initialise and set values for each array of strings in List courses
                int j = 0, k = 0, l = 0;
                int courseID = -1, professorID = -1;
                String courseName = null;
                int[] lectureCapacity = null, tutorialCapacity = null, labCapacity = null;

                //courseName is the first string in the array
                courseName = courses.get(i)[0];

                //courseID is the second string in the array
                courseID = Integer.parseInt(courses.get(i)[1]);

                //professor ID is the third string in the array
                professorID = Integer.parseInt(courses.get(i)[2]);

                //number of lectures is the fourth string in the array and is set as the length of lectureCapacity array
                lectureCapacity = new int[Integer.parseInt(courses.get(i)[3])];

                //loop for the length of lectureCapacity and set the capacity of each lecture
                for(j = 0; j<lectureCapacity.length; j++){
                    lectureCapacity[j] = Integer.parseInt(courses.get(i)[4+j]);
                }

                //number of tutorials is the (fifth plus length of lectureCapacity) array and is set as the length of
                //tutorialCapacity array
                tutorialCapacity = new int[Integer.parseInt(courses.get(i)[4+lectureCapacity.length])];

                //loop for the length of tutorialCapacity and set the capacity of each tutorial
                for(k = 0; k<tutorialCapacity.length; k++){
                    tutorialCapacity[k] = Integer.parseInt(courses.get(i)[5+lectureCapacity.length+k]);
                }

                //number of labs is the (sixth plus length of lectureCapacity plus length of tutorialCapacity) array
                // and is set as the length of labCapacity array
                labCapacity = new int[Integer.parseInt(courses.get(i)[5+lectureCapacity.length+tutorialCapacity.length])];

                //loop for the length of labCapacity and set the capacity of each lab
                for(l = 0; l<labCapacity.length; l++){
                    labCapacity[l] = Integer.parseInt(courses.get(i)[6+lectureCapacity.length+tutorialCapacity.length]);
                }

                //add course into database
                courseManager.addCourse(courseID, courseName, professorID, lectureCapacity, tutorialCapacity, labCapacity);
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
