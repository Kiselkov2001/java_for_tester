package ru.kis.mantis.manager;

import jakarta.mail.*;
import ru.kis.mantis.common.CommonFunc;
import ru.kis.mantis.model.MailMessage;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MailHelper extends HelperBase {
    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String email, String password, Duration duration) {
        var start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration.toMillis()) {
            try {
                var inbox = getInbox(email, password);
                inbox.open(Folder.READ_ONLY);
                var messages = inbox.getMessages();
                var result = Arrays.stream(messages).map(x -> {
                            try {
                                return new MailMessage()
                                        .withFrom(x.getFrom()[0].toString())
                                        .withContent((String) x.getContent());
                            } catch (MessagingException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList();
                inbox.close();
                inbox.getStore().close();
                if (result.size() > 0) return result;

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("No mail");
    }

    private static Folder getInbox(String email, String password){
        try {
            var session = Session.getInstance(new Properties());
            var store = session.getStore("pop3");
            store.connect("localhost", email, password);
            return store.getFolder("INBOX");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void drain(String email, String password) { //it's possible delete messages around JamesAPI
        try {
            var inbox = getInbox(email, password);
            inbox.open(Folder.READ_WRITE);
            Arrays.stream(inbox.getMessages()).forEach(m-> {
                try {
                    m.setFlag(Flags.Flag.DELETED, true);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            inbox.close(); //it's deleted mail
            inbox.getStore().close();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractUrl(String email, String password) {
        var messages = receive(email, password, Duration.ofSeconds(10));
        return CommonFunc.extractUrl(messages.get(0).content());

//        var text = messages.get(0).content();
//        var pattern = Pattern.compile("http://\\S*");
//        var matcher = pattern.matcher(text);
//        if (matcher.find()) {
//            return text.substring(matcher.start(), matcher.end());
//        }
//        return null;
    }

}
