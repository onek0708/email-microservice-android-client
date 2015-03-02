package com.example.novy.emailsender.sending_email;

import android.app.Application;

import com.google.common.collect.ImmutableList;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by novy on 01.03.15.
 */
public class EmailServiceApiGateway {

    private Application application;
    private AsyncHttpClient httpClient;

    public EmailServiceApiGateway(Application application, AsyncHttpClient httpClient) {
        this.application = application;
        this.httpClient = httpClient;
    }

    public void send(String sender, String password, String recipient, String topic, String content) {
        JSONObject jsonPayload = new JSONObject();
        JSONArray recipients = new JSONArray(ImmutableList.of(recipient));
        try {
            jsonPayload.put("sender", sender);
            jsonPayload.put("password", password);
            jsonPayload.put("recipients", recipients);
            jsonPayload.put("subject", topic);
            jsonPayload.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonPayload.toString());
            System.out.println(jsonPayload.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpClient.post(application, "http://10.0.2.2:8080/emailservice/", entity, "application/json",
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        System.out.println("success " + statusCode);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        System.out.println("failure " + statusCode);
                    }
                }
        );
    }
}
