import java.util.ArrayList;
import java.util.List;

public class RecordManager {
    private RecordDB recordDB = new RecordDB();
    public String[] getStudentListByCourseLesson (String courseName, int lessonType, int lessonID){
        List<Record> selectedRecords = new ArrayList<Record>();
        List<Record> recordList = RecordDB.getRecordList();
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getCourseName().equals(courseName)){
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
        String[] studentNameList = new String[selectedRecords.size()];
        for(int j = 0; j < selectedRecords.size(); j++){
            studentNameList[j] = selectedRecords.get(j).getStudentName();
        }
        return studentNameList;
    }

    public String[] getCourseListByStudent(String studentName){

        Record[] records = recordDB.getRecordsByStudentName(studentName);
        String[] courseNameList = new String[records.length];
        for(int i = 0; i < records.length; i++){
            courseNameList[i] = records[i].getCourseName();
        }
        return courseNameList;
    }

    public int getNumCourseByStudent(String studentName){
        return recordDB.getRecordsByStudentName(studentName).length;
    }

    public String getGradeByCourseStudent(String courseName, String studentName){
        return recordDB.getRecord(courseName, studentName).getGrade();
    }

    public int getNumStudentsByCourse (String courseName){
        return recordDB.getRecordsByCourse(courseName).length;
    }

    public int getNumStudentsByCourseLesson(String courseName, int lessonType, int lessonID){
        return recordDB.getRecordsByCourseLesson(courseName, lessonType, lessonID).length;
    }

    public double getOverallMarksByCourseStudent(String courseName, String studentName){
        return recordDB.getRecord(courseName, studentName).getOverallMarks();
    }






}
