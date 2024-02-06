package ru.kis.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class MailTests extends TestBase {
    String username = "user2";

    @Test
    void canReceiveEmail() {
        var messages = app.mail().receive(String.format("%s@localhost", username), "password", Duration.ofSeconds(10));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);
    }

    @Test
    void canDrainInbox() {
        app.mail().drain(String.format("%s@localhost", username), "password");
    }

    @Test
    void canExtractUrl() {
        var messages = app.mail().receive(String.format("%s@localhost", username), "password", Duration.ofSeconds(10));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
    }

}
