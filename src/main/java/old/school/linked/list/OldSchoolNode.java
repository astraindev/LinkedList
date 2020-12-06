package old.school.linked.list;

import java.util.Iterator;
import java.util.Objects;

/**
 * Represents a node for a linked list. Nodes store a generic element and a pointer to the next node.
 *
 * @param <E> Any Object type
 */
public class OldSchoolNode<E> implements Iterator<OldSchoolNode<E>> {
    private E element;
    private OldSchoolNode<E> nextNode;

    private OldSchoolNode() { }

    /**
     * Constructor that stores the given element. The next node is set to null.
     *
     * @param element The element to store in the node
     */
    public OldSchoolNode(E element) {
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
    public void setNextNode(OldSchoolNode<E> nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * Gets the next node in the linked list
     *
     * @return the next node
     */
    public OldSchoolNode<E> getNextNode() {
        return nextNode;
    }

    @Override
    public boolean hasNext() {
        return nextNode != null;
    }

    @Override
    public OldSchoolNode<E> next() {
        return nextNode;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof OldSchoolNode) {
            return element.equals(((OldSchoolNode<?>) object).element);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(element);
    }
}