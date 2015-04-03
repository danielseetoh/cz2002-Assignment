/**
 * Created by danielseetoh on 3/31/2015.
 */

import java.util.*;

public class Student {

    private String studentName;
    private String studentID;
    private List<Record> recordList = new ArrayList<Record>();

    public Student(String studentName, String studentID){
        this.studentName = studentName;
        this.studentID = studentID;
    }

    public void registerCourse(Course courseName){  //remove sem and year from this and from Course class
        Record record = new Record(this, courseName);
        recordList.add(record);
    }

    public void printTranscript(){
        //print out transcript
        for(int i = 0; i<recordList.size(); i++){
            System.out.println("The overall grade for " + recordList.get(i).getCourse().getCourseName() +
                    " is " + recordList.get(i).getGrade());
            System.out.println("The overall mark for " + recordList.get(i).getCourse().getCourseName() +
                    " is " + recordList.get(i).getOverallMarks());
            System.out.println("/n");
        }
    }

    public String getID(){ //new method
        return studentID;
    }

    public Record getRecordList(int i){
        return recordList.get(i);
    }

    public List getWholeRecordList() {
        return recordList;
    }
}
