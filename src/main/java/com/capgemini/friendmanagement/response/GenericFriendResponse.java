package com.capgemini.friendmanagement.response;

public class GenericFriendResponse {
    private boolean isSuccess;

    public GenericFriendResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
