package old.school.linked.list.basic;

import java.util.Objects;

public class Node<E> {
    private E data;
    private Node<E> next;

    private Node() {
        super();
        next = null;
    }

    public Node(E data) {
        this();
        this.data = data;
    }

    public Node(E data, Node<E> next) {
        this(data);
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return data.equals(node.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
