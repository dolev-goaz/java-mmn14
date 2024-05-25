package q1;

// class representing a person
public class Person implements Comparable<Person> {
    private final String name;
    private final String id;
    private final int birthYear;

    // constructor
    public Person(String name, String id, int birthYear) {
        this.name = name;
        this.id = id;
        this.birthYear = birthYear;
    }

    // returns the year of birth of the person
    public int getBirthYear() {
        return this.birthYear;
    }

    @Override
    // comparison with another person
    public int compareTo(Person o) {
        // if the year is lower, then the person is older
        return o.getBirthYear() - this.getBirthYear();
    }

    @Override
    // string representation
    public String toString() {
        return String.format("Name: %s; ID: %s; Birth Year: %d", this.name, this.id, this.birthYear);
    }
}
