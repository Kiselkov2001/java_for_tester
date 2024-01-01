package model;

import java.util.HashMap;
import java.util.Map;

public class ContactData {
    HashMap<String, String> dicBase = initDictonary("");

    public ContactData() {
        clearDictonary(dicBase);
    }

    public ContactData(HashMap<String, String> dic) {
        for (Map.Entry entry : dic.entrySet()) {
            if (!this.dicBase.containsKey(entry.getKey())) continue;
            this.dicBase.put((String)entry.getKey(), (String)entry.getValue());
        }
    }

    public static HashMap<String, String> initDictonary(String sfx) {
        HashMap<String, String> map = new HashMap<>();
        map.put("firstname", String.format("firstname%s", sfx));
        map.put("middlename", String.format("middlename%s", sfx));
        map.put("lastname", String.format("lastname%s", sfx));
        map.put("nickname", String.format("nickname%s", sfx));
        map.put("title", String.format("title%s", sfx));
        map.put("company", String.format("company%s", sfx));
        map.put("address", String.format("address%s", sfx));
        map.put("home", String.format("home%s", sfx));
        map.put("email", String.format("email%s", sfx));
        return map;
    }

    public static void clearDictonary(HashMap<String, String> dic) {
        for (Map.Entry entry : dic.entrySet()) {
            dic.put((String)entry.getKey(), "");
        }
    }

    public String[][] getEntryDictonary() {
        String[][] arr = new String[dicBase.size()][2]; int i = 0;
        for (Map.Entry entry : dicBase.entrySet()) {
            arr[i][0] = (String)entry.getKey();
            arr[i][1] = (String)entry.getValue();
            i++;
        }
        return arr;
    }

}

