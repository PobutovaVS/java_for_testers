package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void CanRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData()
                    .withFirsName("vdfv")
                    .withMiddleName("dsdvs")
                    .withLastName("dvfdvf")
                    .withMobile("vvdfv"));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size()); //выбираем рандомный индекс из списка
        app.contacts().removeContact(oldContacts.get(index)); // удаляем этот контакт
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts); //строим список? с которым мы будем сранивать newGroups(его копию)
        expectedList.remove(index);// удалем этот заданный контакт из ожидаемого списка (из копии старого)
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void CanRemoveAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "ddb", "dfvd", "ddfv", "dfvdfv"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

}
