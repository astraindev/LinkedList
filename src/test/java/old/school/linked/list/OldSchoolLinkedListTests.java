package old.school.linked.list;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;

public class OldSchoolLinkedListTests {
    @Test
    void isEmpty() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        Assert.assertTrue(list.isEmpty());
        list.append(42);
        Assert.assertFalse(list.isEmpty());
        Assert.assertEquals(list.length(), 1);
        list.pop();
        Assert.assertTrue(list.isEmpty());
        list.append(42);
        list.delete(0);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    void length() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        Assert.assertEquals(list.length(), 0);
        list.append(1).append(2).append(3).append(4).append(5);
        Assert.assertEquals(list.length(), 5);
        list.delete(2);
        Assert.assertEquals(list.length(), 4);
        list.pop();
        list.pop();
        list.pop();
        list.pop();
        Assert.assertEquals(list.length(), 0);
    }

    @Test
    void get() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        Assert.assertNull(list.get());
        list.append(42);
        Assert.assertEquals(list.get().intValue(), 42);
        list.insert(3);
        Assert.assertEquals(list.get().intValue(), 3);
        list.gotoIndex(1);
        Assert.assertEquals(list.get().intValue(), 42);
        list.pop();
        list.pop();
        Assert.assertNull(list.get());
    }

    @Test
    void begin() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.append(1).append(2).append(3).append(4).append(5).begin();
        Assert.assertEquals(list.get().intValue(), 1);
    }

    @Test
    void gotoIndex() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.append(1).append(2).append(3).append(4).append(5).begin();
        Assert.assertEquals(list.gotoIndex(2).get().intValue(), 3);
        Assert.assertEquals(list.gotoIndex(1).get().intValue(), 2);
        Assert.assertEquals(list.gotoIndex(3).get().intValue(), 4);
        Assert.assertEquals(list.gotoIndex(0).get().intValue(), 1);
        Assert.assertEquals(list.gotoIndex(4).get().intValue(), 5);
    }

    @Test
    void getIndex() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.append(1).append(2).append(3).append(4).append(5);
        Assert.assertEquals(list.get(2).intValue(), 3);
        Assert.assertEquals(list.get(1).intValue(), 2);
        Assert.assertEquals(list.get(3).intValue(), 4);
        Assert.assertEquals(list.get(0).intValue(), 1);
        Assert.assertEquals(list.get(4).intValue(), 5);
    }

    @Test
    void insert() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.insert(1).insert(2).insert(3).insert(4).insert(5);
        Assert.assertEquals(list.get(2).intValue(), 3);
        Assert.assertEquals(list.get(1).intValue(), 4);
        Assert.assertEquals(list.get(3).intValue(), 2);
        Assert.assertEquals(list.get(0).intValue(), 5);
        Assert.assertEquals(list.get(4).intValue(), 1);
    }

    @Test
    void insertIndex() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.insert(1).insert(2).insert(4).insert(5);
        list.insert(2, 3);
        list.insert(4, 42);
        Assert.assertEquals(list.get(2).intValue(), 3);
        Assert.assertEquals(list.get(4).intValue(), 42);
        list.insert(0, 3);
        Assert.assertEquals(list.get(0).intValue(), 3);
        list.insert(list.length() - 1, 3);
        Assert.assertEquals(list.get(list.length() - 2).intValue(), 3);
    }

    @Test
    void append() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.append(1);
        Assert.assertEquals(list.get(0).intValue(), 1);
        list.append(2);
        Assert.assertEquals(list.get(1).intValue(), 2);
    }

    @Test
    void pop() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.append(1).append(2);
        Assert.assertEquals(list.pop().intValue(), 2);
        Assert.assertEquals(list.pop().intValue(), 1);
        Assert.assertNull(list.pop());
    }

    @Test
    void delete() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.insert(2).insert(1);
        Assert.assertEquals(list.delete().intValue(), 1);
        Assert.assertEquals(list.delete().intValue(), 2);
        Assert.assertNull(list.delete());
    }

    @Test
    void deleteIndex() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.insert(5).insert(4).insert(3).insert(2).insert(1);
        Assert.assertEquals(list.delete(2).intValue(), 3);
        Assert.assertEquals(list.delete(3).intValue(), 5);
        Assert.assertEquals(list.delete(1).intValue(), 2);
        Assert.assertEquals(list.delete(1).intValue(), 4);
        Assert.assertEquals(list.delete(0).intValue(), 1);
        Assert.assertNull(list.delete(0));
    }

    @Test
    void iterator() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.append(1).append(2).append(3).append(4).append(5);

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
    void nextElement() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.append(1).append(2).append(3).append(4).append(5).begin();
        Assert.assertEquals(list.get().intValue(), 1);
        Assert.assertEquals(list.nextElement().get().intValue(), 2);
        Assert.assertEquals(list.nextElement().get().intValue(), 3);
        Assert.assertEquals(list.nextElement().get().intValue(), 4);
        Assert.assertEquals(list.nextElement().get().intValue(), 5);
        Assert.assertNull(list.nextElement().get());
    }

    @Test
    void remove() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.append(1).append(2).append(3).append(4).append(5);

        Iterator<Integer> iterator = list.iterator();

        iterator.remove();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 1);
        iterator.remove();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 2);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 3);
        iterator.remove();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 4);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next().intValue(), 5);
        iterator.remove();
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(list.length(), 2);


    }

    @Test
    void indexOf() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.append(1).append(42).append(3).append(42).append(5).append(42).append(7);
        Assert.assertEquals(list.indexOf(42), 1);
        Assert.assertEquals(list.indexOf(72), -1);
    }

    @Test
    void lastIndexOf() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.append(1).append(42).append(3).append(42).append(5).append(42).append(7);
        Assert.assertEquals(list.lastIndexOf(42), 5);
        Assert.assertEquals(list.lastIndexOf(72), -1);
    }

    @Test
    void contains() {
        OldSchoolLinkedList<Integer> list = new OldSchoolLinkedList<>();
        list.append(1).append(42).append(3).append(42).append(5).append(42).append(7);
        Assert.assertTrue(list.contains(42));
        Assert.assertFalse(list.contains(72));
    }
}