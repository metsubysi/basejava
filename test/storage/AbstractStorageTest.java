package storage;

import model.*;

import static java.awt.Event.HOME;
import static org.junit.Assert.assertEquals;

import org.junit.Before;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import Exception.NotExistStorageException;
import Exception.ExistStorageException;

import javax.swing.text.Position;
import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    protected final static File STORAGE_DIR = new File("C:\\javaprojects\\new");
    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_5 = "uuid5";
    private static final String UUID_6 = "uuid6";
    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;
    private static final Resume R5;
    private static final Resume R6;

    static {
        R1 = new Resume(UUID_1, "Алексей Захаров");
        R2 = new Resume(UUID_2, "Анна Смирнова");
        R3 = new Resume(UUID_3, "Дмитрий Соколов");
        R4 = new Resume(UUID_4, "Екатерина Новикова");
        R5 = new Resume(UUID_5, "Мария Ильина");
        R6 = new Resume(UUID_6, "Сергей Волков");


        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"),
                                new Organization.Position(LocalDate.parse("2011-09-09"), LocalDate.parse("2012-09-09"), "position2", "content2"))));

        R1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Organization2", "http://Organization2.ru",
                        new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
    }


    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
        storage.save(R4);
        storage.save(R5);
    }

    @org.junit.Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @org.junit.Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_4, "new person");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_4)));
    }

    @org.junit.Test
    public void getAll() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(5, list.size());
        assertEquals(list, Arrays.asList(R1, R2, R3, R4, R5));
    }

    @org.junit.Test
    public void get() throws Exception {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
        assertGet(R4);
        assertGet(R5);
    }

    @org.junit.Test
    public void save() throws Exception {
        storage.save(R6);
        assertSize(6);
        assertGet(R6);
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_4);
        assertSize(4);
        storage.get(UUID_4);
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        // Пытаемся удалить несуществующий UUID (который выдаст индекс -1)
        storage.delete("DUMMY_UUID_THAT_DOES_NOT_EXIST");
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        // Пытаемся удалить несуществующий UUID (который выдаст индекс -1)
        storage.update(storage.get("DUMMY_UUID_THAT_DOES_NOT_EXIST"));
    }

    @org.junit.Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        // Пытаемся удалить несуществующий UUID (который выдаст индекс -1)
        storage.save(new Resume(UUID_2, "Анна Смирнова"));
    }

    @org.junit.Test
    public void size() {
        assertSize(5);
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }
}