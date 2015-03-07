package com.example.novy.emailsender.sending_email.model;

import com.example.novy.emailsender.login.model.Sender;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.lambdista.util.FailableSupplier;
import com.lambdista.util.Try;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 02.03.15.
 */
@EqualsAndHashCode
public class MessageData {

    private final Sender sender;
    private final Recipients recipients;
    private final Message message;

    private MessageData(String sender, String password, Collection<String> recipients,
                       String subject, String content) {
        this.sender = Sender.of(sender, password);
        this.recipients = Recipients.of(recipients);
        this.message = Message.of(subject, content);
    }

    public static Try<MessageData> of(final String sender, final String password, final Collection<String> recipients,
    final String subject, final String content) {

        return Try.apply(
                new FailableSupplier<MessageData>() {
                    @Override
                    public MessageData get() throws Exception {
                        return new MessageData(
                                sender, password, recipients, subject, content
                        );
                    }
                }
        );
    }

    public String sender() {
        return sender.senderEmail();
    }

    public String password() {
        return sender.senderPassword();
    }

    public Collection<String> recipients() {
        return recipients.recipients();
    }

    public String subject() {
        return message.subject();
    }

    public String content() {
        return message.content();
    }


}
