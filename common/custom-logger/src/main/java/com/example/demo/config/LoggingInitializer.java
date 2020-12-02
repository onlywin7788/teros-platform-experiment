package com.example.demo.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import com.example.demo.DemoApplication;
import com.example.demo.LogProperties;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.nio.charset.Charset;

public class LoggingInitializer implements ApplicationContextInitializer {


    LogProperties logProperties;

    public LoggingInitializer(LogProperties logProperties) {
        this.logProperties = logProperties;
    }


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        LoggerContext logCtx = (LoggerContext) LoggerFactory.getILoggerFactory();

        PatternLayoutEncoder logEncoder = new PatternLayoutEncoder();
        logEncoder.setContext(logCtx);
        logEncoder.setCharset(Charset.forName( "UTF-8" ) );
        logEncoder.setPattern("%-12date{YYYY-MM-dd HH:mm:ss.SSS} %-5level - %msg%n");
        logEncoder.start();


        // console 로깅 설정

        /*
        <?xml version="1.0" encoding="UTF-8"?>
        <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
            <encoder>
                <charset>UTF-8</charset>
                <pattern>%-12date{YYYY-MM-dd HH:mm:ss.SSS} %-5level - %msg%n</pattern>
            </encoder>
        </appender>
        */

        ConsoleAppender logConsoleAppender = new ConsoleAppender();
        logConsoleAppender.setContext(logCtx);
        logConsoleAppender.setName("console");
        logConsoleAppender.setEncoder(logEncoder);
        logConsoleAppender.start();


        logEncoder = new PatternLayoutEncoder();
        logEncoder.setContext(logCtx);
        logEncoder.setPattern("%d{yyyy:MM:dd HH:mm:ss.SSS} %-5level --- [%thread] %logger{35} : %msg %n");
        //logEncoder.setPattern("%d{yyyyMMdd HH:mm:ss.SSS} [%thread] %-3level %logger{5} - %msg %n");
        //logEncoder.setPattern("%-12date{YYYY-MM-dd HH:mm:ss.SSS} %-5level - %msg%n");
        logEncoder.start();


        // file 로깅 설정

        /*

        <?xml version="1.0" encoding="UTF-8"?>
        <appender name="dailyRollingFileAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
            <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <fileNamePattern>${LOG_FILE}/logfile.%d{yyyy-MM-dd}-%i.log</fileNamePattern>
                <maxHistory>30</maxHistory>
                <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                    <maxFileSize>5KB</maxFileSize>
                </timeBasedFileNamingAndTriggeringPolicy>
            </rollingPolicy>
            <encoder>
                <charset>UTF-8</charset>
                <pattern>%d{yyyy:MM:dd HH:mm:ss.SSS} %-5level --- [%thread] %logger{35} : %msg %n</pattern>
            </encoder>
        </appender>

        */

        RollingFileAppender logFileAppender = new RollingFileAppender();
        logFileAppender.setContext(logCtx);
        logFileAppender.setName("logFile");
        logFileAppender.setEncoder(logEncoder);
        logFileAppender.setAppend(true);
        logFileAppender.setFile("logs/logfile.log");


        SizeAndTimeBasedFNATP sizeAndTimeBasedFNATP = new SizeAndTimeBasedFNATP();
        sizeAndTimeBasedFNATP.setMaxFileSize(FileSize.valueOf(logProperties.getMaxFileSize()));
        //sizeAndTimeBasedFNATP.setMaxFileSize(new FileSize(1));
        //sizeAndTimeBasedFNATP.setMaxFileSize(FileSize.valueOf("5kb"));


        TimeBasedRollingPolicy logFilePolicy = new TimeBasedRollingPolicy();
        logFilePolicy.setContext(logCtx);
        logFilePolicy.setParent(logFileAppender);
        logFilePolicy.setFileNamePattern("logs/logfile.%d{yyyy-MM-dd}-%i.log");
        //logFilePolicy.setFileNamePattern("logs/logfile-%d{yyyy-MM-dd_HH}.log");
        logFilePolicy.setMaxHistory(30);


        logFilePolicy.setTimeBasedFileNamingAndTriggeringPolicy(sizeAndTimeBasedFNATP);
        System.out.println(logFilePolicy.toString());
        logFilePolicy.start();


        logFileAppender.setRollingPolicy(logFilePolicy);
        logFileAppender.start();


        Logger log = logCtx.getLogger(DemoApplication.class);
        log.setAdditive(false);
        log.setLevel(Level.DEBUG);
        log.addAppender(logConsoleAppender);
        log.addAppender(logFileAppender);


        log.info("Example log from {}", DemoApplication.class.getSimpleName());
        log.error("error");
        log.warn("warn");
        log.info("info");
        log.trace("trace");
        log.debug("debug");

    }
}
