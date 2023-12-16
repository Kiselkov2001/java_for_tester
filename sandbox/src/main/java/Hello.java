import java.io.File;

public class Hello {
    public static void main(String[] args) {
        System.out.println("hello, world");

        var configFile = new File("build.gradle");
        System.out.println(configFile.getAbsolutePath());
        System.out.println(configFile.exists());
    }
}
