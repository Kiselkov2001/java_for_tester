package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact1st() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData(ContactData.initDictonary("2", false)));
        }
        int prvCount = app.contacts().getCount();
        app.contacts().removeContact();
        int newCount = app.contacts().getCount();
        Assertions.assertEquals(prvCount + 1, newCount);
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
