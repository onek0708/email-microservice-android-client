package com.example.novy.emailsender.sending_email.model;

import com.example.novy.emailsender.MessageHolder;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by novy on 06.03.15.
 */
public class Recipients {

    private static final EmailValidator validator = EmailValidator.getInstance();

    private Collection<String> recipients = Lists.newArrayList();

    public Recipients(Collection<String> recipients) {
        Preconditions.checkArgument(hasOnlyValidEmailAddresses(recipients), MessageHolder.INVALID_RECIPIENT_ADDRESS);
        this.recipients = new ArrayList<>(recipients);
    }

    public static Recipients of(Collection<String> recipients) {
        return new Recipients(recipients);
    }

    public static Recipients of(String... recipients) {
        return new Recipients(Arrays.asList(recipients));
    }

    public Collection<String> recipients() {
        return Collections.unmodifiableCollection(recipients);
    }

    private boolean hasOnlyValidEmailAddresses(Collection<String> recipients) {
        return Iterables.all(recipients, new Predicate<String>() {
            @Override
            public boolean apply(String emailString) {
                return validator.isValid(emailString);
            }
        });
    }
}
