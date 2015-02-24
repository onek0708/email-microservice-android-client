package com.example.novy.emailsender;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.apache.commons.validator.routines.EmailValidator;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View view) {
        final EditText emailField = (EditText) findViewById(R.id.emailField);
        final EditText passwordField = (EditText) findViewById(R.id.passwordField);

        final String emailString = emailField.getText().toString();
        final String passwordString = passwordField.getText().toString();

        if (emailIsValid(emailString) && passwordNotEmpty(passwordString)) {
            showEmailContentActivity(emailString, passwordString);
        } else {
            showErrorDialog();
        }
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

    private boolean passwordNotEmpty(String passwordString) {
        return !passwordString.isEmpty();
    }

    private void showErrorDialog() {
        AlertDialog.Builder dialogAlert = new AlertDialog.Builder(this);

        dialogAlert.setMessage("Wrong email format!");
        dialogAlert.setTitle("Error");

        dialogAlert.setPositiveButton("OK", null);
        dialogAlert.setCancelable(true);
        dialogAlert.create().show();
    }

    private boolean emailIsValid(String emailString) {
        final EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(emailString);
    }
}
