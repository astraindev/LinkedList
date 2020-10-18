package old.school.linked.list;

import java.util.Iterator;

/**
 * This is an old-school linked list. Pretty much straight out of the
 * textbooks save the method names. The linked list is single meaning you can
 * only go forward, not backward. This means that many methods (like append
 * and get) take O(n) time.
 *
 * The main element storage methods are: insert, delete, append, and pop. The
 * delete method is traditionally named remove. However, I'm using an Iterator
 * which means the remove method is taken. The insert/delete use indexes
 * whereas the append/pop methods are only at the end of the linked list.
 *
 * Methods for traversing the linked list include: begin, get, nextElement,
 * and gotoIndex. Of course, the iterator can be used as well.
 *
 * @param <E> the type of element stored in the nodes of the linked list.
 */
public class LinkedList<E> implements Iterator<E>, Iterable<E> {
    private Node<E> head;
    private Node<E> current;
    private int size;
    private int iteratorIndex;
    private boolean didNext;

    /**
     * Creates an empty linked list.
     */
    public LinkedList() {
        this.head = null;
        this.current = null;
        this.size = 0;
    }

    /**
     * Determines if there are elements in the list or not. A newly contructed linked list is always empty.
     *
     * @return true if there are no elements in the linked list, otherwise
     *         false
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * The length of the linked list.
     *
     * @return the number of elements in the linked list
     */
    public int length() {
        return size;
    }

    /**
     * Gets the element at the current position. See the begin, gotoIndex, and
     * nextElement methods.
     *
     * @return the current element
     *
     */
    public E get() {
        return current == null ? null : current.getElement();
    }

    /**
     * Rewinds back to the beginning (head) of the linked list.
     */
    public void begin() {
        current = head;
    }

    /**
     * Move forward to the next element in the linked list.
     *
     * @return a pointer back to this linked list
     */
    public LinkedList<E> nextElement() {
        if (current != null) {
            current = current.next();
        }
        return this;
    }

    /**
     * Moves to the element at the given index.
     *
     * @param index the index of the element in the linked list
     * @return a pointer back to this linked list
     */
    public LinkedList<E> gotoIndex(int index) {
        checkIndex(index);
        begin();

        for (int i = 0; i < size; i ++) {
            if (i == index) break;
            current = current.next();
        }

        return this;
    }

    /**
     * Goes to the index and gets that element
     *
     * @param index index of the element
     * @return the element at the given index
     */
    public E get(int index) {
        return gotoIndex(index).get();
    }

    /**
     * Inserts the element at the beginning (head) of the linked list.
     *
     * @param element the element to insert
     * @return a reference back to this linked list
     */
    public LinkedList<E> insert(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.setNextNode(head);
        head = newNode;
        current = head;
        size++;
        return this;
    }

    /**
     * Inserts the element at the given index of the linked list.
     *
     * @param index where to store the element
     * @param element the element to insert
     */
    public void insert(int index, E element) {
        if (!(index > 0)) {
            insert(element);
            return;
        }

        gotoIndex(index - 1).insertNode(element, false);
    }

    /**
     * Appends the element ot the end of the linked list
     *
     * @param element the element to be appended
     * @return a reference back to this linked list
     */
    public LinkedList<E> append(E element) {
        if (isEmpty()) return insert(element);

        gotoIndex(size - 1).insertNode(element, true);
        return this;
    }

    /**
     * Removes and returns the last element from the linked list.
     *
     * @return the last element on the linked list.
     */
    public E pop() {
        if (isEmpty()) return null;

        if (!(size > 1)) {
            E element = head.getElement();
            head = null;
            current = null;
            size = 0;
            return element;
        }

        gotoIndex(size - 2);
        Node<E> previous = current;
        gotoIndex(size - 1);
        previous.setNextNode(null);
        E element = current.getElement();
        current = previous;
        size--;
        return element;
    }

    /**
     * Deletes and returns the first element on the linked list
     *
     * @return the first element
     */
    public E delete() {
        if (isEmpty()) return null;

        if (size == 1) return pop();

        E element = head.getElement();
        head = head.getNextNode();
        current = head;
        size--;
        return element;
    }

    /**
     * Deletes and returns the element that is at the given index.
     *
     * @param index the index where the element is located
     * @return the element at the given index
     */
    public E delete(int index) {
        if (isEmpty() || !(index > 0)) return delete();

        gotoIndex(index - 1);
        Node<E> previous = current;
        gotoIndex(index);
        E element = current.getElement();
        previous.setNextNode(current.getNextNode());
        current = previous;
        size--;
        return element;
    }

    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element.
     *
     * @param element element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int indexOf(E element) {
        return indexOfElement(element, false);
    }

    /**
     * Returns the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element.
     *
     * @param element element to search for
     * @return the index of the last occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int lastIndexOf(E element) {
        return indexOfElement(element, true);
    }

    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    /* BEGIN Iterator */

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
        return iteratorIndex < size;
    }

    @Override
    public E next() {
        if (hasNext()) {
            didNext = true;
            return this.get(iteratorIndex++);
        }

        return null;
    }

    /* END Iterator */

    @Override
    public int hashCode() {
        int hash = 1033;
        hash = 5039 * hash + size;
        hash = 5039 * hash + (head == null ? 0 : head.hashCode());
        hash = 5039 * hash + super.hashCode();

        return hash;
    }

    /**
     * Convenience method to throw the ArrayIndexOutOfBoundsException.
     *
     * @param index to check
     * @throws ArrayIndexOutOfBoundsException when out of bounds
     */
    private void checkIndex(int index) throws ArrayIndexOutOfBoundsException {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException("Linked list is empty");
        if ((!(index < size)) || index < 0) throw new ArrayIndexOutOfBoundsException("size: " + size + ", index: " + index);
    }

    /**
     * Convenience method that inserts the element before (insert) or
     * after (append). The current parameter is assumed to be set
     * properly.
     *
     * @param element the element to insert
     * @param after true to append, otherwise false to insert
     */
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

    /**
     * Convenience method that finds the index of the given element. If goOn
     * is true, then return the last index, otherwise return the first index.
     *
     * @param element the element to match
     * @param goOn if true, match last element, otherwise match first element
     * @return index of element, otherwise -1
     */
    private int indexOfElement(E element, boolean goOn ) {
        int found = -1;
        int index = 0;

        for (E item : this) {
            if (item.equals(element)) {
                found = index;

                if (! goOn) break;
            }

            index++;
        }

        return found;
    }
}
