package old.school.linked.list;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListTests {
    LinkedList<Integer> list;

    @BeforeMethod
    void beforeMethod() {
        list = new LinkedList<>();
    }

    @Test
    void size() {
        Assert.assertEquals(list.size(), 0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Assert.assertEquals(list.size(), 5);
        list.remove(2);
        Assert.assertEquals(list.size(), 4);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        Assert.assertEquals(list.size(), 0);
    }

    @Test
    void isEmpty() {
        Assert.assertTrue(list.isEmpty());
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Assert.assertFalse(list.isEmpty());
        list.remove(2);
        Assert.assertFalse(list.isEmpty());
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    void contains() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Assert.assertTrue(list.contains(2));
        Assert.assertTrue(list.contains(4));

        Assert.assertFalse(list.contains(42));
    }

    @Test
    void iterator() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Iterator<Integer> iterator = list.iterator();

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 1);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 2);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 3);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 4);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 5);
        Assert.assertFalse(iterator.hasNext());
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
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        iterator.remove();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 2);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 3);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 4);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 5);
        Assert.assertFalse(iterator.hasNext());

        list.add(0, 1);

        // Tail
        iterator = list.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 1);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 2);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 3);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 4);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 5);
        iterator.remove();
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(list.get(list.size() - 1).intValue(), 4);

        list.add(5);

        // Middle
        iterator = list.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 1);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 2);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 3);
        iterator.remove();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 4);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 5);
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(list.get(2).intValue(), 4);
        Assert.assertEquals(list.get(list.size() - 1).intValue(), 5);

        list.add(2, 3);

        // Illegal removes
        iterator = list.iterator();
        Assert.assertTrue(iterator.hasNext());
        iterator.remove();
        Assert.assertEquals(iterator.next().intValue(), 1);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 2);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 3);
        iterator.remove();
        iterator.remove();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 4);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 5);
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(list.get(2).intValue(), 4);
        Assert.assertEquals(list.get(list.size() - 1).intValue(), 5);

        list.add(2, 3);

        // Double middle removes
        iterator = list.iterator();
        Assert.assertTrue(iterator.hasNext());
        iterator.remove();
        Assert.assertEquals(iterator.next().intValue(), 1);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 2);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 3);
        iterator.remove();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 4);
        iterator.remove();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 5);
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(list.get(2).intValue(), 5);
        Assert.assertEquals(list.get(list.size() - 1).intValue(), 5);

        try {
            // Make sure the double removal didn't screw up the iterator
            for (Integer value : list) {
                Assert.assertNotNull(value);    // This list has no null values added - just an always true test
                                                // for during the looping.
            }
        } catch (Exception ex) {
            Assert.fail(ex.getMessage(), ex);
        }
    }

    @Test
    void toArray() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Object[] array = list.toArray();
        Assert.assertEquals(array[0].toString(), "1");
        Assert.assertEquals(array[1].toString(), "2");
        Assert.assertEquals(array[2].toString(), "3");
        Assert.assertEquals(array[3].toString(), "4");
        Assert.assertEquals(array[4].toString(), "5");
    }

    @Test
    void genericsToArray() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Integer[] array = list.toArray(list.toArray(new Integer[0]));
        Assert.assertEquals(array[0].intValue(), 1);
        Assert.assertEquals(array[1].intValue(), 2);
        Assert.assertEquals(array[2].intValue(), 3);
        Assert.assertEquals(array[3].intValue(), 4);
        Assert.assertEquals(array[4].intValue(), 5);
    }

    @Test
    void add() {
        Assert.assertTrue(list.add(1));
        Assert.assertTrue(list.add(2));
        Assert.assertTrue(list.add(3));
        Assert.assertTrue(list.add(4));
        Assert.assertTrue(list.add(5));
    }

    @Test
    void remove() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Assert.assertEquals(list.size(), 5);

        Assert.assertEquals(list.remove(2).intValue(), 3);
        Assert.assertEquals(list.size(), 4);
        Assert.assertEquals(list.remove(1).intValue(), 2);
        Assert.assertEquals(list.size(), 3);
        Assert.assertEquals(list.remove(2).intValue(), 5);
        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list.remove(0).intValue(), 1);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.remove(0).intValue(), 4);
        Assert.assertEquals(list.size(), 0);

        // Tail test
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Assert.assertEquals(list.size(), 5);

        list.remove(list.size() - 1);   // 5
        list.remove(list.size() - 1);   // 4
        Assert.assertEquals(list.remove(list.size() - 1).intValue(), 3);
        list.remove(list.size() - 1);   // 2
        list.remove(list.size() - 1);   // 1
        Assert.assertTrue(list.isEmpty());
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

        Assert.assertTrue(list.containsAll(array));

        array.add(42);

        Assert.assertFalse(list.containsAll(array));
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

        Assert.assertEquals(list.get(0).intValue(), 1);
        Assert.assertEquals(list.get(1).intValue(), 2);
        Assert.assertEquals(list.get(2).intValue(), 3);
        Assert.assertEquals(list.get(3).intValue(), 4);
        Assert.assertEquals(list.get(4).intValue(), 5);
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

        Assert.assertEquals(list.get(0).intValue(), 42);
        Assert.assertEquals(list.get(1).intValue(), 43);
        Assert.assertEquals(list.get(2).intValue(), 44);

        // Head
        list.clear();
        list.add(1);
        list.addAll(0, array);

        Assert.assertEquals(list.get(0).intValue(), 42);
        Assert.assertEquals(list.get(1).intValue(), 43);
        Assert.assertEquals(list.get(2).intValue(), 44);
        Assert.assertEquals(list.get(3).intValue(), 1);

        // Tail
        list.clear();
        list.add(1);
        list.addAll(list.size(), array);

        Assert.assertEquals(list.get(0).intValue(), 1);
        Assert.assertEquals(list.get(1).intValue(), 42);
        Assert.assertEquals(list.get(2).intValue(), 43);
        Assert.assertEquals(list.get(3).intValue(), 44);

        // Middle
        list.clear();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        list.addAll(2, array);

        Assert.assertEquals(list.get(0).intValue(), 1);
        Assert.assertEquals(list.get(1).intValue(), 2);
        Assert.assertEquals(list.get(2).intValue(), 42);
        Assert.assertEquals(list.get(3).intValue(), 43);
        Assert.assertEquals(list.get(4).intValue(), 44);
        Assert.assertEquals( list.get(5).intValue(), 4);
        Assert.assertEquals( list.get(6).intValue(), 5);

        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.addAll(42, array));
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

        Assert.assertTrue(list.removeAll(array));
        Assert.assertEquals(list.get(0).intValue(), 1);
        Assert.assertEquals(list.get(1).intValue(), 3);
        Assert.assertEquals(list.get(2).intValue(), 5);
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

        Assert.assertTrue(list.retainAll(array));
        Assert.assertEquals(list.get(0).intValue(), 2);
        Assert.assertEquals(list.get(1).intValue(), 4);
    }

    @Test
    void clear() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Assert.assertEquals(list.size(), 5);

        list.clear();
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    void get() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Assert.assertEquals(list.get(0).intValue(), 1);
        Assert.assertEquals(list.get(1).intValue(), 2);
        Assert.assertEquals(list.get(2).intValue(), 3);
        Assert.assertEquals(list.get(3).intValue(), 4);
        Assert.assertEquals(list.get(4).intValue(), 5);
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.get(42));
    }

    @Test
    void set() {
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.set(0, 0));
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.set(0, 42);    // Head
        list.set(2, 43);    // Middle
        list.set(4, 44);    // Tail

        Assert.assertEquals(list.get(0).intValue(), 42);
        Assert.assertEquals(list.get(1).intValue(), 2);
        Assert.assertEquals(list.get(2).intValue(), 43);
        Assert.assertEquals(list.get(3).intValue(), 4);
        Assert.assertEquals(list.get(4).intValue(), 44);
    }

    @Test
    void indexAdd() {
        list.add(0, 2);     // head and isEmpty
        list.add(0, 1);     // head
        list.add(2, 5);     // tail
        list.add(2, 4);     // middle
        list.add(2, 3);     // middle

        Assert.assertEquals(list.get(0).intValue(), 1);
        Assert.assertEquals(list.get(1).intValue(), 2);
        Assert.assertEquals(list.get(2).intValue(), 3);
        Assert.assertEquals(list.get(3).intValue(), 4);
        Assert.assertEquals(list.get(4).intValue(), 5);
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.add(42, 42));
    }

    @Test
    void removeObject() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Assert.assertTrue(list.remove(new Integer(3)));
        Assert.assertEquals(list.get(0).intValue(), 1);
        Assert.assertEquals(list.get(1).intValue(), 2);
        Assert.assertEquals(list.get(2).intValue(), 4);
        Assert.assertEquals(list.get(3).intValue(), 5);
    }

    @Test
    void indexOf() {
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(3);
        list.add(4);

        Assert.assertEquals(list.indexOf(3), 1);
    }

    @Test
    void lastIndexOf() {
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(3);
        list.add(4);

        Assert.assertEquals(list.lastIndexOf(3), 3);
    }

    @Test
    void listIterator() {
        Assert.assertThrows(UnsupportedOperationException.class, list::listIterator);
    }

    @Test
    void indexListIterator() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> list.listIterator(0));
    }

    @Test
    void subList() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> list.subList(0, 0));
    }
}