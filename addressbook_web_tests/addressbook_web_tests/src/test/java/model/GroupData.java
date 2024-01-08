package model;

import org.openqa.selenium.WebElement;

import java.util.Comparator;

public record GroupData(String id, String name, String header, String footer) {
    public GroupData() {
        this("", "", "", "");
    }

    public GroupData withId(String id) { return new GroupData(id, this.name, this.header, this.footer); }

    public GroupData withName(String name) {
        return new GroupData(this.id, name, this.header, this.footer);
    }

    public GroupData withHeader(String header) { return new GroupData(this.id, this.name, header, this.footer); }

    public GroupData withFooter(String footer) {
        return new GroupData(this.id, this.name, this.header, footer);
    }

    public static int compareById(GroupData o1, GroupData o2) {
        return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    }
}
