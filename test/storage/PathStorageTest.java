package storage;

import storage.serializer.ObjectStreamSerializer;

public class StreamPathStorageTest extends AbstractStorageTest{
    public StreamPathStorageTest() {
        super(new FileStorage(STORAGE_DIR.getAbsolutePath()), new ObjectStreamSerializer());
    }
}