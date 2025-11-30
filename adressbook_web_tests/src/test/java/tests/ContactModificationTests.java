package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {
    @Test
    void canModifyContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "name", "middlename", "lastname", "mobile",""));
        }
        var oldContact = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContact.size());
        var testData = new ContactData().withFirstName("modified name");
        app.contacts().modifyContact(oldContact.get(index), testData);
        /* 1й параметр это группа, которую хотим модифицировать,
        2й параметр содержит данные, которыми  заполнится группа*/
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContact);
        expectedList.set(index, testData.withId(oldContact.get(index).id()));

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем айдишники последователно
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
