package com.storage;

import com.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> storage = new ArrayList<>();

    public Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            Resume r = storage.get(i);
            if (r.getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    void doDelete(Integer serchKey) {
        storage.remove(serchKey.intValue());
    }

    @Override
    void doUpdate(Resume r, Integer searchKey) {
        storage.set(searchKey, r);
    }

    @Override
    Resume doGet(Integer searchKey) {
       return storage.get(searchKey);
    }

    @Override
    void doSave(Resume r, Integer searchKey) {
        storage.add(r);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
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
