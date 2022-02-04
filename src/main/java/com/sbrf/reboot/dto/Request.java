package com.sbrf.reboot.dto;

public class Request {
    private String atmNumber;

    public Request(String atmNumber) {
        this.atmNumber = atmNumber;
    }

    public Request(){}

    public String getAtmNumber() {
        return atmNumber;
    }
}
