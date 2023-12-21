package utilities;

import exceptions.AutomationUtilException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class MailHandler {

    private String username;
    private String password;
    private final static String MAIL_HOST = "imap.gmail.com";

    public MailHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private Folder getFolder() throws MessagingException {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("imaps");
        store.connect(MAIL_HOST, username, password);

        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        return folder;
    }

    private Message[] getUnreadMessages(String recipient, String emailSubject) throws MessagingException {
        Message[] messages;
        Folder inbox = getFolder();

        //Create a search term of recipient and email subject
        RecipientTerm recipientTerm = new RecipientTerm(Message.RecipientType.TO, new InternetAddress(recipient));
        SubjectTerm subjectTerm = new SubjectTerm(emailSubject);
        AndTerm andTerm1 = new AndTerm(recipientTerm, subjectTerm);

        // create a search term for all "unseen" messages
        Flags seen = new Flags(Flags.Flag.SEEN);
        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);

        SearchTerm searchTerm = new AndTerm(andTerm1, unseenFlagTerm);

        messages = inbox.search(searchTerm);
        return messages;
    }

    public String getMessageContent(String recipient, String emailSubject) throws MessagingException, IOException {
        Message[] messages = getUnreadMessages(recipient, emailSubject);
        if (messages.length == 0) {
            throw new AutomationUtilException("No email found from: " + recipient);
        }

        Message message = messages[0];

        String line;
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(message.getInputStream()));
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        System.out.println(buffer);
        return buffer.toString();
    }

}
