package old.school.linked.list;

import java.util.Iterator;

public class Node<T> implements Iterator<Node<T>> {
    private final T item;
    private Node<T> nextNode;

    public Node(T item) {
        this.item = item;
        this.nextNode = null;
    }

    public T getItem() {
        return item;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    @Override
    public boolean hasNext() {
        return nextNode != null;
    }

    @Override
    public Node<T> next() {
        return nextNode;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Node) {
            return item.equals(((Node<?>) object).item);
        }
        return false;
    }
}
