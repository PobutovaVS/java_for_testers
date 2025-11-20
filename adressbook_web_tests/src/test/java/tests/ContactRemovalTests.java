package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class ContactRemovalTests extends TestBase {
    private WebDriver driver;

    @Test
    public void CanRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("ddb", "dfvd", "ddfv", "dfvdfv"));
        }
        app.contacts().removeContact();
    }

}
