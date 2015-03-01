package com.example.novy.emailsender.login;

import com.google.common.base.Strings;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Created by novy on 01.03.15.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter {

    private final EmailValidator validator;
    private MainActivity activity;

    public MainActivityPresenterImpl(EmailValidator validator) {
        this.validator = validator;
    }

    public MainActivityPresenterImpl(EmailValidator validator, MainActivity activity) {
        this.validator = validator;
        this.activity = activity;
    }

    @Override
    public void handle(String senderEmail, String senderEmailPassword) {
        if (!emailValid(senderEmail)) {
            activity.showErrorMessage("Invalid email format!");
        } else if (passwordEmpty(senderEmailPassword)) {
            activity.showErrorMessage("Empty password!");
        } else {
            activity.showEmailContentActivity(senderEmail, senderEmailPassword);
        }
    }

    @Override
    public void setView(MainActivity activity) {
        this.activity = activity;
    }

    private boolean emailValid(String email) {
        return validator.isValid(email);
    }

    private boolean passwordEmpty(String password) {
        return Strings.isNullOrEmpty(password);
    }
}
