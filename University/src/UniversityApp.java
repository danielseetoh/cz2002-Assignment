/**
 * Created by danielseetoh on 4/1/2015.
 */

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.*;

public class UniversityApp {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        University university = new University();
        initialize(university);
        while(choice!=11) {
            String studentName, studentID, courseName, componentName, professorID;
            int capacity, numberOfComponents;
            double componentWeightage;
            Course tempCourse;
            Student tempStudent;
            Professor tempProf = null;
            System.out.println("Enter your choice: ");
            System.out.println(" 1. Add a student");
            System.out.println(" 2. Add a course ");
            System.out.println(" 3. Register Student for a course ");
            System.out.println(" 4. Check available slot in a class (vacancy in a class)");
            System.out.println(" 5. Print student list by lecture, tutorial or laboratory session for a course");
            System.out.println(" 6. Enter course assessment components weightage");
            System.out.println(" 7. Enter coursework mark - inclusive of its components");
            System.out.println(" 8. Enter exam mark");
            System.out.println(" 9. Print course statistics");
            System.out.println("10. Print student transcript");
            System.out.println("11. Exit");
            System.out.println("12. Print Students List");
            System.out.println("13. Print Course List");
            System.out.println("14. Print Professor List");
            System.out.println("15. Print Course Assessment Weightage");
            System.out.println("16. Mass Enrollment");

            choice = sc.nextInt();

            switch(choice){
                case 1:
                    //1. Add a student
                    System.out.println("Enter the student's name: ");
                    studentName = sc.next();
                    System.out.println("Enter the student's ID: ");
                    studentID = sc.next();

                    //TODO: Check for invalid data entries
                    // Appropriate error message display
                    // Need to check for duplicate studentID
                    if(university.isDuplicateStudentID(studentID)){
                        // Appropriate error message display
                        System.out.println("Error: studentID already exists. Please try again.");
                    } else {
                        university.addStudent(studentName, studentID);
                        System.out.println("Student added.");

                        // the listing of all students should be displayed after the addition
                        printStudentList(university.studentList);
                    }
                    break;
                case 2:
                    //2. Add a course
                    //listing of all courses should be displayed after the addition TOGETHER with Professor in charge (coordinator).
                    
                    boolean redo = true;
                    
                    //TODO: Check for invalid data entries
                    // Appropriate error message display
                    // Need to check for duplicate courseName
                    
                    while (redo = true){
                        System.out.println("\n" + "Enter the course name: ");
                        courseName = sc.next().toUpperCase();                        
                    
                        if(university.isDuplicateCourseName(courseName)){
                            System.out.println("Error: courseName already exists. Please try again.\n");
                        } else {
                            Course course = new Course(courseName);

                          //Add professor in charge
                            System.out.println("Select ID of professor in charge: ");
                            printProfessorList(university.professorList);
                            professorID = sc.next();
                            tempProf = university.getProfessorByID(professorID);
                            course.setProfessor(tempProf);

                            //Set maximum capacity of course
                            System.out.println("Enter the maximum capacity of the course: ");
                            capacity = sc.nextInt();
                            course.setMaxCapacity(capacity);

                            //Add Lectures
                            System.out.println("Enter the number of lectures:");
                            int numberOfLectures = sc.nextInt();
                            course.addLectures(numberOfLectures);

                            //Add Tutorials
                            System.out.println("Enter the number of tutorials:");
                            int numberOfTutorials = sc.nextInt();
                            course.addTutorials(numberOfTutorials);

                            //Add Labs
                            System.out.println("Enter the number of labs:");
                            int numberOfLabs = sc.nextInt();
                            course.addLabs(numberOfLabs);

                            //Add course to courseList
                            university.addCourse(course);
                            System.out.println("Course added.");

                            //print Course List
                            printCourseList(university.courseList);
                            redo = false;
                        }
                    }
                    break;
                    
                case 3:
                    //3. Register Student for a course

                    //Select student
                    System.out.println("Enter the selected student's ID: ");
                    printStudentList(university.studentList);
                    studentID = sc.next();
                    tempStudent = university.getStudentByID(studentID);

                    //Select course
                    System.out.println("Enter the selected course name: ");
                    printCourseList(university.courseList);
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);

                    //TODO: Check register for same course again
                    //TODO: Check Invalid data entries

                    //Check student and course are NOT NULL
                    if(tempCourse == null || tempStudent == null){
                        System.out.println("tempCourse: "+ tempCourse);
                        System.out.println("tempStudent: "+ tempStudent);
                    }else{
                        //Register student in course
                        tempCourse.enrollStudent(tempStudent);

                        //Register student for Lecture
                        System.out.println(tempCourse.getLectureList().size());
                        if(tempCourse.getLectureList().size() > 0){
                            System.out.println("Select Lecture");
                            printLessonList(tempCourse.getLectureList());
                            int selectedID = sc.nextInt();
                            tempCourse.getLecture(selectedID - 1).enrollLesson(tempStudent);
                            System.out.println("Enrolled in Lecture ID: " + selectedID);
                        }
                        //Register student for Tutorial
                        if(tempCourse.getTutorialList().size() > 0){
                            System.out.println("Select Tutorial");
                            printLessonList(tempCourse.getTutorialList());
                            int selectedID = sc.nextInt();
                            tempCourse.getTutorial(selectedID - 1).enrollLesson(tempStudent);
                            System.out.println("Enrolled in Tutorial ID: " + selectedID);
                        }
                        //Register student for Lab
                        if(tempCourse.getLabList().size() > 0){
                            System.out.println("Select Lab");
                            printLessonList(tempCourse.getLabList());
                            int selectedID = sc.nextInt();
                            tempCourse.getLab(selectedID - 1).enrollLesson(tempStudent);
                            System.out.println("Enrolled in Lab ID: " + selectedID);
                        }
                    }
                    break;
                case 4: //4. Check available slot in a class (vacancy in a class)

                    //Select course
                    System.out.println("Enter the course name: ");
                    printCourseList(university.courseList);
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);

                    //TODO: Check invalid data entries

                    System.out.println("Lectures");
                    printLessonList(tempCourse.getLectureList());
                    System.out.println("Tutorials");
                    printLessonList(tempCourse.getTutorialList());
                    System.out.println("Labs");
                    printLessonList(tempCourse.getLabList());
                    break;
                case 5: //5. Print student list by lecture, tutorial or laboratory session for a course
                    //Select Course
                    System.out.println("Enter the name of the course: ");
                    printCourseList(university.courseList);
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);

                    //TODO: Check invalid data entries

                    System.out.println("Print list by:");
                    System.out.println("(1) Lecture");
                    System.out.println("(2) Tutorial");
                    System.out.println("(3) Laboratory");
                    int lessonType = sc.nextInt();

                    switch(lessonType){
                        case 1:
                            if(!tempCourse.getLectureList().isEmpty()){
                                for(int i = 0; i < tempCourse.getLectureList().size(); i++){
                                    Lecture tempLecture = tempCourse.getLecture(i);
                                    System.out.println("Student List of Lecture " + tempLecture.getID());
                                    printStudentList(tempLecture.getStudentList());
                                }
                            } else {
                                System.out.println("No Lecture for " + tempCourse.getCourseName());
                            }
                            break;
                        case 2:
                            if(!tempCourse.getTutorialList().isEmpty()) {
                                for (int i = 0; i < tempCourse.getTutorialList().size(); i++) {
                                    Tutorial tempTutorial = tempCourse.getTutorial(i);
                                    System.out.println("Student List of Tutorial " + tempTutorial.getID());
                                    printStudentList(tempTutorial.getStudentList());
                                }
                            } else {
                                System.out.println("No Tutorial for " + tempCourse.getCourseName());
                            }
                            break;
                        case 3:
                            if(!tempCourse.getLabList().isEmpty()) {
                                for (int i = 0; i < tempCourse.getLabList().size(); i++) {
                                    Laboratory tempLab = tempCourse.getLab(i);
                                    System.out.println("Student List of Laboratory " + tempLab.getID());
                                    printStudentList(tempLab.getStudentList());
                                }
                            } else {
                                System.out.println("No Laboratory for " + tempCourse.getCourseName());
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 6: //6. Enter course assessment components weightage
                    System.out.println("Enter the name of the course: ");
                    printCourseList(university.courseList);
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);

                    //TODO: Check invalid data entries

                    System.out.println("Enter Exam Weightage: ");
                    double examWeight = sc.nextDouble();
                    tempCourse.setExamWeight(examWeight);
                    System.out.println("Exam Weightage: " + examWeight);
                    System.out.println("Coursework Total Weightage: " + (1 - examWeight));

                    System.out.println("Enter number of Coursework Components: ");
                    numberOfComponents = sc.nextInt();
                    double sum = 0;
                    for(int i = 0; i < numberOfComponents; i++){
                        System.out.println("Enter name of component " + (i+1));
                        componentName = sc.next();
                        CourseComponent tempComponent = tempCourse.createCourseComponent(componentName);
                        if(i == numberOfComponents - 1){
                            System.out.println(componentName + " Weightage (of total coursework): " + (1 - sum));
                            tempComponent.setWeight(1 - sum);
                        } else {
                            System.out.println("Enter the weightage (of total coursework): ");
                            componentWeightage = sc.nextDouble();
                            tempComponent.setWeight(componentWeightage);
                            sum += componentWeightage;
                        }
                    }

                    // TODO: set size of courseworkMarks Array in Record
                    // or enrollment must be after set assessment components

                    break;
                case 7: // 7. Enter coursework mark - inclusive of its components
                    System.out.println("Enter the name of the course: ");
                    printCourseList(university.courseList);
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);

                    System.out.println("Enter ID of student: ");
                    printStudentList(tempCourse.getStudentList());
                    studentID = sc.next();
                    tempStudent = university.getStudentByID(studentID);
                    Record tempRecord = tempStudent.getRecordByCourseName(courseName);

                    for(int i = 0; i < tempCourse.getCourseworkLength(); i++){
                        System.out.println("Enter mark for Component " + tempCourse.getCourseworkList(i).getComponentName() + ": ");
                        double componentMarks = sc.nextDouble();
                        tempRecord.setCourseworkMarks(i, componentMarks);
                    }

                    //TODO: Need to debug setCourseworkMarks

                    System.out.println("Component marks entered.");
                    break;
                case 8: // 8. Enter exam mark
                    System.out.println("Enter the name of the course: ");
                    printCourseList(university.courseList);
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);

                    System.out.println("Enter ID of student: ");
                    printStudentList(tempCourse.getStudentList());
                    studentID = sc.next();
                    tempStudent = university.getStudentByID(studentID);
                    tempRecord = tempStudent.getRecordByCourseName(courseName);

                    System.out.println("Enter mark for Exam");
                    double examMarks = sc.nextDouble();
                    tempRecord.setExamMarks(examMarks);

                    //TODO: Need to debug setExamMarks

                    System.out.println("Exam marks entered.");
                    break;
                case 9: // 9. Print course statistics
                    System.out.println("Enter the name of the course: ");
                    printCourseList(university.courseList);
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);
                    tempCourse.printStats();
                    break;
                case 10: // 10. Print student transcript
                    System.out.println("Enter id of student: ");
                    printStudentList(university.studentList);
                    studentID = sc.next();
                    tempStudent = university.getStudentByID(studentID);
                    tempStudent.printTranscript();
                    break;
                case 11: // 11. Exit
                    System.out.println("Exiting...");
                    break;
                case 12: // 12. Print Student List
                    printStudentList(university.studentList);
                    break;
                case 13: // 13. Print Course List
                    printCourseList(university.courseList);
                    break;
                case 14: // 14. Print Professor List
                    printProfessorList(university.professorList);
                    break;
                case 15: //15. Print Course Assessment Weightage
                    System.out.println("Enter the name of the course: ");
                    printCourseList(university.courseList);
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);
                    printAssessmentWeightage(tempCourse);
                    break;
                case 16: // 16. Mass Enrollment
                    massEnrollment(university);
                    break;
                default:
                    System.out.println("Invalid Choice.");
                    break;
            }
        }
    }

    public static void initialize(University university){
        university.addStudent("Andy","1");
        university.addStudent("Bob","2");
        university.addStudent("Cindy","3");
        university.addStudent("Daniel","4");
        university.addStudent("Edwin","5");
        university.addStudent("Frankie","6");
        university.addStudent("Geraldine","7");
        university.addStudent("Helen","8");
        university.addStudent("Irene","9");
        university.addStudent("Joan","10");
        university.addStudent("Kian Boon","11");
        university.addStudent("Leonard", "12");
        university.addStudent("Melvyn", "13");
        university.addStudent("Nigel", "14");
        university.addStudent("Yew Long", "15");

        university.addProfessor("Fan Rui", "1");
        university.addProfessor("Zhang Jie", "2");
        university.addProfessor("Kheng Leong", "3");
        university.addProfessor("Bing Sheng", "4");

        university.addCourse("CZ2001", "Fan Rui", 20, 1, 0, 0);
        university.addCourse("CZ2002", "Zhang Jie", 20, 1, 2, 0);
        university.addCourse("CZ2005", "Bing Sheng", 20, 1, 2, 2);

    }

    public static void massEnrollment(University university){
        for(int i = 0; i < university.courseList.size(); i++){
            Course tempCourse = university.courseList.get(i);
            for(int j = 0; j < university.studentList.size(); j++){
                Student tempStudent = university.studentList.get(j);
                tempCourse.enrollStudent(tempStudent);
                if(tempCourse.getLectureList().size() > 0){
                    tempCourse.getLecture(j%tempCourse.getLectureList().size()).enrollLesson(tempStudent);
                }
                if(tempCourse.getTutorialList().size() > 0) {
                    tempCourse.getTutorial(j % tempCourse.getTutorialList().size()).enrollLesson(tempStudent);
                }
                if(tempCourse.getLabList().size() > 0) {
                    tempCourse.getLab(j % tempCourse.getLabList().size()).enrollLesson(tempStudent);
                }
            }
        }
    }

    public static void printStudentList(List<Student> studentList){
        System.out.println("ID\tStudent Name");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.printf("%-2s\t%-12s\n",
                    studentList.get(i).getStudentID(),
                    studentList.get(i).getStudentName());
        }
    }

    public static void printCourseList(List<Course> courseList){
        System.out.println("Course\tProfessorIC\tCap\tLec\tTut\tLab");
        for(int i = 0; i < courseList.size(); i++){
            Course tempCourse = courseList.get(i);
            System.out.printf("%-7s\t%-11s\t%3d\t%3d\t%3d\t%3d\n",
                    tempCourse.getCourseName(),
                    tempCourse.getProfessorIC().getName(),
                    tempCourse.getMaxCapacity(),
                    tempCourse.getLectureList().size(),
                    tempCourse.getTutorialList().size(),
                    tempCourse.getLabList().size());
        }
    }

    public static void printProfessorList(List<Professor> professorList){
        System.out.println("ID\tProfessor Name");
        for(int i = 0; i < professorList.size(); i++) {
            System.out.printf("%-2s\t%-14s\n",
                    professorList.get(i).getProfessorID(),
                    professorList.get(i).getName());
        }
    }

    public static void printLessonList(List<Lesson> lessonList){
        System.out.println("ID\tVacancy");
        for(int i = 0; i < lessonList.size(); i++){
            System.out.printf("%2d\t", lessonList.get(i).getID());
            System.out.printf("%4d/%2d\n", lessonList.get(i).getVacancy(), lessonList.get(i).getMaxCapacity());
        }
    }

    public static void printAssessmentWeightage(Course tempCourse){
        System.out.println("Exam Weightage: " + tempCourse.getExamWeight());
        System.out.println("Coursework Weightage: " + (1 - tempCourse.getExamWeight()));
        for(int i = 0; i < tempCourse.getCourseworkLength(); i++) {
            System.out.println(tempCourse.getCourseworkList(i).getComponentName() + " Component Weightage (of coursework): " + tempCourse.getCourseworkWeight(i));
        }
    }
}
