package com;

import com.storage.ArrayStorage;
import com.model.Resume;
import com.storage.SortedArrayStorage;

import java.io.File;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid6");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r2.getFullName()));
        ARRAY_STORAGE.update(r2);


        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getFullName());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("******************************");
        String rootDirectoryPath = "C:\\Users\\PC\\BaseJava\\basejava\\src";
        File rootDirectory = new File(rootDirectoryPath);
        listFiles(rootDirectory);
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r.getFullName());
        }
    }
    public static void listFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("-----------------------------------");
                    System.out.println("DIR: " + file.getName());
                    listFiles(file);
                    System.out.println("-----------------------------------");
                } else {
                    System.out.println("Fail: " + file.getName());
                }
            }
        }
    }
}
