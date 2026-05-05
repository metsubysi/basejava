package storage.serializer;

import model.Resume;

import java.io.*;

public class ObjectStreamSerializer implements StreamSerializer {

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
