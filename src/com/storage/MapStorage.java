package com.storage;

import com.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{

    private final Map<String, Resume> storage = new HashMap<>();

    public Object getSearchKey(String uuid) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getValue().getUuid().equals(uuid)) {
                return entry.getKey();
            }
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
    public Resume[] getAll() {
        Map<String, Resume> resumeMap = new HashMap<>(storage);
        return resumeMap.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
