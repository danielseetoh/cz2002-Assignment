/**
 * Created by danielseetoh on 4/1/2015.
 */

import java.util.*;

public class UniversityApp {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        University university = new University();
        initialize(university);
        while(choice!=11) {
            String studentName, studentID, courseName, componentName, professorID;
            int capacity;
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
                        printStudentList(university);
                    }
                    break;
                case 2:
                    //2. Add a course
                    //listing of all courses should be displayed after the addition TOGETHER with Professor in charge (coordinator).
                    System.out.println("Enter the course name: ");
                    courseName = sc.next();

                    //TODO: Check for invalid data entries
                    // Appropriate error message display

                    // Need to check for duplicate courseName
                    if(university.isDuplicateCourseName(courseName)){
                        System.out.println("Error: courseName already exists. Please try again.");
                    } else {
                        Course course = new Course(courseName);

                        //Add professor in charge
                        System.out.println("Select ID of professor in charge: ");
                        printProfessorList(university);
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
                        printCourseList(university);
                    }
                    break;
                case 3:
                    //3. Register Student for a course

                    //Select student
                    System.out.println("Enter the selected student's ID: ");
                    printStudentList(university);
                    studentID = sc.next();
                    tempStudent = university.getStudentByID(studentID);

                    //Select course
                    System.out.println("Enter the selected course name: ");
                    printCourseList(university);
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);

                    //Check student and course are NOT NULL
                    if(tempCourse == null || tempStudent == null){
                        System.out.println("tempCourse: "+ tempCourse);
                        System.out.println("tempStudent: "+ tempStudent);
                    }else{
                        //Register student in course
                        tempCourse.enrollStudent(tempStudent);

                        //Register student for Lecture
                        System.out.println(tempCourse.getLectureList().size());
                        System.out.println(tempCourse.getLectureList().get(0).getClass());
                        if(tempCourse.getLectureList().size() > 0){
                            System.out.println("Select Lecture");
                            printLessonList(tempCourse.getLectureList());
                            int selectedID = sc.nextInt();
                            tempCourse.getLecture(selectedID).enrollLesson(tempStudent);
                            System.out.println("Selected Lecture ID: " + selectedID);
                        }
                        //Register student for Tutorial
                        if(tempCourse.getTutorialList().size() > 0){
                            System.out.println("Select Tutorial");
                            printLessonList(tempCourse.getTutorialList());
                            int selectedID = sc.nextInt();
                            tempCourse.getTutorial(selectedID).enrollLesson(tempStudent);
                            System.out.println("Selected Tutorial ID: " + selectedID);
                        }
                        //Register student for Lab
                        if(tempCourse.getLabList().size() > 0){
                            System.out.println("Select Lab");
                            printLessonList(tempCourse.getLabList());
                            int selectedID = sc.nextInt();
                            tempCourse.getLab(selectedID).enrollLesson(tempStudent);
                            System.out.println("Selected Lab ID: " + selectedID);
                        }
                    }
                    break;
                case 4: //4. Check available slot in a class (vacancy in a class)
                    System.out.println("Enter the course name: ");
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);
                    for(int i = 0; i < tempCourse.getLectureList().size(); i++){
                        System.out.println("Lecture " + (i+1) + " has " + tempCourse.getLecture(i).getVacancy() + " vacancies.");
                    }
                    for(int i = 0; i < tempCourse.getTutorialList().size(); i++){
                        System.out.println("Tutorial " + (i+1) + " has " + tempCourse.getTutorial(i).getVacancy() + " vacancies.");
                    }
                    for(int i = 0; i < tempCourse.getLabList().size(); i++){
                        System.out.println("Lab " + (i+1) + " has " + tempCourse.getLab(i).getVacancy() + " vacancies.");
                    }

                    break;
                case 5: //5. Print student list by lecture, tutorial or laboratory session for a course
                    System.out.println("Enter the name of the course: ");
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);

                    for(int i = 0; i<tempCourse.getLectureList().size(); i++){
                        System.out.println("Lecture " + (i+1) + " has the following students: ");
                        for(int j = 0; j < tempCourse.getLecture(i).getStudentList().size(); j++){
                            System.out.println(tempCourse.getLecture(i).getStudentList().get(j));
                        }
                        System.out.println("/n");
                    }

                    for(int i = 0; i<tempCourse.getTutorialList().size(); i++){
                        System.out.println("Tutorial " + (i+1) + " has the following students: ");
                        for(int j = 0; j < tempCourse.getTutorial(i).getStudentList().size(); j++){
                            System.out.println(tempCourse.getTutorial(i).getStudentList().get(j));
                        }
                        System.out.println("/n");
                    }

                    for(int i = 0; i < tempCourse.getLabList().size(); i++){
                        System.out.println("Lab " + (i+1) + " has the following students: ");
                        for(int j = 0; j < tempCourse.getLab(i).getStudentList().size(); j++){
                            System.out.println(tempCourse.getLab(i).getStudentList().get(j));
                        }
                        System.out.println("/n");
                    }
                    break;
                case 6: //6. Enter course assessment components weightage
                    System.out.println("Enter the name of the course: ");
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);
                    System.out.println("Enter the name of the course component you wish to create: ");
                    componentName = sc.next();
                    CourseComponent tempComponent = tempCourse.createCourseComponent(componentName);
                    System.out.println("Enter the weightage: ");
                    componentWeightage = sc.nextDouble();
                    tempComponent.setWeight(componentWeightage);
                    break;
                case 7: // 7. Enter coursework mark - inclusive of its components
                    System.out.println("Enter id of student: ");
                    studentID = sc.next();
                    tempStudent = university.getStudentByID(studentID);
                    System.out.println("Enter the name of the course: ");
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);
                    for(int i = 0; i < tempCourse.getCourseworkLength(); i++){
                        System.out.println("Enter mark for Component " + tempCourse.getCourseworkList(i).getComponentName() + ": ");
                        double componentMarks = sc.nextDouble();
                        tempStudent.getRecordByCourseName(courseName).setCourseworkMarks(i, componentMarks);
                    }
                    System.out.println("Component marks entered.");
                    break;
                case 8: // 8. Enter exam mark
                    System.out.println("Enter id of student: ");
                    studentID = sc.next();
                    tempStudent = university.getStudentByID(studentID);
                    System.out.println("Enter the name of the course: ");
                    courseName = sc.next();
                    System.out.println("Enter mark for Exam: ");
                    double examMarks = sc.nextDouble();
                    tempStudent.getRecordByCourseName(courseName).setExamMarks(examMarks);
                    System.out.println("Exam marks entered.");
                    break;
                case 9: // 9. Print course statistics
                    System.out.println("Enter the name of the course: ");
                    courseName = sc.next();
                    tempCourse = university.getCourseByName(courseName);
                    tempCourse.printStats();
                    break;
                case 10: // 10. Print student transcript
                    System.out.println("Enter id of student: ");
                    studentID = sc.next();
                    tempStudent = university.getStudentByID(studentID);
                    tempStudent.printTranscript();
                    break;
                case 11: // 11. Exit
                    System.out.println("Exiting...");
                    break;
                case 12: // 12. Print Student List
                    printStudentList(university);
                    break;
                case 13: // 13. Print Course List
                    printCourseList(university);
                    break;
                case 14: // 14. Print Professor List
                    printProfessorList(university);
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

    public static void printStudentList(University university){
        List<Student> studentList = university.studentList;
        System.out.println("ID\tStudent Name");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.printf("%-2s\t%-12s\n",
                    studentList.get(i).getStudentID(),
                    studentList.get(i).getStudentName());
        }
    }

    public static void printCourseList(University university){
        List<Course> courseList = university.courseList;
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

    public static void printProfessorList(University university){
        List<Professor> professorList = university.professorList;
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
            //System.out.printf("%7d\n", lessonList.get(i).getVacancy());
        }
    }
}
