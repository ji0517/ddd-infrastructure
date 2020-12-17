package com.xwtec.infrastructure.eventbus.spring.produce;

public class EventBusResult {

    final static String OK = "ok";

    final static String FAIL = "fail";

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

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
