/**
 * Created by LEONARD on 14/4/2015.
 */

import java.util.ArrayList;
import java.util.List;

public class RecordDB {
    private List<Record> recordList = new ArrayList<Record>();


    public Record getRecord (String courseName, String studentName){
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentName().equals(studentName) && currentRecord.getCourseName().equals(courseName)){
                return currentRecord;
            }
        }
        return null;
    }

    public Record getRecordList (String courseName, studentName){
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            return currentRecord;
        }

        return null;
    }

    public Record[] getRecordsByCourse (String courseName){
        List<Record> courseRecords = new ArrayList<Record>();
        for(int i = 0; i < recordList.size(); i++){
            if(recordList.get(i).getCourseName().equals(courseName)){
                courseRecords.add(recordList.get(i));
            }
        }
        Record[] recordArray = new Record[courseRecords.size()];
        for(int j = 0; j < recordArray.length; j++){
            recordArray[j] = courseRecords.get(j);
        }
        return recordArray;
    }

    public Record[] getRecordsByStudentName(String studentName){
        List<Record> selectedRecords = new ArrayList<Record>();
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentName().equals(studentName)){
                selectedRecords.add(currentRecord);
            }
        }
        Record[] records = new Record[selectedRecords.size()];
        for(int i = 0; i < selectedRecords.size(); i++){
            records[i] = selectedRecords.get(i);
        }
        return records;
    }

    public void addRecord (String courseName, String studentName, int[] lessonChoice, int numComponents, double examWeight, double[] courseworkWeight) {
        recordList.add(new Record(courseName, studentName, lessonChoice, numComponents, examWeight, courseworkWeight));
    }
}