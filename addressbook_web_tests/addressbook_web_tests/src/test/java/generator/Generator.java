package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunc;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Generator {
    @Parameter(names = {"--type", "-t"})
    String type;
    @Parameter(names = {"--output", "-o"})
    String output;
    @Parameter(names = {"--format", "-f"})
    String format;
    @Parameter(names = {"--count", "-c"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type))
            return generateGroups();
        else if ("contacts".equals(type))
            return generateContacts();
        else
            throw new IllegalArgumentException("Unknown data type: " + type);
    }

    private Object generateContacts() {
        var lst = new ArrayList<ContactData>();
        var rnd = new Random();
        for (int i = 0; i < count; i++) {
            lst.add(new ContactData()
                    .withFirstName(CommonFunc.randomString(rnd.nextInt(21)))
                    .withLastName(CommonFunc.randomString(rnd.nextInt(21)))
                    .withAddress(CommonFunc.randomString(rnd.nextInt(21)))
                    .withEmail(CommonFunc.randomString(rnd.nextInt(21)))
                    .withHome(CommonFunc.randomString(rnd.nextInt(21))));
        }
        return lst;
    }

    private Object generateGroups() {
        var lst = new ArrayList<GroupData>();
        var rnd = new Random();
        for (int i = 0; i < count; i++) {
            lst.add(new GroupData()
                    .withName(CommonFunc.randomString(rnd.nextInt(21)))
                    .withHeader(CommonFunc.randomString(rnd.nextInt(21)))
                    .withFooter(CommonFunc.randomString(rnd.nextInt(21))));
        }
        return lst;
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);
        } else if ("yaml".equals(format)) {
            ObjectMapper mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        } else if ("xml".equals(format)) {
            ObjectMapper mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Unknown data format: " + format);
        }
    }
}
