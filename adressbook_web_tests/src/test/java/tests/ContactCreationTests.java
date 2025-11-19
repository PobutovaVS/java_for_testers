package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;


public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {

        app.contacts().createContact(new ContactData("firstname", "middlename", "lastname", "8999999999"));
    }

}
