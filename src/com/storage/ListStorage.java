package com.storage;

import com.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    public Object getSearchKey(String fullName) {
        for (int i = 0; i < storage.size(); i++) {
            Resume r = storage.get(i);
            if (r.getFullName().equals(fullName)) {
                return i;
            }
        }
        return null;
    }

    @Override
    void doDelete(Object index) {
        storage.remove(0);
    }

    @Override
    void doUpdate(Resume r, Object index) {
        storage.set((Integer) index, r);
    }

    Resume doGet(Object index) {
       return storage.get(((Integer) index));
    }

    @Override
    void doSave(Resume r) {
        storage.add(r);
    }

    public void clear() {
        storage.clear();
    }

    @Override
    protected boolean isExisting(Object index) {
        return index != null;
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
