package q1;

public class LinkedList<T> {
    LinkedListItem<T> head;
    LinkedListItem<T> tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public LinkedListItem<T> getHead() {
        return head;
    }

    public LinkedListItem<T> getTail() {
        return tail;
    }

    public void add(T value) {
        LinkedListItem<T> next = new LinkedListItem<T>(value);
        if (head == null) {
            head = next;
            tail = head;
            return;
        }
        tail.setNext(next);
        tail = next;
    }

    public T remove() throws EmptyListException {
        if (head == null) {
            throw new EmptyListException("List is empty");
        }
        T value = head.getValue();
        head = head.getNext();
        if (head == null) {
            // if the head went past the tail
            tail = null;
        }
        return value;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "Empty list";
        }
        String output = "";
        LinkedListItem<T> current = head;

        while (current != tail) {
            output += tail.getValue().toString() + " -> ";
        }
        output += tail.getValue();
        return output;
    }
}
