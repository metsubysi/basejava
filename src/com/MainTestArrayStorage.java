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
        Resume r1 = new Resume("Ethan Patel");
        Resume r2 = new Resume("Olivia Kim");
        Resume r3 = new Resume("Liam Singh");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        System.out.println("\nCreated 3 resume");
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
        System.out.println("\nGet r1: " + ARRAY_STORAGE.get(r2.getUuid()));
        ARRAY_STORAGE.update(r2);
        System.out.println("\nUpdated  resume r2");
        printAll();

        ARRAY_STORAGE.delete(r1.getUuid());
        System.out.println("\nDeleted resume r1");
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        ARRAY_STORAGE.clear();
        System.out.println("\nClear storage");
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("******************************");
        String rootDirectoryPath = "C:\\Users\\PC\\BaseJava\\basejava\\src";
        File rootDirectory = new File(rootDirectoryPath);
        listFiles(rootDirectory);
    }

    static void printAll() {
        System.out.println("Get All");
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
