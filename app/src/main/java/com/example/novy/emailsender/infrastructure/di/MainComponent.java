package com.example.novy.emailsender.infrastructure.di;

import com.example.novy.emailsender.sending_email.EmailSendingActivity;
import com.example.novy.emailsender.EmailSenderApplication;
import com.example.novy.emailsender.login.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by novy on 25.02.15.
 */

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(EmailSenderApplication application);
    void inject(MainActivity activity);
    void inject(EmailSendingActivity activity);
}
