package storage;
import storage.serializer.ObjectStreamSerializer;
import storage.serializer.XmlStreamSerializer;

public class PathStorageTest extends AbstractStorageTest{
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}