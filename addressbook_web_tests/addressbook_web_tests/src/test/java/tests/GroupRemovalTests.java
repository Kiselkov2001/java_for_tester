package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var prvList = app.groups().getList();
        var rnd = new Random();
        int index = rnd.nextInt(prvList.size());
        app.groups().removeGroup(prvList.get(index));
        var newList = app.groups().getList();
        Assertions.assertEquals(prvList.size() - 1, newList.size());
        var lst = new ArrayList<>(prvList);
        lst.remove(index);
        Assertions.assertEquals(lst, newList);
    }

    @Test
    public void canRemoveAllGroups() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        int newCount = app.groups().getCount();
        Assertions.assertEquals(0, newCount);
    }

}
