package com.storage;

import com.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<Resume>{

    private final Map<String, Resume> storage = new HashMap<>();

    public Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    void doDelete(Resume r) {
        storage.remove(r.getUuid());
    }

    @Override
    void doUpdate(Resume r1, Resume resume) {
        storage.put(r1.getUuid(), r1);
    }

    @Override
    Resume doGet(Resume r) {
        return r;
    }

    @Override
    void doSave(Resume r1, Resume resume) {
        storage.put(r1.getUuid(), r1);
    }

    @Override
    protected boolean isExist(Resume r) {
        return r != null;
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
