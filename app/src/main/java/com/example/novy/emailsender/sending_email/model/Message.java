package com.example.novy.emailsender.sending_email.model;

import com.example.novy.emailsender.MessageHolder;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 06.03.15.
 */

@Value
@Accessors(fluent = true)
public class Message {

    private final String subject;
    private final String content;

    private Message(String subject, String content) {
        Preconditions.checkArgument(notEmpty(subject), MessageHolder.EMPTY_SUBJECT);
        Preconditions.checkArgument(notEmpty(content), MessageHolder.EMPTY_CONTENT);

        this.subject = subject;
        this.content = content;
    }

    public static Message of(String subject, String content) {
        return new Message(subject, content);
    }

    private boolean notEmpty(String string) {
        return !Strings.isNullOrEmpty(string);
    }
}
