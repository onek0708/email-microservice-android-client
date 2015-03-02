package utils.builders;

import com.google.common.collect.ImmutableList;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by novy on 02.03.15.
 */

@Setter
@Accessors(fluent = true, chain = true)
@NoArgsConstructor(staticName = "newStringEntity")
public class StringEntityBuilder {

    private String sender = "sender@gmail.com";
    private String password = "password";
    private Collection<String> recipients = ImmutableList.of("sender@gmail.com");
    private String subject = "subject";
    private String content = "content";

    public StringEntity build() throws Exception {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("sender", sender);
        jsonObject.put("password", password);
        jsonObject.put("recipients", new JSONArray(recipients));
        jsonObject.put("subject", subject);
        jsonObject.put("content", content);

        return new StringEntity(jsonObject.toString());
    }
}
