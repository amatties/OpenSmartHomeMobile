package com.example.alberto.smart;

/**
 * Created by Alberto on 20/06/2018.
 */

public class Config {


    Long id;
    String ip;

    public Config(Long id, String ip) {
        this.id = id;
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
