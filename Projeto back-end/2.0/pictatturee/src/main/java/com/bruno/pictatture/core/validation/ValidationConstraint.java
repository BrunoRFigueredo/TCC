package com.bruno.pictatture.core.validation;

public class ValidationConstraint {

    private String message;
    private Boolean satisfied;

    public ValidationConstraint() {
    }

    public ValidationConstraint(String message, Boolean satisfied) {
        this.message = message;
        this.satisfied = satisfied;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isSatisfied() {
        return satisfied;
    }

    public void setSatisfied(Boolean satisfied) {
        this.satisfied = satisfied;
    }
}
