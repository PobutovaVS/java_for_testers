package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withMiddleName(CommonFunctions.randomString(10))
                    .withMobile(CommonFunctions.randomString(10)));

            var contacts = app.hbm().getContactList();
            var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                    Stream.of(contact.home(), contact.mobile(), contact.work(), contact.phone2())
                            .filter(s -> s != null && !"".equals(s))
                            .collect(Collectors.joining("\n"))));
            var phones = app.contacts().getPhones();
            Assertions.assertEquals(expected, phones);
        }
    }

    @Test
    void testAddress() {
        var contacts = app.hbm().getContactList();
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withMiddleName(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10))
                    .withEmail(CommonFunctions.randomString(10))
                    .withEmail2(CommonFunctions.randomString(10))
                    .withEmail3(CommonFunctions.randomString(10)));
            app.refreshPage();
        }
        app.refreshPage();
        var contact = contacts.get(0);
        var address = app.contacts().getAddress(contact);
        var expected = contact.address();
        Assertions.assertEquals(expected, address);
    }

    @Test
    void testEmails() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withMiddleName(CommonFunctions.randomString(10))
                    .withEmail(CommonFunctions.randomString(10))
                    .withEmail2(CommonFunctions.randomString(10))
                    .withEmail3(CommonFunctions.randomString(10)));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !"".equals(s)) //s не должно быть равно 0 и не должно быть равно пустой строке
                        .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getEmails();
        Assertions.assertEquals(expected, phones);

    }
}


