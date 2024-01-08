package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData(ContactData.initDictonary("2", false)));
        }
        var prvList = app.contacts().getList();
        var rnd = new Random();
        int index = rnd.nextInt(prvList.size());

        app.contacts().removeContact(index);

        var newList = app.contacts().getList();
        Assertions.assertEquals(prvList.size() - 1, newList.size());
        prvList.remove(index);

        var arr1 = prvList.stream().map(ContactData::repr).toArray(String[]::new);
        var arr2 = newList.stream().map(ContactData::repr).toArray(String[]::new);

        Assertions.assertArrayEquals(arr1, arr2);
    }

    @Test
    public void canRemoveAllContacts() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData(ContactData.initDictonary("2", false)));
        }
        app.contacts().removeAllContacts();
        int newCount = app.contacts().getCount();
        Assertions.assertEquals(0, newCount);
    }

}
