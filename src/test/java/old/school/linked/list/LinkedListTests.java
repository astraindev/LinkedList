package old.school.linked.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class LinkedListTests {
    LinkedList<Integer> list;

    @BeforeEach
    void beforeEach() {
        list = new LinkedList<>();
    }

    @Test
    void size() {
        Assertions.assertEquals(0, list.size());
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Assertions.assertEquals(5, list.size());
        list.remove(2);
        Assertions.assertEquals(4, list.size());
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void isEmpty() {
        Assertions.assertTrue(list.isEmpty());
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Assertions.assertFalse(list.isEmpty());
        list.remove(2);
        Assertions.assertFalse(list.isEmpty());
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void contains() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Assertions.assertTrue(list.contains(2));
        Assertions.assertTrue(list.contains(4));

        Assertions.assertFalse(list.contains(42));
    }

    @Test
    void iterator() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

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
    void iteratorRemove() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // Head
        Iterator<Integer> iterator = list.iterator();
        Assertions.assertTrue(iterator.hasNext());
        iterator.next();
        iterator.remove();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(2, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(3, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(4, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(5, iterator.next());
        Assertions.assertFalse(iterator.hasNext());

        list.add(0, 1);

        // Tail
        iterator = list.iterator();
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
        iterator.remove();
        Assertions.assertFalse(iterator.hasNext());
        Assertions.assertEquals(4, list.get(list.size() - 1));

        list.add(5);

        // Middle
        iterator = list.iterator();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(1, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(2, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(3, iterator.next());
        iterator.remove();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(4, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(5, iterator.next());
        Assertions.assertFalse(iterator.hasNext());
        Assertions.assertEquals(4, list.get(2));
        Assertions.assertEquals(5, list.get(list.size() - 1));

        list.add(2, 3);

        // Illegal removes
        iterator = list.iterator();
        Assertions.assertTrue(iterator.hasNext());
        iterator.remove();
        Assertions.assertEquals(1, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(2, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(3, iterator.next());
        iterator.remove();
        iterator.remove();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(4, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(5, iterator.next());
        Assertions.assertFalse(iterator.hasNext());
        Assertions.assertEquals(4, list.get(2));
        Assertions.assertEquals(5, list.get(list.size() - 1));

        list.add(2, 3);

        // Double middle removes
        iterator = list.iterator();
        Assertions.assertTrue(iterator.hasNext());
        iterator.remove();
        Assertions.assertEquals(1, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(2, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(3, iterator.next());
        iterator.remove();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(4, iterator.next());
        iterator.remove();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(5, iterator.next());
        Assertions.assertFalse(iterator.hasNext());
        Assertions.assertEquals(5, list.get(2));
        Assertions.assertEquals(5, list.get(list.size() - 1));

        Assertions.assertDoesNotThrow(() -> {
            // Make sure the double removal didn't screw up the iterator
            for (Integer value : list) {
                Assertions.assertNotNull(value);    // This list has no null values added - just an always true test
                                                    // for during the looping.
            }
        });
    }

    @Test
    void toArray() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Object[] array = list.toArray();
        Assertions.assertEquals("1", array[0].toString());
        Assertions.assertEquals("2", array[1].toString());
        Assertions.assertEquals("3", array[2].toString());
        Assertions.assertEquals("4", array[3].toString());
        Assertions.assertEquals("5", array[4].toString());
    }

    @Test
    void genericsToArray() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Integer[] array = list.toArray(list.toArray(new Integer[0]));
        Assertions.assertEquals(1, array[0]);
        Assertions.assertEquals(2, array[1]);
        Assertions.assertEquals(3, array[2]);
        Assertions.assertEquals(4, array[3]);
        Assertions.assertEquals(5, array[4]);
    }

    @Test
    void add() {
        Assertions.assertTrue(list.add(1));
        Assertions.assertTrue(list.add(2));
        Assertions.assertTrue(list.add(3));
        Assertions.assertTrue(list.add(4));
        Assertions.assertTrue(list.add(5));
    }

    @Test
    void remove() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Assertions.assertEquals(5, list.size());

        Assertions.assertEquals(3, list.remove(2));
        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals(2, list.remove(1));
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals(5, list.remove(2));
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(1, list.remove(0));
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(4, list.remove(0));
        Assertions.assertEquals(0, list.size());

        // Tail test
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Assertions.assertEquals(5, list.size());

        list.remove(list.size() - 1);   // 5
        list.remove(list.size() - 1);   // 4
        Assertions.assertEquals(3, list.remove(list.size() - 1));
        list.remove(list.size() - 1);   // 2
        list.remove(list.size() - 1);   // 1
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void containsAll() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        List<Integer> array = new ArrayList<>();
        array.add(2);
        array.add(4);

        Assertions.assertTrue(list.containsAll(array));

        array.add(42);

        Assertions.assertFalse(list.containsAll(array));
    }

    @Test
    void addAll() {
        List<Integer> array = new ArrayList<>();
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);

        list.add(1);
        list.addAll(array);

        Assertions.assertEquals(1, list.get(0));
        Assertions.assertEquals(2, list.get(1));
        Assertions.assertEquals(3, list.get(2));
        Assertions.assertEquals(4, list.get(3));
        Assertions.assertEquals(5, list.get(4));
    }

    @Test
    void indexAddAll() {
        // isEmpty
        List<Integer> array = new ArrayList<>();
        array.add(42);
        array.add(43);
        array.add(44);

        // Is Empty
        list.addAll(0, array);

        Assertions.assertEquals(42, list.get(0));
        Assertions.assertEquals(43, list.get(1));
        Assertions.assertEquals(44, list.get(2));

        // Head
        list.clear();
        list.add(1);
        list.addAll(0, array);

        Assertions.assertEquals(42, list.get(0));
        Assertions.assertEquals(43, list.get(1));
        Assertions.assertEquals(44, list.get(2));
        Assertions.assertEquals( 1, list.get(3));

        // Tail
        list.clear();
        list.add(1);
        list.addAll(list.size(), array);

        Assertions.assertEquals( 1, list.get(0));
        Assertions.assertEquals(42, list.get(1));
        Assertions.assertEquals(43, list.get(2));
        Assertions.assertEquals(44, list.get(3));

        // Middle
        list.clear();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        list.addAll(2, array);

        Assertions.assertEquals( 1, list.get(0));
        Assertions.assertEquals( 2, list.get(1));
        Assertions.assertEquals(42, list.get(2));
        Assertions.assertEquals(43, list.get(3));
        Assertions.assertEquals(44, list.get(4));
        Assertions.assertEquals( 4, list.get(5));
        Assertions.assertEquals( 5, list.get(6));

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.addAll(42, array));
    }

    @Test
    void removeAll() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        List<Integer> array = new ArrayList<>();
        array.add(2);
        array.add(4);

        Assertions.assertTrue(list.removeAll(array));
        Assertions.assertEquals(1, list.get(0));
        Assertions.assertEquals(3, list.get(1));
        Assertions.assertEquals(5, list.get(2));
    }

    @Test
    void retainAll() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        List<Integer> array = new ArrayList<>();
        array.add(2);
        array.add(4);

        Assertions.assertTrue(list.retainAll(array));
        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(4, list.get(1));
    }

    @Test
    void clear() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Assertions.assertEquals(5, list.size());

        list.clear();
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void get() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Assertions.assertEquals(1, list.get(0));
        Assertions.assertEquals(2, list.get(1));
        Assertions.assertEquals(3, list.get(2));
        Assertions.assertEquals(4, list.get(3));
        Assertions.assertEquals(5, list.get(4));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.get(42));
    }

    @Test
    void set() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.set(0, 0));
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.set(0, 42);    // Head
        list.set(2, 43);    // Middle
        list.set(4, 44);    // Tail

        Assertions.assertEquals(42, list.get(0));
        Assertions.assertEquals(2, list.get(1));
        Assertions.assertEquals(43, list.get(2));
        Assertions.assertEquals(4, list.get(3));
        Assertions.assertEquals(44, list.get(4));
    }

    @Test
    void indexAdd() {
        list.add(0, 2);     // head and isEmpty
        list.add(0, 1);     // head
        list.add(2, 5);     // tail
        list.add(2, 4);     // middle
        list.add(2, 3);     // middle

        Assertions.assertEquals(1, list.get(0));
        Assertions.assertEquals(2, list.get(1));
        Assertions.assertEquals(3, list.get(2));
        Assertions.assertEquals(4, list.get(3));
        Assertions.assertEquals(5, list.get(4));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.add(42, 42));
    }

    @Test
    void removeObject() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Assertions.assertTrue(list.remove(new Integer(3)));
        Assertions.assertEquals(1, list.get(0));
        Assertions.assertEquals(2, list.get(1));
        Assertions.assertEquals(4, list.get(2));
        Assertions.assertEquals(5, list.get(3));
    }

    @Test
    void indexOf() {
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(3);
        list.add(4);

        Assertions.assertEquals(1, list.indexOf(3));
    }

    @Test
    void lastIndexOf() {
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(3);
        list.add(4);

        Assertions.assertEquals(3, list.lastIndexOf(3));
    }

    @Test
    void listIterator() {
        Assertions.assertThrows(UnsupportedOperationException.class, list::listIterator);
    }

    @Test
    void indexListIterator() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> list.listIterator(0));
    }

    @Test
    void subList() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> list.subList(0, 0));
    }
}