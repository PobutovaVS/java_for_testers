package manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public void createGroup(GroupData group) {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void removeGroup(GroupData group) {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroups();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        openGroupsPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    private void submitGroupCreation() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.name("new"));
    }

    private void removeSelectedGroups() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.name("delete"));
    }


    private void returnToGroupsPage() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData group) {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void initGroupModification() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.name("edit"));
    }

    private void selectGroup(GroupData group) {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroups() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroups();

    }

    private void selectAllGroups() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();  //цикл, который перебирает все элементы коллекции чекбокс
        }
    }

    public List<GroupData> getList() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        openGroupsPage();
        var groups = new ArrayList<GroupData>();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        for (var span : spans) {
            var name = span.getText();
            var checkbox = span.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
