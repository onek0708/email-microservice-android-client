package utils.builders;

import com.example.novy.emailsender.sending_email.MessageData;
import com.google.common.collect.ImmutableList;

import java.util.Collection;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by novy on 02.03.15.
 */
@Setter
@Accessors(fluent = true, chain = true)
@NoArgsConstructor(staticName = "newMessageData")
public class MessageDataBuilder {

    private String sender = "sender@gmail.com";
    private String password = "password";
    private Collection<String> recipients = ImmutableList.of("sender@gmail.com");
    private String subject = "subject";
    private String content = "content";

    public MessageData build() throws Exception {
        return new MessageData(sender, password, recipients, subject, content);
    }
}
