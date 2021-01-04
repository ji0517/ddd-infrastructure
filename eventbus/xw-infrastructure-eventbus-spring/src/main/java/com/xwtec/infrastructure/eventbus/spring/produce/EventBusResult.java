package com.xwtec.infrastructure.eventbus.spring.produce;

public class EventBusResult {

    public final static String OK = "ok";

    public final static String FAIL = "fail";

    private String code;
    private String message;

    private EventBusResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static EventBusResult ok() {
        return new EventBusResult(OK, "");
    }

    public static EventBusResult ok(String message) {
        return new EventBusResult(OK, message);
    }


    public static EventBusResult fail(String message) {
        return new EventBusResult(FAIL, message);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
