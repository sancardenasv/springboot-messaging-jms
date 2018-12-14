package com.electroeing.messagingjms.models;

import java.io.Serializable;

public class Message implements Serializable {
    private String to;
    private String body;

    public Message(String to, String body) {
        this.to = to;
        this.body = body;
    }

    public Message() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("to='").append(to).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
