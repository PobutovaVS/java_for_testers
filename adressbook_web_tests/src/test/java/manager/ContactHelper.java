package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactPage() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
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
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.linkText("home page"));
    }

    public boolean isContactPresent() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        openContactPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        openContactPage();
        initContactCreation();
        fillContactForm(contact);
        submitCreationContact();
        returnToHomePage();
    }

    public void removeContact() {
        //openHomePage();
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        selectContact();
        WebDriverWait wait1 = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        removeSelectedContact();
        WebDriverWait wait2 = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        returnToHomePage();
    }

    private void submitCreationContact() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("mobile"), contact.mobile());
    }

    private void initContactCreation() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.linkText("add new"));
    }

    private void selectContact() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.name("selected[]"));
    }

    private void removeSelectedContact() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.name("delete"));
    }

    public int getCount() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        return manager.driver.findElements(By.name("selected[]")).size();
    }

}

