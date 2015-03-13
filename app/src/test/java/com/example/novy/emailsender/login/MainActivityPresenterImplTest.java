package com.example.novy.emailsender.login;

import com.example.novy.emailsender.MessageHolder;

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
    private MainActivity mainActivityMock;

    @Test
    public void shouldDisplayErrorMessageOnActivityGivenInvalidEmailAddress() throws Exception {

        final String invalidEmailAddress = "invalidEmailAddress";
        final MainActivityPresenter objectUnderTest = new MainActivityPresenterImpl(mainActivityMock);

        objectUnderTest.handle(invalidEmailAddress, "password");

        verify(mainActivityMock, times(1)).showErrorMessage(MessageHolder.INVALID_SENDER_ADDRESS);
    }

    @Test
    public void shouldDisplayErrorMessageOnActivityGivenEmptyPassword() throws Exception {

        final String validEmailAddress = "valid@gmail.com";
        final MainActivityPresenter objectUnderTest = new MainActivityPresenterImpl(mainActivityMock);

        objectUnderTest.handle(validEmailAddress, "");

        verify(mainActivityMock, times(1)).showErrorMessage(MessageHolder.EMPTY_PASSWORD);
    }

    @Test
    public void shouldSwitchActivityToEmailContentActivityOnValidEmailAndPassword() throws Exception {

        final String validEmailAddress = "valid@gmail.com";
        final String validPassword = "password";
        final MainActivityPresenter objectUnderTest = new MainActivityPresenterImpl(mainActivityMock);

        objectUnderTest.handle(validEmailAddress, validPassword);

        verify(mainActivityMock, times(1)).showEmailContentActivity(validEmailAddress, validPassword);
    }
}