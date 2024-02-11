package ru.kis.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String browser;
    private SessionHelper session;
    private HttpSessionHelper http;
    private MailHelper mail;
    private JamesCliHelper JamesCli;
    private JamesApiHelper JamesApi;
    DeveloperMailHelper developerMail;
    private SoapApiHelper restApi;
    private RestApiHelper soapApi;
    private Properties properties;

    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;
    }

    public WebDriver driver() {
        if (driver == null) {
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
            //session().login(properties.getProperty("web.username"), properties.getProperty("web.password"));
        }
        return driver;
    }

    public SessionHelper session() {
        if (session == null) {
            session = new SessionHelper(this);
        }
        return session;
    }

    public HttpSessionHelper http() {
        if (http == null) {
            http = new HttpSessionHelper(this);
        }
        return http;
    }

    public JamesCliHelper JamesCli() {
        if (JamesCli == null) {
            JamesCli = new JamesCliHelper(this);
        }
        return JamesCli;
    }

    public JamesApiHelper JamesApi() {
        if (JamesApi == null) {
            JamesApi = new JamesApiHelper(this);
        }
        return JamesApi;
    }

    public MailHelper mail() {
        if (mail == null) {
            mail = new MailHelper(this);
        }
        return mail;
    }

    public DeveloperMailHelper developerMail() {
        if (developerMail == null) {
            developerMail = new DeveloperMailHelper(this);
        }
        return developerMail;
    }

    public SoapApiHelper rest() {
        if (restApi == null) {
            restApi = new SoapApiHelper(this);
        }
        return restApi;
    }

    public RestApiHelper soap() {
        if (soapApi == null) {
            soapApi = new RestApiHelper(this);
        }
        return soapApi;
    }

    public String property(String name) {
        return properties.getProperty(name);
    }

    public boolean isElementPresent(By locater) {
        try {
            driver().findElement(locater);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public int countElements(By locater) {
        return driver.findElements(locater).size();
    }

}
