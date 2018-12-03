package com.example.alberto.smart;

public class Device {




    private int id;
    private String name;
    private int port;
    private int port_status;

    public Device(int id, String name, int port, int port_status) {
        this.id = id;
        this.name = name;
        this.port = port;
        this.port_status = port_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort_status() {
        return port_status;
    }

    public void setPort_status(int port_status) {
        this.port_status = port_status;
    }
}
