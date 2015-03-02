package com.example.novy.emailsender.sending_email;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by novy on 02.03.15.
 */
public class StringEntityFactory {

    public StringEntity fromMessageData(MessageData messageData) throws JSONException, UnsupportedEncodingException {
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("sender", messageData.sender());
        jsonObject.put("password", messageData.password());
        jsonObject.put("recipients", new JSONArray(messageData.recipients()));
        jsonObject.put("subject", messageData.subject());
        jsonObject.put("content", messageData.content());

        return new StringEntity(jsonObject.toString());
    }
}
