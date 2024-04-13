package com.storage;

import com.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage{

    private final Map<String, Resume> storage = new HashMap<>();

    public Object getSearchKey(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    @Override
    void doDelete(Object index) {
        storage.remove((String) index);
    }

    @Override
    void doUpdate(Resume r, Object index) {
        storage.put((String) index, r);
    }

    @Override
    Resume doGet(Object index) {
        return storage.get((String) index);
    }

    @Override
    void doSave(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    boolean isExisting(Object index) {
        return index != null;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>((Collection) storage);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
