package com.example.novy.emailsender.login.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class SenderTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnIllegalArgumentExceptionGivenInvalidSenderAddress() throws Exception {

        Sender.of("invalidEmail", "nonEmptyPassword");

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnIllegalArgumentExceptionGivenInvalidPassword() throws Exception {

        Sender.of("valid.email@gmail.com", "");
    }

    @Test
    public void shouldCreateSenderOtherwise() throws Exception {

        final Sender sender = Sender.of("valid.email@gmail.com", "aPassword");

        Assertions.assertThat(sender.senderEmail()).isEqualTo("valid.email@gmail.com");
        Assertions.assertThat(sender.senderPassword()).isEqualTo("aPassword");

    }
}