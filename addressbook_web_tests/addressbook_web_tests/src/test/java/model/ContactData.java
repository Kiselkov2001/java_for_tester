package model;

import java.util.HashMap;
import java.util.Map;

public class ContactData {
    public static String[] fld = new String[]{
            "firstname",
            "middlename",
            "lastname",
            "nickname",
            "title",
            "company",
            "address",
            "home",
            "email"};

    HashMap<String, String> dicBase = initDictonary("", false);

    public ContactData() {
        clearDictonary(dicBase);
    }

    public ContactData(String[] arr) {
        clearDictonary(dicBase);
        for (int i = 0; i < arr.length; i++) {
            var x = arr[i].split(":");
            if (x.length != 2) continue;
            if (!dicBase.containsKey(x[0])) continue;
            dicBase.put((String) x[0], x[1]);
        }
    }

    public ContactData(HashMap<String, String> dic) {
        for (Map.Entry entry : dic.entrySet()) {
            if (!this.dicBase.containsKey(entry.getKey())) continue;
            this.dicBase.put((String) entry.getKey(), (String) entry.getValue());
        }
    }

    public static HashMap<String, String> initDictonary(String sfx, boolean forceClear) {
        HashMap<String, String> map = new HashMap<>();
        for (String s : fld)
            map.put(s, String.format(forceClear ? s : "", sfx));
        return map;
    }

    public static void clearDictonary(HashMap<String, String> dic) {
        for (Map.Entry entry : dic.entrySet()) {
            dic.put((String) entry.getKey(), "");
        }
    }

    public String getProperty(String key) {
        return dicBase.get(key);
    }

    public String[][] getEntryDictonary() {
        String[][] arr = new String[dicBase.size()][2];
        int i = 0;
        for (Map.Entry entry : dicBase.entrySet()) {
            arr[i][0] = (String) entry.getKey();
            arr[i][1] = (String) entry.getValue();
            i++;
        }
        return arr;
    }

    public static int compare(ContactData o1, ContactData o2) {
        var rez = o1.getProperty("lastname").compareTo(o2.getProperty("lastname"));
        if (rez == 0)
            return o1.getProperty("firstname").compareTo(o2.getProperty("firstname"));
        return rez;
    }

    public String[] getTuple() {
        return getTuple(getProperty("lastname"), getProperty("firstname"));
    }

    public static String[] getTuple(String lastname, String firstname) {
        return new String[]{
                String.format("lastname:%s", lastname),
                String.format("firstname:%s", firstname)};
    }

    public String repr() {
        var t = getTuple();
        return String.format("%s, %s", t[0], t[1]);
    }
}

