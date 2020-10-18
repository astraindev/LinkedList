package old.school.linked.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Node Tests")
public class NodeTests {
    @Test
    void constructor() {
        Node<Integer> node = new Node<>(42);
        Assertions.assertEquals(42, node.getElement());
        Assertions.assertNull(node.getNextNode());
    }

    @Test
    void getElement() {
        Node<Integer> node = new Node<>(42);
        Assertions.assertEquals(42, node.getElement());
    }

    @Test
    void nextNode() {
        Node<Integer> node1 = new Node<>(42);
        Node<Integer> node2 = new Node<>(72);
        node1.setNextNode(node2);

        Assertions.assertEquals(node2, node1.getNextNode());
        Assertions.assertNull(node2.getNextNode());
    }

    @Test
    void next() {
        Node<Integer> node1 = new Node<>(42);
        Node<Integer> node2 = new Node<>(72);
        Node<Integer> node3 = new Node<>(52);
        node1.setNextNode(node2);
        node2.setNextNode(node3);
        Node<Integer> head = node1;

        Assertions.assertEquals(42, head.getElement());
        Assertions.assertTrue(head.hasNext());
        head = head.getNextNode();
        Assertions.assertEquals(72, head.getElement());
        Assertions.assertTrue(head.hasNext());
        head = head.getNextNode();
        Assertions.assertEquals(52, head.getElement());
        Assertions.assertFalse(head.hasNext());
    }

    @Test
    void equals() {
        Node<Integer> node1 = new Node<>(42);
        Node<Integer> node2 = new Node<>(72);
        Node<Integer> node3 = new Node<>(42);

        Assertions.assertNotSame(node1, node3);
        Assertions.assertEquals(node3, node1);
        Assertions.assertNotEquals(node2, node1);
    }
}
