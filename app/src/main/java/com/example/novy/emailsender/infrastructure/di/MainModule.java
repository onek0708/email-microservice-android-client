package com.example.novy.emailsender.infrastructure.di;

import android.app.Application;

import com.example.novy.emailsender.sending_email.EmailServiceApiGateway;
import com.example.novy.emailsender.login.MainActivityPresenter;
import com.example.novy.emailsender.login.MainActivityPresenterImpl;
import com.loopj.android.http.AsyncHttpClient;

import org.apache.commons.validator.routines.EmailValidator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by novy on 25.02.15.
 */

@Module
public class MainModule {

    private Application context;

    public MainModule(Application context) {
        this.context = context;
    }

    @Provides
    public Application provideApplicationContext() {
        return context;
    }

    @Provides
    public AsyncHttpClient provideAsyncHttpClient() {
        return new AsyncHttpClient();
    }

    @Provides
    public EmailValidator provideEmailValidator() {
        return EmailValidator.getInstance();
    }

    @Provides
    public EmailServiceApiGateway provideEmailServiceApiGateway(Application context, AsyncHttpClient httpClient) {
        return new EmailServiceApiGateway(context, httpClient);
    }

    @Provides
    public MainActivityPresenter provideMainActivityPresenter(EmailValidator validator) {
        return new MainActivityPresenterImpl(validator);
    }
}
