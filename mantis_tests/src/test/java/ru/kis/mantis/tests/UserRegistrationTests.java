package ru.kis.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.kis.mantis.common.CommonFunc;
import ru.kis.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase {
    DeveloperMailUser user;

    @Test
    void canCreateUser() {
        String password = "password";

        // создать пользователя на почтовом сервере (developerMail)
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

        // заполняем форму и отправляем (браузер)
        app.session().registerUser(user.name(), email); //startCreation()

        // получаем (ждём) почту (developerMail)
        var message = app.developerMail().receive(user, Duration.ofSeconds(30));

        // извлекаем ссылку из письма
        var url = CommonFunc.extractUrl(message);

        // проходим по ссылке и завершаем регистрацию (браузер)
        app.session().updateUser(url, user.name(), password);

        // проверяем что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser() {
        if (user != null) app.developerMail().deleteUser(user);
    }

    public static Stream<String> randomUser() {
        return Stream.of(CommonFunc.randomString(8));
    }

    @ParameterizedTest
    @MethodSource("randomUser")
    void canCreateUserAPI(String username) {
        var email = String.format("%s@localhost", username);
        String password = "password";

        // создать пользователя на почтовом сервере (JamesApiHelper)
        app.JamesApi().addUser(email, password);

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

    @ParameterizedTest
    @MethodSource("randomUser")
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        String password = "password";

        // создать пользователя на почтовом сервере (JamesCliHelper)
        app.JamesCli().addUser(email, password);

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
