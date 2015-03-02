package com.example.novy.emailsender.sending_email;

import com.example.novy.emailsender.ErrorMessageHolder;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Created by novy on 02.03.15.
 */
public class EmailSendingActivityPresenterImpl implements EmailSendingActivityPresenter {

    private final EmailValidator emailValidator;
    private final EmailServiceApiGateway emailServiceApiGateway;
    private EmailSendingActivity activity;

    public EmailSendingActivityPresenterImpl(EmailValidator emailValidator, EmailServiceApiGateway emailServiceApiGateway) {
        this.emailValidator = emailValidator;
        this.emailServiceApiGateway = emailServiceApiGateway;
    }

    public EmailSendingActivityPresenterImpl(EmailSendingActivity activity,
                                             EmailValidator emailValidator,
                                             EmailServiceApiGateway emailServiceApiGateway) {
        this(emailValidator, emailServiceApiGateway);
        this.activity = activity;
    }

    @Override
    public void handle(MessageData messageData) {
//        todo: refactor
        if (hasInvalidRecipientAddress(messageData)) {
            activity.showErrorMessage(ErrorMessageHolder.INVALID_RECIPIENT_ADDRESS);
        } else if (hasEmptySubject(messageData)) {
            activity.showErrorMessage(ErrorMessageHolder.EMPTY_SUBJECT);
        } else if (hasEmptyContent(messageData)) {
            activity.showErrorMessage(ErrorMessageHolder.EMPTY_CONTENT);
        } else {
            performSendEmailRequest(messageData);
        }
    }

    private void performSendEmailRequest(MessageData messageData) {
        emailServiceApiGateway.send(messageData, new EmailSendResponseHandler() {
            @Override
            public void onSuccess() {
                activity.showMessage(ErrorMessageHolder.SEND_SUCCESS);
            }

            @Override
            public void onFailure(Throwable error) {
                activity.showErrorMessage(ErrorMessageHolder.SEND_FAILURE);
            }
        });
    }

    private boolean hasEmptySubject(MessageData messageData) {
        return Strings.isNullOrEmpty(messageData.subject());
    }

    private boolean hasEmptyContent(MessageData messageData) {
        return Strings.isNullOrEmpty(messageData.content());
    }

    private boolean hasInvalidRecipientAddress(MessageData messageData) {
        return Iterables.any(
                messageData.recipients(), email -> !emailValidator.isValid(email)
        );
    }

    @Override
    public void setView(EmailSendingActivity activity) {
        this.activity = activity;
    }
}
