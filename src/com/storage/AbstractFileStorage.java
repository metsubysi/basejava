package com.storage;

import com.model.Resume;

import java.io.*;
import java.util.UUID;

public abstract class AbstractFileStorage {

    public void saveResume(Resume resume) {
        String fileName = generateFileName(resume.getUuid());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(resume);
            System.out.println("Resume saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving resume to file: " + e.getMessage());
        }
    }

    public Resume loadResume(UUID uuid) {
        String fileName = generateFileName(uuid);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Resume resume = (Resume) ois.readObject();
            System.out.println("Resume loaded from file: " + fileName);
            return resume;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading resume from file: " + e.getMessage());
            return null;
        }
    }

    private String generateFileName(UUID uuid) {
        return uuid.toString() + ".txt";
    }

    public abstract void deleteResume(UUID uuid);
}