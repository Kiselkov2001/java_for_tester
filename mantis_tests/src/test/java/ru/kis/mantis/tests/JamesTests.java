package ru.kis.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.kis.mantis.common.CommonFunc;

public class JamesTests extends TestBase {
    @Test
    void canCreateUser() {
        app.JamesCli().addUser(String.format("%s@localhost", CommonFunc.randomString(8)),"password");
    }
}
