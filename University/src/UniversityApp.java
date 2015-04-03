/**
 * Created by danielseetoh on 4/1/2015.
 */

import java.util.*;

public class UniversityApp {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        University university = new University();
        while(choice!=11) {
            String studentName, studentID, courseName, componentName;
            int capacity, counter;
            String name, temp;
            double componentWeightage;
            Course tempCourse;
            Student tempStudent;
            Professor tempProf = null;
            System.out.println("Enter your choice: ");
            System.out.println("1. Add a student");
            System.out.println("2. Add a course ");
            System.out.println("3. Register Student for a course ");
            System.out.println("4. Check available slot in a class (vacancy in a class)");
            System.out.println("5. Print student list by lecture, tutorial or laboratory session for a course");
            System.out.println("6. Enter course assessment components weightage");
            System.out.println("7. Enter coursework mark - inclusive of its components");
            System.out.println("8. Enter exam mark");
            System.out.println("9. Print course statistics");
            System.out.println("10. Print student transcript");
            System.out.println("11. Exit");

            choice = sc.nextInt();
            switch(choice){
                case 1: //1. Add a student
                    System.out.println("Enter the student's name: ");
                    studentName = sc.next();
                    System.out.println("Enter the student's ID: ");
                    studentID = sc.next();
                    university.addStudent(studentName, studentID);
                    System.out.println("Student added.");
                    for(int i = 0; i < university.studentList.size(); i++)
                        System.out.println(university.studentList.get(i).getStudentID());
                    break;
                case 2: //2. Add a course
                    System.out.println("Enter the course name: ");
                    courseName = sc.next();
                    Course course = new Course(courseName);
                    System.out.println("Enter the maximum capacity of the course: ");
                    capacity = sc.nextInt();
                    course.setMaxCapacity(capacity);
                    university.addCourse(courseName, capacity);
                    System.out.println("Course added.");
                    for(int i = 0; i < university.courseList.size(); i++)
                        System.out.println(university.courseList.get(i).getCourseName());
                    break;
                case 3: //3. Register Student for a course
                    System.out.println("Enter the student's id: ");
                    studentID = sc.next();
                    System.out.println("Enter the course name: ");
                    courseName = sc.next();
                    tempStudent = university.getStudentByID(studentID);
                    tempCourse = university.getCourseByName(courseName);
                    if(tempCourse == null || tempStudent == null){
                        System.out.println("tempCourse: "+ tempCourse);
                        System.out.println("tempStudent: "+ tempStudent);
                        break;
                    }else{
                        tempCourse.enrollStudent(tempStudent);
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
                default:
                    System.out.println("Invalid Choice.");
                    break;
            }
        }


    }

}
