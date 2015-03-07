package com.example.novy.emailsender.sending_email.model;

import com.example.novy.emailsender.sending_email.model.Recipients;
import com.google.common.collect.ImmutableList;

import org.junit.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

public class RecipientsTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateRecipientsWithInvalidEmailAddress() throws Exception {

        Recipients.of("valid@gmail.com", "invalidEmailAddress");
    }

    @Test
    public void shouldCreateRecipientsOtherwise() throws Exception {

        final Collection<String> expectedEmailAddresses = ImmutableList.of(
                "first@gmail.com", "second@gmail.com"
        );

        final Recipients recipients = Recipients.of(expectedEmailAddresses);

        assertThat(recipients.recipients()).containsExactlyElementsOf(expectedEmailAddresses);
    }
}