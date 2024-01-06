// Lesson_04
package ru.kis.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
}
