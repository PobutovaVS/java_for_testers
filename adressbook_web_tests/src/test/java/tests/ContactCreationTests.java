package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
    /*    for (var name : List.of("", "name")) {
            for (var middlename : List.of("", "middlename")) {
                for (var lastname : List.of("", "lastname")) {
                    for (var mobile : List.of("", "phone")) {
                        result.add(new ContactData().withFirstName("firstname").withMiddleName("middlename").withLastName("lastname").withMobile("phone"));
                    }
                }
            }
        }*/
        var json = "";
        try (var reader = new FileReader("contacts.json");
             var breader = new BufferedReader(reader)
        ) {
            var line = breader.readLine();
            while (line != null) {
                json = json + line;
                line = breader.readLine();
            }
        }
        //var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }

    public static List<ContactData> singleRandomContact() {
        return List.of(new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withMiddleName(CommonFunctions.randomString(20))
                .withLastName(CommonFunctions.randomString(30))
                .withMobile(CommonFunctions.randomString(10)));
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void CanCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем айдишники последователно
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);

    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(new ContactData()
                .withLastName("name'")
                .withFirstName("")
                .withMiddleName("")
                .withMobile("")
                .withPhoto("")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void CanNotCreateContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Assertions.assertEquals(newContacts, oldContacts);
    }

    @Test
    public void CanCreateOneContact() {
        var contact = new ContactData()
                .withLastName(CommonFunctions.randomString(10))
                .withFirstName(CommonFunctions.randomString(10))
                .withMiddleName(CommonFunctions.randomString(10))
                .withMobile(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
        app.hbm().createContact(contact);

    }

    @Test
    public void CanCreateContactsInGroup() {
        var contact = new ContactData()
                .withLastName(CommonFunctions.randomString(10))
                .withFirstName(CommonFunctions.randomString(10))
                .withMiddleName(CommonFunctions.randomString(10))
                .withMobile(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "gg", "gg", "gg"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContactInGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());

    }

    @Test
    public void CanAddContactToGroup() {
        if (!app.hbm().getContactList().isEmpty()) {
           app.contacts().removeAllContacts();
        }
        app.hbm().getContactList();

        app.contacts().createContact(new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withMiddleName(CommonFunctions.randomString(10))
                .withMobile(CommonFunctions.randomString(10)));

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "gg", "gg", "gg"));
        }
        var group = app.hbm().getGroupList().get(0);
        var contacts = app.hbm().getContactList();
        var oldRelated = app.hbm().getContactsInGroup(group);
        var contact = contacts.get(0);
        app.contacts().addContactToGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }
}
