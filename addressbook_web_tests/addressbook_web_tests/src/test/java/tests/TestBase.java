package tests;

import manager.ApplicationManager;
import model.GroupData;
import org.junit.jupiter.api.BeforeEach;

import java.util.Comparator;
import java.util.Random;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) app = new ApplicationManager();
        app.init(System.getProperty("browser", "chrome"));
    }

    public static String randomString(int n) {
        var result = "";
        var rnd = new Random();
        for (int i = 0; i < n; i++) result += (char)('a' + rnd.nextInt(26));
        return result;
    }
}
