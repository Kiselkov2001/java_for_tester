package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertListGroup(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convertGroup).collect(Collectors.toList());
//        List<GroupData> lst = new ArrayList<>();
//        for (var r : records) {
//            lst.add(convertGroup(r));
//        }
//        return lst;
    }

    private static GroupData convertGroup(GroupRecord r) {
        return new GroupData("" + r.id, r.name, r.header, r.footer);
    }

    private static GroupRecord convertGroup(GroupData g) {
        var id = g.id();
        if ("".equals(id)) id = "0";
        return new GroupRecord(Integer.parseInt(id), g.name(), g.header(), g.footer());
    }

    public List<GroupData> getGroupList() {
        return convertListGroup(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    static List<ContactData> convertListContact(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convertContact).collect(Collectors.toList());
//        List<ContactData> lst = new ArrayList<>();
//        for (var r : records) {
//            lst.add(convertContact(r));
//        }
//        return lst;
    }

    private static ContactData convertContact(ContactRecord r) {
        return new ContactData("" + r.id, r.firstname, r.lastname, "", r.address,
                r.home, r.mobile, r.work, r.phone2,
                r.email, r.email2, r.email3);
    }

    private static ContactRecord convertContact(ContactData c) {
        var id = c.id();
        if ("".equals(id)) id = "0";
        return new ContactRecord(Integer.parseInt(id), c.firstname(), c.lastname(), c.address());
    }

    public List<ContactData> getContactList() {
        return convertListContact(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count(*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData group) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertGroup(group));
            session.getTransaction().commit();
        });
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count(*) from ContactRecord where deprecated is null", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactData contact) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContact(contact));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertListContact(session.get(GroupRecord.class, group.id()).contacts);
        });
    }
}
