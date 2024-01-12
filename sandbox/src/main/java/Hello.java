import java.io.File;

public class Hello {
    public static void main(String[] args) {
        pathFile();

//        var z = calculate();
//        System.out.println(z);
//        System.out.println("hello, world");
    }

    public static void pathFile() {
        var configFile = new File("build.gradle");
        System.out.println(configFile.getAbsolutePath());
        System.out.println(configFile.exists());

        System.out.println(String.format("working directory: %s", new File("").getAbsolutePath()));
    }

    private static int calculate() {
        var x = 1;
        var y = 0;
        var z = divide(x, y);
        return z;
    }

    private static int divide(int x, int y) {
        int z = 0;
        try {
            z = x / y;
        } catch (ArithmeticException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return z;
    }
}
