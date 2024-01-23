package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunc;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
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
                    .withName(CommonFunc.randomString(5))
                    .withHeader(CommonFunc.randomString(5))
                    .withFooter(CommonFunc.randomString(5)));
        return lst;
    }

    public static List<GroupData> groupProviderJSON() throws IOException {
        var lst = new ArrayList<GroupData>();
        ObjectMapper mapper = new ObjectMapper();
//        var str = new File("").getAbsolutePath().toString();
//        var f = new File("groups.json");
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>() {
        });
        lst.addAll(value);
        return lst;
    }

    public static List<GroupData> negativeGroupProvider() {
        return new ArrayList<>(List.of(
                new GroupData().withName("group_name'")));
    }

    public static List<GroupData> singleRandomGroup() {
        return List.of(new GroupData()
                .withName(CommonFunc.randomString(5))
                .withHeader(CommonFunc.randomString(10))
                .withFooter(CommonFunc.randomString(20)));
    }

    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroup(GroupData group) {
        var prvList = app.hbm().getGroupList(); //app.groups().getList(); //app.jdbc().getGroupList();
        app.groups().createGroup(group);
        var newList = app.hbm().getGroupList(); //app.groups().getList(); //app.jdbc().getGroupList();
        newList.sort(GroupData::compareById);
        var maxId = newList.get(newList.size() - 1).id();

        var lst = new ArrayList<>(prvList);
        lst.add(group.withId(maxId));
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
