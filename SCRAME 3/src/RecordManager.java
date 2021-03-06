import java.util.ArrayList;
import java.util.List;

/**
 * handles the logic dealing with the record data
 */
public class RecordManager {

    // Instance variable
    private RecordDB recordDB = new RecordDB();

    // GET METHODS

    /**
     * Gets a list of student IDs for selected lesson in a course
     * @param courseID identification number of the course
     * @param lessonOption ENUM value of LECTURE, LAB, TUTORIAL. Represents the difference lesson types.
     * @param lessonID identification number of the lesson type chosen
     * @return list of student IDs for selected lesson in a course
     */
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

    /**
     * Gets a list of course IDs for selected student
     * @param studentID identification number of student
     * @return list of course IDs registered by the student
     */
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

    /**
     * Gets the number of courses for selected student
     * @param studentID identification number of student
     * @return number of courses taken by particular student
     */
    public int getNumCourseByStudentID(int studentID){
        return recordDB.getRecordsByStudentID(studentID).length;
    }

    /**
     * Gets grade for selected student taking a selected course
     * @param courseID identification number of course
     * @param studentID identification number of student
     * @return grade of particular student in a particular course
     */
    public String getGradeByCourseStudent(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).getGrade();
    }

    /**
     * Gets the number of students in selected course
     * @param courseID identification number of course
     * @return number of students registered in the course
     */
    public int getNumStudentsByCourseID (int courseID){
        if(recordDB.getRecordsByCourse(courseID) == null){
            return 0;
        }
        return recordDB.getRecordsByCourse(courseID).length;
    }

    /**
     * Gets the overall marks of a selected student for a selected course
     * @param courseID identification number of course
     * @param studentID identification number of the student
     * @return overall grade of a particular student for a particular course
     */
    public double getOverallMarksByCourseStudent(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).getOverallMarks();
    }

    /**
     * Gets the exam marks of a selected student for a selected course
     * @param courseID identification number of course
     * @param studentID identification number of student
     * @return exam marks of a particular student of a particular course
     */
    public double getExamMarksByCourseStudent(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).getExamMarks();
    }

    /**
     * Retrieves all coursework marks of a selected student for a selected course
     * @param courseID identification number of course
     * @param studentID identification number of student
     * @return array of coursework marks of a selected student for a selected course
     */
    public double[] getCourseworkMarksByCourseStudent(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).getCourseworkComponentMarks();
    }

    /**
     * Gets the average overall marks by all the students for a selected course
     * @param courseID identification number of course
     * @return average overall marks of the course
     */
    public double getAverageOverallMarksByCourseID(int courseID){
        Record[] courseRecords = recordDB.getRecordsByCourse(courseID);
        double sum = 0;
        if(courseRecords == null){
            return 0;
        }
        for (Record courseRecord : courseRecords) {
            sum += courseRecord.getOverallMarks();
        }
        return sum/courseRecords.length;
    }

    /**
     * Gets the average exam marks by all the students for a selected course
     * @param courseID identification number of course
     * @return average exam marks for the course
     */
    public double getAverageExamMarksByCourseID(int courseID){
        Record[] courseRecords = recordDB.getRecordsByCourse(courseID);
        double sum = 0;
        if(courseRecords == null){
            return 0;
        }
        for (Record courseRecord : courseRecords) {
            sum += courseRecord.getExamMarks();
        }
        return sum/courseRecords.length;
    }

    /**
     * Gets the average marks for coursework components by all the students for a selected course
     * @param courseID identification number of course
     * @return average marks of all coursework components by all students from a selected course
     */
    public double getAverageTotalCourseworkMarksByCourseID(int courseID){
        Record[] courseRecords = recordDB.getRecordsByCourse(courseID);
        double sum = 0;
        if(courseRecords == null){
            return 0;
        }
        for (Record courseRecord : courseRecords) {
            sum += courseRecord.getTotalCourseworkMarks();
        }
        return sum/courseRecords.length;
    }

    /**
     * Gets the number of records in the database
     * @return number of records in the database
     */
    public int getNumRecords(){
        return recordDB.getRecordList().size();
    }

    // SET METHODS

    /**
     * Add a new record to record database
     * @param courseID identification number of the courses the student has registered
     * @param studentID identification number of the student
     * @param lessonChoice identification number of the lab,tutorial or lab the student is registered to
     * @param numComponents coursework marks of the various components of coursework
     * @param examWeight weightage of the exam marks
     * @param courseworkWeight weightage of coursework marks
     */
    public void addRecord (int courseID, int studentID, int[] lessonChoice, int numComponents, double examWeight, double[] courseworkWeight) {
        recordDB.addRecord(courseID, studentID, lessonChoice, numComponents, examWeight, courseworkWeight);
    }

    /**
     *  Sets the marks for the individual coursework components for selected student taking a selected course
     * @param courseID identification number of the course
     * @param studentID identification number of the student
     * @param courseworkComponentMarks marks of the coursework components
     */
    public void setCourseworkComponentMarks(int courseID, int studentID, double[] courseworkComponentMarks){
        recordDB.getRecord(courseID, studentID).setCourseworkComponentMarks(courseworkComponentMarks);
    }

    /**
     * Sets the exam marks for the selected student taking a selected course
     * @param courseID identification number of the course
     * @param studentID identification number of the student
     * @param examMarks marks of the exam
     */
    public void setExamMarks(int courseID, int studentID, double examMarks){
        recordDB.getRecord(courseID, studentID).setExamMarks(examMarks);
    }



    //METHODS FOR CHECKING

    /**
     * Checks if a record with a the course ID and studentID already exists
     * @param courseID identification number of the course
     * @param studentID identification number of the student
     * @return true if a record with the same course ID and student ID already exists
     */
    public boolean existingRecord (int courseID, int studentID) {
        boolean result = false;
        List<Record> recordList = recordDB.getRecordList();
        for (Record currentRecord : recordList) {
            if (currentRecord.getCourseID() == courseID && (currentRecord.getStudentID() == studentID)) {

                //Returns true if a record with the same course ID and student ID already exists
                result = true;
            }
        }
        return result;
    }

    /**
     * Checks if a selected record has been marked and ready to be printed in transcript
     * @param courseID identification number of the course
     * @param studentID identification number of the student
     * @return true if marked
     */
    public boolean isMarked(int courseID, int studentID){
        return recordDB.getRecord(courseID, studentID).isMarked();
    }

}
