package test;

import com.model.*;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    @Test
    public void main() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<String> achievementsList = new ArrayList<>();
        achievementsList.add("Организация команды и успешная реализация Java " +
                "проектов для сторонних заказчиков: приложения автопарк " +
                "на стеке Spring Cloud/микросервисы, система мониторинга " +
                "показателей спортсменов на Spring Boot, участие в " +
                "проекте МЭШ на Play-2, многомодульный Spring Boot + " +
                "Vaadin проект для комплексных DIY смет");
        achievementsList.add("С 2013 года: разработка проектов \"Разработка Web " +
                "приложения\",\"Java Enterprise\", \"Многомодульный maven. " +
                "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн " +
                "стажировок и ведение проектов. Более 3500 выпускников.");
        achievementsList.add("Реализация двухфакторной аутентификации для онлайн " +
                "платформы управления проектами Wrike. Интеграция с Twilio, " +
                "DuoSecurity, Google Authenticator, Jira, Zendesk.");

        List<String> qualificationList = new ArrayList<>();
        qualificationList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualificationList.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualificationList.add("Python: Django.");
        qualificationList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");

        //Make List of experience
        String descriptionOrganization = "Проектирование и разработка \" +\n" +
                "                \"онлайн платформы управления проектами Wrike (Java 8 API, \" +\n" +
                "                \"Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis).\" +\n" +
                "                \" Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.";
        String positionOrganization = "Старший разработчик (backend)";
        List<Period> periods = new ArrayList<>();
        try {
            periods.add(new Period(dateFormat.parse("1.10.14"),
                    dateFormat.parse("1.01.16"), descriptionOrganization,
                    positionOrganization));
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }
        Organization organization = new Organization("Wrike",
                "https://www.wrike.com/", periods);
        List<Organization> experienceList = new ArrayList<>();
        experienceList.add(organization);
//-------------------------------------------------
        //Make List of education
        descriptionOrganization = "Курс 'Объектно-ориентированный анализ ИС. " +
                "Концептуальное моделирование на UML.'";
        List<Period> periodsEducation = new ArrayList<>();
        try {
            periodsEducation.add(new Period(dateFormat.parse("1.03.11"),
                    dateFormat.parse("1.04.11"), descriptionOrganization,
                    positionOrganization));
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }
        Organization organizationEducation = new Organization("Luxoft", "http:" +
                "//www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                periodsEducation);
        List<Organization> educationList = new ArrayList<>();
        educationList.add(organizationEducation);
//-------------------------------------------------
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
        ListSections achievementSection = new ListSections(achievementsList);
        resume.addSection(Resume.SectionsType.ACHIEVEMENT, achievementSection);
        ListSections qualificationSection = new ListSections(qualificationList);
        resume.addSection(Resume.SectionsType.QUALIFICATIONS, qualificationSection);
        OrganizationSections experienceSections = new OrganizationSections(experienceList);
        resume.addSection(Resume.SectionsType.EXPERIENCE, experienceSections);
        OrganizationSections educationSections = new OrganizationSections(educationList);
        resume.addSection(Resume.SectionsType.EDUCATION, educationSections);

        System.out.println(resume.getFullName());
        printContacts(resume);
        System.out.println();
        for (Resume.SectionsType type : Resume.SectionsType.values()) {
            AbstractSections section = resume.getSection(type);
            if (section instanceof TextSections) {
                printTextSection(section);
            } else if (section instanceof ListSections) {
                printListSection(section);
            } else if (section instanceof OrganizationSections) {
                printOrganizationSection(section);
            }
        }
    }

    public void printContacts(Resume resume){
        System.out.println("\nКонтакты:");
        for (Resume.ContactsType type : Resume.ContactsType.values()) {
            String contact = resume.getContact(type);
            if (contact != null) {
                System.out.println(type + ": " + contact);
            }
        }
    }

    public void printTextSection(AbstractSections section){
        System.out.println("Type: TextSections");
        System.out.println(((TextSections) section).getContent());
    }

    public void printListSection(AbstractSections section) {
        List<String> strings = ((ListSections) section).getStrings();
        for (String str : strings) {
            System.out.println(str);
        }
    }

    public void printOrganizationSection(AbstractSections section) {
        System.out.println("Type: OrganizationSections");
        List<Organization> organizations  = ((OrganizationSections) section).getOrganizations();
        for (Organization org : organizations) {
            List<Period> period = org.getPeriods();
            for (Period per : period) {
                System.out.println("\n" + org.getTitle());
                System.out.println(per.getStartDate() + " - " + per.getEndDate());
                System.out.println(per.getPosition());
                System.out.println(per.getDescription());

            }
        }
    }
}
