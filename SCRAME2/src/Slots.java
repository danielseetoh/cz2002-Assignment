
public class Slots {
    private int capacity;
    private int vacancies;

    public Slots(int capacity){
        this.capacity = capacity;
        this.vacancies = capacity;
    }

    //Getters
    public int getCapacity() {
        return capacity;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    public boolean isFull(){
        if(vacancies <= 0){
            return true;
        } else {}
        return false;
    }
}
