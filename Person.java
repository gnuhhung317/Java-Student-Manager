package StudentManagement;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable, Comparable<Person> {
    private static int countPerson = 0;
    private int iD;
    private String name;
    private LocalDate birthday;
    private String address;
    private double height;
    private double weight;

    public Person(String name, LocalDate birthday, String address, double height, double weight) {

        this.countPerson++;
        this.iD = countPerson;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.height = height;
        this.weight = weight;
    }

    public Person() {

    }

    public int getID() {
        return this.iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getID() + "'" +
                ", name='" + getName() + "'" +
                ", birthday='" + getBirthday() + "'" +
                ", address='" + getAddress() + "'" +
                ", height='" + getHeight() + "'" +
                ", weight='" + getWeight() + "'" +
                "}";
    }

    @Override
    public int compareTo(Person o) {
        return (this.iD == o.iD) ? 1 : -1;
    }
}
