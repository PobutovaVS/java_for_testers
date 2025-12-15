package manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void removeGroup(GroupData group) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroups();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        openGroupsPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    private void submitGroupCreation() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.name("new"));
    }

    private void removeSelectedGroups() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.name("delete"));
    }


    private void returnToGroupsPage() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData group) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void initGroupModification() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.name("edit"));
    }

    private void selectGroup(GroupData group) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroups() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroups();

    }

    private void selectAllGroups() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
//        for (var checkbox : checkboxes) {
//            checkbox.click();  //цикл, который перебирает все элементы коллекции чекбокс
//        }
        // checkboxes.forEach(checkbox->checkbox.click());
        checkboxes.forEach(WebElement::click);
    }

    public List<GroupData> getList() {
        WebDriverWait wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        openGroupsPage();
        // var groups = new ArrayList<GroupData>();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));

        return spans.stream()
                .map(span -> {
                    var name = span.getText();
                    var checkbox = span.findElement(By.name("selected[]"));
                    var id = checkbox.getAttribute("value");
                    new GroupData().withId(id).withName(name);
                })
                .collect(Collectors.toList());

//        for (var span : spans) {
//            var name = span.getText();
//            var checkbox = span.findElement(By.name("selected[]"));
//            var id = checkbox.getAttribute("value");
//            groups.add(new GroupData().withId(id).withName(name));
//        }
//        return groups;
    }
}
