package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
            app.hbm().createContact(new ContactData("", "ddb", "dfvd", "ddfv", "dfvdfv", "", "", "", "","","","",""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }


    @Test
    public void CanRemoveContactFromGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "gg", "gg", "gg"));
        }
        var groups = app.hbm().getGroupList();
        var maxId = groups.get(groups.size() - 1).id();
        var group = new GroupData().withId(maxId);
        var contactsInGroup = app.hbm().getContactsInGroup(group);
        if (contactsInGroup.isEmpty()) {
            var contact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withMiddleName(CommonFunctions.randomString(20))
                    .withLastName(CommonFunctions.randomString(30))
                    .withMobile(CommonFunctions.randomString(10));
            app.contacts().createContactInGroup(contact, group);
        }
        var oldListOfContacts = app.hbm().getContactsInGroup(group);
        var contactForRemove = oldListOfContacts.get(0);
        app.contacts().removeContactFromGroup(contactForRemove, group);
        var newListOfContacts = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldListOfContacts.size() - 1, newListOfContacts.size());
    }
}
