package test;

import com.exception.ExistStorageException;
import com.exception.NotExistStorageException;
import com.model.*;
import com.storage.Storage;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("D:\\file");
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static Resume R1;
    private static Resume R2;
    private static Resume R3;
    private static Resume R4;

    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        List<String> achievementsList = new ArrayList<>();
        achievementsList.add("inormation for test (achievements1)");
        achievementsList.add("inormation for test (achievements2)");
        achievementsList.add("inormation for test (achievements3)");

        List<String> qualificationList = new ArrayList<>();
        achievementsList.add("inormation for test (qualification1)");
        achievementsList.add("inormation for test (qualification2)");
        achievementsList.add("inormation for test (qualification3)");

        List<Organization> experienceList1 = new ArrayList<>();
        LocalDate startDateExperience = LocalDate.of(2010, 8, 2);
        LocalDate endDateExperience = LocalDate.of(2019, 6, 9);
        List<Position> periodsExperience = new ArrayList<>();
        String descriptionExperience = "Description organization of education";
        String positionExperience = "";
        periodsExperience.add(new Position(startDateExperience, endDateExperience,
                descriptionExperience, positionExperience));
        Organization organizationExperience = new Organization("Luxoft", "http:" +
                "//www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                periodsExperience);
        experienceList1.add(organizationExperience);

        List<Organization> experienceList2 = new ArrayList<>();
        LocalDate startDateExperience2 = LocalDate.of(2010, 8, 2);
        LocalDate endDateExperience2 = LocalDate.of(2019, 6, 9);
        List<Position> periodsExperience2 = new ArrayList<>();
        String descriptionExperience2 = "Description organization of education";
        String positionExperience2 = "";
        periodsExperience2.add(new Position(startDateExperience2, endDateExperience2,
                descriptionExperience2, positionExperience2));
        Organization organizationExperience2 = new Organization("Luxoft", "http:" +
                "//www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                periodsExperience);
        experienceList2.add(organizationExperience2);

        List<Organization> educationList = new ArrayList<>();
        LocalDate startDateEducation = LocalDate.of(2011, 3, 1);
        LocalDate endDateEducation = LocalDate.of(2012, 4, 5);
        List<Position> periodsEducation = new ArrayList<>();
        String descriptionOrganization = "Description organization of education";
        String positionOrganization = "";
        periodsEducation.add(new Position(startDateEducation, endDateEducation,
                descriptionOrganization, positionOrganization));
        Organization organizationEducation = new Organization("Luxoft", "http:" +
                "//www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                periodsEducation);
        educationList.add(organizationEducation);

        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection(achievementsList));

        R1.addSection(SectionType.QUALIFICATIONS, new ListSection(qualificationList));
        R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(experienceList1));
        R1.addSection(SectionType.EDUCATION, new OrganizationSection(educationList));
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(experienceList2));
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(R1, R2, R3));
    }

    @Test
    public void save() throws Exception {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_2);
        assertSize(2);
        storage.get(UUID_2) ;
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

}
