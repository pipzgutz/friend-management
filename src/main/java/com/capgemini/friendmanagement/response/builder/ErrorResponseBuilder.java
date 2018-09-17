package com.capgemini.friendmanagement.response.builder;

import com.capgemini.friendmanagement.response.ErrorResponse;

public final class ErrorResponseBuilder {
    private String errorMessage;

    private ErrorResponseBuilder() {
    }

    public static ErrorResponseBuilder anErrorResponse() {
        return new ErrorResponseBuilder();
    }

    public ErrorResponseBuilder withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public ErrorResponse build() {
        return new ErrorResponse(errorMessage);
    }
}
