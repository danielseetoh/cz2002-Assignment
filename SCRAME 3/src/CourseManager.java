import java.util.List;

/**
 * Manages the course information
 */
public class CourseManager {

    //instance variable
    /**
     * TODO: javadoc
     */
    private CourseDB courseDB = new CourseDB();
    
    //GET METHODS
    /**
     * Retrieves the number of vacancies for all lessons of a particular type for a course
     * @param courseID identification number of the course
     * @param option ENUM value of LECTURE, LAB, TUTORIAL. Represents the difference lesson types.
     * @return number of vacancies for the chosen lesson type
     */
    public int [] getLessonVacancyByCourseID(int courseID, LessonOption option) {
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //Choose which Lesson type to retrieve vacancy
        switch (option) {
            case LECTURE:   //Lecture
                //Retrieve LectureList for Course
                List<Lecture> lectureList =  course.getLectureList();

                //int Array to store vacancies
                int [] lectureArray = new int[lectureList.size()];

                //Vacancies for all Lectures
                for(int i = 0; i < lectureList.size(); i++)
                    lectureArray[i] = lectureList.get(i).getVacancies();
                return lectureArray;

            case TUTORIAL:  //Tutorial
                //Retrieve TutorialList for Course
                List<Tutorial> tutorialList =  course.getTutorialList();

                //int Array to store vacancies
                int [] tutorialArray = new int[tutorialList.size()];

                //Vacancies for all Tutorials
                for(int i = 0; i < tutorialList.size(); i++)
                    tutorialArray[i] = tutorialList.get(i).getVacancies();
                return tutorialArray;

            case LAB:       //Lab
                //Retrieve LabList for Course
                List<Lab> labList =  course.getLabList();

                //int Array to store vacancies
                int [] labArray= new int[labList.size()];

                //Vacancies for all Labs
                for(int i = 0; i < labList.size(); i++)
                    labArray[i] = labList.get(i).getVacancies();
                return labArray;
        }

        //if Course does not exist, return null
        return null;
    }

    /**
     * Retrieves the capacity for all Lessons of a LessonOption type for a Course
     * @param courseID Identification number of the course
     * @param option ENUM value of LECTURE, LAB, TUTORIAL. Represents the difference lesson types.
     * @return lesson capacity of given CourseID
     */
    public int [] getLessonCapacityByCourseID(int courseID, LessonOption option) {
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //Choose which Lesson type to retrieve capacity
        switch (option) {
            case LECTURE:   //Lecture
                //Retrieve LectureList for Course
                List<Lecture> lectureList =  course.getLectureList();

                //int Array to store capacity
                int [] lectureArray = new int[lectureList.size()];

                //Capacity for all Lectures
                for(int i = 0; i < lectureList.size(); i++)
                    lectureArray[i] = lectureList.get(i).getCapacity();
                return lectureArray;

            case TUTORIAL:  //Tutorial
                //Retrieve TutorialList for Course
                List<Tutorial> tutorialList =  course.getTutorialList();

                //int Array to store capacity
                int [] tutorialArray = new int[tutorialList.size()];

                //Capacity for all Tutorials
                for(int i = 0; i < tutorialList.size(); i++)
                    tutorialArray[i] = tutorialList.get(i).getCapacity();
                return tutorialArray;

            case LAB:       //Lab
                //Retrieve LabList for Course
                List<Lab> labList =  course.getLabList();

                //int Array to store capacity
                int [] labArray= new int[labList.size()];

                //capacity for all Labs
                for(int i = 0; i < labList.size(); i++)
                    labArray[i] = labList.get(i).getCapacity();
                return labArray;
        }

        //if Course does not exist, return null
        return null;
    }

    /**
     * Retrieves the number of coursework of a Course
     * @param courseID Identification number of the course
     * @return number of coursework of a course
     */
    public int getNumComponentsByCourseID(int courseID){
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //return number of Coursework of Course
        return course.getNumCoursework();
    }

    /**
     * Retrieves the Exam Weight of a course
     * @param courseID Identification number of Course
     * @return Exam weightage of a given courseID
     */
    public double getExamWeightByCourse(int courseID){
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //return Exam Weight of Course
        return course.getExamWeight();
    }

    /**
     * Retrieves all coursework weightage of a particular course
     * @param courseID
     * @return courseworkWeight of all coursework of a course in a double Array
     */
    public double[] getCourseworkWeightByCourse(int courseID){
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //return all coursework Weight of Course
        return course.getCourseworkWeight();
    }

    /**
     * Retrieves list of all CourseID
     * @return list of all CourseID
     */
    public int [] getCourseIDList() {
        //get list of all courses from courseDV
        List<Course> courseList = courseDB.getCourseList();

        //new int Array to store courseID
        int[] courseIDList = new int[courseList.size()];

        //get all CourseIDs
        for (int i = 0; i < courseList.size(); i++)
            courseIDList[i] = courseList.get(i).getCourseID();
        return courseIDList;
    }

    /**
     * prints a list of all courses with their respective ID, name and professors.
     */
    public void printCourseList() {
        //get list of all courses from courseDB
        List<Course> courseList = courseDB.getCourseList();

        System.out.printf("%s\t%20s\t%s\n", "CourseID", "Course Name", "ProfessorID");

        for (int i = 0; i < courseList.size(); i++) {
            System.out.printf("%8d\t%20s\t%11d\n", courseList.get(i).getCourseID(), courseList.get(i).getCourseName(), courseList.get(i).getProfessorID());
        }
    }

    //SET METHODS
    /**
     * add Course into courseDB after instantiating Course
     * @param courseID identification number of course
     * @param courseName name of course
     * @param professorID identification number of professor
     * @param capacityLecture maximum number of students this lecture can take
     * @param capacityTutorial maximum number of students this tutorial can take
     * @param capacityLab maximum number of students this lab can take
     */
    public void addCourse (int courseID, String courseName, int professorID, int [] capacityLecture, int [] capacityTutorial, int [] capacityLab) {
        //create new Course object
        Course course = new Course(courseID, courseName,  professorID);

        //add Lectures, add Tutorials and add Labs for new Course
        course.addLecture(capacityLecture);
        course.addTutorial(capacityTutorial);
        course.addLab(capacityLab);

        //pass Course object to courseDB to be added
        courseDB.add(course);
    }

    /**
     * Set vacancy for a Lesson of a Course
     * @param courseID identification number of course
     * @param option ENUM value of LECTURE, LAB, TUTORIAL. Represents the difference lesson types.
     * @param ID identification number of the chosen lecture,lab or tutorial
     */
    public void setVacancyByCourseLesson(int courseID, LessonOption option, int ID) {

        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //Choose which Lesson type to set vacancy
        switch (option) {
            case LECTURE:   //Lecture
                course.setLectureVacancy(ID);
            break;

            case TUTORIAL:  //Tutorial
                course.setTutorialVacancy(ID);
            break;

            case LAB:       //Lab
                course.setLabVacancy(ID);
            break;
        }
    }

    //set Weight of all coursework of a course

    /**
     * Sets the component weightage using courseID
     * @param courseID identification number of the course to set
     * @param examWeight exam weightage of the course to set
     * @param courseworkWeight coursework weightage of the course to set
     */
    public void setComponentWeightByCourseID(int courseID, double examWeight, double [] courseworkWeight) {
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //setComponentWeight for Course
        course.setComponentWeightage(examWeight, courseworkWeight);
    }

    //METHODS FOR CHECKING
    /**
     * check whether Course exist in CourseDB
     * @param courseID identification number of the course to check
     * @return true if course already exists in database
     */
    public boolean isExistingCourse(int courseID) {
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //if Course does not exist
        if ( course == null)
            return false;

        //if courseID exists
        return true;
    }

    /**
     * check whether Course is ready for registration
     * @param courseID identification number of the course to check
     * @return true if course is ready for registration
     */
    public boolean isCourseReadyForRegistrationByID(int courseID) {
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //if courseID exist AND courseComponents valid
        if (isExistingCourse(courseID) && course.isCourseComponentsValid())
            return true;

        return false;
    }
}
