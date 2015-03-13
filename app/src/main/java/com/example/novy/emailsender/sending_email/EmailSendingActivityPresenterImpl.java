package com.example.novy.emailsender.sending_email;

import com.example.novy.emailsender.MessageHolder;
import com.example.novy.emailsender.sending_email.model.MessageData;
import com.lambdista.util.Try;

import java.util.Collection;

/**
 * Created by novy on 02.03.15.
 */
public class EmailSendingActivityPresenterImpl implements EmailSendingActivityPresenter {

    private final EmailServiceApiGateway emailServiceApiGateway;
    private EmailSendingActivity activity;

    public EmailSendingActivityPresenterImpl(EmailServiceApiGateway emailServiceApiGateway) {
        this.emailServiceApiGateway = emailServiceApiGateway;
    }

    public EmailSendingActivityPresenterImpl(EmailSendingActivity activity,
                                             EmailServiceApiGateway emailServiceApiGateway) {
        this(emailServiceApiGateway);
        this.activity = activity;
    }

    @Override
    public void handle(final String sender, final String password, final Collection<String> recipients,
                       final String subject, final String content) {

        Try<MessageData> messageDataMonad = MessageData.of(
                sender, password, recipients, subject, content
        );

        if (messageDataMonad.isFailure()) {
            activity.showErrorMessage(
                    messageDataMonad
                    .failed()
                    .get()
                    .getMessage()
            );
        } else {
            performSendEmailRequest(messageDataMonad.get());
        }


    }

    private void performSendEmailRequest(MessageData messageData) {
        emailServiceApiGateway.send(messageData, new CallbackHandler() {
            @Override
            public void onSuccess() {
                activity.showMessage(MessageHolder.SEND_SUCCESS);
            }

            @Override
            public void onFailure(Throwable error) {
                activity.showErrorMessage(MessageHolder.SEND_FAILURE);
            }
        });
    }

    @Override
    public void setView(EmailSendingActivity activity) {
        this.activity = activity;
    }
}
