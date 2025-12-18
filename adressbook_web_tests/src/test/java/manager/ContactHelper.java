package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactPage() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        if (!manager.isElementPresent(By.name("Edit / add address book entry"))) {
            click(By.linkText("add new"));
        }
    }

    public void openHomePage() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        if (!manager.isElementPresent(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"))) {
            click(By.linkText("home page"));
        }
    }

    public void clickHomePage() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            click(By.cssSelector("#logo"));
    }

    public void returnToHomePage() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.cssSelector("a[href^='edit.php?id=%s']"));


    }

    public boolean isContactPresent() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        openContactPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        openContactPage();
        //initContactCreation();
        fillContactForm(contact);
        submitCreationContact();
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        //returnToHomePage();
        clickHomePage();
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        refreshPage();
    }

    private void selectGroup(GroupData group) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void createContactInGroup(ContactData contact, GroupData group) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        openContactPage();
        //initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitCreationContact();
        returnToHomePage();
        clickHomePage();
    }

    public void refreshPage() {
        ((JavascriptExecutor) manager.driver).executeScript("location.reload()");
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        openHomePage();
        // createContact(contact);
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        selectContact(contact);
        selectGroupForContact(group);
        addToGroup();
        returnToHomePage();
    }

    private void addToGroup() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.name("add"));
    }

    private void selectGroupForContact(GroupData group) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());

    }


    public void removeContactFromGroup(ContactData contact, GroupData group) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        openHomePage();
        selectContactGroup(group);
        selectContact(contact);
        removeSelectedContactFromGroup();
        returnToHomePage();

    }

    private void removeSelectedContactFromGroup() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.xpath("//input[@name=\'remove\']"));
    }

    private void selectContactGroup(GroupData group) {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }


    public void removeContact(ContactData contact) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        //openHomePage();
        selectContact(contact);
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        removeSelectedContacts();
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        returnToHomePage();
    }

    private void submitCreationContact() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("mobile"), contact.mobile());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("email3"), contact.email3());
        //attach(By.name("photo"), contact.photo());
    }


    private void initContactCreation() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.linkText("add new"));
    }

    private void selectContact(ContactData contact) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void removeSelectedContacts() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.xpath("//input[@name=\'delete\']"));
    }

    public int getCount() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();  //цикл, который перебирает все элементы коллекции чекбокс
        }
    }

    public List<ContactData> getList() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        var contacts = new ArrayList<ContactData>();
        var tds = manager.driver.findElements(By.name("entry"));
        for (var td : tds) {
            var first_name = td.findElement(By.xpath("./td[3]"));
            var last_name = td.findElement(By.xpath("./td[2]"));
            var firstname = first_name.getText();
            var lastname = last_name.getText();
            var checkbox = td.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return contacts;
    }

    private void initContactModification(ContactData contact) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.cssSelector(String.format("a[href^='edit.php?id=%s']", contact.id())));
    }

    private void submitContactModification() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.name("update"));
    }


    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();

    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public String getAddress(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText();
    }

    public Map<String, String> getEmails() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, phones);
        }
        return result;
    }
}

