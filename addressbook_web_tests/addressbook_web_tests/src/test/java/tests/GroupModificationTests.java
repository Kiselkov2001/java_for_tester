package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupModificationTests extends TestBase {
    @Test
    public void canModifyGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var prvList = app.jdbc().getGroupList(); //app.groups().getList();
        var rnd = new Random();
        int index = rnd.nextInt(prvList.size());
        var group = new GroupData().withName("modified name");
        app.groups().modifyGroup(prvList.get(index), group);
        var newList = app.jdbc().getGroupList(); //app.groups().getList();
        var lst = new ArrayList<>(prvList);
        lst.set(index, group.withId(lst.get(index).id()));
        lst.sort(GroupData::compareById);
        newList.sort(GroupData::compareById);
        Assertions.assertEquals(lst, newList);
    }

}
