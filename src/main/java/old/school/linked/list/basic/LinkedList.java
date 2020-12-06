package old.school.linked.list;

public class SimpleLinkedList<E> {
    private Node<E> head;

    public SimpleLinkedList() {
        head = null;
    }

    public SimpleLinkedList(Node<E> head) {
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

    public void insertAfter(Node<E> node, Node<E> newNode) {
        newNode.setNext(node.getNext());
        node.setNext(newNode);
    }

    public void insert(Node<E> newNode) {
        newNode.setNext(head);
        head = newNode;
    }

    public void removeAfter(Node<E> node) {
        node.setNext(node.getNext().getNext());
    }

    public void remove() {
        head = head.getNext();
    }
}
