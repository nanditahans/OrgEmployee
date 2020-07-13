package com.example.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RootResponseDTO implements Serializable {
        private boolean success;
        private boolean error;
        private List<ErrorResponseObject> errors;
        private Serializable body;

        /**
         * Instantiates a new Root response dto.
         */
        public RootResponseDTO(){

        }

        public RootResponseDTO(boolean success, String body, boolean error, List<ErrorResponseObject> errors){
            this.success = success;
            this.body = body;
            this.error = error;
            this.errors = errors;
        }

        public RootResponseDTO(boolean success, String body){
            this.success = success;
            this.body = body;
            this.error = false;
            this.errors = new ArrayList<>();
        }

        public <T> RootResponseDTO(boolean success, T body) {
            this.success = success;
            this.error = false;
            this.errors = new ArrayList<>();
            this.body = new ArrayList<T>();
            ((ArrayList) this.body).add(body);
        }

       /* public RootResponseDTO(boolean success){
        this.success = success;
        this.error = false;
    }*/
        public boolean isSuccess() {
            return success;
        }

        public void setStatus(boolean success) {
            this.success = success;
        }

        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }


        public List<ErrorResponseObject> getErrors() {
            return errors;
        }

        public void setErrors(List<ErrorResponseObject> errors) {
            this.errors = errors;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }


        public Serializable getBody() {
            return body;
        }


        public void setBody(Serializable body) {
            this.body = body;
        }


        public void addErrorToRequest(String errorKey, String errorString){
            if (this.errors != null) {
                this.errors.add(new ErrorResponseObject(errorKey, errorString));
            } else {
                this.errors = new ArrayList<>();
                this.errors.add(new ErrorResponseObject(errorKey, errorString));
            }
        }
    }
