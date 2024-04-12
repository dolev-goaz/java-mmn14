package q1;

// class representing a generic linked list
public class LinkedList<T> {
    LinkedListItem<T> head;
    LinkedListItem<T> tail;

    // constructor
    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    // returns the first item of the linked list
    public LinkedListItem<T> getHead() {
        return head;
    }

    // returns the last item of the linked list
    public LinkedListItem<T> getTail() {
        return tail;
    }

    // adds a new value to the end of the list
    public void add(T value) {
        LinkedListItem<T> newItem = new LinkedListItem<T>(value);
        if (head == null) {
            // if the head is null, meaning its the first item
            // means the list is empty, so both head and tail would be this item
            head = newItem;
            tail = head;
            return;
        }
        // add to the end of the list
        tail.setNext(newItem);
        // update the end of the list
        tail = newItem;
    }

    // removes the head from the list. if the list is empty- throw EmptyListException.
    public T remove() throws EmptyListException {
        if (head == null) {
            throw new EmptyListException("List is empty");
        }
        T value = head.getValue();
        // update the head of the list
        head = head.getNext();
        if (head == null) {
            // if the head went past the tail, we should nullify the tail as well
            tail = null;
        }
        return value;
    }

    @Override
    // string representation
    public String toString() {
        if (head == null) {
            return "Empty list";
        }
        String output = "";
        LinkedListItem<T> current = head;

        while (current != tail) {
            output += current.getValue().toString() + " -> ";
            current = current.getNext();
        }
        output += tail.getValue();
        return output;
    }
}
