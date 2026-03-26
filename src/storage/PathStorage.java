package storage;

import model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Exception.StorageException;
public abstract class AbstractPathStorage extends AbstractStorage<Path>{
    private Path directory;
    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory");
        }
        this.directory = directory;
    }

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;
    protected abstract Resume doRead(InputStream is) throws IOException;

    @Override
    public void clear() {
        getFilesList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(path))) {
            doWrite(r, bos);
        } catch (IOException e) {
            throw new StorageException("Directory write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create path " + getFileName(path), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("path delete error", getFileName(path), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(path))) {
            return doRead(bis);
        } catch (IOException e) {
            throw new StorageException("File read error", getFileName(path), e);
        }
    }

    @Override
    public List<Resume> doCopyAll() {
        return getFilesList().map(this::doGet).collect(Collectors.toList());
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }
    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("directory read error", e);
        }
    }
}
