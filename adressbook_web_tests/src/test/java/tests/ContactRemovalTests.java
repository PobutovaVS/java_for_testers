package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void CanRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("ddb", "dfvd", "ddfv", "dfvdfv"));
        }
        app.contacts().removeContact();
    }
}
