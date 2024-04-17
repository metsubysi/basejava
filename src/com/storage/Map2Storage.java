package com.storage;

import com.model.Resume;

import java.util.*;

public class Map2Storage extends AbstractStorage{

    private final Map<String, Resume> storage = new HashMap<>();

    public Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    void doDelete(Object resume) {
        storage.remove(((Resume) resume).getFullName());
    }

    @Override
    void doUpdate(Resume r, Object index) {
        storage.put(r.getFullName(), r);
    }

    @Override
    Resume doGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    void doSave(Resume r) {
        storage.put(r.getFullName(), r);
    }

    @Override
    boolean isExisting(Object index)  {
        return index != null;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
