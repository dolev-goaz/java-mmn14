package q1;

import java.util.Scanner;

public class Main {
    private static final int INPUT_COUNT = 6;

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        for (int i = 0; i < INPUT_COUNT; i++) {
            list.add(receiveString("Insert a string: "));
        }
        System.out.println(list);
        LinkedList<String> reversed = reverseList(list);
        System.out.println(reversed);
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

    private static String receiveString(String message) {
        System.out.print(message);
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

}
