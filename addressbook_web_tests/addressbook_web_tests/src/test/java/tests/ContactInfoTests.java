package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {
    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData("",
                    "lastname22", "firstname22", "", "",
                    "1111", "2222", "3333", "4444",
                    "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testEmails() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData("",
                    "lastname33", "firstname33", "", "",
                    "1111", "2222", "3333", "4444",
                    "aaaa", "bbbb", "cccc"));
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var emails = app.contacts().getEmails(contact);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, emails);
    }

}
