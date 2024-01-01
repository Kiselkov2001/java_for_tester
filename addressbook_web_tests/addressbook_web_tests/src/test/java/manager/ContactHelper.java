package manager;

import model.ContactData;
import org.openqa.selenium.By;

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

    public void removeContact() {
        selectContact();
        removeSelectedContact();
    }

    public boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void removeSelectedContact() {
        acceptAlert();
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void submitContactCreation() {
        click(By.xpath("(//input[@name=\'submit\'])[2]"));
    }

    private void fillContactForm(ContactData contact) {
        String[][] arr = contact.getEntryDictonary();

        for (String[] a: arr ) {
            type(By.name(a[0]), a[1]);
        }
    }
}
