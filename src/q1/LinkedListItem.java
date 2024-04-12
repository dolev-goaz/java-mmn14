package q1;

// a class representing a single, generic item of a generic linked list
public class LinkedListItem<T> {
    private T value;
    private LinkedListItem<T> next;

    // constructor that accepts only the item's value
    public LinkedListItem(T value) {
        this(value, null);
    }

    // constructor that accepts the next cell as well
    public LinkedListItem(T value, LinkedListItem<T> next) {
        this.value = value;
        this.next = next;
    }

    // returns the item connected to this current one
    public LinkedListItem<T> getNext() {
        return next;
    }

    // sets the item connected to this current one
    public void setNext(LinkedListItem<T> next) {
        this.next = next;
    }

    // returns the value stored by this item
    public T getValue() {
        return value;
    }

    // sets the value of this item
    public void setValue(T value) {
        this.value = value;
    }
}
