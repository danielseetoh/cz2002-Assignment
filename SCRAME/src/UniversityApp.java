import java.util.Scanner;

public class UniversityApp {

    private static Scanner sc = new Scanner(System.in);
    private static University university = new University();

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
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    printStudentNameList();
                    break;
                default:
                    break;
            }
        }

    }

    private static void addStudent(){
        boolean success = false;
        while(!success){
            System.out.println("Enter name of student");
            String studentName = sc.next();
            // currently assume studentName has no space

            success = university.addStudent(studentName);
            if(!success){
                System.out.println("Student with name " + studentName + " already exists. Please try again with a different name.");
            } else {
                System.out.println("Student with name " + studentName + " is successfully registered with ID: " + university.getStudentByName(studentName).getStudentID());
            }
        }
    }

    private static void addProfessor(){
        boolean success = false;
        while(!success){
            System.out.println("Enter name of professor");
            String professorName = sc.next();
            // currently assume professorName has no space

            success = university.addProfessor(professorName);
            if(!success){
                System.out.println("Professor with name " + professorName + " already exists. Please try again with a different name.");
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

            System.out.println("Enter total capacity of course:");
            int capacity = sc.nextInt();

            System.out.println("Enter number of lectures:");
            int numLectures = sc.nextInt();
            System.out.println("Enter number of tutorials:");
            int numTutorials = sc.nextInt();
            System.out.println("Enter number of labs:");
            int numLabs = sc.nextInt();


            success = university.addCourse(courseName, professorName, capacity, numLectures, numTutorials, numLabs);
            if(!success){
                System.out.println("Course with name " + courseName + " already exists. Please try again with a different name.");
            }
        }
    }

    private static void registerStudentForCourse(){
        System.out.println("Enter name of student");
        String studentName = sc.next();

        System.out.println("Enter name of course");
        String courseName = sc.next();

        Course currentCourse = university.getCourseByName(courseName);
        int numLectures = currentCourse.getNumLectures();
        int lectureChoice = -1;
        if(numLectures > 0){
            System.out.println("Select a lecture ID");
            for(int i = 0; i < numLectures; i++){
                System.out.println(i);
            }
            lectureChoice = sc.nextInt();
        }
        int numTutorials = currentCourse.getNumTutorials();
        int tutorialChoice = -1;
        if(numTutorials > 0){
            System.out.println("Select a tutorial ID");
            for(int i = 0; i < numTutorials; i++){
                System.out.println(i);
            }
            tutorialChoice = sc.nextInt();
        }
        int numLabs = currentCourse.getNumLabs();
        int labChoice = -1;
        if(numLabs > 0){
            System.out.println("Select a lab ID");
            for(int i = 0; i < numLabs; i++){
                System.out.println(i);
            }
            labChoice = sc.nextInt();
        }

        university.addRecord(studentName, courseName, lectureChoice, tutorialChoice, labChoice);
    }

    private static void printStudentTranscript(){
        System.out.println("Enter name of student");
        String studentName = sc.next();
        Record[] studentRecords = university.getRecordsByStudentName(studentName);
        for(int i = 0; i < studentRecords.length; i++){
            if(studentRecords[i].isMarked()){
                System.out.println(studentRecords[i].getCourseName()+"\t"+studentRecords[i].getGrade());
            }
        }
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
        university.getCourseByName(courseName).setComponentWeightage(examWeight, courseworkWeight);
    }

    private static void setCourseworkMark(){
        System.out.println("Enter name of student");
        String studentName = sc.next();

        System.out.println("Enter name of course");
        String courseName = sc.next();

        Record studentRecord = university.getRecord(courseName, studentName);
        int numberOfComponents = studentRecord.getNumberOfCourseworkComponents();
        double[] componentMarks = new double[numberOfComponents];
        for(int i = 0; i < numberOfComponents; i++){
            System.out.println("Enter marks for component["+i+"]:");
            componentMarks[i] = sc.nextDouble();
        }
        studentRecord.setCourseworkComponentMarks(componentMarks);
    }

    private static void setExamMark() {
        System.out.println("Enter name of student");
        String studentName = sc.next();

        System.out.println("Enter name of course");
        String courseName = sc.next();

        Record studentRecord = university.getRecord(courseName, studentName);
        System.out.println("Enter marks for exam:");
        studentRecord.setExamMarks(sc.nextDouble());
    }

    private static void printStudentNameList(){
        String[] studentNameList = university.getStudentNameList();
        for(int i = 0; i < studentNameList.length; i++){
            System.out.println(studentNameList[i]);
        }
    }

}
