import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Created by danielseetoh on 4/15/2015.
 */
public class test {

    public static void main(String[] args){
        loadStudents();
        loadProfessors();
        loadCourses();
    }

    private static void loadStudents(){
        List<String> students = new ArrayList<String>();
        Scanner sc;

        try{
            sc = new Scanner(new File("students.txt"));
            while(sc.hasNextLine()){
                students.add(sc.nextLine());
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        for(int i = 0; i<students.size(); i++) {
            studentManager.addStudent(students.get(i));
        }
    }

    private static void loadProfessors(){
        List<String> professors = new ArrayList<String>();
        Scanner sc;

        try{
            sc = new Scanner(new File("professors.txt"));
            while(sc.hasNextLine()){
                professors.add(sc.nextLine());
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        for(int i = 0; i<professors.size(); i++){
            professorManager.addProfessor(professors.get(i));
        }
    }

    private static void loadCourses(){
        List<String[]> courses = new ArrayList<>();
        Scanner sc;
        String line;
        String delimiter = "[ ]+";
        try{
            sc = new Scanner(new File("courses.txt"));
            while(sc.hasNextLine()){
                line = sc.nextLine();
                courses.add(line.split(delimiter));
            }
            for(int i = 0; i<courses.size(); i++){
                int j = 0, k = 0, l = 0;
                int courseID = -1, professorID = -1;
                String courseName = null;
                int[] lectureCapacity = null, tutorialCapacity = null, labCapacity = null;
                courseName = courses.get(i)[0];
                courseID = Integer.parseInt(courses.get(i)[1]);
                professorID = Integer.parseInt(courses.get(i)[2]);
                lectureCapacity = new int[Integer.parseInt(courses.get(i)[3])];
                for(j = 0; j<lectureCapacity.length; j++){
                    lectureCapacity[j] = Integer.parseInt(courses.get(i)[4+j]);
                }
                if(Integer.parseInt(courses.get(i)[4+lectureCapacity.length]) == 0){
                    tutorialCapacity = null;
                    if(Integer.parseInt(courses.get(i)[5+lectureCapacity.length]) == 0){
                        labCapacity = null;
                    }
                    else{
                        labCapacity = new int[Integer.parseInt(courses.get(i)[5+lectureCapacity.length])];
                        for(l = 0; l<labCapacity.length; l++){
                            labCapacity[l] = Integer.parseInt(courses.get(i)[6+lectureCapacity.length]);
                        }
                    }
                }else {
                    tutorialCapacity = new int[Integer.parseInt(courses.get(i)[4 + lectureCapacity.length])];
                    for (k = 0; k < tutorialCapacity.length; k++) {
                        tutorialCapacity[k] = Integer.parseInt(courses.get(i)[5 + lectureCapacity.length + k]);
                    }
                    if(Integer.parseInt(courses.get(i)[5+lectureCapacity.length+tutorialCapacity.length]) == 0){
                        labCapacity = null;
                    }
                    else{
                        labCapacity = new int[Integer.parseInt(courses.get(i)[5+lectureCapacity.length+tutorialCapacity.length])];
                        for(l = 0; l<labCapacity.length; l++){
                            labCapacity[l] = Integer.parseInt(courses.get(i)[6+lectureCapacity.length+tutorialCapacity.length]);
                        }
                    }

                }
                courseManager.addCourse(courseID, courseName, professorID, lectureCapacity, tutorialCapacity, labCapacity);
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
