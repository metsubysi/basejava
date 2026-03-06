import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class example {
    static final String WHITE = "\u001B[97m";
    static final String BLUE = "\u001B[94m";
    static final String RED = "\u001B[91m";
    static final String BLOCK = "█";
    static final int LEN = 10;

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int n = 100000; // количество элементов
        int middle = n / 2;

        // -------------------------
        // Создание ArrayList
        long start = System.nanoTime();
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add("Элемент " + i);
        }
        long end = System.nanoTime();
        System.out.println("ArrayList создание: " + (end - start) / 1_000_000 + " ms");

        // -------------------------
        // Создание LinkedList
        start = System.nanoTime();
        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            linkedList.add("Элемент " + i);
        }
        end = System.nanoTime();
        System.out.println("LinkedList создание: " + (end - start) / 1_000_000 + " ms");

        // -------------------------
        // Добавление в середину
        start = System.nanoTime();
        arrayList.add(middle, "НОВЫЙ");
        end = System.nanoTime();
        System.out.println("ArrayList добавление в середину: " + (end - start) + " ns");

        start = System.nanoTime();
        linkedList.add(middle, "НОВЫЙ");
        end = System.nanoTime();
        System.out.println("LinkedList добавление в середину: " + (end - start) + " ns");

        // -------------------------
        // Добавление в конец
        start = System.nanoTime();
        arrayList.add("КОНЕЦ");
        end = System.nanoTime();
        System.out.println("ArrayList добавление в конец: " + (end - start) + " ns");

        start = System.nanoTime();
        linkedList.add("КОНЕЦ");
        end = System.nanoTime();
        System.out.println("LinkedList добавление в конец: " + (end - start) + " ns");

        // -------------------------
        // Доступ к элементу в середине
        start = System.nanoTime();
        String s1 = arrayList.get(middle);
        end = System.nanoTime();
        System.out.println("ArrayList доступ к середине: " + (end - start) + " ns");

        start = System.nanoTime();
        String s2 = linkedList.get(middle);
        end = System.nanoTime();
        System.out.println("LinkedList доступ к середине: " + (end - start) + " ns");

        // -------------------------
        // Удаление из середины
        start = System.nanoTime();
        arrayList.remove(middle);
        end = System.nanoTime();
        System.out.println("ArrayList удаление из середины: " + (end - start) + " ns");

        start = System.nanoTime();
        linkedList.remove(middle);
        end = System.nanoTime();
        System.out.println("LinkedList удаление из середины: " + (end - start) + " ns");

        Iterator<String> ar = linkedList.iterator();
// Удаление элементов, кратных 2, через итератор LinkedList
        start = System.nanoTime();
        Iterator<String> linkedIt = linkedList.iterator();
        int index = 0;
        while (linkedIt.hasNext()) {
            linkedIt.next();
            if (index % 2 == 0) {
                linkedIt.remove();
            }
            index++;
        }
        end = System.nanoTime();
        System.out.println("LinkedList удаление через Iterator: " + (end - start) + " ns");

        // -------------------------
        // Удаление элементов, кратных 2, через итератор ArrayList
        start = System.nanoTime();
        Iterator<String> arrayIt = arrayList.iterator();
        index = 0;
        while (arrayIt.hasNext()) {
            arrayIt.next();
            if (index % 2 == 0) {
                arrayIt.remove();
            }
            index++;
        }
        end = System.nanoTime();
        System.out.println("ArrayList удаление через Iterator: " + (end - start) + " ns");
    }
}
