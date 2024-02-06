package ru.kis.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.kis.mantis.common.CommonFunc;

import java.time.Duration;

public class UserRegistrationTests extends TestBase {
    private String username = CommonFunc.randomString(8);
    private String password = "password";

    @Test
    void canRegisterUser(){
        var email = String.format("%s@localhost", username);

        // создать пользователя на почтовом сервере (JamesHelper)
        app.JamesCli().addUser(email,password);

        // заполняем форму и отправляем (браузер)
        app.session().registerUser(username, email);

        // получаем (ждём) почту (MailHelper)
        app.mail().receive(email, password, Duration.ofSeconds(10));

        // извлекаем ссылку из письма
        var url = app.mail().extractUrl(email, password);

        // проходим по ссылке и завершаем регистрацию (браузер)
        app.session().updateUser(url, username, password);

        // проверяем что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
