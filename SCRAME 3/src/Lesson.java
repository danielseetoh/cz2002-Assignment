
public class Lesson {

    //instance variables
    protected int ID;
    protected int capacity;
    protected int vacancies;
    protected String name;

    //constructor
    public Lesson(int ID, int capacity){
        this.ID = ID;
        this.capacity = capacity;
        this.vacancies = capacity;  //initialize vacancy = capacity. No registration yet
    }

    //get vacancies
    public int getVacancies(){
        return this.vacancies;
    }

    //get ID
    public int getID(){
        return this.ID;
    }

    //get capacity
    public int getCapacity(){
        return this.capacity;
    }

    //get name
    public String getName(){
        return this.name;
    }

    //minus vacancy by 1
    public void setVacancies(){
        vacancies--;
    }
}
