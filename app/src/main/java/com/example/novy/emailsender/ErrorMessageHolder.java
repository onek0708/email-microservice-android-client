package com.example.novy.emailsender;

/**
 * Created by novy on 02.03.15.
 */
public class ErrorMessageHolder {

    public static final String INVALID_SENDER_ADDRESS = "Invalid sender email address!";
    public static final String EMPTY_PASSWORD = "Password cannot be empty!";

    public static final String INVALID_RECIPIENT_ADDRESS = "Invalid recipient email address!";
    public static final String EMPTY_SUBJECT = "Subject cannot be empty!";
    public static final String EMPTY_CONTENT = "Message content cannot be empty!";

    public static final String SEND_SUCCESS = "Email sent.";
    public static final String SEND_FAILURE = "Unable to send an email!";

    public static final String UNEXPECTED_ERROR = "Unexpected error!";

    private ErrorMessageHolder() {
    }
}
