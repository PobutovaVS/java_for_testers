import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupCreationTests extends TestBase {

    @Test
    public void CanCreateGroup() {
        openGroupsPage();
        createGroup("group_name", "group header", "group footer");
    }

    @Test
    public void CanCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup("", "", "");
    }
}