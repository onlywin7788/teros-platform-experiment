package com.example.demo;



import ch.qos.logback.classic.Level;
import com.example.demo.common.logger.CommonLogger;
import com.example.demo.common.logger.config.LogProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		// 프로젝트 실행시 콘솔/ 파일 로깅 설정
		SpringApplication application = new SpringApplication(DemoApplication.class);

		// setting properties
		LogProperties logProperties = new LogProperties();
		logProperties.setFilePath("logs/logfile");
		logProperties.setLevel(Level.INFO );
		logProperties.setMaxKBFileSize(3);
		logProperties.setType(logProperties.LOG_TYPE_CIRCULAR);

		// set logger
		CommonLogger commonLogger = new CommonLogger(logProperties);

		application.addInitializers(commonLogger);
		application.run(args);

		//application.addInitializers(new CommonLogger(logProperties));
		//application.run(args);

		commonLogger.info("HELLO WORLD");


	}


	/*public static class LoggingInitializer implements ApplicationContextInitializer {
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {

			LoggerContext logCtx = (LoggerContext) LoggerFactory.getILoggerFactory();

			PatternLayoutEncoder logEncoder = new PatternLayoutEncoder();
			logEncoder.setContext(logCtx);
			logEncoder.setCharset(Charset.forName( "UTF-8" ) );
			logEncoder.setPattern("%-12date{YYYY-MM-dd HH:mm:ss.SSS} %-5level - %msg%n");
			logEncoder.start();

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

			RollingFileAppender logFileAppender = new RollingFileAppender();
			logFileAppender.setContext(logCtx);
			logFileAppender.setName("logFile");
			logFileAppender.setEncoder(logEncoder);
			logFileAppender.setAppend(true);
			logFileAppender.setFile("logs/logfile.log");


			SizeAndTimeBasedFNATP sizeAndTimeBasedFNATP = new SizeAndTimeBasedFNATP();

			//sizeAndTimeBasedFNATP.setMaxFileSize(new FileSize(1));

			sizeAndTimeBasedFNATP.setMaxFileSize(FileSize.valueOf("5kb"));

			TimeBasedRollingPolicy logFilePolicy = new TimeBasedRollingPolicy();
			logFilePolicy.setContext(logCtx);
			logFilePolicy.setParent(logFileAppender);
			//logFilePolicy.setFileNamePattern("logs/tomcat_log.%d{yyyy-MM-dd}-%i.log");
			logFilePolicy.setFileNamePattern("logs/logfile-%d{yyyy-MM-dd_HH}.log");
			logFilePolicy.setMaxHistory(30);

			//logFilePolicy.setTimeBasedFileNamingAndTriggeringPolicy(sizeAndTimeBasedFNATP);
			logFilePolicy.start();



			logFileAppender.setRollingPolicy(logFilePolicy);
			logFileAppender.start();

			//CommonLogger log = logCtx.getLogger("Main"); // 한번 바꿔봄
			CommonLogger log = logCtx.getLogger(DemoApplication.class);
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
	}*/
}
