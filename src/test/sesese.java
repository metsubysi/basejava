package test;

import com.model.Resume;
import com.model.TextSections;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class sesese {
    @Test
    public void testResumeData() {
        Resume resume = new Resume("Григорий Кислин");

        // Adding contacts
        resume.addContact(Resume.ContactsType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(Resume.ContactsType.PHONE, "+7(921) 855-0482");
        resume.addContact(Resume.ContactsType.SKYPE, "skype:grigory.kislin");
        resume.addContact(Resume.ContactsType.SOCIAL_MEDIA, "Профиль LinkedIn");
        resume.addContact(Resume.ContactsType.SOCIAL_MEDIA, "Профиль GitHub");

        // Adding sections
        TextSections personalSection = new TextSections("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.addSection(Resume.SectionsType.PERSONAL, personalSection);

        TextSections objectiveSection = new TextSections("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.addSection(Resume.SectionsType.OBJECTIVE, objectiveSection);

        // Asserting that the data is correctly added to the resume
        assertEquals("Григорий Кислин", resume.getFullName());
        assertEquals("gkislin@yandex.ru", resume.getContact(Resume.ContactsType.EMAIL));
        assertEquals("skype:grigory.kislin", resume.getContact(Resume.ContactsType.SKYPE));
        assertEquals("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.", ((TextSections) resume.getSection(Resume.SectionsType.PERSONAL)).getContent());
        assertEquals("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям", ((TextSections) resume.getSection(Resume.SectionsType.OBJECTIVE)).getContent());
    }
}
