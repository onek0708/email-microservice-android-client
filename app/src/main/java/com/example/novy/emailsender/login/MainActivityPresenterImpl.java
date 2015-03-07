package com.example.novy.emailsender.login;

import com.example.novy.emailsender.ErrorMessageHolder;
import com.example.novy.emailsender.login.model.Sender;
import com.google.common.base.Strings;
import com.lambdista.util.FailableSupplier;
import com.lambdista.util.Try;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Created by novy on 01.03.15.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter {

    private MainActivity activity;

    public MainActivityPresenterImpl() {}

    public MainActivityPresenterImpl(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void handle(final String senderEmail, final String senderEmailPassword) {
        final Try<Sender> senderMonad = Try.apply(
                new FailableSupplier<Sender>() {
                    @Override
                    public Sender get() throws Exception {
                        return Sender.of(senderEmail, senderEmailPassword);
                    }
                }
        );

        if (senderMonad.isFailure()) {
            activity.showErrorMessage(
                    senderMonad
                    .failed()
                    .get()
                    .getMessage()
            );
        } else {
            activity.showEmailContentActivity(senderEmail, senderEmailPassword);
        }
    }

    @Override
    public void setView(MainActivity activity) {
        this.activity = activity;
    }
}
