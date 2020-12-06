package old.school.linked.list;


import org.testng.Assert;
import org.testng.annotations.Test;

public class OldSchoolNodeTests {
    @Test
    void constructor() {
        OldSchoolNode<Integer> node = new OldSchoolNode<>(42);
        Assert.assertEquals(node.getElement(), new Integer(42));
        Assert.assertNull(node.getNextNode());
    }

    @Test
    void getElement() {
        OldSchoolNode<Integer> node = new OldSchoolNode<>(42);
        Assert.assertEquals(node.getElement(), new Integer(42));
    }

    @Test
    void nextNode() {
        OldSchoolNode<Integer> node1 = new OldSchoolNode<>(42);
        OldSchoolNode<Integer> node2 = new OldSchoolNode<>(72);
        node1.setNextNode(node2);

        Assert.assertEquals(node1.getNextNode(), node2);
        Assert.assertNull(node2.getNextNode());
    }

    @Test
    void next() {
        OldSchoolNode<Integer> node1 = new OldSchoolNode<>(42);
        OldSchoolNode<Integer> node2 = new OldSchoolNode<>(72);
        OldSchoolNode<Integer> node3 = new OldSchoolNode<>(52);
        node1.setNextNode(node2);
        node2.setNextNode(node3);
        OldSchoolNode<Integer> head = node1;

        Assert.assertEquals(head.getElement(), new Integer(42));
        Assert.assertTrue(head.hasNext());
        head = head.getNextNode();
        Assert.assertEquals(head.getElement(), new Integer(72));
        Assert.assertTrue(head.hasNext());
        head = head.getNextNode();
        Assert.assertEquals(head.getElement(), new Integer(52));
        Assert.assertFalse(head.hasNext());
    }

    @Test
    void equals() {
        OldSchoolNode<Integer> node1 = new OldSchoolNode<>(42);
        OldSchoolNode<Integer> node2 = new OldSchoolNode<>(72);
        OldSchoolNode<Integer> node3 = new OldSchoolNode<>(42);

        Assert.assertNotSame(node1, node3);
        Assert.assertEquals(node1, node3);
        Assert.assertNotEquals(node1, node2);
    }
}