package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {
    public SessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }

    public void registration(String name, String mail) {
        click(By.xpath("//a[@href=\'signup_page.php\']"));
        type(By.name("username"), name);
        type(By.name("email"), mail);
        click(By.cssSelector("input[type='submit']"));

    }

    public void submitRegistration(String name, String password1, String password2) {
        type(By.name("realname"), name);
        type(By.name("password"), password1);
        type(By.name("password_confirm"), password2);
        click(By.cssSelector("span[class='bigger-110']"));

    }
}
