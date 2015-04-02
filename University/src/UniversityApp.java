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
            int id, capacity, counter;
            String name, temp;
            Course tempCourse = null;
            Student tempStudent = null;
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
                case 1:
                    System.out.println("Enter the student's name: ");
                    name = sc.next();
                    System.out.println("Enter the student's ID: ");
                    id = sc.nextInt();
                    Student student = new Student(name, id);
                    university.studentList.add(student);
                    System.out.println("Student added.");
                    break;
                case 2:
                    System.out.println("Enter the course name: ");
                    name = sc.next();
                    Course course = new Course(name);
                    System.out.println("Enter the maximum capacity of the course: ");
                    capacity = sc.nextInt();
                    course.setMaxCapacity(capacity);
                    university.courseList.add(course);
                    System.out.println("Course added.");
                    break;
                case 3:
                    System.out.println("Enter the student's id: ");
                    id = sc.nextInt();
                    System.out.println("Enter the course name: ");
                    name = sc.next();
                    counter = 0;
                    for(int i = 0; i<university.studentList.size(); i++){
                        if(university.studentList.get(i).getID() == id){
                            tempStudent = university.studentList.get(i);

                        }
                    }

                    for(int i = 0; i<university.courseList.size(); i++){
                        if(university.courseList.get(i).getCourseName() == name){
                            tempCourse = university.courseList.get(i);

                        }
                    }

                    if(tempCourse==null || tempStudent==null){
                        break;
                    }else{
                        tempCourse.enrollStudent(tempStudent);
                    }

                    break;
                case 4:
                    System.out.println("Enter the course name: ");
                    name = sc.next();
                    for(int i = 0; i<university.courseList.size(); i++){
                        if(university.courseList.get(i).getCourseName() == name){
                            tempCourse = university.courseList.get(i);

                        }
                    }
                        for(int i = 0; i<tempCourse.getWholeLectureList().size(); i++){
                            System.out.println("Lecture " + (i+1) + " has " + tempCourse.getLectureList(i).getVacancies() + " vacancies.");
                        }
                        for(int i = 0; i<tempCourse.getWholeTutorialList().size(); i++){
                            System.out.println("Tutorial " + (i+1) + " has " + tempCourse.getTutorialList(i).getVacancies() + " vacancies.");
                        }
                        for(int i = 0; i<tempCourse.getWholeLabList().size(); i++){
                            System.out.println("Lab " + (i+1) + " has " + tempCourse.getLabList(i).getVacancies() + " vacancies.");
                        }

                    break;
                case 5:
                    System.out.println("Enter the name of the course: ");
                    name = sc.next();
                    for(int i = 0; i<university.courseList.size(); i++){
                        if(university.courseList.get(i).getCourseName() == name){
                            tempCourse = university.courseList.get(i);

                        }
                    }

                    for(int i = 0; i<tempCourse.getWholeLectureList().size(); i++){
                        System.out.println("Lecture " + (i+1) + " has the following students: ");
                        for(int j = 0; j<tempCourse.getLectureList(i).getStudentList().length; j++){
                            System.out.println(tempCourse.getLectureList(i).getStudentList()[j]);
                        }
                        System.out.println("/n");
                    }

                    for(int i = 0; i<tempCourse.getWholeTutorialList().size(); i++){
                        System.out.println("Tutorial " + (i+1) + " has the following students: ");
                        for(int j = 0; j<tempCourse.getTutorialList(i).getStudentList().length; j++){
                            System.out.println(tempCourse.getTutorialList(i).getStudentList()[j]);
                        }
                        System.out.println("/n");
                    }

                    for(int i = 0; i<tempCourse.getWholeLabList().size(); i++){
                        System.out.println("Lab " + (i+1) + " has the following students: ");
                        for(int j = 0; j<tempCourse.getLabList(i).getStudentList().length; j++){
                            System.out.println(tempCourse.getLabList(i).getStudentList()[j]);
                        }
                        System.out.println("/n");
                    }
                    break;
                case 6:
                    System.out.println("Enter the name of the course: ");
                    name = sc.next();
                    for(int i = 0; i<university.courseList.size(); i++){
                        if(university.courseList.get(i).getCourseName() == name){
                            tempCourse = university.courseList.get(i);
                        }
                    }
                    System.out.println("Enter the name of the course component you wish to create: ");
                    name = sc.next();
                    tempCourse.createCourseComponent(name);
                    System.out.println("Enter the weightage: ");




                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                default:
                    System.out.println("Invalid Choice.");
                    break;
            }
        }


    }

}
