package com.example.novy.emailsender.sending_email;

import android.app.Application;

import com.example.novy.emailsender.sending_email.model.MessageData;
import com.lambdista.util.Try;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;

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

    public void send(MessageData messageData, final CallbackHandler handler) {
        final Try<StringEntity> stringEntityMonad = stringEntityFactory.fromMessageData(messageData);
        if (stringEntityMonad.isFailure()) {
            handler.onFailure(
                    stringEntityMonad
                            .failed()
                            .get()
            );
        }

        httpClient.post(application, "http://10.0.2.2:8080/emailservice/", stringEntityMonad.getOrElse(null), "application/json",
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
