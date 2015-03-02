package com.example.novy.emailsender.sending_email;

import com.example.novy.emailsender.ErrorMessageHolder;
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
    private EmailValidator emailValidatorMock;

    @Mock
    private EmailServiceApiGateway emailServiceApiGatewayMock;

    @Test
    public void shouldShowErrorMessageOnActivityGivenInvalidRecipientAddress() throws Exception {
        final String invalidEmailAddress = "invalidEmailAddress";
        given(emailValidatorMock.isValid(any(String.class))).willReturn(true);
        given(emailValidatorMock.isValid(invalidEmailAddress)).willReturn(false);
        final EmailSendingActivityPresenter objectUnderTest = new EmailSendingActivityPresenterImpl(
                activityMock, emailValidatorMock, emailServiceApiGatewayMock
        );

        final MessageData messageData = MessageDataBuilder
                .newMessageData()
                .recipients(ImmutableList.of(invalidEmailAddress, "valid@gmail.com"))
                .build();

        objectUnderTest.handle(messageData);

        verify(activityMock, times(1)).showErrorMessage(ErrorMessageHolder.INVALID_RECIPIENT_ADDRESS);
    }

    @Test
    public void shouldShowErrorMessageOnActivityGivenEmptySubject() throws Exception {
        given(emailValidatorMock.isValid(any(String.class))).willReturn(true);
        final EmailSendingActivityPresenter objectUnderTest = new EmailSendingActivityPresenterImpl(
                activityMock, emailValidatorMock, emailServiceApiGatewayMock
        );

        final MessageData messageData = MessageDataBuilder
                .newMessageData()
                .subject("")
                .build();

        objectUnderTest.handle(messageData);

        verify(activityMock, times(1)).showErrorMessage(ErrorMessageHolder.EMPTY_SUBJECT);
    }

    @Test
    public void shouldShowErrorMessageOnActivityGivenEmptyContent() throws Exception {
        given(emailValidatorMock.isValid(any(String.class))).willReturn(true);
        final EmailSendingActivityPresenter objectUnderTest = new EmailSendingActivityPresenterImpl(
                activityMock, emailValidatorMock, emailServiceApiGatewayMock
        );

        final MessageData messageData = MessageDataBuilder
                .newMessageData()
                .content("")
                .build();

        objectUnderTest.handle(messageData);

        verify(activityMock, times(1)).showErrorMessage(ErrorMessageHolder.EMPTY_CONTENT);
    }
}