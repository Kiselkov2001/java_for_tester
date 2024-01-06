package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() {
        var lst = new ArrayList<GroupData>(List.of(
                new GroupData("group_name2", "", "")));
        for (var name : List.of("", "group_name"))
            for (var header : List.of("", "group_header"))
                for (var footer : List.of("", "group_footer"))
                    lst.add(new GroupData(name, header, footer));
        for (int i = 0; i < 5; i++)
            lst.add(new GroupData(randomString(5), randomString(5), randomString(5)));
        return lst;
    }

    public static List<GroupData> negativeGroupProvider() {
        var lst = new ArrayList<GroupData>(List.of(
                new GroupData("group_name'", "", "")));
        return lst;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        int prvCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newCount = app.groups().getCount();
        Assertions.assertEquals(prvCount + 1, newCount);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void cannotCreateGroup(GroupData group) {
        int prvCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newCount = app.groups().getCount();
        Assertions.assertEquals(prvCount, newCount);
    }

}
