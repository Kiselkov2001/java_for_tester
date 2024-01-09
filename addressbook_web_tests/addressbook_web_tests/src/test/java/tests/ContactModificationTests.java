package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Random;

public class ContactModificationTests extends TestBase {
    @Test
    public void canModifyContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData(ContactData.initDictonary("2", false)));
        }
        var prvList = app.contacts().getList();
        var rnd = new Random();
        int index = rnd.nextInt(prvList.size());

        var contact = new ContactData(ContactData.getTuple("modified lastname", "modified firstname"));
        app.contacts().modifyContact(index, contact);

        var newList = app.contacts().getList();
        prvList.set(index, contact);

        prvList.sort(ContactData::compare);
        newList.sort(ContactData::compare);

        var arr1 = prvList.stream().map(ContactData::repr).toArray(String[]::new);
        var arr2 = newList.stream().map(ContactData::repr).toArray(String[]::new);

        Assertions.assertArrayEquals(arr1, arr2);
    }

}
