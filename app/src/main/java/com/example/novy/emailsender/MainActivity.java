package com.example.novy.emailsender;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.example.novy.emailsender.infrastructure.di.Dagger_MainComponent;
import com.example.novy.emailsender.infrastructure.di.MainComponent;
import com.example.novy.emailsender.infrastructure.di.MainModule;

import org.apache.commons.validator.routines.EmailValidator;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity {

    private MainComponent component;

    @Inject
    EmailValidator emailValidator;

    @InjectView(R.id.emailField)
    EditText emailField;

    @InjectView(R.id.passwordField)
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        component = getEmailSenderApplication().component();
        component.inject(this);
    }

    public void login(View view) {
        final String emailString = emailField.getText().toString();
        final String passwordString = passwordField.getText().toString();

        if (emailHasInvalidFormat(emailString)) {
            showErrorDialog("Wrong email format!");
        } else if (passwordIsEmpty(passwordString)) {
            showErrorDialog("Empty password!");
        } else {
            showEmailContentActivity(emailString, passwordString);
        }
    }

    private boolean emailHasInvalidFormat(String emailString) {
        return !emailValidator.isValid(emailString);
    }

    private void showEmailContentActivity(String emailString, String passwordString) {

        Intent intent = new Intent(MainActivity.this, EmailContentActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString(IntentConstants.SENDER_EMAIL_STRING, emailString);
        bundle.putString(IntentConstants.SENDER_PASSWORD_STRING, passwordString);

        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private boolean passwordIsEmpty(String passwordString) {
        return passwordString.isEmpty();
    }

    private void showErrorDialog(String dialogMessage) {
        AlertDialog.Builder dialogAlert = new AlertDialog.Builder(this);

        dialogAlert.setMessage(dialogMessage);
        dialogAlert.setTitle("Error");

        dialogAlert.setPositiveButton("OK", null);
        dialogAlert.setCancelable(true);
        dialogAlert.create().show();
    }
}
