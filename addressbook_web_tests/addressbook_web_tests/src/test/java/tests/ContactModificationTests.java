package tests;

import common.CommonFunc;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactModificationTests extends TestBase {
    @Test
    public void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData("", "lastname", "firstname", "", "", "", "", "", "", "", "", ""));
        }
        var prvList = app.hbm().getContactList(); //app.contacts().getList(); //app.jdbc().getContactList();
        var rnd = new Random();
        int index = rnd.nextInt(prvList.size());

        var contact = new ContactData()
                .withLastName("modified_lastnameYY")
                .withFirstName("modified_firstnameYY");
        app.contacts().modifyContact(prvList.get(index), contact);

        var newList = app.hbm().getContactList(); //app.contacts().getList(); //app.jdbc().getContactList();
        prvList.set(index, contact
                .withId(prvList.get(index).id())
                .withAddress(prvList.get(index).address())
                .withHome(prvList.get(index).home())
                .withEmail(prvList.get(index).email()));

        prvList.sort(ContactData::compareById);
        newList.sort(ContactData::compareById);

        //it's compare without photo
        var arr1 = prvList.stream().map(ContactData::repr).toArray(String[]::new);
        var arr2 = newList.stream().map(ContactData::repr).toArray(String[]::new);

        Assertions.assertArrayEquals(arr1, arr2);
        //Assertions.assertEquals(prvList, newList);
    }

    @Test
    public void canAddContactInGroup() {
        ContactData contact;
        GroupData group;

        var lstCouple = seekCoupleGroupAndContactWithoutLink();

        if (lstCouple.isEmpty()) {
            contact = new ContactData()
                    .withLastName(CommonFunc.randomString(10))
                    .withFirstName(CommonFunc.randomString(15))
                    .withAddress(CommonFunc.randomString(20));

            app.contacts().createContact(contact);

            group = new GroupData()
                    .withName(CommonFunc.randomString(10))
                    .withHeader(CommonFunc.randomString(15))
                    .withFooter(CommonFunc.randomString(20));

            app.groups().createGroup(group);

            lstCouple = seekCoupleGroupAndContactWithoutLink();
        }

        group = (GroupData) lstCouple.get(0);
        contact = (ContactData) lstCouple.get(1);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().addContactInGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);

        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());

        oldRelated.add(contact
                .withId(contact.id())
                .withLastName(contact.lastname())
                .withFirstName(contact.firstname())
                .withAddress(contact.address()));

        oldRelated.sort(ContactData::compareById);
        newRelated.sort(ContactData::compareById);

        Assertions.assertEquals(oldRelated, newRelated);
    }

    public List<Object> seekCoupleGroupAndContactWithoutLink() {
        var lst = new ArrayList<Object>();
        var lstGroups = app.hbm().getGroupList();
        var lstContacts = app.hbm().getContactList();
        if (lstGroups.isEmpty() || lstContacts.isEmpty()) return lst;

        for (GroupData g : lstGroups) {
            for (ContactData c : lstContacts) {
                var oldRelated = app.hbm().getContactsInGroup(g);
                if (!oldRelated.contains(c)) {
                    lst.add(g);
                    lst.add(c);
                    return lst;
                }
            }
        }
        return lst;
    }

    @Test
    public void canDelContactInGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        ContactData contact = null;

        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "lastname", "firstname", "", "", "", "", "", "", "", "", ""));
        }

        var oldRelated = app.hbm().getContactsInGroup(group);

        if (oldRelated.isEmpty()) {
            var rnd = new Random();
            var lst = app.hbm().getContactList();
            int index = rnd.nextInt(lst.size());
            contact = lst.get(index);
            app.contacts().addContactInGroup(contact, group);
        }

        oldRelated = app.hbm().getContactsInGroup(group);

        var rnd = new Random();
        int index = rnd.nextInt(oldRelated.size());
        contact = oldRelated.get(index);

        app.contacts().delContactInGroup(contact, group);

        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());

        oldRelated.remove(contact);

        oldRelated.sort(ContactData::compareById);
        newRelated.sort(ContactData::compareById);

        Assertions.assertEquals(oldRelated, newRelated);
    }
}
