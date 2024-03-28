package com.storage;

import com.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage{

    List<Resume> storage = new ArrayList<>();

    public Integer getIndex(String resumeId)
    {
        for (int i = 0; i < storage.size(); i++) {
            Resume r = storage.get(i);
            if (r.getUuid().equals(resumeId)) {
                return i;
            }
        }
        return null;
    }

    @Override
    void delete_(Object index)
    {
        storage.remove(((Integer) index).intValue());
    }

    @Override
    void update_(Resume r, Object index)
    {
        storage.set((Integer) index, r);
    }

    Resume get_(Object index)
    {
       return storage.get(((Integer) index));
    }

    @Override
    void save_(Resume r) {
        storage.add(r);
    }

    public void clear()
    {
        storage.clear();
    }

    @Override
    protected boolean isObtain(Object index)
    {
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
