package storage.serializer;

import model.*;
import Exception.StorageException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static model.SectionType.*;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for(Map.Entry<ContactType, String> entry: r.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for(Map.Entry<SectionType, Section> entry: r.getSections().entrySet()) {
                SectionType sectionType = SectionType.valueOf(entry.getKey().name());
                dos.writeUTF(entry.getKey().name());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(entry.getValue().toString());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        ListSection listSection = (ListSection) entry.getValue();
                        List<String> list = listSection.getItems();
                        dos.writeInt(list.size());
                        for (String s : list) {
                            dos.writeUTF(s);
                        }
                    }
                    case EXPERIENCE, EDUCATION -> {
                        OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                        List<Organization> list = organizationSection.getOrganizations();
                        dos.writeInt(list.size());
                        for (Organization organization : list) {
                            dos.writeUTF(organization.getName());
                            if (organization.getUrl() != null) {
                                dos.writeBoolean(true);
                                dos.writeUTF(organization.getUrl());
                            } else {
                                dos.writeBoolean(false);
                            }
                            List<Organization.Position> positions = organization.getPositions();
                            dos.writeInt(positions.size());
                            for(Organization.Position position : positions) {
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getTitle());
                                if (position.getDescription() != null) {
                                    dos.writeBoolean(true);
                                    dos.writeUTF(position.getDescription());
                                } else {
                                    dos.writeBoolean(false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                ContactType contactType = ContactType.valueOf(dis.readUTF());
                resume.addContact(contactType, dis.readUTF());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> resume.addSection(sectionType, new TextSection(dis.readUTF()));
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        int size_1 = dis.readInt();
                        List<String> list = new ArrayList<>();
                        for(i = 0; i < size_1; i++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(list));
                    }

                    case EXPERIENCE, EDUCATION -> {
                        int size_2 = dis.readInt();
                        List<Organization> organizations = new ArrayList<Organization>();
                        for(int j = 0; j < size_2; j++) {
                            String name = dis.readUTF();
                            String url = null;
                            if (dis.readBoolean()) {
                                url = dis.readUTF();
                            }
                            Link homePage = new Link(name, url);
                            List<Organization.Position> positions = new ArrayList<Organization.Position>();
                            int size_3 = dis.readInt();
                            for(int t = 0; t < size_3; t++) {
                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                LocalDate endDate = LocalDate.parse(dis.readUTF());
                                String title = dis.readUTF();
                                String description = null;
                                if (dis.readBoolean()) {
                                    description = dis.readUTF();
                                }
                                Organization.Position position= new Organization.Position(startDate,
                                        endDate,
                                        title,
                                        description);
                                positions.add(position);
                            }
                            organizations.add(new Organization(homePage, positions));
                        }
                        resume.addSection(sectionType, new OrganizationSection(organizations));
                    }
                }
            }
            return resume;
        }
    }
}
