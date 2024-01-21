package manager;

import model.ContactData;
import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var lst = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("select group_id, group_name, " +
                "group_header, group_footer from group_list")){

            //conn.setAutoCommit(true);

            while (result.next()) {
                lst.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

    public List<ContactData> getContactList() {
        var lst = new ArrayList<ContactData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("select id, lastname, " +
                     "firstname from addressbook where deprecated is null")){

            //conn.setAutoCommit(true);

            while (result.next()) {
                lst.add(new ContactData()
                        .withId(result.getString("id"))
                        .withLastName(result.getString("lastname"))
                        .withFirstName(result.getString("firstname")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

}
