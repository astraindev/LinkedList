package old.school.linked.list;

import java.util.Iterator;

/**
 * Represents a node for a linked list. Nodes store a generic element and a pointer to the next node.
 *
 * @param <E> Any Object type
 */
public class Node<E> implements Iterator<Node<E>> {
    private E element;
    private Node<E> nextNode;

    private Node() { }

    /**
     * Constructor that stores the given element. The next node is set to null.
     *
     * @param element The element to store in the node
     */
    public Node(E element) {
        this.element = element;
        this.nextNode = null;
    }

    /**
     * Returns the stored element
     *
     * @return the generic element that was stored in the constructor
     */
    public E getElement() {
        return element;
    }

    /**
     * Set the next node in the linked list
     *
     * @param nextNode the next node
     */
    public void setNextNode(Node<E> nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * Gets the next node in the linked list
     *
     * @return the next node
     */
    public Node<E> getNextNode() {
        return nextNode;
    }

    @Override
    public boolean hasNext() {
        return nextNode != null;
    }

    @Override
    public Node<E> next() {
        return nextNode;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Node) {
            return element.equals(((Node<?>) object).element);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 65537;
        hash = 32771 * hash + element.toString().length();
        hash = 32771 * hash + element.hashCode();
        return hash;
    }
}
