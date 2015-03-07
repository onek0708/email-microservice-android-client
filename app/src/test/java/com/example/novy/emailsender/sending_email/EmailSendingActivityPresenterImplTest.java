package com.example.novy.emailsender.sending_email;

import com.example.novy.emailsender.ErrorMessageHolder;
import com.example.novy.emailsender.sending_email.model.MessageData;
import com.google.common.collect.ImmutableList;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import utils.builders.MessageDataBuilder;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmailSendingActivityPresenterImplTest {

    @Mock
    private EmailSendingActivity activityMock;

    @Mock
    private EmailServiceApiGateway emailServiceApiGatewayMock;

    @Test
    public void shouldShowErrorMessageOnActivityGivenInvalidRecipientAddress() throws Exception {

        final EmailSendingActivityPresenter objectUnderTest = new EmailSendingActivityPresenterImpl(
                activityMock, emailServiceApiGatewayMock
        );

        objectUnderTest.handle(
                "sender@gmail.com",
                "password",
                ImmutableList.of("valid@gmail.com", "invalidRecipient"),
                "subject",
                "content"
        );

        verify(activityMock, times(1)).showErrorMessage(ErrorMessageHolder.INVALID_RECIPIENT_ADDRESS);
    }

    @Test
    public void shouldShowErrorMessageOnActivityGivenEmptySubject() throws Exception {
        final EmailSendingActivityPresenter objectUnderTest = new EmailSendingActivityPresenterImpl(
                activityMock, emailServiceApiGatewayMock
        );

        objectUnderTest.handle(
                "sender@gmail.com",
                "password",
                ImmutableList.of("recipient@gmail.com"),
                "",
                "content"
        );

        verify(activityMock, times(1)).showErrorMessage(ErrorMessageHolder.EMPTY_SUBJECT);
    }

    @Test
    public void shouldShowErrorMessageOnActivityGivenEmptyContent() throws Exception {
        final EmailSendingActivityPresenter objectUnderTest = new EmailSendingActivityPresenterImpl(
                activityMock, emailServiceApiGatewayMock
        );

        objectUnderTest.handle(
                "sender@gmail.com",
                "password",
                ImmutableList.of("recipient@gmail.com"),
                "subject",
                ""
        );

        verify(activityMock, times(1)).showErrorMessage(ErrorMessageHolder.EMPTY_CONTENT);
    }
}