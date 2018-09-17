package com.capgemini.friendmanagement.request;

public class GenericEmailRequest {
    private String email;

    public GenericEmailRequest() {
    }

    public GenericEmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
