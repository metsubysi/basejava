package test;

import com.model.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        LocalDate startDate = LocalDate.of(2014, 10, 1);
        LocalDate endDate = LocalDate.of(2016, 1, 1);
        List<Period> periods = new ArrayList<>();
        periods.add(new Period(startDate, endDate, descriptionOrganization,
                    positionOrganization));
        Organization organization = new Organization("Wrike",
                "https://www.wrike.com/", periods);
        List<Organization> experienceList = new ArrayList<>();
        experienceList.add(organization);
//-------------------------------------------------
        //Make List of education
        descriptionOrganization = "Курс 'Объектно-ориентированный анализ ИС. " +
                "Концептуальное моделирование на UML.'";
        //startDate = LocalDate.of(2011, 3, 1);
        //endDate = LocalDate.of(2011, 4, 1);
        List<Period> periodsEducation = new ArrayList<>();
        periodsEducation.add(new Period(startDate, endDate, descriptionOrganization,
                positionOrganization));
        Organization organizationEducation = new Organization("Luxoft", "http:" +
                "//www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                periodsEducation);
        List<Organization> educationList = new ArrayList<>();
        educationList.add(organizationEducation);
//-------------------------------------------------
        Resume resume = new Resume("Григорий Кислин");

        // Adding contacts
        resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        resume.addContact(ContactType.LINKEDIN, "Профиль LinkedIn");
        resume.addContact(ContactType.GITHUB, "Профиль GitHub");

        // Adding sections
        TextSections personalSection = new TextSections("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.addSection(SectionType.PERSONAL, personalSection);
        TextSections objectiveSection = new TextSections("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.addSection(SectionType.OBJECTIVE, objectiveSection);
        ListSection achievementSection = new ListSection(achievementsList);
        resume.addSection(SectionType.ACHIEVEMENT, achievementSection);
        ListSection qualificationSection = new ListSection(qualificationList);
        resume.addSection(SectionType.QUALIFICATIONS, qualificationSection);
        OrganizationSection experienceSections = new OrganizationSection(experienceList);
        resume.addSection(SectionType.EXPERIENCE, experienceSections);
        OrganizationSection educationSections = new OrganizationSection(educationList);
        resume.addSection(SectionType.EDUCATION, educationSections);

        System.out.println(resume.getFullName());
        printContacts(resume);
        System.out.println();
        for (SectionType type : SectionType.values()) {
            AbstractSection section = resume.getSection(type);
            if (section instanceof TextSections) {
                printTextSection(section);
            } else if (section instanceof ListSection) {
                printListSection(section);
            } else if (section instanceof OrganizationSection) {
              //printOrganizationSection(section);
                System.out.println(section.toString());
            }
        }
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
        System.out.println("Type: TextSections");
        System.out.println(((TextSections) section).getContent());
    }

    public void printListSection(AbstractSection section) {
        List<String> strings = ((ListSection) section).getStrings();
        for (String str : strings) {
            System.out.println(str);
        }
    }
}
