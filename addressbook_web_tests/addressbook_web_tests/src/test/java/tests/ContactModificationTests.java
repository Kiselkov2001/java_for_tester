package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Random;

public class ContactModificationTests extends TestBase {
    @Test
    public void canModifyContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData().withLastName("LastName3"));
        }
        var prvList = app.jdbc().getContactList(); //app.contacts().getList();
        var rnd = new Random();
        int index = rnd.nextInt(prvList.size());

        var contact = new ContactData()
                .withLastName("modified_lastnameZZ")
                .withFirstName("modified_firstnameZZ");
        app.contacts().modifyContact(prvList.get(index), contact);

        var newList = app.jdbc().getContactList(); //app.contacts().getList();
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

}
