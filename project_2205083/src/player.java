import java.io.Serializable;

public class player implements Serializable
{
    public String name;
    public String country;
    public int age;
    public double height;
    public String club;
    public String position;
    public int number;
    public int salary;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getClub() {
        return this.club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    public player(String name, String country, int age, double height, String club, String position, int number, int salary) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.club = club;
        this.position = position;
        this.number = number;
        this.salary = salary;
    }
    public player(String name, String country, int age, double height, String club, String position, int salary) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.club = club;
        this.position = position;
        this.number = -1;
        this.salary = salary;   
    }
    public void details ()
    {
        System.out.println("Name: " + name);
        System.out.println("Country: " + country);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
        System.out.println("Club: " + club);
        System.out.println("Position: " + position);
        if (number!=-1) System.out.println("Jersey Number: " + number);
        System.out.println("Weekly Salaray: " + salary);

    }
    
}