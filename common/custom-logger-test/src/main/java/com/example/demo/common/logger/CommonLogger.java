package com.example.demo.common.logger;

import com.example.demo.common.logger.appender.CustomFileAppender;
import com.example.demo.common.logger.config.LogProperties;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class CommonLogger implements ApplicationContextInitializer {


    LogProperties logProperties;
    CustomFileAppender customFileAppender = null;

    public CommonLogger(LogProperties logProperties) {
        this.logProperties = logProperties;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        //(수정) 여기서 로거 불러오기
        //문제 - console 이랑 file 둘 다 쓰면 콘솔로그가 두줄 씩 찍힘
        //customFileAppender = new CustomFileAppender(logProperties);
        customFileAppender = new CustomFileAppender(); //default

        customFileAppender.error("error");
        customFileAppender.warn("warn");
        customFileAppender.info("info");
        customFileAppender.trace("trace");
        customFileAppender.debug("debug");
    }

    public void info(String msg) {
        customFileAppender.info(msg);
    }
/*
    public void debug(String msg) {

        logger.debug(msg);

    }

    public void warn(String msg) {

        logger.warn(msg);

    }

    public void trace(String msg) {

        logger.trace(msg);

    }

    public void error(String msg) {

        logger.error(msg);

    }
*/
}
