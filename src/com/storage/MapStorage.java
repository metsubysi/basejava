package com.storage;

import com.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage{
    private Map<String, Resume> storage = new HashMap<>();

    public Object getIndex_(String uuid) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getValue().getUuid().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    void delete_(Object index)
    {
        storage.remove((String) index);
    }

    @Override
    void update_(Resume r, Object index)
    {
        storage.put((String) index, r);
    }

    @Override
    Resume get_(Object index)
    {
        return storage.get((String) index);
    }

    @Override
    void save_(Resume r)
    {
        storage.put(r.getUuid(), r);
    }

    @Override
    boolean isObtain(Object index)
    {
        return index != null;
    }

    @Override
    public void clear()
    {
        storage.clear();
    }

    @Override
    public Resume[] getAll()
    {
        List<Resume> resumeList = new ArrayList<>(storage.values());
        return resumeList.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
