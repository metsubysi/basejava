import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FailExample {


    public static void main(String[] args) throws IOException {
        System.out.println("gogogo");
        System.out.println(System.getProperty("user.dir"));
        System.out.println("gogo");
        String filePath = ".\\.gitignore";
        File file = new File(filePath);
        System.out.println(file.getCanonicalFile());
        File dir = new File("./src");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            System.out.println("Voshli suda");
            for (String name : dir.list()) {
                System.out.println(name);
            }
        }
        FileInputStream fis = null;
        System.out.println("pered try");
        try {
            System.out.println("v nutri 1");
            fis = new FileInputStream(filePath);
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {

            if (fis != null) {
                try {
                    System.out.println("v nutri 2");
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("filefile.txt"))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("_____________________________________________________________________________________");
        String p = "C:\\javaprojects\\basejava";
        String space = "   ";
        printDirectory(p, space);
    }
    public static void printDirectory(String p, String space) {
        ;
        File file = new File(p);
        String[] names = file.list();
        for (String f : names) {
            if (new File(p + "\\" + f).isDirectory()) {
                System.out.println(space + f);
                printDirectory(p + "\\" + f, "////" + space);
            }
            else System.out.println(space + f);
        }
    }
}
