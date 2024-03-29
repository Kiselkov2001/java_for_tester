package manager;

import io.qameta.allure.Step;
import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void initContactCreation() {
        if (isElementPresent(By.linkText("add new"))) {
            click(By.linkText("add new"));
        }
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void selectGroupFilter(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void selectToGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    public void removeContact(int index) {
        selectContact(index);
        removeSelectedContacts();
    }

    @Step
    public void removeContact(ContactData contac) {
        selectContact(contac);
        removeSelectedContacts();
    }

    public void modifyContactByIndex(int index, ContactData contact) {
        clickButtonEditByIndex(index);
        fillContactForm(contact);
        submitContactModify();
        returnToHomePage();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        clickButtonEdit(contact);
        fillContactForm(modifiedContact);
        submitContactModify();
        returnToHomePage();
    }

    private void clickButtonEditByIndex(int index) {
        var tr = manager.driver.findElements(By.cssSelector("#maintable tr[name='entry']")).get(index);
        var btn = tr.findElement(By.cssSelector("img[title='Edit']"));
        btn.click();
    }

    private void clickButtonEdit(ContactData contact) {
        var tr = manager.driver.findElement(By.cssSelector(String.format("tr:has(input[name='selected[]'][value='%s'])", contact.id())));
        var btn = tr.findElement(By.cssSelector("img[title='Edit']"));
        btn.click();
    }

    public boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    private void selectContact(int index) {
        manager.driver.findElements(By.name("selected[]")).get(index).click();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void removeSelectedContacts() {
        acceptAlert();
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void returnToHome() {
        click(By.linkText("home"));
    }

    private void submitContactCreation() {
        click(By.xpath("(//input[@name=\'submit\'])[2]"));
    }

    private void submitContactModify() {
        click(By.cssSelector("input[name='update']"));
    }

    private void fillContactForm(ContactData contact) {

        String[][] arr = new String[][]{
                new String[]{"lastname", contact.lastname()},
                new String[]{"firstname", contact.firstname()},
                new String[]{"address", contact.address()},
                new String[]{"email", contact.email()},
                new String[]{"email2", contact.email2()},
                new String[]{"email3", contact.email3()},
                new String[]{"home", contact.home()},
                new String[]{"mobile", contact.mobile()},
                new String[]{"work", contact.work()},
                new String[]{"photo", contact.photo()},
        };

        for (String[] a : arr) {
            if (!a[1].isEmpty())
                if (a[0] != "photo")
                    type(By.name(a[0]), a[1]);
                else
                    attach(By.name(a[0]), a[1]);
        }
    }

    public int getCount() {
        return manager.countElements(By.name("selected[]"));
    }

    public void removeAllContacts() {
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var lst = manager.driver.findElements(By.name("selected[]"));
        for (var chk : lst) chk.click();
    }

    public List<ContactData> getList() {
        returnToHome();
        var cnt = new ArrayList<ContactData>();
        var lst = manager.driver.findElements(By.cssSelector("#maintable tr[name='entry']"));
        for (var s : lst) {
            var id = s.findElement(By.xpath("./td[1]/input")).getAttribute("value");
            var lastname = s.findElement(By.xpath("./td[2]")).getText();
            var firstname = s.findElement(By.xpath("./td[3]")).getText();
            var address = s.findElement(By.xpath("./td[4]")).getText();
            var email = s.findElement(By.xpath("./td[5]")).getText();
            var home = s.findElement(By.xpath("./td[6]")).getText();
            cnt.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstname)
                    .withLastName(lastname)
                    .withAddress(address)
                    .withEmail(email)
                    .withHome(home));
        }
        return cnt;
    }

    public void addContactInGroup(ContactData contact, GroupData group) {
        returnToHome();
        selectContact(contact);
        selectToGroup(group);
        clickButtomAddTo();
        returnToHome();
    }

    public void clickButtomAddTo() {
        click(By.cssSelector("input[value='Add to']"));
    }

    public void delContactInGroup(ContactData contact, GroupData group) {
        returnToHome();
        selectGroupFilter(group);
        selectContact(contact);
        clickButtomRemoveFrom();
        returnToHome();
    }

    public void clickButtomRemoveFrom() {
        click(By.cssSelector("input[name='remove']"));
    }

    public Map<String, String> getPhones() {
        var dict = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            dict.put(id, phones);
        }
        return dict;
    }

    public Map<String, String> getEmails() {
        var dict = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var emails = row.findElements(By.tagName("td")).get(4).getText();
            dict.put(id, emails);
        }
        return dict;
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public String getEmails(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText();
    }

    public String getAddress(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText();
    }

}
