package tests;

import common.CommonFunc;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class GroupModificationTests extends TestBase {
    @Test
    public void canModifyGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var prvList = app.hbm().getGroupList(); //app.groups().getList(); //app.jdbc().getGroupList();
        var rnd = new Random();
        int index = rnd.nextInt(prvList.size());
        var group = new GroupData().withName(CommonFunc.randomString(10));
        app.groups().modifyGroup(prvList.get(index), group);
        var newList = app.hbm().getGroupList(); //app.groups().getList(); //app.jdbc().getGroupList();
        var lst = new ArrayList<>(prvList);
        lst.set(index, group.withId(lst.get(index).id()));
//        lst.sort(GroupData::compareById);
//        newList.sort(GroupData::compareById);
//        Assertions.assertEquals(lst, newList);
        Assertions.assertEquals(Set.copyOf(lst), Set.copyOf(newList));
    }

}
