package com.storage;

import com.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    public Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            Resume r = storage.get(i);
            if (r.getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    void doDelete(Object index) {
        storage.remove(((Integer) index).intValue());
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
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
