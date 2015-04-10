import java.util.ArrayList;
import java.util.List;

public class RecordDB {
    private List<Record> recordList = new ArrayList<Record>();

    //Getters
    private Record[] getRecordsByStudentName(String studentName){
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
            courseNameList[i] = records[i].getCourseName();
        }
        return courseNameList;
    }

    public int getNumCourseByStudent(String studentName){
        return getRecordsByStudentName(studentName).length;
    }

    public String getGradeByCourseStudent(String courseName, String studentName){
        return getRecord(courseName, studentName).getGrade();
    }

    private Record[] getRecordsByCourse (String courseName){
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

    private Record[] getRecordsByCourseLesson(String courseName, int lessonType, int lessonID){
        List<Record> courseRecords = new ArrayList<Record>();
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getCourseName().equals(courseName)){
                switch(lessonType){
                    case 0:
                        if(currentRecord.getLectureChoice() == lessonID){
                            courseRecords.add(currentRecord);
                        }
                        break;
                    case 1:
                        if(currentRecord.getTutorialChoice() == lessonID){
                            courseRecords.add(currentRecord);
                        }
                        break;
                    case 2:
                        if(currentRecord.getLabChoice() == lessonID){
                            courseRecords.add(currentRecord);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        Record[] recordArray = new Record[courseRecords.size()];
        for(int j = 0; j < recordArray.length; j++){
            recordArray[j] = courseRecords.get(j);
        }
        return recordArray;
    }

    public int getNumStudentsByCourse (String courseName){
        return getRecordsByCourse(courseName).length;
    }

    public int getNumStudentsByCourseLesson(String courseName, int lessonType, int lessonID){
        return getRecordsByCourseLesson(courseName, lessonType, lessonID).length;
    }

    public double getOverallMarksByCourseStudent(String courseName, String studentName){
        return getRecord(courseName, studentName).getOverallMarks();
    }

    public double getExamMarksByCourseStudent(String courseName, String studentName){
        return getRecord(courseName, studentName).getExamMarks();
    }

    public double[] getCourseworkMarksByCourseStudent(String courseName, String studentName){
        return getRecord(courseName, studentName).getCourseworkComponentMarks();
    }

    public String[] getStudentNamesByCourse(String courseName){
        Record[] courseRecords = getRecordsByCourse(courseName);
        String[] result = new String[courseRecords.length];
        for(int i = 0; i < courseRecords.length; i++){
            result[i] = courseRecords[i].getStudentName();
        }
        return result;
    }

    public double getAverageOverallMarksByCourse(String courseName){
        Record[] courseRecords = getRecordsByCourse(courseName);
        double sum = 0;
        for(int i = 0; i < courseRecords.length; i++){
            sum += courseRecords[i].getOverallMarks();
        }
        return sum/courseRecords.length;
    }

    public double getAverageExamMarksByCourse(String courseName){
        Record[] courseRecords = getRecordsByCourse(courseName);
        double sum = 0;
        for(int i = 0; i < courseRecords.length; i++){
            sum += courseRecords[i].getExamMarks();
        }
        return sum/courseRecords.length;
    }

    public double getAverageTotalCourseworkMarksByCourse(String courseName){
        Record[] courseRecords = getRecordsByCourse(courseName);
        double sum = 0;
        for(int i = 0; i < courseRecords.length; i++){
            sum += courseRecords[i].getTotalCourseworkMarks();
        }
        return sum/courseRecords.length;
    }




    //Setters
    public void addRecord (String courseName, String studentName, int[] lessonChoice, int numComponents, double examWeight, double[] courseworkWeight) {
        recordList.add(new Record(courseName, studentName, lessonChoice, numComponents, examWeight, courseworkWeight));
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
