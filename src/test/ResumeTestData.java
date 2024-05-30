package test;

import com.model.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    @Test
    public void main() {
        Resume resume = createResume("uuid0","Григорий Кислин");

        System.out.println(resume.getFullName());
        printContacts(resume);
        System.out.println();
        for (SectionType type : SectionType.values()) {
            AbstractSection section = resume.getSection(type);
            if (section instanceof TextSection) {
                System.out.println("___________________");
                printTextSection(section);
            } else if (section instanceof ListSection) {
                System.out.println("___________________");
                printListSection(section);
            } else if (section instanceof OrganizationSection) {
                System.out.println("___________________");
                System.out.println(section.toString());
            }
        }
    }

    public Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(fullName);

        // Adding contacts
        resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        resume.addContact(ContactType.LINKEDIN, "Профиль LinkedIn");
        resume.addContact(ContactType.GITHUB, "Профиль GitHub");

        // Adding sections
        TextSection personalSection = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.addSection(SectionType.PERSONAL, personalSection);
        TextSection objectiveSection = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.addSection(SectionType.OBJECTIVE, objectiveSection);
        ListSection achievementSection = new ListSection(getAchievementsList());
        resume.addSection(SectionType.ACHIEVEMENT, achievementSection);
        ListSection qualificationSection = new ListSection(getQualificationList());
        resume.addSection(SectionType.QUALIFICATIONS, qualificationSection);
        OrganizationSection experienceSections = new OrganizationSection(getExperienceList());
        resume.addSection(SectionType.EXPERIENCE, experienceSections);
        OrganizationSection educationSections = new OrganizationSection(getEducationList());
        resume.addSection(SectionType.EDUCATION, educationSections);

        return resume;
    }

    private List<String> getAchievementsList() {
        List<String> achievementsList = new ArrayList<>();
        achievementsList.add("Организация команды и успешная реализация Java \n" +
                "проектов для сторонних заказчиков: приложения автопарк \n" +
                "на стеке Spring Cloud/микросервисы, система мониторинга \n" +
                "показателей спортсменов на Spring Boot, участие в \n" +
                "проекте МЭШ на Play-2, многомодульный Spring Boot \n" +
                "Vaadin проект для комплексных DIY смет");
        achievementsList.add("С 2013 года: разработка проектов \"Разработка Web \n" +
                "приложения\",\"Java Enterprise\", \"Многомодульный maven. \n" +
                "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). \n" +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн \n" +
                "стажировок и ведение проектов. Более 3500 выпускников.");
        achievementsList.add("Реализация двухфакторной аутентификации для онлайн \n" +
                "платформы управления проектами Wrike. Интеграция с Twilio, \n" +
                "DuoSecurity, Google Authenticator, Jira, Zendesk.");
        return achievementsList;
    }

    private List<String> getQualificationList() {
        List<String> qualificationList = new ArrayList<>();
        qualificationList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualificationList.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualificationList.add("Python: Django.");
        qualificationList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        return qualificationList;
    }

    private List<Organization> getExperienceList() {
        List<Organization> experienceList = new ArrayList<>();
        String descriptionOrganization = "Проектирование и разработка  \n" +
                "онлайн платформы управления проектами Wrike (Java 8 API, \n" +
                "Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis).\n" +
                "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.";
        String positionOrganization = "Старший разработчик (backend)";
        LocalDate startDate = LocalDate.of(2014, 10, 1);
        LocalDate endDate = LocalDate.of(2016, 1, 1);
        List<Period> periods = new ArrayList<>();
        periods.add(new Period(startDate, endDate, descriptionOrganization,
                positionOrganization));
        Organization organization = new Organization("Wrike",
                "https://www.wrike.com/", periods);
        experienceList.add(organization);
        return experienceList;
    }

    private List<Organization> getEducationList() {
        List<Organization> educationList = new ArrayList<>();
        String descriptionOrganization = "Курс 'Объектно-ориентированный анализ ИС.\n " +
                "Концептуальное моделирование на UML.'";
        String positionOrganization = "";
        LocalDate startDate = LocalDate.of(2011, 3, 1);
        LocalDate endDate = LocalDate.of(2011, 4, 1);
        List<Period> periodsEducation = new ArrayList<>();
        periodsEducation.add(new Period(startDate, endDate, descriptionOrganization,
                positionOrganization));
        Organization organizationEducation = new Organization("Luxoft", "http:" +
                "//www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                periodsEducation);
        educationList.add(organizationEducation);
        return educationList;
    }

    public void printContacts(Resume resume){
        System.out.println("\nКонтакты:");
        for (ContactType type : ContactType.values()) {
            String contact = resume.getContact(type);
            if (contact != null) {
                System.out.println(type + ": " + contact);
            }
        }
    }

    public void printTextSection(AbstractSection section){
        System.out.println(((TextSection) section).getContent());
    }

    public void printListSection(AbstractSection section) {
        List<String> strings = ((ListSection) section).getStrings();
        for (String str : strings) {
            System.out.println(" * " + str);
        }
    }
}
