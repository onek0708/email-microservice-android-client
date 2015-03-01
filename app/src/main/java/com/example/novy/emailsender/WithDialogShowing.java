package com.example.novy.emailsender;

/**
 * Created by novy on 01.03.15.
 */
public interface WithDialogShowing {

    void showMessage(String title, String message);
    void showErrorMessage(String message);
    void showMessage(String message);
}
