public class country {
    public String name;
    public int number;

    public country() {
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    


    public country(String name, int number) {
        this.name = name;
        this.number = number;
    }

}
