package ru.kis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ReversChecks {
    @Test
    void testSqrt() {
        var n = 5.0;
        var s = Math.sqrt(n);
        var m = s * s;
        Assertions.assertEquals(m, n, 0.00001);
    }

    @Test
    void testSort() {
        var lst = new ArrayList<>(List.of(3, 7, 4, 9, 0));
        lst.sort(Integer::compareTo);
        for (int i = 0; i < lst.size() - 1; i++) {
            Assertions.assertTrue(lst.get(i) <= lst.get(i + 1));
        }
    }
}