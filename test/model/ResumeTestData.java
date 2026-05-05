package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static Resume ResumeTestData(String uuid, String fulName)
    {
        Resume r = new Resume(uuid, fulName);
        r.addContact(ContactType.MOBILE, "+7(921) 855-0482");
        r.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        r.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        r.addContact(ContactType.LINKEDIN, "https://linkedin.com");
        r.addContact(ContactType.GITHUB, "https://github.com");
        r.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com");
        r.addContact(ContactType.HOME_PAGE, "https://example.com");

        // ===== OBJECTIVE =====
        r.addSection(SectionType.OBJECTIVE,
                new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        // ===== PERSONAL =====
        r.addSection(SectionType.PERSONAL,
                new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        // ===== ACHIEVEMENT =====
        r.addSection(SectionType.ACHIEVEMENT,
                new ListSection(List.of(
                        "Организация команды и успешная реализация Java проектов (Spring Cloud, Spring Boot, Vaadin)",
                        "С 2013 года: разработка и проведение онлайн стажировок (3500+ выпускников)",
                        "Реализация двухфакторной аутентификации (Twilio, Google Authenticator)",
                        "Разработка ERP системы и интеграции (1С, LDAP, BPM)",
                        "Создание RIA приложения (Spring, GWT, HTML5)",
                        "Разработка JavaEE фреймворка (SOA, JMS, Glassfish)",
                        "Интеграция платежных систем (Сбербанк, Cyberplat и др.)"
                )));

        // ===== QUALIFICATIONS =====
        r.addSection(SectionType.QUALIFICATIONS,
                new ListSection(List.of(
                        "Java, Scala, Python, JavaScript",
                        "Spring, Hibernate, JPA, GWT, Vaadin",
                        "PostgreSQL, Redis, Oracle, MySQL",
                        "Maven, Gradle, Jenkins, Docker",
                        "ООП, SOA, UML, архитектурные шаблоны",
                        "Английский: Upper Intermediate"
                )));

        // ===== EXPERIENCE =====
        List<Organization> experience = new ArrayList<>();

        Organization javaProjects = new Organization("Java Online Projects", null);
        javaProjects.addPosition(new Organization.Position(
                LocalDate.of(2013, 10, 1),
                null,
                "Автор проекта",
                "Создание и проведение Java стажировок"
        ));
        experience.add(javaProjects);


        Organization wrike = new Organization("Wrike", null);
        wrike.addPosition(new Organization.Position(
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1),
                "Senior Developer",
                "Разработка платформы управления проектами"
        ));
        experience.add(wrike);


        Organization rit = new Organization("RIT Center", null);
        rit.addPosition(new Organization.Position(
                LocalDate.of(2012, 4, 1),
                LocalDate.of(2014, 10, 1),
                "Java Architect",
                "Разработка ERP системы и CI"
        ));
        experience.add(rit);


        r.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(experience));


        List<Organization> education = new ArrayList<>();

        Organization coursera = new Organization("Coursera", null);
        coursera.addPosition(new Organization.Position(
                LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 5, 1),
                "Functional Programming in Scala",
                null
        ));
        education.add(coursera);


        Organization itmo = new Organization("ИТМО", null);

        itmo.addPosition(new Organization.Position(
                LocalDate.of(1993, 9, 1),
                LocalDate.of(1996, 7, 1),
                "Аспирантура",
                null
        ));

        itmo.addPosition(new Organization.Position(
                LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 7, 1),
                "Инженер",
                null
        ));

        education.add(itmo);


        r.addSection(SectionType.EDUCATION,
                new OrganizationSection(education));
        return r;
    }
}
