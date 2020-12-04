package com.example.demo;



import com.example.demo.config.LoggingInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	//private static final Logger logger = (Logger) LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(DemoApplication.class);
		LogProperties logProperties = new LogProperties("5kb");
		application.addInitializers(new LoggingInitializer(logProperties));
		application.run(args);

		//SpringApplication.run(DemoApplication.class, args);
/*
		log.error("error");
		log.warn("warn");
		log.info("info");
		log.trace("trace");
		log.debug("debug");*/


		/*LoggerContext logCtx = (LoggerContext) LoggerFactory.getILoggerFactory();

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
		logEncoder.setPattern("%d{yyyyMMdd HH:mm:ss.SSS} [%thread] %-3level %logger{5} - %msg %n");
		//logEncoder.setPattern("%-12date{YYYY-MM-dd HH:mm:ss.SSS} %-5level - %msg%n");
		logEncoder.start();

		RollingFileAppender logFileAppender = new RollingFileAppender();
		logFileAppender.setContext(logCtx);
		logFileAppender.setName("logFile");
		logFileAppender.setEncoder(logEncoder);
		logFileAppender.setAppend(true);
		logFileAppender.setFile("logs/logfile.log");

		TimeBasedRollingPolicy logFilePolicy = new TimeBasedRollingPolicy();
		logFilePolicy.setContext(logCtx);
		logFilePolicy.setParent(logFileAppender);
		logFilePolicy.setFileNamePattern("logs/logfile-%d{yyyy-MM-dd_HH}.log");
		logFilePolicy.setMaxHistory(7);
		logFilePolicy.start();

		logFileAppender.setRollingPolicy(logFilePolicy);
		logFileAppender.start();

		Logger log = logCtx.getLogger("Main");
		log.setAdditive(false);
		log.setLevel(Level.DEBUG);
		log.addAppender(logConsoleAppender);
		log.addAppender(logFileAppender);




		log.info("Example log from {}", DemoApplication.class.getSimpleName());


		SpringApplication.run(DemoApplication.class, args);*/
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

			//Logger log = logCtx.getLogger("Main"); // 한번 바꿔봄
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
	}*/
}
