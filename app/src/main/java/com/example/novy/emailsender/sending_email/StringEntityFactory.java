package com.example.novy.emailsender.sending_email;

import com.example.novy.emailsender.sending_email.model.MessageData;
import com.lambdista.util.FailableSupplier;
import com.lambdista.util.Try;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by novy on 02.03.15.
 */
public class StringEntityFactory {

    public Try<StringEntity> fromMessageData(final MessageData messageData) {
        return Try.apply(
                new FailableSupplier<StringEntity>() {
                    @Override
                    public StringEntity get() throws Exception {
                        return createFromMessageData(messageData);
                    }
                }
        );
    }

    private StringEntity createFromMessageData(MessageData messageData)
            throws JSONException, UnsupportedEncodingException {

        final JSONObject jsonObject = new JSONObject();

        jsonObject
                .put("sender", messageData.sender())
                .put("password", messageData.password())
                .put("recipients", new JSONArray(messageData.recipients()))
                .put("subject", messageData.subject())
                .put("content", messageData.content());

        return new StringEntity(jsonObject.toString());
    }
}
