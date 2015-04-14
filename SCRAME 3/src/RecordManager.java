import java.util.ArrayList;
import java.util.List;

public class RecordManager {

    private RecordDB recordDB = new RecordDB();

    public int[] getStudentIDListByCourseLesson (int courseID, int lessonType, int lessonID){
        List<Record> selectedRecords = new ArrayList<Record>();
        List<Record> recordList = recordDB.getRecordList();
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getCourseID() == courseID){
                switch(lessonType){
                    case 0:
                        if(currentRecord.getLectureChoice() == lessonID){
                            selectedRecords.add(currentRecord);
                        }
                        break;
                    case 1:
                        if(currentRecord.getTutorialChoice() == lessonID){
                            selectedRecords.add(currentRecord);
                        }
                        break;
                    case 2:
                        if(currentRecord.getLabChoice() == lessonID){
                            selectedRecords.add(currentRecord);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        int[] studentNameList = new int[selectedRecords.size()];
        for(int j = 0; j < selectedRecords.size(); j++){
            studentNameList[j] = selectedRecords.get(j).getStudentID();
        }
        return studentNameList;
    }

    public int[] getCourseIDByStudentID(int studentID){

        Record[] records = recordDB.getRecordsByStudentID(studentID);
        int[] courseIDList = new int[records.length];
        for(int i = 0; i < records.length; i++){
            courseIDList[i] = records[i].getCourseID();
        }
        return courseIDList;
    }

    public int getNumCourseByStudentID(int studentID){
        return recordDB.getRecordsByStudentID(studentID).length;
    }

    public String getGradeByCourseStudent(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).getGrade();
    }

    public int getNumStudentsByCourseID (int courseID){
        return recordDB.getRecordsByCourse(courseID).length;
    }

    public int getNumStudentsByCourseLesson(int courseID, LessonOption lessonOption, int lessonID){
        return recordDB.getRecordsByCourseLesson(courseID, lessonOption, lessonID).length;
        //ask leonard
    }

    public double getOverallMarksByCourseStudent(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).getOverallMarks();
    }

    public double getExamMarksByCourseStudent(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).getExamMarks();
    }

    public double[] getCourseworkMarksByCourseStudent(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).getCourseworkComponentMarks();
    }

    public int[] getStudentIDByCourse(int courseID){
        Record[] courseRecords = recordDB.getRecordsByCourse(courseID);
        int[] result = new int[courseRecords.length];
        for(int i = 0; i < courseRecords.length; i++){
            result[i] = courseRecords[i].getStudentID();
        }
        return result;
    }

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

    //Verifiers
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
