package old.school.linked.list;

import java.util.*;

public class LinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int length;

    public LinkedList() {
        clear();
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[length];
        Node<E> obj = head;

        for (int i = 0; i < length; i++) {
            array[i] = obj.element;
            obj = obj.next;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) throw new NullPointerException();

        List<T> array = (List<T>) new ArrayList<>(this);

        return array.toArray(a);
    }

    @Override
    public boolean add(E e) {
        Node<E> node = new Node<>(e, null);

        if (isEmpty()) {
            head = tail = node;
            length = 1;
        } else {
            tail.next = node;
            tail = node;
            length++;
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) return false;

        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean found = true;

        for (Object obj : c) {
            if (! contains(obj)) {
                found = false;
                break;
            }
        }

        return found;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) return false;

        for (E element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()) return false;

        int count = index;

        for (E element : c) {
            add(count++, element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean found = false;

        Iterator<E> iterator = iterator();

        while (iterator.hasNext()) {
            E element = iterator.next();
            if (c.contains(element)) {
                found = true;
                iterator.remove();
            }
        }

        return found;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean notFound = false;

        Iterator<E> iterator = iterator();

        while (iterator.hasNext()) {
            E element = iterator.next();

            if (! c.contains(element)) {
                notFound = true;
                iterator.remove();
            }
        }

        return notFound;
    }

    @Override
    public void clear() {
        head = tail = null;
        length = 0;
    }

    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index, length);

        // Head
        if (index == 0) {
            E value = head.element;
            head.element = element;
            return value;
        }

        // Tail
        if (index == length - 1) {
            E value = tail.element;
            tail.element = element;
            return value;
        }

        Node<E> node = getNode(index);
        E value = node.element;
        node.element = element;

        return value;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index, length + 1);

        // Append to end of linked list
        if (index == length || isEmpty()) {
            add(element);
            return;
        }

        // Add in front of head
        if (index == 0) {
            head = new Node<>(element, head);
            length++;
            return;
        }

        // Add in the middle
        Node<E> previous = getNode(index - 1);
        Node<E> node = getNode(index);
        previous.next = new Node<>(element, node);
        length++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index, length);

        // Clear when linked list only has one node
        if (index == 0 && length == 1) {
            E element = head.element;
            clear();
            return element;
        }

        // Remove head
        if (index == 0) {
            E element = head.element;
            head = head.next;
            length--;
            return element;
        }

        // Remove tail
        if (index == length - 1) {
            E element = tail.element;
            Node<E> previous = getNode(length - 2);
            previous.next = null;
            tail = previous;
            length--;
            return element;
        }

        // Remove index that's in the middle
        Node<E> previous = getNode(index - 1);
        Node<E> node = getNode(index);
        E element = node.element;
        previous.next = node.next;
        length--;

        return element;
    }

    @Override
    public int indexOf(Object o) {
        return indexOfElement(o, true);
    }

    @Override
    public int lastIndexOf(Object o) {
        return indexOfElement(o, false);
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private Node<E> getNode(int index) {
        checkIndex(index, length);

        Node<E> node = head;
        int counter = 0;

        while (node != null) {
            if (counter == index) break;
            node = node.next;
            counter++;
        }

        return node;
    }

    private void checkIndex(int index, int size) {
        if (!((index >= 0) && (index < size))) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private int indexOfElement(Object o, boolean stop) {
        Node<E> node = head;
        int counter = 0;
        int foundIndex = -1;

        while (node != null) {
            if (node.element.equals(o)) {
                foundIndex = counter;
                if (stop) break;
            }
            node = node.next;
            counter++;
        }

        return foundIndex;
    }


    private static class Node<S> {
        S element;
        Node<S> next;

        public Node(S element, Node<S> next) {
            this.element = element;
            this.next = next;
        }
    }

    public class LinkedListIterator implements Iterator<E> {
        private Node<E> node;
        private Node<E> previous;
        private Node<E> previousPrevious;
        private boolean didNext;

        public LinkedListIterator() {
            node = head;
            previous = null;
            previousPrevious = null;
            didNext = false;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            E element = node.element;
            previousPrevious = previous;
            previous = node;
            node = node.next;
            didNext = true;
            return element;
        }

        @Override
        public void remove() {
            if (previous == null || (!didNext)) return;

            didNext = false;

            if (previous == head) {
                head = head.next;
                previous = null;
                length--;
                return;
            }

            if (previous == tail) {
                previousPrevious.next = null;
                tail = previousPrevious;
                node = null;
                length--;
                return;
            }

            previousPrevious.next = node;
            previous = previousPrevious;
            previousPrevious = null;
            length--;
        }
    }
}
