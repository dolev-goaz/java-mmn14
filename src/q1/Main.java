package q1;

import java.util.Scanner;

public class Main {
    private static final int INPUT_COUNT = 6;

    public static void main(String[] args) {
        LinkedList<String> list = receiveListFromUser();
        System.out.println(list);
        LinkedList<String> reversed = reverseList(list);
        System.out.println(reversed);

        // --------

        LinkedList<Person> people = createPeopleList();
        System.out.println(people);
        Person oldest = max(people);
        System.out.println("THE OLDEST PERSON " + oldest);
    }

    private static LinkedList<Person> createPeopleList() {
        LinkedList<Person> people = new LinkedList<Person>();
        people.add(new Person("Dolev Goaz", "123456789", 2001));
        people.add(new Person("Barack Obama", "382137232", 1961));
        people.add(new Person("Benjamin the Third", "312032100", 802));
        people.add(new Person("Bob the Builder", "812738126", 1997));
        return people;
    }

    private static <T extends Comparable<T>> T max(LinkedList<T> list) {
        if (list.getHead() == null) {
            return null;
        }

        T currentMax = list.getHead().getValue();
        LinkedListItem<T> current = list.getHead().getNext();
        while (current != null) {
            T newItem = current.getValue();
            if (newItem.compareTo(currentMax) > 0) {
                currentMax = newItem;
            }

            current = current.getNext();
        }
        return currentMax;
    }

    private static <T> LinkedList<T> reverseList(LinkedList<T> source) {
        LinkedListItem<T> current = source.getHead();
        LinkedListItem<T> previous = null;
        LinkedListItem<T> next;

        while (current != null) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }

        // now previous is the new head
        LinkedList<T> output = new LinkedList<T>();
        while(previous != null) {
            output.add(previous.getValue());
            previous = previous.getNext();
        }

        return output;
    }

    private static LinkedList<String> receiveListFromUser() {
        LinkedList<String> list = new LinkedList<String>();
        for (int i = 0; i < INPUT_COUNT; i++) {
            list.add(receiveString("Insert a string: "));
        }
        return list;
    }

    private static String receiveString(String message) {
        System.out.print(message);
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

}
