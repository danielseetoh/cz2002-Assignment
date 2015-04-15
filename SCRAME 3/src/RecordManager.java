import java.util.ArrayList;
import java.util.List;

public class RecordManager {

    // Instance variable
    private RecordDB recordDB = new RecordDB();

    // GET METHODS

    // Gets a list of student IDs for selected lesson in a course
    public int[] getStudentIDListByCourseLesson (int courseID, LessonOption lessonOption, int lessonID){
        List<Record> selectedRecords = new ArrayList<>();
        List<Record> recordList = recordDB.getRecordList();

        // Traverse through all the records to search for records relating to the course, lesson type and lesson ID
        for (Record currentRecord : recordList) {
            if (currentRecord.getCourseID() == courseID) {
                switch (lessonOption) {
                    case LECTURE:
                        if (currentRecord.getLectureChoice() == lessonID) {

                            //Add to list of selected records
                            selectedRecords.add(currentRecord);
                        }
                        break;
                    case TUTORIAL:
                        if (currentRecord.getTutorialChoice() == lessonID) {

                            //Add to list of selected records
                            selectedRecords.add(currentRecord);
                        }
                        break;
                    case LAB:
                        if (currentRecord.getLabChoice() == lessonID) {

                            //Add to list of selected records
                            selectedRecords.add(currentRecord);
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        // Create an integer array to store student IDs from the list of selected records
        int[] studentNameList = new int[selectedRecords.size()];
        for(int j = 0; j < selectedRecords.size(); j++){
            studentNameList[j] = selectedRecords.get(j).getStudentID();
        }
        return studentNameList;
    }

    // Gets a list of course IDs for selected student
    public int[] getCourseIDByStudentID(int studentID){

        // Retrieve records for selected student
        Record[] records = recordDB.getRecordsByStudentID(studentID);
        if(records == null){
            return null;
        }

        // Create an integer array to store course IDs from the selected student
        int[] courseIDList = new int[records.length];
        for(int i = 0; i < records.length; i++){
            courseIDList[i] = records[i].getCourseID();
        }
        return courseIDList;
    }

    // Gets the number of courses for selected student
    public int getNumCourseByStudentID(int studentID){
        return recordDB.getRecordsByStudentID(studentID).length;
    }

    // Gets grade for
    public String getGradeByCourseStudent(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).getGrade();
    }

    public int getNumStudentsByCourseID (int courseID){
        return recordDB.getRecordsByCourse(courseID).length;
    }

    /*
    public int getNumStudentsByCourseLesson(int courseID, LessonOption lessonOption, int lessonID){
        return recordDB.getRecordsByCourseLesson(courseID, lessonOption, lessonID).length;
        //ask leonard
    }
    */

    public double getOverallMarksByCourseStudent(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).getOverallMarks();
    }

    public double getExamMarksByCourseStudent(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).getExamMarks();
    }

    public double[] getCourseworkMarksByCourseStudent(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).getCourseworkComponentMarks();
    }

    /*
    public int[] getStudentIDByCourse(int courseID){
        Record[] courseRecords = recordDB.getRecordsByCourse(courseID);
        int[] result = new int[courseRecords.length];
        for(int i = 0; i < courseRecords.length; i++){
            result[i] = courseRecords[i].getStudentID();
        }
        return result;
    }
    */

    public double getAverageOverallMarksByCourseID(int courseID){
        Record[] courseRecords = recordDB.getRecordsByCourse(courseID);
        double sum = 0;
        for(int i = 0; i < courseRecords.length; i++){
            sum += courseRecords[i].getOverallMarks();
        }
        return sum/courseRecords.length;
    }

    public double getAverageExamMarksByCourseID(int courseID){
        Record[] courseRecords = recordDB.getRecordsByCourse(courseID);
        double sum = 0;
        for(int i = 0; i < courseRecords.length; i++){
            sum += courseRecords[i].getExamMarks();
        }
        return sum/courseRecords.length;
    }

    public double getAverageTotalCourseworkMarksByCourseID(int courseID){
        Record[] courseRecords = recordDB.getRecordsByCourse(courseID);
        double sum = 0;
        for(int i = 0; i < courseRecords.length; i++){
            sum += courseRecords[i].getTotalCourseworkMarks();
        }
        return sum/courseRecords.length;
    }

    //Setters
    public void addRecord (int courseID, int studentID, int[] lessonChoice, int numComponents, double examWeight, double[] courseworkWeight) {
        recordDB.getRecordList().add(new Record(courseID, studentID, lessonChoice, numComponents, examWeight, courseworkWeight));
    }

    public void setCourseworkComponentMarks(int courseID, int studentID, double[] courseworkComponentMarks){
        recordDB.getRecord(courseID, studentID).setCourseworkComponentMarks(courseworkComponentMarks);
    }

    public void setExamMarks(int courseID, int studentID, double examMarks){
        recordDB.getRecord(courseID, studentID).setExamMarks(examMarks);
    }

    //METHODS FOR CHECKING
    public boolean existingRecord (int courseID, int studentID) {
        boolean result = false;
        List<Record> recordList = recordDB.getRecordList();
        for (int i = 0; i < recordList.size(); i++) {
            Record currentRecord = recordList.get(i);
            if (currentRecord.getCourseID() == courseID && (currentRecord.getStudentID() == studentID)) {
                result = true;
            }
        }
        return result;
    }

    public boolean isMarked(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).isMarked();
    }

}
