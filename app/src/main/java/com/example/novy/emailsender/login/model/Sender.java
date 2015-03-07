package com.example.novy.emailsender.login.model;

import com.example.novy.emailsender.ErrorMessageHolder;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import org.apache.commons.validator.routines.EmailValidator;

import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 06.03.15.
 */

@Value
@Accessors(fluent = true)
public class Sender {

    private static final EmailValidator emailValidator = EmailValidator.getInstance();

    private final String senderEmail;
    private final String senderPassword;

    private Sender(String senderEmail, String senderPassword) {
        Preconditions.checkArgument(emailStringValid(senderEmail), ErrorMessageHolder.INVALID_SENDER_ADDRESS);
        Preconditions.checkArgument(passwordNotEmpty(senderPassword), ErrorMessageHolder.EMPTY_PASSWORD);

        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;
    }

    public static Sender of(String senderEmail, String senderPassword) {
        return new Sender(senderEmail, senderPassword);
    }

    private boolean emailStringValid(String senderEmail) {
        return emailValidator.isValid(senderEmail);
    }

    private boolean passwordNotEmpty(String senderPassword) {
        return !Strings.isNullOrEmpty(senderPassword);
    }
}
