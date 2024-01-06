package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactCreationTests extends TestBase {
    public static List<ContactData> contactProvider() {
        var lst = new ArrayList<>(List.of(
                new ContactData(new String[]{"firstname:contact_name1", "lastname:1", "address:2", "email:3", "home:4"})));
        for (var firstname : List.of("firstname:", "firstname:firstname"))
            for (var address : List.of("address:", "address:address"))
                for (var lastname : List.of("lastname", "lastname:lastname"))
                    lst.add(new ContactData(new String[]{firstname, address, lastname}));
        var arr = new String[ContactData.fld.length];
        var rnd = new Random();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < ContactData.fld.length; j++)
                arr[j] = ContactData.fld[j] + ":" + randomString(rnd.nextInt(21));

            lst.add(new ContactData(arr));
        }
        return lst;
    }

    public static List<ContactData> negativeContactProvider() {
        var lst = new ArrayList<>(List.of(
                new ContactData(new String[]{"firstname:contact_name'", "", ""})));
        return lst;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int prvCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newCount = app.contacts().getCount();
        Assertions.assertEquals(prvCount + 1, newCount);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void cannotCreateContact(ContactData contact) {
        int prvCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newCount = app.contacts().getCount();
        Assertions.assertEquals(prvCount, newCount);
    }

}
