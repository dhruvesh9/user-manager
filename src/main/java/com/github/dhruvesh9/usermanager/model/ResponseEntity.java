package com.github.dhruvesh9.usermanager.model;

public class ResponseEntity {
    private int statusCode;
    private String message;
    private String body;

    public ResponseEntity(){    }

    public ResponseEntity(int statusCode, String message,String body) {
        this.statusCode = statusCode;
        this.message = message;
        this.body = body;
    }
}
