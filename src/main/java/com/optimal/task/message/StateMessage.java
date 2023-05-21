package com.optimal.task.message;

public class StateMessage {

    private String text;
    private boolean success;
    private Integer code;


    public StateMessage(String text, boolean success, Integer code) {
        this.text = text;
        this.success = success;
        this.code = code;
    }

    public StateMessage(String text, boolean success) {
        this.text = text;
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public StateMessage() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public StateMessage parse(Message message){
        return new StateMessage(message.getName(), message.isState(), message.getCode());
    }
}
