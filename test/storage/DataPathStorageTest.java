package storage;

import storage.serializer.DataStreamSerializer;

import static storage.AbstractStorageTest.STORAGE_DIR;

public class DataPathStorageTest extends AbstractStorageTest{
    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}
