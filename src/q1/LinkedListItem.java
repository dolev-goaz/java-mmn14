package q1;

public class LinkedListItem<T> {
    private T value;
    private LinkedListItem<T> next;

    public LinkedListItem(T value) {
        this(value, null);
    }

    public LinkedListItem(T value, LinkedListItem<T> next) {
        this.value = value;
        this.next = next;
    }

    public LinkedListItem<T> getNext() {
        return next;
    }

    public void setNext(LinkedListItem<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
