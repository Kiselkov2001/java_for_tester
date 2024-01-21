package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunc;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
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
                    .withFirstName(CommonFunc.randomString(rnd.nextInt(21)))
                    .withLastName(CommonFunc.randomString(rnd.nextInt(21)))
                    .withAddress(CommonFunc.randomString(rnd.nextInt(21)))
                    .withEmail(CommonFunc.randomString(rnd.nextInt(21)))
                    .withHome(CommonFunc.randomString(rnd.nextInt(21))));
        }
        return lst;
    }

    public static List<ContactData> contactProviderJSON() throws IOException {
        var lst = new ArrayList<ContactData>();
        ObjectMapper mapper = new ObjectMapper();
        //ObjectMapper mapper = new XmlMapper(); //contacts.xml
        var value = mapper.readValue(new File("contacts.json"),
                new TypeReference<List<ContactData>>() {
                });
        lst.addAll(value);
        return lst;
    }

    public static List<ContactData> negativeContactProvider() {
        return new ArrayList<>(List.of(
                new ContactData().withLastName("contact_name'")));
    }

    public static List<ContactData> singleRandomContact() {
        return List.of(new ContactData()
                .withFirstName(CommonFunc.randomString(10))
                .withLastName(CommonFunc.randomString(15)));
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContact(ContactData contact) {
        var prvList = app.jdbc().getContactList(); //app.contacts().getList();
        app.contacts().createContact(contact);
        var newList = app.jdbc().getContactList(); //app.contacts().getList();
        Assertions.assertEquals(prvList.size() + 1, newList.size());

        newList.sort(ContactData::compareById);
        prvList.sort(ContactData::compareById);

        prvList.add(contact.withId(newList.get(newList.size() - 1).id()));
        Assertions.assertEquals(prvList, newList);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void cannotCreateContact(ContactData contact) {
        int prvCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newCount = app.contacts().getCount();
        Assertions.assertEquals(prvCount, newCount);
    }

    @Test
    public void canCreateContact() {
        var contact = new ContactData()
                .withLastName(CommonFunc.randomString(10))
                .withFirstName(CommonFunc.randomString(10))
                .withPhoto(CommonFunc.randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);
    }

}
