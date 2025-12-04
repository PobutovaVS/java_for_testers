package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void CanRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName("vdfv")
                    .withMiddleName("dsdvs")
                    .withLastName("dvfdvf")
                    .withMobile("vvdfv"));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size()); //выбираем рандомный индекс из списка
        app.contacts().removeContact(oldContacts.get(index)); // удаляем этот контакт
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts); //строим список? с которым мы будем сранивать newGroups(его копию)
        expectedList.remove(index);// удалем этот заданный контакт из ожидаемого списка (из копии старого)
        Assertions.assertEquals(newContacts, expectedList);
    }


    @Test
    public void CanRemoveAllContactsAtOnce() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "ddb", "dfvd", "ddfv", "dfvdfv",""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

}
