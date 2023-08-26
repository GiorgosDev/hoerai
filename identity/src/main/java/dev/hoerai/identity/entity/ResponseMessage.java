package dev.hoerai.identity.entity;


public class ResponseMessage {


    private final int status;
    private final String message;
    private final Object payload;

    public ResponseMessage(int status, String message, Object payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getPayload() {
        return payload;
    }


}
