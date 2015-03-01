package com.example.novy.emailsender.login;

/**
 * Created by novy on 01.03.15.
 */
public interface MainActivityPresenter {

    void handle(String senderEmail, String senderEmailPassword);
    void setView(MainActivity activity);
}
