package ru.kis.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SessionHelper extends HelperBase {
    public SessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.xpath("//input[@type='submit']"));
        type(By.name("password"), password);
        click(By.xpath("//input[@type='submit']"));
    }

    public boolean isLoggedIn() {
        return manager.isElementPresent(By.cssSelector("span.user-info"));
    }

    public void updateUser(String url, String username, String password) {
        manager.driver().get(url);

        WebDriverWait wait = new WebDriverWait(manager.driver(), Duration.ofSeconds(10));
//        var elem = wait.until(ExpectedConditions.visibilityOfElementLocated
//                (By.xpath(String.format("//span[text()='Edit Account - %s']", username))));

        var elem = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("input#realname")));
        elem.sendKeys(username);

        elem = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("input#password")));
        elem.sendKeys(password);

        elem = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("input#password-confirm")));
        elem.sendKeys(password);

        elem = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("button[type='submit']")));
        elem.click();
    }

    public void registerUser(String username, String email) {
        WebDriverWait wait = new WebDriverWait(manager.driver(), Duration.ofSeconds(10));
        var elem = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("a[href='signup_page.php']")));
        elem.click();

        elem = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("input#username")));
        elem.sendKeys(username);

        elem = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("input#email-field")));
        elem.sendKeys(email);

        elem = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("input[type='submit']")));
        elem.click();
    }
}
