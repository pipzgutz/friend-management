package com.capgemini.friendmanagement.response;

import java.util.List;

public class GenericFriendResponse {
    private boolean isSuccess;
    private List<String> recipients;
    private List<String> friendEmails;
    private String count;

    public GenericFriendResponse() {
    }

    public GenericFriendResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public GenericFriendResponse(boolean isSuccess, List<String> recipients, List<String> friendEmails, String count) {
        this.isSuccess = isSuccess;
        this.recipients = recipients;
        this.friendEmails = friendEmails;
        this.count = count;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public List<String> getFriendEmails() {
        return friendEmails;
    }

    public void setFriendEmails(List<String> friendEmails) {
        this.friendEmails = friendEmails;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
