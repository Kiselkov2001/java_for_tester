package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() {
        var lst = new ArrayList<>(List.of(
                new GroupData().withName("group_name2")));
        for (var name : List.of("", "group_name"))
            for (var header : List.of("", "group_header"))
                for (var footer : List.of("", "group_footer"))
                    lst.add(new GroupData()
                            .withName(name)
                            .withHeader(header)
                            .withFooter(footer));
        for (int i = 0; i < 5; i++)
            lst.add(new GroupData()
                    .withName(randomString(5))
                    .withHeader(randomString(5))
                    .withFooter(randomString(5)));
        return lst;
    }

    public static List<GroupData> negativeGroupProvider() {
        var lst = new ArrayList<>(List.of(
                new GroupData().withName("group_name'")));
        return lst;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        var prvList = app.groups().getList();
        app.groups().createGroup(group);
        var newList = app.groups().getList();
        newList.sort(GroupData::compareById);
        var lst = new ArrayList<>(prvList);
        lst.add(group.withId(newList.get(newList.size() - 1).id()).withHeader("").withFooter(""));
        lst.sort(GroupData::compareById);
        Assertions.assertEquals(lst, newList);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void cannotCreateGroup(GroupData group) {
        var prvList = app.groups().getList();
        app.groups().createGroup(group);
        var newList = app.groups().getList();
        Assertions.assertEquals(prvList, newList);
    }

}
