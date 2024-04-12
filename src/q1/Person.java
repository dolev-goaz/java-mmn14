package q1;

public class Person implements Comparable<Person> {
    private String name;
    private String id;
    private int birthYear;

    public Person(String name, String id, int birthYear) {
        this.name = name;
        this.id = id;
        this.birthYear = birthYear;
    }

    public int getBirthYear() {
        return this.birthYear;
    }

    @Override
    public int compareTo(Person o) {
        // if the year is lower, then the person is older
        return o.getBirthYear() - this.getBirthYear();
    }

    @Override
    public String toString() {
        return String.format("Name: %s; ID: %s; Birth Year: %d", this.name, this.id, this.birthYear);
    }
}
