package ru.kis.mantis.common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunc {

    public static String randomFile(String dir) {
        var lst = new File(dir).list();
        var rnd = new Random();
        int index = rnd.nextInt(lst.length);
        return Paths.get(dir, lst[index]).toString();
    }

    public static String randomStringOld(int n) {
        var result = "";
        var rnd = new Random();
        for (int i = 0; i < n; i++) result += (char) ('a' + rnd.nextInt(26));
        return result;
    }

    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomInteger = () -> rnd.nextInt(26);
        return Stream.generate(randomInteger)
                .limit(n)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());
    }

}
