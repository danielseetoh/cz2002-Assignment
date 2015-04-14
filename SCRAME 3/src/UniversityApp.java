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
        String name;

        //loadObjects();

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
                    System.out.println("Enter the student's name.");
                    name = sc.nextLine();
                    studentManager.addStudent(name);
                    System.out.println("Student " + name + " has been registered.");
                    break;
                case 2:
                    System.out.println("Enter the professor's name. ");
                    name = sc.nextLine();
                    professorManager.addProfessor(name);
                    System.out.println("Professor " + name + " has been registered.");
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
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
                case 12:
                    break;
                case 13:
                    break;
                default:
                    System.out.println("That is not a valid choice.");
                    break;
            }

        }
    }



/*    function to load from file all objects

    private static void loadObjects(){

    }

    */
}
