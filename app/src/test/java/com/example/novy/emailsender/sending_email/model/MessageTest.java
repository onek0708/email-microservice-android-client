package com.example.novy.emailsender.sending_email.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class MessageTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnIllegalArgumentExceptionGivenEmptySubject() throws Exception {

        Message.of("", "content");

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnIllegalArgumentExceptionGivenEmptyMessage() throws Exception {

        Message.of("subject", "");
    }

    @Test
    public void shouldCreateMessageOtherwise() throws Exception {

        final Message message = Message.of("subject", "content");

        assertThat(message.subject()).isEqualTo("subject");
        assertThat(message.content()).isEqualTo("content");

    }
}