package com.example.novy.emailsender.infrastructure.di;

import org.apache.commons.validator.routines.EmailValidator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by novy on 25.02.15.
 */

@Module
public class MainModule {

    @Provides
    public EmailValidator providesEmailValidator() {
        return EmailValidator.getInstance();
    }
}
