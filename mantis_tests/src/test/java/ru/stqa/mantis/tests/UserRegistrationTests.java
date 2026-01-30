package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUserCmd() {
        //создать пользователя (адрес) на почтовом сервере (JamesHelper)
        var name = CommonFunctions.randomString(4);
        try {
            app.jamesCli().addUser(name + "@localhost", "password");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //заполняем форму создания и отправляем (браузер)
        app.session().registration(name, name + "@localhost");
        //ждем почту (MailHelper)
        var messages = app.mail().receive(name + "@localhost", "password", Duration.ofSeconds(10));
        //извлекаем ссылку из письма
        var text = messages.get(0).content();
        //проходим по ссылке и завершаем регистрацию (браузер)
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = null;
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end()); // присваиваем
        }
        if (url != null) {
            app.driver().get(url);
        } else {
            throw new RuntimeException("Ссылка не найдена");
        }
        //проверяем, что пользователь может залогиниться с новым паролем (HttpSessionHelper)
        app.session().submitRegistration(name, "password", "password");
        app.http().login(name, "password");
        app.http().isLoggedIn();
    }


    @Test
    void canRegisterUser() {
        var name = CommonFunctions.randomString(4);
        try {
            app.jamesCli().addUser(name + "@localhost", "password");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //заполняем форму создания и отправляем (браузер)
        app.session().registration(name, name + "@localhost");
        //ждем почту (MailHelper)
        var messages = app.mail().receive(name + "@localhost", "password", Duration.ofSeconds(10));
        //извлекаем ссылку из письма
        var text = messages.get(0).content();
        //проходим по ссылке и завершаем регистрацию (браузер)
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = null;
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end()); // присваиваем
        }
        if (url != null) {
            app.driver().get(url);
        } else {
            throw new RuntimeException("Ссылка не найдена");
        }
        //проверяем, что пользователь может залогиниться с новым паролем (HttpSessionHelper)
        app.session().submitRegistration(name, "password", "password");
        app.http().login(name, "password");
        app.http().isLoggedIn();

    }

    @Test
    void canRegisterUserApi() {
        var name = CommonFunctions.randomString(4);
        app.jamesApi().addUser(name + "@localhost", "password");

        app.rest().createUser(new UserData()
                .withUsername(name)
                .withPassword(CommonFunctions.randomString(4))
                .withEmail(name + "@localhost"));

        var messages = app.mail().receive(name + "@localhost", "password", Duration.ofSeconds(10));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = null;
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end()); // присваиваем
        }
        if (url != null) {
            app.driver().get(url);
        } else {
            throw new RuntimeException("Ссылка не найдена");
        }
        app.session().submitRegistration(name, "password", "password");
        app.http().login(name, "password");
        app.http().isLoggedIn();
    }
}
