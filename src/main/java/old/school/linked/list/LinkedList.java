package old.school.linked.list;

import java.util.Iterator;

public class LinkedList<E> implements Iterator<E>, Iterable<E> {
    private Node<E> head;
    private Node<E> current;
    private Node<E> lastNext;
    private int size;
    private int iteratorIndex;
    private boolean didNext;

    public LinkedList() {
        this.head = null;
        this.current = null;
        this.size = 0;
    }

    public LinkedList(E element) {
        head = new Node<>(element);
        current = head;
        size = 1;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int length() {
        return size;
    }

    public E get() {
        return current == null ? null : current.getItem();
    }

    public void begin() {
        current = head;
    }

    public LinkedList<E> nextElement() {
        if (current != null) {
            current = current.next();
        }
        return this;
    }

    public LinkedList<E> gotoIndex(int index) {
        checkIndex(index);
        begin();

        for (int i = 0; i < size; i ++) {
            if (i == index) break;
            current = current.next();
        }

        return this;
    }

    public E get(int index) {
        return gotoIndex(index).get();
    }

    public LinkedList<E> insert(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.setNextNode(head);
        head = newNode;
        current = head;
        size++;
        return this;
    }

    public LinkedList<E> insert(int index, E element) {
        if (!(index > 0)) return insert(element);

        gotoIndex(index - 1).insertNode(element, false);
        return this;
    }

    public LinkedList<E> append(E element) {
        if (isEmpty()) return insert(element);

        gotoIndex(size - 1).insertNode(element, true);
        return this;
    }

    public E pop() {
        if (isEmpty()) return null;

        if (!(size > 1)) {
            E element = head.getItem();
            head = null;
            current = null;
            size = 0;
            return element;
        }

        gotoIndex(size - 2);
        Node<E> previous = current;
        gotoIndex(size - 1);
        previous.setNextNode(null);
        E element = current.getItem();
        current = previous;
        size--;
        return element;
    }

    public E delete() {
        if (isEmpty()) return null;

        if (size == 1) return pop();

        E element = head.getItem();
        head = head.getNextNode();
        current = head;
        size--;
        return element;
    }

    public E delete(int index) {
        if (isEmpty() || !(index > 0)) return delete();

        gotoIndex(index - 1);
        Node<E> previous = current;
        gotoIndex(index);
        E element = current.getItem();
        previous.setNextNode(current.getNextNode());
        current = previous;
        size--;
        return element;
    }

    @Override
    public void remove() {
        if (! didNext) return;

        if (iteratorIndex > 0) {
            iteratorIndex--;
        }

        delete(iteratorIndex);
    }

    @Override
    public Iterator<E> iterator() {
        iteratorIndex = 0;
        didNext = false;
        return this;
    }

    @Override
    public boolean hasNext() {
        if (isEmpty()) return false;
        if (!(iteratorIndex < size)) return false;
        return true;
    }

    @Override
    public E next() {
        if (hasNext()) {
            didNext = true;
            return this.get(iteratorIndex++);
        }

        return null;
    }

    private void checkIndex(int index) throws ArrayIndexOutOfBoundsException {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException("Linked list is empty");
        if ((!(index < size)) || index < 0) throw new ArrayIndexOutOfBoundsException("size: " + size + ", index: " + index);
    }

    private void insertNode(E element, boolean after) {
        Node<E> previous = current;

        if (after) {
            current = previous.getNextNode();
        } else {
            current = current.getNextNode();
        }

        Node<E> newNode = new Node<>(element);
        newNode.setNextNode(current);
        previous.setNextNode(newNode);
        current = newNode;
        size++;
    }
}
