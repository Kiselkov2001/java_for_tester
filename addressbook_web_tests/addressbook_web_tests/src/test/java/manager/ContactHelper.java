package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

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

    public void removeContact(int index) {
        selectContact(index);
        removeSelectedContacts();
    }

    public void modifyContact(int index, ContactData contact) {
        clickButtonEdit(index);
        fillContactForm(contact);
        submitContactModify();
        returnToHomePage();
    }

    private void clickButtonEdit(int index) {
        var tr = manager.driver.findElements(By.cssSelector("#maintable tr[name='entry']")).get(index);
        var btn = tr.findElement(By.cssSelector("img[title='Edit']"));
        btn.click();
    }

    public boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    private void selectContact1st() {
        click(By.name("selected[]"));
    }

    private void selectContact(int index) {
        manager.driver.findElements(By.name("selected[]")).get(index).click();
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
        String[][] arr = contact.getEntryDictonary();

        for (String[] a : arr) {
            if (!a[1].isEmpty())
                type(By.name(a[0]), a[1]);
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
            var lastname = s.findElement(By.xpath("./td[2]")).getText();
            var firstname = s.findElement(By.xpath("./td[3]")).getText();
            cnt.add(new ContactData(ContactData.getTuple(lastname, firstname)));
        }
        return cnt;
    }

}
