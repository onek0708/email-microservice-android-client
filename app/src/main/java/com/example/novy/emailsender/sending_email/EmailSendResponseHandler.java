package com.example.novy.emailsender.sending_email;

/**
 * Created by novy on 02.03.15.
 */
public interface EmailSendResponseHandler {

    void onSuccess();
    void onFailure(Throwable error);
}
