package com.capgemini.friendmanagement.request;

public class SubscriptionRequest {
    private String requestor;
    private String target;

    public SubscriptionRequest() {
    }

    public SubscriptionRequest(String requestor, String target) {
        this.requestor = requestor;
        this.target = target;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
