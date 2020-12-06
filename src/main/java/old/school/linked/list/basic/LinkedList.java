package old.school.linked.list.basic;

public class LinkedList<E> {
    private Node<E> head;

    public LinkedList() {
        head = null;
    }

    public LinkedList(Node<E> head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    /**
     * Inserts the new node after the given, reference node.
     * @param node the reference node
     * @param newNode the node to be inserted
     */
    public void insertAfter(Node<E> node, Node<E> newNode) {
        newNode.setNext(node.getNext());
        node.setNext(newNode);
    }

    /**
     * Inserts the given node at the very beginning of the linked list.
     *
     * @param newNode the node to be placed as the head of the list
     */
    public void insert(Node<E> newNode) {
        newNode.setNext(head);
        head = newNode;
    }

    /**
     * Removes the node after the given, reference node.
     *
     * @param node the reference node
     */
    public void removeAfter(Node<E> node) {
        node.setNext(node.getNext().getNext());
    }

    public void remove() {
        head = head.getNext();
    }
}
