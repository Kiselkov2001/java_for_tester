package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        int prvCount = app.groups().getCount();
        app.groups().removeGroup();
        int newCount = app.groups().getCount();
        Assertions.assertEquals(prvCount - 1, newCount);
    }

    @Test
    public void canRemoveAllGroups() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        int newCount = app.groups().getCount();
        Assertions.assertEquals(0, newCount);
    }

}
