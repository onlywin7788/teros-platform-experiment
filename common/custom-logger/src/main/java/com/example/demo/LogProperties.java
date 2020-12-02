package com.example.demo;

public class LogProperties {

    String maxFileSize;

    public LogProperties(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
}
