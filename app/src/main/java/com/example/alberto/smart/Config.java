package com.example.alberto.smart;

/**
 * Created by Alberto on 20/06/2018.
 */

public class Config {


    Long id;
    String topic;
    int outpin;
    int status;

    public Config(Long id, int outpin, int status, String topic) {
        this.id = id;
        this.outpin = outpin;
        this.topic = topic;
        this.status = status;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOutpin() {
        return outpin;
    }

    public void setOutpin(int outpin) {
        this.outpin = outpin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
