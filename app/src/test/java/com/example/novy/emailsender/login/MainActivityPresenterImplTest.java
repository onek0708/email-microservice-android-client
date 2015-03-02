package com.example.novy.emailsender.login;

import com.example.novy.emailsender.ErrorMessageHolder;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterImplTest {

    @Mock
    private EmailValidator emailValidatorMock;

    @Mock
    private MainActivity mainActivityMock;

    @Test
    public void shouldDisplayErrorMessageOnActivityGivenInvalidEmailAddress() throws Exception {

        final String invalidEmailAddress = "invalidEmailAddress";
        given(emailValidatorMock.isValid(invalidEmailAddress)).willReturn(false);
        final MainActivityPresenter objectUnderTest = new MainActivityPresenterImpl(emailValidatorMock, mainActivityMock);

        objectUnderTest.handle(invalidEmailAddress, "password");

        verify(mainActivityMock, times(1)).showErrorMessage(ErrorMessageHolder.INVALID_SENDER_ADDRESS);
    }

    @Test
    public void shouldDisplayErrorMessageOnActivityGivenEmptyPassword() throws Exception {

        final String validEmailAddress = "valid@email.address";
        given(emailValidatorMock.isValid(validEmailAddress)).willReturn(true);
        final MainActivityPresenter objectUnderTest = new MainActivityPresenterImpl(emailValidatorMock, mainActivityMock);

        objectUnderTest.handle(validEmailAddress, "");

        verify(mainActivityMock, times(1)).showErrorMessage(ErrorMessageHolder.EMPTY_PASSWORD);
    }

    @Test
    public void shouldSwitchActivityToEmailContentActivityOnValidEmailAndPassword() throws Exception {

        final String validEmailAddress = "valid@email.address";
        final String validPassword = "password";
        given(emailValidatorMock.isValid(validEmailAddress)).willReturn(true);
        final MainActivityPresenter objectUnderTest = new MainActivityPresenterImpl(emailValidatorMock, mainActivityMock);

        objectUnderTest.handle(validEmailAddress, validPassword);

        verify(mainActivityMock, times(1)).showEmailContentActivity(validEmailAddress, validPassword);
    }
}