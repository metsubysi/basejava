package com.storage;

import com.exception.NotExistStorageException;
import com.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage{

    List<Resume> storage = new ArrayList<>();
    @Override
    public void clear() {
        storage.clear();
        size = 0;
    }

    @Override
    public void update(Resume r) {
        if (get(r.getUuid()) != null) {
            save(r);
        } else {
            throw new NotExistStorageException("Resume " + r.getUuid() + " not found");
        }
    }

    @Override
    public void save(Resume r) {
        storage.add(r);
        size++;
    }

    @Override
    public Resume get(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                return r;
            }
        }
        throw new NotExistStorageException("Resume " + uuid + " not found");
    }

    @Override
    public void delete(String uuid) {
        Resume r = get(uuid);
        storage.remove(r);
        size--;
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }
}
