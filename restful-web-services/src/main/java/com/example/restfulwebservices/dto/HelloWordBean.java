package com.example.restfulwebservices.dto;

public class HelloWordBean {

    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HelloWordBean(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWordBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
