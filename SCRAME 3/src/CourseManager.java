import java.util.List;

public class CourseManager {

    //instance variable
    private CourseDB courseDB = new CourseDB();


    
    //GET METHODS
    //get vacancy for all Lessons of a LessonOption type for a Course
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
                    labArray[i] = labList.get(i).getCapacity();
                return labArray;
        }

        //if Course does not exist, return null
        return null;
    }

    //get capacity for all Lessons of a LessonOption type for a Course
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
                    lectureArray[i] = lectureList.get(i).getVacancies();
                return lectureArray;

            case TUTORIAL:  //Tutorial
                //Retrieve TutorialList for Course
                List<Tutorial> tutorialList =  course.getTutorialList();

                //int Array to store capacity
                int [] tutorialArray = new int[tutorialList.size()];

                //Capacity for all Tutorials
                for(int i = 0; i < tutorialList.size(); i++)
                    tutorialArray[i] = tutorialList.get(i).getVacancies();
                return tutorialArray;

            case LAB:       //Lab
                //Retrieve LabList for Course
                List<Lab> labList =  course.getLabList();

                //int Array to store capacity
                int [] labArray= new int[labList.size()];

                //capacity for all Labs
                for(int i = 0; i < labList.size(); i++)
                    labArray[i] = labList.get(i).getVacancies();
                return labArray;
        }

        //if Course does not exist, return null
        return null;
    }

    //get number of coursework of a Course
    public int getNumComponentsByCourseID(int courseID){
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //return number of Coursework of Course
        return course.getNumCoursework();
    }

    //get Exam Weight of a Course
    public double getExamWeightByCourse(int courseID){
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //return Exam Weight of Course
        return course.getExamWeight();
    }

    //get courseworkWeight of all coursework of a course in a double Array
    public double[] getCourseworkWeightByCourse(int courseID){
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //return all coursework Weight of Course
        return course.getCourseworkWeight();
    }

    //get list of all CourseIDs
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

    public void printCourseList() {
        //get list of all courses from courseDV
        List<Course> courseList = courseDB.getCourseList();

        System.out.printf("%10s%30s%20s\n","CourseID","Course Name","Professor-in-charge");

        for (int i = 0; i < courseList.size(); i++) {
            System.out.printf("%10d%30s%20d\n", courseList.get(i).getCourseID(), courseList.get(i).getCourseName(), courseList.get(i).getProfessorID());
        }
    }



    //SET METHODS
    //add Course into courseDB after instantiating Course
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

    //set vacancy for a Lesson of a Course
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
    public void setComponentWeightByCourseID(int courseID, double examWeight, double [] courseworkWeight) {
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //setComponentWeight for Course
        course.setComponentWeightage(examWeight, courseworkWeight);
    }



    //METHODS FOR CHECKING
    //check whether Course exist in CourseDB
    public boolean isExistingCourse(int courseID) {
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //if Course does not exist
        if ( course == null)
            return false;

        //if courseID exists
        return true;
    }

    //check whether Course is ready for registration
    public boolean isCourseReadyForRegistrationByID(int courseID) {
        //get Course object based on courseID
        Course course = courseDB.getCourse(courseID);

        //if courseID exist AND courseComponents valid
        if (isExistingCourse(courseID) && course.isCourseComponentsValid())
            return true;

        return false;
    }
}
