import java.util.ArrayList;
import java.util.List;

public class RecordDB {
    private List<Record> recordList = new ArrayList<Record>();

    //Getters
    private Record[] getRecordsByStudentName(String studentName){
        List<Record> recordList = new ArrayList<Record>();
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentName().equals(studentName)){
                recordList.add(currentRecord);
            }
        }
        Record[] records = new Record[recordList.size()];
        for(int i = 0; i < recordList.size(); i++){
            records[i] = recordList.get(i);
        }
        return records;
    }

    private Record getRecord (String courseName, String studentName){
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentName().equals(studentName) && currentRecord.getCourseName().equals(courseName)){
                return currentRecord;
            }
        }
        return null;
    }

    public String[] getStudentNameListByCourseLesson (String courseName, int lessonType, int lessonID){
        List<Record> selectedRecords = new ArrayList<Record>();
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
        Record[] records = getRecordsByStudentName(studentName);
        String[] courseNameList = new String[records.length];
        for(int i = 0; i < records.length; i++){
            courseNameList[i] = records[i].getStudentName();
        }
        return courseNameList;
    }

    public int getNumCourseByStudent(String studentName){
        return getRecordsByStudentName(studentName).length;
    }

    public String getGradeByCourseStudent(String courseName, String studentName){
        return getRecord(courseName, studentName).getGrade();
    }




    //Setters
    public void addRecord (String courseName, String studentName, int[] lessonChoice) {
        recordList.add(new Record(courseName, studentName, lessonChoice));
    }

    public void setCourseworkComponentMarks(String courseName, String studentName, double[] courseworkComponentMarks){
        getRecord(courseName, studentName).setCourseworkComponentMarks(courseworkComponentMarks);
    }

    public void setExamMarks(String courseName, String studentName, double examMarks){
        getRecord(courseName, studentName).setExamMarks(examMarks);
    }




    //Verifiers
    public boolean existingRecord (String courseName, String studentName) {
        boolean result = false;
        for (int i = 0; i < recordList.size(); i++) {
            Record currentRecord = recordList.get(i);
            if (currentRecord.getCourseName().equals(courseName) && currentRecord.getStudentName().equals(studentName)) {
                result = true;
            }
        }
        return result;
    }

    public boolean isMarked(String courseName, String studentName){
        return getRecord(courseName, studentName).isMarked();
    }
}
