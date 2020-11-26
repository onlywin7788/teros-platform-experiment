package com.example.demo.common.logger.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import com.example.demo.common.logger.config.LogProperties;
import org.slf4j.LoggerFactory;


public class CustomFileAppender {

    Logger logger = null;
    LoggerContext logCtx = null;
    LogProperties logProperties =null;


    public CustomFileAppender(){
        System.out.println("///default///");
        logProperties = new LogProperties();
        logProperties.setFilePath("logs/logfile");
        logProperties.setLevel(Level.INFO );
        logProperties.setMaxKBFileSize(10240);
        logProperties.setType(logProperties.LOG_TYPE_CIRCULAR);
        setFileConfig();


    }

    public CustomFileAppender( LogProperties logProperties) {
        this.logProperties = logProperties;

        setFileConfig();



    }

    public void setFileConfig(){

        logCtx = (LoggerContext) LoggerFactory.getILoggerFactory();
        logger = logCtx.getLogger(Logger.ROOT_LOGGER_NAME);

        PatternLayoutEncoder logEncoder = new PatternLayoutEncoder();
        logEncoder.setContext(logCtx);
        logEncoder.setPattern("%d{yyyy:MM:dd HH:mm:ss.SSS} %-5level --- [%thread] %logger{35} : %msg %n");
        logEncoder.start();


        // file appender
        RollingFileAppender logFileAppender = new RollingFileAppender();
        FileAppender fileAppender = new FileAppender();
        SizeAndTimeBasedFNATP sizeAndTimeBasedFNATP = new SizeAndTimeBasedFNATP();
        TimeBasedRollingPolicy logFilePolicy = new TimeBasedRollingPolicy();

        logFileAppender.setContext(logCtx);
        logFileAppender.setName("logFile");
        logFileAppender.setEncoder(logEncoder);
        logFileAppender.setAppend(true);
        logFileAppender.setFile(logProperties.getFilePath()+".log");//logFileAppender.setFile("logs/logfile.log");


        // type 별 설정
        if(logProperties.getType()== logProperties.getLOG_TYPE_LINEAR()) { //if(logProperties.getType()=="linear") {
            // linear 단일 파일명에 계속하여 로그를 저장하는 형태
            fileAppender.setContext(logCtx);
            fileAppender.setName("linearLogFile");
            fileAppender.setEncoder(logEncoder);
            fileAppender.setAppend(false);
            fileAppender.setFile(logProperties.getFilePath()+".log");
            fileAppender.start();

            logger.addAppender(fileAppender);// (수정) linear일 때
            logger.setAdditive(false);
            logger.setLevel(logProperties.getLevel());//log.setLevel(Level.DEBUG);

        }else if (logProperties.getType()== logProperties.LOG_TYPE_CIRCULAR) {
            System.out.println("circular-------------");
            // circular 특정 파일 크기 이상이 되면, 파일이 백업되며, 새로운 파일에 로그 저장 Ex) program.log  program.log , program.log.1 (원본 로그 저장)
            // 이름에 날짜도 찍힘

            sizeAndTimeBasedFNATP.setMaxFileSize(FileSize.valueOf(logProperties.getMaxKBFileSize().toString()+"kb"));
            //sizeAndTimeBasedFNATP.setMaxFileSize(new FileSize(500L));
            //sizeAndTimeBasedFNATP.setMaxFileSize(FileSize.valueOf(logProperties.getMaxFileSize()));
            //sizeAndTimeBasedFNATP.setMaxFileSize(FileSize.valueOf("1kb"));

            logFilePolicy.setContext(logCtx);
            logFilePolicy.setParent(logFileAppender);
            logFilePolicy.setFileNamePattern(logProperties.getFilePath().toString()+".%d{yyyy-MM-dd}.%i.log");
            logFilePolicy.setMaxHistory(30);
            logFilePolicy.setTimeBasedFileNamingAndTriggeringPolicy(sizeAndTimeBasedFNATP);
            logFilePolicy.start();

            logFileAppender.setRollingPolicy(logFilePolicy);
            logFileAppender.start();

            logger.setAdditive(false);
            logger.setLevel(logProperties.getLevel());//log.setLevel(Level.DEBUG);
            logger.addAppender(logFileAppender);

        }else if(logProperties.getType()== logProperties.LOG_TYPE_DAILY) {
            // Daily : 날짜 단위로 로그가 저장 Ex) program.20200210.log
            // 오늘 파일은 날짜 안바뀜

            logFilePolicy.setContext(logCtx);
            logFilePolicy.setParent(logFileAppender);
            logFilePolicy.setFileNamePattern(logProperties.getFilePath().toString()+".%d{yyyy-MM-dd}.log");
            logFilePolicy.setMaxHistory(30);
            logFilePolicy.start();

            logFileAppender.setRollingPolicy(logFilePolicy);
            logFileAppender.start();

            logger.setAdditive(false);
            logger.setLevel(logProperties.getLevel());//log.setLevel(Level.DEBUG);
            logger.addAppender(logFileAppender);

        }


    }



    public void info(String msg){

        logger.info(msg);

    }

    public void debug(String msg){

        logger.debug(msg);

    }

    public void warn(String msg){

        logger.warn(msg);

    }

    public void trace(String msg){

        logger.trace(msg);

    }

    public void error(String msg){

        logger.error(msg);

    }

    //console

/*        ConsoleAppender logConsoleAppender = new ConsoleAppender();
        logConsoleAppender.setContext(logCtx);
        logConsoleAppender.setName("console");
        logConsoleAppender.setEncoder(logEncoder);
        logConsoleAppender.start();

        logger.setAdditive(false);
        logger.setLevel(Level.INFO);
        logger.addAppender(logConsoleAppender);*/

}
