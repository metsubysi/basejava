package com.storage;

import com.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<String> {

    private final Map<String, Resume> storage = new HashMap<>();

    public String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    void doDelete(String uuid) {
        storage.remove(uuid);
    }

    @Override
    void doUpdate(Resume r, String uuid) {
        storage.put(uuid, r);
    }

    @Override
    Resume doGet(String uuid) {
        return storage.get(uuid);
    }

    @Override
    void doSave(Resume r, String uuid) {
        storage.put(uuid, r);
    }

    @Override
    protected boolean isExist(String uuid)  {
        return storage.containsKey(uuid);
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
