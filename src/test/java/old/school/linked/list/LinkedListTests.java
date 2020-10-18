package old.school.linked.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

@DisplayName("Linked List Tests")
public class LinkedListTests {
    @Test
    void isEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        Assertions.assertTrue(list.isEmpty());
        list.append(42);
        Assertions.assertFalse(list.isEmpty());
        Assertions.assertEquals(1, list.length());
        list.pop();
        Assertions.assertTrue(list.isEmpty());
        list.append(42);
        list.delete(0);
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void length() {
        LinkedList<Integer> list = new LinkedList<>();
        Assertions.assertEquals(0, list.length());
        list.append(1).append(2).append(3).append(4).append(5);
        Assertions.assertEquals(5, list.length());
        list.delete(2);
        Assertions.assertEquals(4, list.length());
        list.pop();
        list.pop();
        list.pop();
        list.pop();
        Assertions.assertEquals(0, list.length());
    }

    @Test
    void get() {
        LinkedList<Integer> list = new LinkedList<>();
        Assertions.assertNull(list.get());
        list.append(42);
        Assertions.assertEquals(42, list.get());
        list.insert(3);
        Assertions.assertEquals(3, list.get());
        list.gotoIndex(1);
        Assertions.assertEquals(42, list.get());
        list.pop();
        list.pop();
        Assertions.assertNull(list.get());
    }

    @Test
    void begin() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1).append(2).append(3).append(4).append(5).begin();
        Assertions.assertEquals(1, list.get());
    }

    @Test
    void gotoIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1).append(2).append(3).append(4).append(5).begin();
        Assertions.assertEquals(3, list.gotoIndex(2).get());
        Assertions.assertEquals(2, list.gotoIndex(1).get());
        Assertions.assertEquals(4, list.gotoIndex(3).get());
        Assertions.assertEquals(1, list.gotoIndex(0).get());
        Assertions.assertEquals(5, list.gotoIndex(4).get());
    }

    @Test
    void getIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1).append(2).append(3).append(4).append(5);
        Assertions.assertEquals(3, list.get(2));
        Assertions.assertEquals(2, list.get(1));
        Assertions.assertEquals(4, list.get(3));
        Assertions.assertEquals(1, list.get(0));
        Assertions.assertEquals(5, list.get(4));
    }

    @Test
    void insert() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(1).insert(2).insert(3).insert(4).insert(5);
        Assertions.assertEquals(3, list.get(2));
        Assertions.assertEquals(4, list.get(1));
        Assertions.assertEquals(2, list.get(3));
        Assertions.assertEquals(5, list.get(0));
        Assertions.assertEquals(1, list.get(4));
    }

    @Test
    void insertIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(1).insert(2).insert(4).insert(5);
        list.insert(2, 3);
        list.insert(4, 42);
        Assertions.assertEquals(3, list.get(2));
        Assertions.assertEquals(42, list.get(4));
        list.insert(0, 3);
        Assertions.assertEquals(3, list.get(0));
        list.insert(list.length() - 1, 3);
        Assertions.assertEquals(3, list.get(list.length() - 2));
    }

    @Test
    void append() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        Assertions.assertEquals(1, list.get(0));
        list.append(2);
        Assertions.assertEquals(2, list.get(1));
    }

    @Test
    void pop() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1).append(2);
        Assertions.assertEquals(2, list.pop());
        Assertions.assertEquals(1, list.pop());
        Assertions.assertNull(list.pop());
    }

    @Test
    void delete() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(2).insert(1);
        Assertions.assertEquals(1, list.delete());
        Assertions.assertEquals(2, list.delete());
        Assertions.assertNull(list.delete());
    }

    @Test
    void deleteIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(5).insert(4).insert(3).insert(2).insert(1);
        Assertions.assertEquals(3, list.delete(2));
        Assertions.assertEquals(5, list.delete(3));
        Assertions.assertEquals(2, list.delete(1));
        Assertions.assertEquals(4, list.delete(1));
        Assertions.assertEquals(1, list.delete(0));
        Assertions.assertNull(list.delete(0));
    }

    @Test
    void iterator() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1).append(2).append(3).append(4).append(5);

        Iterator<Integer> iterator = list.iterator();

        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(1, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(2, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(3, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(4, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(5, iterator.next());
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    void nextElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1).append(2).append(3).append(4).append(5).begin();
        Assertions.assertEquals(1, list.get());
        Assertions.assertEquals(2, list.nextElement().get());
        Assertions.assertEquals(3, list.nextElement().get());
        Assertions.assertEquals(4, list.nextElement().get());
        Assertions.assertEquals(5, list.nextElement().get());
        Assertions.assertNull(list.nextElement().get());
    }

    @Test
    void remove() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1).append(2).append(3).append(4).append(5);

        Iterator<Integer> iterator = list.iterator();

        iterator.remove();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(1, iterator.next());
        iterator.remove();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(2, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(3, iterator.next());
        iterator.remove();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(4, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(5, iterator.next());
        iterator.remove();
        Assertions.assertFalse(iterator.hasNext());
        Assertions.assertEquals(2, list.length());


    }

    @Test
    void indexOf() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1).append(42).append(3).append(42).append(5).append(42).append(7);
        Assertions.assertEquals(1, list.indexOf(42));
        Assertions.assertEquals(-1, list.indexOf(72));
    }

    @Test
    void lastIndexOf() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1).append(42).append(3).append(42).append(5).append(42).append(7);
        Assertions.assertEquals(5, list.lastIndexOf(42));
        Assertions.assertEquals(-1, list.lastIndexOf(72));
    }

    @Test
    void contains() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1).append(42).append(3).append(42).append(5).append(42).append(7);
        Assertions.assertTrue(list.contains(42));
        Assertions.assertFalse(list.contains(72));
    }
}
