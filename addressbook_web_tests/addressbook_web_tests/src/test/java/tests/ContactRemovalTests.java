package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData().withLastName("LastName3"));
        }
        var prvList = app.jdbc().getContactList(); //app.contacts().getList();
        var rnd = new Random();
        int index = rnd.nextInt(prvList.size());

        //app.contacts().removeContact(index);
        app.contacts().removeContact(prvList.get(index));

        var newList = app.jdbc().getContactList(); //app.contacts().getList();
        Assertions.assertEquals(prvList.size() - 1, newList.size());
        prvList.remove(index);

        newList.sort(ContactData::compareById);
        prvList.sort(ContactData::compareById);

        Assertions.assertEquals(prvList, newList);
    }

    @Test
    public void canRemoveAllContacts() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData().withLastName("LastName3"));
        }
        app.contacts().removeAllContacts();
        int newCount = app.contacts().getCount();
        Assertions.assertEquals(0, newCount);
    }

}
