package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        int contactCount=app.contacts().getCount();
        app.contacts().createContact(new ContactData("firstname", "middlename",
                "lastname", "8999999999"));
        int newContactCount=app.contacts().getCount();
        Assertions.assertEquals(contactCount+1,newContactCount);
    }
}
