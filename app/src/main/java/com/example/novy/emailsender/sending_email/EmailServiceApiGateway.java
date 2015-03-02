package com.example.novy.emailsender.sending_email;

import android.app.Application;

import com.example.novy.emailsender.ErrorMessageHolder;
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
    private StringEntityFactory stringEntityFactory;

    public EmailServiceApiGateway(Application application, AsyncHttpClient httpClient, StringEntityFactory stringEntityFactory) {
        this.application = application;
        this.httpClient = httpClient;
        this.stringEntityFactory = stringEntityFactory;
    }

    public void send(MessageData messageData, EmailSendResponseHandler handler) {
        StringEntity entity = null;
        try {
            entity = stringEntityFactory.fromMessageData(messageData);
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        httpClient.post(application, "http://10.0.2.2:8080/emailservice/", entity, "application/json",
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        handler.onSuccess();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        handler.onFailure(error);
                    }
                }
        );
    }
}
