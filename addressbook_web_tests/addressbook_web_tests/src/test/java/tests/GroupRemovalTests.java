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
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var prvList = app.hbm().getGroupList(); //app.groups().getList(); //app.jdbc().getGroupList();
        var rnd = new Random();
        int index = rnd.nextInt(prvList.size());
        app.groups().removeGroup(prvList.get(index));
        var newList = app.hbm().getGroupList(); //app.groups().getList(); //app.jdbc().getGroupList();
        Assertions.assertEquals(prvList.size() - 1, newList.size());
        var lst = new ArrayList<>(prvList);
        lst.remove(index);
        Assertions.assertEquals(lst, newList);
    }

    @Test
    public void canRemoveAllGroups() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        long newCount = app.hbm().getGroupCount(); //app.groups().getCount(); //app.jdbc().getGroupList().size();
        Assertions.assertEquals(0, newCount);
    }

}
