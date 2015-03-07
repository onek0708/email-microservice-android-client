package com.example.novy.emailsender.sending_email;

import com.example.novy.emailsender.sending_email.model.MessageData;

import java.util.Collection;

/**
 * Created by novy on 02.03.15.
 */
public interface EmailSendingActivityPresenter {

    void handle(String sender,
                String password,
                Collection<String> recipients,
                String subject,
                String content
    );
    void setView(EmailSendingActivity activity);
}
