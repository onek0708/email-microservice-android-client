package com.example.novy.emailsender;

import android.app.AlertDialog;
import android.support.v4.app.FragmentActivity;

/**
 * Created by novy on 01.03.15.
 */
public abstract class BaseActivity extends FragmentActivity implements WithDialogShowing {

    public EmailSenderApplication getEmailSenderApplication() {
        return (EmailSenderApplication) getApplication();
    }

    @Override
    public void showMessage(String title, String message) {
        AlertDialog.Builder dialogAlert = new AlertDialog.Builder(this);

        dialogAlert.setMessage(message);
        dialogAlert.setTitle(title);

        dialogAlert.setPositiveButton("OK", null);
        dialogAlert.setCancelable(true);
        dialogAlert.create().show();
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage("Error", message);
    }

    @Override
    public void showMessage(String message) {
        showMessage("", message);
    }
}
