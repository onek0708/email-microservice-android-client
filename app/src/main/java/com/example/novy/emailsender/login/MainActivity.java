package com.example.novy.emailsender.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.novy.emailsender.BaseActivity;
import com.example.novy.emailsender.EmailSendingActivity;
import com.example.novy.emailsender.IntentConstants;
import com.example.novy.emailsender.R;
import com.example.novy.emailsender.WithDialogShowing;
import com.example.novy.emailsender.infrastructure.di.MainComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements WithDialogShowing {

    private MainComponent component;

    @Inject
    MainActivityPresenter presenter;

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

        presenter.setView(this);
    }

    @OnClick(R.id.continueButton)
    public void handleContinueButtonClicked() {
        final String senderAddress = emailField.getText().toString();
        final String senderPassword = passwordField.getText().toString();

        presenter.handle(senderAddress, senderPassword);
    }

    public void showEmailContentActivity(String emailString, String passwordString) {

        Intent intent = new Intent(MainActivity.this, EmailSendingActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString(IntentConstants.SENDER_EMAIL_STRING, emailString);
        bundle.putString(IntentConstants.SENDER_PASSWORD_STRING, passwordString);

        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
