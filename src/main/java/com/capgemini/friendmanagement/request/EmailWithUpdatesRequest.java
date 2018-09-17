package com.capgemini.friendmanagement.request;

public class EmailWithUpdatesRequest {
    private String email;
    private String text;

    public EmailWithUpdatesRequest() {
    }

    public EmailWithUpdatesRequest(String email, String text) {
        this.email = email;
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
