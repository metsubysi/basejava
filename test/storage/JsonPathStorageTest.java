package storage;

import storage.serializer.JsonStreamSerializer;

import static storage.AbstractStorageTest.STORAGE_DIR;

public class JsonPathStorageTest extends AbstractStorageTest{
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
