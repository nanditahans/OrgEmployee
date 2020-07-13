package com.example.demo.dto;

public class ErrorResponseObject {
    private String errorField;
    private String message;

    public ErrorResponseObject() {
        errorField = "default";
    }

    public static ErrorResponseObject build() {
        return new ErrorResponseObject();
    }

    public ErrorResponseObject(String errorField, String message) {
        this.errorField = errorField;
        this.message = message;
    }

        public String getErrorField () {
            return errorField;
        }

        public void setErrorField (String errorField){
            this.errorField = errorField;
        }

        public String getMessage () {
            return message;
        }
        public void setMessage (String message){
            this.message = message;
        }
    }



