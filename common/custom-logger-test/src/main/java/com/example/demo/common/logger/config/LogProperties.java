package com.example.demo.common.logger.config;


import ch.qos.logback.classic.Level;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LogProperties {

    public final int LOG_LEVEL_INFO=1000;
    public final int LOG_LEVEL_DEBUG=1001;
    public final int LOG_LEVEL_ERROR=1002;

    public final int LOG_TYPE_LINEAR=1003;
    public final int LOG_TYPE_CIRCULAR=1004;
    public final int LOG_TYPE_DAILY=1005;

    private Integer maxKBFileSize;
    private String filePath;
    private Integer type;
    private Level level;
    public LogProperties() {

    }
}
