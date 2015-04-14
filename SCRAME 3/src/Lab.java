
public class Lab {

    private int ID;
    private int capacity;
    private int vacancies;

    public Lab(int ID, int capacity){

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

    public void setVacancies(){

        vacancies--;

    }

}
