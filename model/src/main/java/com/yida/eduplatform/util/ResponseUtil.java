package com.yida.eduplatform.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ResponseUtil {
    private HashMap<String, Object> result;
    private boolean success;
    private String message;

    public ResponseUtil(){
        this.result = new HashMap<>();
        this.success = false;
        this.message = "";
    }

    public HashMap<String, Object> getResult() {
        return result;
    }

    public void setResult(HashMap<String, Object> result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
