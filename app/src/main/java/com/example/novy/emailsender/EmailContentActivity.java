package com.example.novy.emailsender;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class EmailContentActivity extends ActionBarActivity {
    private String senderEmail;
    private String senderPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_content);

        Bundle parameterBundle = getIntent().getExtras();
        senderEmail = String.valueOf(parameterBundle.get(IntentConstants.SENDER_EMAIL_STRING));
        senderPassword = String.valueOf(parameterBundle.get(IntentConstants.SENDER_PASSWORD_STRING));

        System.out.println(senderEmail + " " + senderPassword);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_email_content, menu);
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
}
