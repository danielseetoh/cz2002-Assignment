import java.util.ArrayList;
import java.util.List;

public class University {

    // Single Responsibility: to store and retrieve student data, course data, record data

    private List<Student> studentList = new ArrayList<Student>();
    private List<Course> courseList = new ArrayList<Course>();
    private List<Record> recordList = new ArrayList<Record>();
    private List<Professor> professorList = new ArrayList<Professor>();


    //Getters
    public String[] getStudentNameList(){
        String[] studentNameList = new String[studentList.size()];
        for(int i = 0; i < studentList.size(); i++){
            studentNameList[i] = studentList.get(i).getStudentName();
        }
        return studentNameList;
    }

    public Course getCourseByName (String courseName) {
        for(int i = 0; i < courseList.size(); i++){
            Course currentCourse = courseList.get(i);
            if(currentCourse.getCourseName().equals(courseName)){
                return currentCourse;
            }
        }
        return null;
    }

    public Student getStudentByName (String studentName) {
        for(int i = 0; i < studentList.size(); i++){
            Student currentStudent = studentList.get(i);
            if(currentStudent.getStudentName().equals(studentName)){
                return currentStudent;
            }
        }
        return null;
    }

    public Record[] getRecordsByStudentName(String studentName){
        int counter = 0;
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentName().equals(studentName)){
                counter++;
            }
        }
        Record[] records = new Record[counter];
        counter = 0;
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentName().equals(studentName)){
                records[counter] = currentRecord;
                counter++;
            }
        }
        return records;
    }

    public Record getRecord (String courseName, String studentName){
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentName().equals(studentName) && currentRecord.getCourseName().equals(courseName)){
                return currentRecord;
            }
        }
        return null;
    }

    public String[] getCourseNameList(){
        String[] courseNameList = new String[courseList.size()];
        for(int i = 0; i < courseList.size(); i++){
            courseNameList[i] = courseList.get(i).getCourseName();
        }
        return courseNameList;
    }

    public int getVacancyByCourseName(String courseName){
        return getCourseByName(courseName).getVacancies();
    }

    public int getNumLecturesByCourseName (String courseName){
        return getCourseByName(courseName).getNumLectures();
    }

    public int getNumTutorialsByCourseName (String courseName){
        return getCourseByName(courseName).getNumTutorials();
    }

    public int getNumLabsByCourseName (String courseName){
        return getCourseByName(courseName).getNumLabs();
    }

    public int[] getLectureVacancyByCourseName (String courseName){
        Course currentCourse = getCourseByName(courseName);
        int[] lectureVacancy = currentCourse.getLectureVacancies();
        return lectureVacancy;
    }

    public int[] getTutorialVacancyByCourseName (String courseName){
        Course currentCourse = getCourseByName(courseName);
        int[] tutorialVacancy = currentCourse.getTutorialVacancies();
        return tutorialVacancy;
    }

    public int[] getLabVacancyByCourseName (String courseName){
        Course currentCourse = getCourseByName(courseName);
        int[] labVacancy = currentCourse.getLabVacancies();
        return labVacancy;
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


    //Setters
    public boolean addStudent (String studentName) {
        if(existingStudentName(studentName)){
            return false;
        } else {
            int newID = studentList.size();
            studentList.add(new Student(studentName, newID));
            return true;
        }
    }

    public boolean addCourse (String courseName, String professorName, int capacity,  int numLectures, int numTutorials, int numLabs) {
        if(existingCourseName(courseName)){
            return false;
        } else if(!existingProfessorName(professorName)){
            return false;
        } else if(numLectures < 0 || numTutorials < 0 || numLabs < 0 || capacity < 0){
            return false;
        } else {
            int newID = courseList.size();
            courseList.add(new Course(courseName, professorName, capacity, numLectures, numTutorials, numLabs, newID));
            return true;
        }
    }

    public boolean addRecord (String courseName, String studentName, int lectureChoice, int tutorialChoice, int labChoice) {
        if(existingRecord(courseName, studentName)){
            return false;
        } else {
            recordList.add(new Record(courseName, studentName, lectureChoice, tutorialChoice, labChoice));
            return true;
        }
    }

    public boolean addProfessor (String professorName) {
        if(existingProfessorName(professorName)){
            return false;
        } else {
            int newID = professorList.size();
            professorList.add(new Professor(professorName, newID));
            return true;
        }
    }



    //private methods
    private boolean existingStudentName (String studentName){
        boolean result = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentName().equals(studentName)) {
                result = true;
            }
        }
        return result;
    }

    private boolean existingCourseName (String courseName) {
        boolean result = false;
        for(int i = 0; i < courseList.size(); i++){
            if(courseList.get(i).getCourseName().equals(courseName)){
                result = true;
            }
        }
        return result;
    }

    private boolean existingRecord (String courseName, String studentName) {
        boolean result = false;
        for (int i = 0; i < recordList.size(); i++) {
            Record currentRecord = recordList.get(i);
            if (currentRecord.getCourseName().equals(courseName) && currentRecord.getStudentName().equals(studentName)) {
                result = true;
            }
        }
        return result;
    }

    private boolean existingProfessorName (String professorName){
        boolean result = false;
        for (int i = 0; i < professorList.size(); i++) {
            if (professorList.get(i).getProfessorName().equals(professorName)) {
                result = true;
            }
        }
        return result;
    }

}
