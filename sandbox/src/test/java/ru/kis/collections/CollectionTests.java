// Lesson_04
package ru.kis.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionTests {
    @Test
    void arrayTest() {
        var arr = new String[]{"a", "b", "c"};
        Assertions.assertEquals("a", arr[0]);
    }

    @Test
    void listTest() {
        var lst = new ArrayList<>(List.of("a", "b", "c"));
        Assertions.assertEquals("a", lst.get(0));
        lst.set(0, "d");
        Assertions.assertEquals("d", lst.get(0));

    }

    @Test
    void setTest() {
        var set = new HashSet<>(Set.of("a", "b", "c"));
        //var set = Set.of("a", "b", "c");
        //var set = Set.copyOf(List.of("a", "b", "c", "a"));
        Assertions.assertEquals(3, set.size());
        //var el = set.iterator().next();
        var el = set.stream().findAny().get();

        set.add( "d");
        Assertions.assertEquals(4, set.size());

    }

    @Test
    void mapTest() {
        var digit = new HashMap<Character,String>();
        digit.put('0', "Zero");
        digit.put('1', "One");
        digit.put('2', "Two");
        digit.put('3', "Three");
        digit.put('4', "Fore");

        Assertions.assertEquals("One", digit.get('1'));

    }

}
