package common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class CommonFunc {

    public static String randomFile(String dir) {
        var lst = new File(dir).list();
        var rnd = new Random();
        int index = rnd.nextInt(lst.length);
        return Paths.get(dir, lst[index]).toString();
    }

    public static String randomString(int n) {
        var result = "";
        var rnd = new Random();
        for (int i = 0; i < n; i++) result += (char)('a' + rnd.nextInt(26));
        return result;
    }
}
