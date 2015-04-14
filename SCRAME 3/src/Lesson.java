
public class Lesson {

    protected int ID;
    protected int capacity;
    protected int vacancies;
    protected String name;

    public Lesson(int ID, int capacity){

        this.ID = ID;
        this.capacity = capacity;
        this.vacancies = capacity;

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

    public void setVacancies(){

        vacancies--;

    }

    public String getName(){

        return this.name;

    }

}
