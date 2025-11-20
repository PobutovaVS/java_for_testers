package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactPage() {
        if (!manager.isElementPresent(By.name("Edit / add address book entry"))) {
            click(By.linkText("add new"));
        }
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.name("Number of results:"))) {
            click(By.linkText("home page"));
        }
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isContactPresent() {
        openContactPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        openContactPage();
        initContactCreation();
        fillContactForm(contact);
        submitCreationContact();
    }

    public void removeContact() {
        openHomePage();
        selectContact();
        removeSelectedContact();
        returnToHomePage();
    }

    private void submitCreationContact() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("mobile"), contact.mobile());
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void removeSelectedContact() {
        click(By.name("delete"));
    }

}

