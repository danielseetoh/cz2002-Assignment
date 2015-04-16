
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the record information
 */
public class RecordDB {
    // Instance Variable
    private List<Record> recordList = new ArrayList<Record>();

    //GET METHODS
    /**
     * Retrieve record of a selected student taking a selected course
     * @param courseID identification number of course
     * @param studentID identification number of student
     * @return record of a selected student taking a selected course
     */
    public Record getRecord (int courseID, int studentID){
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentID()==(studentID) && currentRecord.getCourseID()==(courseID)){
                return currentRecord;
            }
        }
        return null;
    }

    // Gets the list of all the records in the database
    public List<Record>  getRecordList (){
        return recordList;
    }

    // Gets the array of records in a selected course
    public Record[] getRecordsByCourse (int courseID){
        List<Record> courseRecords = new ArrayList<Record>();
        for(int i = 0; i < recordList.size(); i++){

            // If record is of the selected course ID, add record to list of selected records
            if(recordList.get(i).getCourseID()==(courseID)){
                courseRecords.add(recordList.get(i));
            }
        }

        // Return null if there are no students registered in this course
        if(courseRecords.size() == 0){
            return null;
        }

        // Convert the list of selected records into an array of records
        Record[] recordArray = new Record[courseRecords.size()];
        for(int j = 0; j < recordArray.length; j++){
            recordArray[j] = courseRecords.get(j);
        }
        return recordArray;
    }

    // Gets the array of records of a selected student
    public Record[] getRecordsByStudentID(int studentID){
        List<Record> selectedRecords = new ArrayList<Record>();
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);

            // If record is of the selected student, add record to list of selected records
            if(currentRecord.getStudentID()==(studentID)){
                selectedRecords.add(currentRecord);
            }
        }

        // Return null if the selected student does not have any registered courses
        if(selectedRecords.size() == 0){
            return null;
        }

        // Convert the list of selected records into an array of records
        Record[] records = new Record[selectedRecords.size()];
        for(int i = 0; i < selectedRecords.size(); i++){
            records[i] = selectedRecords.get(i);
        }
        return records;
    }

    // Add a new record
    public void addRecord (int courseID, int studentID, int[] lessonChoice, int numComponents, double examWeight, double[] courseworkWeight) {
        recordList.add(new Record(courseID, studentID, lessonChoice, numComponents, examWeight, courseworkWeight));
    }
}