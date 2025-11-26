package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        if (!manager.isElementPresent(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"))) {
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

    public void removeContact(ContactData contact) {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        //openHomePage();
        selectContact(contact);
        removeSelectedContacts();
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

    private void selectContact(ContactData contact) {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void removeSelectedContacts() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.name("delete"));
    }

    public int getCount() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();  //цикл, который перебирает все элементы коллекции чекбокс
        }
    }

    public List<ContactData> getList() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        var contacts = new ArrayList<ContactData>();
        var tds = manager.driver.findElements(By.name("entry"));
        for (var td : tds) {
            var first_name = td.findElement(By.xpath("./td[3]"));
            var last_name = td.findElement(By.xpath("./td[2]"));
            var firstname = first_name.getText();
            var lastname = last_name.getText();
            var checkbox = td.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(firstname));
        }
        return contacts;
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href^='edit.php?id=%s']", contact.id())));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }
}

