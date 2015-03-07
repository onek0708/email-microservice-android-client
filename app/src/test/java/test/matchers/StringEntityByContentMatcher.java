package test.matchers;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.http.entity.StringEntity;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.IOException;

/**
 * Created by novy on 02.03.15.
 */
public class StringEntityByContentMatcher {

    public static Matcher<StringEntity> equalsByContent(final StringEntity thatEntity) {
        return new TypeSafeMatcher<StringEntity>() {
            @Override
            public boolean matchesSafely(StringEntity thisEntity) {


                return new EqualsBuilder()
                        .append(contentAsByteArray(thisEntity), contentAsByteArray(thatEntity))
                        .append(thisEntity.getContentLength(), thatEntity.getContentLength())
                        .append(thisEntity.getContentEncoding(), thatEntity.getContentEncoding())
                        .isEquals();
            }

            @Override
            public void describeTo(Description description) {

            }

            private byte[] contentAsByteArray(StringEntity entity) {
                byte content[] = null;

                try {
                    content = IOUtils.toByteArray(entity.getContent());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return content;
            }
        };
    }

}

