package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Properties;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    private ContactHelper contacts;
    private Properties properties;

    public void init(String browser, Properties properties) {
        if (driver != null) return;
        this.properties = properties;

        if ("firefox".equals(browser)) {
            driver = new FirefoxDriver();
        } else if ("chrome".equals(browser)) {
            driver = new ChromeDriver();
        } else {
            throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
        }

        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
        driver.get(properties.getProperty("web.baseURL"));
        driver.manage().window().setSize(new Dimension(1062, 698));
        session().login(properties.getProperty("web.username"), properties.getProperty("web.password"));
    }

    public LoginHelper session() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }

    public GroupHelper groups() {
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public ContactHelper contacts() {
        if (contacts == null) {
            contacts = new ContactHelper(this);
        }
        return contacts;
    }

    public boolean isElementPresent(By locater) {
        try {
            driver.findElement(locater);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public int countElements(By locater) {
        return driver.findElements(locater).size();
    }

}
