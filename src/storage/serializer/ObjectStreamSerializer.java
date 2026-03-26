package storage;

import model.Resume;
import storage.serializer.StreamSerializer;

import java.io.*;

public class ObjectStreamStorage implements StreamSerializer {

    protected ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }
    }

    @Override
    public Resume doRead(InputStream file) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(file)) {
            return (Resume) ois.readObject(); // возвращаем реально объект
        } catch (ClassNotFoundException e) {
            throw new IOException("Cannot read Resume", e);
        }
    }
}
