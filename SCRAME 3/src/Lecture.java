
public class Lecture {

    private int ID;
    private int capacity;
    private int vacancies;

    public Lecture(int ID, int capacity){

        this.ID = ID;
        this.capacity = capacity;

    }

    public int getVacancies(){

        return this.vacancies;

    }

    public int getID(){

        return this.ID;

    }

    public int getCapacity(){

        return this.capacity;

    }

}
