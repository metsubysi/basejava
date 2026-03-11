import model.Resume;

import java.util.*;

public class mainCollection {
    private static final String UUID_1 = "uuid1";
    private static final Resume R1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume R2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume R3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume R4 = new Resume(UUID_4);
    private static final String UUID_5 = "uuid5";
    private static final Resume R5 = new Resume(UUID_5);
    private static final String UUID_6 = "uuid6";
    private static final Resume R6 = new Resume(UUID_6);
    public static void main(String[] arg) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(R1);
        collection.add(R2);
        collection.add(R3);
        collection.add(R4);
        collection.add(R5);
        collection.add(R6);

        for (Resume r: collection) {
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
                //    collection.remove(r);
            }
        }
        System.out.println("PODKLU4ILI ITEPATOP" +
                "_________________");
        Iterator<Resume> iterator = collection.iterator();
        while(iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_3)) {
                iterator.remove();
            }
        }
        System.out.println("BbIBODIM KOLEKUII_________________");
        System.out.println(collection.toString());

        Map<String, Resume> map = new HashMap<>();
        for (int i = 0; i<1000000; i++) {
            map.put("uuid_" + i, new Resume());
        }
        long start = System.currentTimeMillis();
        int sum = 0;
        // Второй цикл: перебор через entrySet()
        start = System.currentTimeMillis();
        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            sum += entry.getValue().toString().length();
        }
        long end = System.currentTimeMillis();
        System.out.println("SUM = " + sum + "; Время второго цикла (entrySet): " + (end - start) + " ms");

        sum = 0;
        for (String key : map.keySet()) {
            sum += map.get(key).toString().length();
        }
        end = System.currentTimeMillis();
        System.out.println("SUM = " + sum + "; Время первого цикла (keySet): " + (end - start) + " ms");

        List<Resume>  resume = Arrays.asList(R1, R2, R3, R4, R5);
        resume.remove(1);
        for(Resume r : resume) {
            System.out.println(r);
        }


    }
}
