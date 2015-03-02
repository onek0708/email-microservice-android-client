package com.example.novy.emailsender.sending_email;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 02.03.15.
 */
@Value
@Accessors(fluent = true)
public class MessageData {

    private final String sender;
    private final String password;

    private final Collection<String> recipients;

    private final String subject;
    private final String content;

    public MessageData(String sender, String password, Collection<String> recipients, String subject, String content) {
        this.sender = sender;
        this.password = password;
        this.recipients = recipients;
        this.subject = subject;
        this.content = content;
    }
}
