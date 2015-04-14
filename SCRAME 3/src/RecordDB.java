/**
 * Created by LEONARD on 14/4/2015.
 */

import java.util.ArrayList;
import java.util.List;

public class RecordDB {
    private List<Record> recordList = new ArrayList<Record>();


    public Record getRecord (int courseID, int studentID){
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentID()==(studentID) && currentRecord.getCourseID()==(courseID)){
                return currentRecord;
            }
        }
        return null;
    }

    public List<Record> getRecordList (){
        return recordList;
    }

    public Record[] getRecordsByCourse (int courseID){
        List<Record> courseRecords = new ArrayList<Record>();
        for(int i = 0; i < recordList.size(); i++){
            if(recordList.get(i).getCourseID()==(courseID)){
                courseRecords.add(recordList.get(i));
            }
        }
        Record[] recordArray = new Record[courseRecords.size()];
        for(int j = 0; j < recordArray.length; j++){
            recordArray[j] = courseRecords.get(j);
        }
        return recordArray;
    }

    public Record[] getRecordsByStudentID(int studentID){
        List<Record> selectedRecords = new ArrayList<Record>();
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentID()==(studentID)){
                selectedRecords.add(currentRecord);
            }
        }
        Record[] records = new Record[selectedRecords.size()];
        for(int i = 0; i < selectedRecords.size(); i++){
            records[i] = selectedRecords.get(i);
        }
        return records;
    }

    public void addRecord (int courseID, int studentID, int[] lessonChoice, int numComponents, double examWeight, double[] courseworkWeight) {
        recordList.add(new Record(courseID, studentID, lessonChoice, numComponents, examWeight, courseworkWeight));
        }
        }