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
                new ContactData()
                        .withFirstName("contact_name1")
                        .withLastName("1")
                        .withAddress("2")
                        .withEmail("3")
                        .withHome("4")));
        for (var firstname : List.of("", "firstname"))
            for (var address : List.of("", "address"))
                for (var lastname : List.of("", "lastname"))
                    lst.add(new ContactData()
                            .withFirstName(firstname)
                            .withLastName(lastname)
                            .withAddress(address));

        var rnd = new Random();
        for (int i = 0; i < 5; i++) {
            lst.add(new ContactData()
                    .withFirstName(randomString(rnd.nextInt(21)))
                    .withLastName(randomString(rnd.nextInt(21)))
                    .withAddress(randomString(rnd.nextInt(21)))
                    .withEmail(randomString(rnd.nextInt(21)))
                    .withHome(randomString(rnd.nextInt(21))));
        }
        return lst;
    }

    public static List<ContactData> negativeContactProvider() {
        return new ArrayList<>(List.of(
                new ContactData().withLastName("contact_name'")));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var prvList = app.contacts().getList();
        app.contacts().createContact(contact);
        var newList = app.contacts().getList();
        Assertions.assertEquals(prvList.size() + 1, newList.size());

        newList.sort(ContactData::compareById);
        prvList.sort(ContactData::compareById);

        prvList.add(contact.withId(newList.get(newList.size() - 1).id()));
        Assertions.assertEquals(prvList, newList);

//        var arr1 = prvList.stream().map(ContactData::repr).toArray(String[]::new);
//        var arr2 = newList.stream().map(ContactData::repr).toArray(String[]::new);

//        Assertions.assertArrayEquals(arr1, arr2);
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
