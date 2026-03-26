package storage;

import model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Exception.StorageException;
import storage.serializer.StreamSerializer;

public class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;
    private StreamSerializer streamSerializer;

    protected AbstractFileStorage(File directory, StreamSerializer streamSerializer) {
        this.streamSerializer = streamSerializer;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable or writeable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                doDelete(file);
            }
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("Directory read error");
        }
        return list.length;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            streamSerializer.doWrite(r, bos);
        } catch (IOException e) {
            throw new StorageException("file write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(r, file);
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new RuntimeException("Cannot delete file: " + file.getAbsolutePath());
        }
    }

    @Override
    protected Resume doGet(File file) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            return streamSerializer.doRead(bis);
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    public List<Resume> doCopyAll() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error");
        }
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(doGet(file));
        }
        return list;
    }
}
