package q1;

import java.util.Scanner;

public class Main {
    private static final int INPUT_COUNT = 6;

    public static void main(String[] args) {
        LinkedList<String> list = receiveListFromUser(INPUT_COUNT);
        System.out.println(list);
        LinkedList<String> reversed = reverseList(list);
        System.out.println(reversed);

        // --------

        LinkedList<Person> people = createPeopleList();
        System.out.println(people);
        Person oldest = max(people);
        System.out.println("THE OLDEST PERSON " + oldest);
    }

    // Creates a list of people
    private static LinkedList<Person> createPeopleList() {
        LinkedList<Person> people = new LinkedList<Person>();
        people.add(new Person("Dolev Goaz", "123456789", 2001));
        people.add(new Person("Barack Obama", "382137232", 1961));
        people.add(new Person("Benjamin the Third", "312032100", 802));
        people.add(new Person("Bob the Builder", "812738126", 1997));
        return people;
    }

    // Returns the maximum value of a list
    private static <T extends Comparable<T>> T max(LinkedList<T> list) {
        if (list.getHead() == null) {
            // if list is empty, there's no max value
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

    // Reverses a linked list WITHOUT MAINTAINING THE SOURCE LIST
    private static <T> LinkedList<T> reverseList(LinkedList<T> source) {
        LinkedListItem<T> current = source.getHead();
        LinkedListItem<T> previous = null;
        LinkedListItem<T> next;

        // logic- always 'store' the current 3 items. point the center one(the current) to the one before it(previous),
        // then move them forward one step. we do that until we traversed the list
        while (current != null) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }

        // now 'previous' is the new head
        current = previous;
        // this operation would be simple if there was a 'setHead' method, but it wasn't in the requirements.
        // so, we loop at the new (makeshift) linked list and feed it into a new linked list.
        LinkedList<T> output = new LinkedList<T>();
        while(current != null) {
            output.add(current.getValue());
            current = current.getNext();
        }

        return output;
    }


    // Receives k strings from the user and creates a linked list out of them.
    private static LinkedList<String> receiveListFromUser(int k) {
        LinkedList<String> list = new LinkedList<String>();
        for (int i = 0; i < k; i++) {
            list.add(receiveString("Insert a string: "));
        }
        return list;
    }

    // Receives a string from the user with the provided input message
    private static String receiveString(String message) {
        System.out.print(message);
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

}
