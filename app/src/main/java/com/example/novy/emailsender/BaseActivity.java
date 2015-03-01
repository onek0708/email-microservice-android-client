package com.example.novy.emailsender;

import android.support.v4.app.FragmentActivity;

/**
 * Created by novy on 01.03.15.
 */
public abstract class BaseActivity extends FragmentActivity {

    public EmailSenderApplication getEmailSenderApplication() {
        return (EmailSenderApplication) getApplication();
    }
}
