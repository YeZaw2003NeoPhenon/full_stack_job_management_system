package com.example.job_management_system.response;


public class CustomResponse<T>{

    private int status;
    private String message;
    private T data;

    public CustomResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public CustomResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class Builder<T>{
        private int status;
        private String message;
        private T data;

//       public Builder(String message, T data){
//            this.message = message;
//            this.data = data;
//        }

        public Builder<T> status(int status){
            this.status = status;
            return this;
        }

        public Builder<T> message(String message){
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public CustomResponse<T> build(){
            return new CustomResponse<>(status,message,data);
        }

    }
}
