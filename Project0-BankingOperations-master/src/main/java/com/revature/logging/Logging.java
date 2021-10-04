package com.revature.logging;

import org.apache.log4j.Logger;

public class Logging {
	
	final static Logger log = Logger.getLogger(Logging.class);
	
	// public final static Logger getLog() { return log; }
	
	private final static void logMessage(Level level, String message) {
		switch (level) {
			case TRACE:
				log.trace(message);
				break;
			case DEBUG:
				log.debug(message);
				break;
			case INFO:
				log.info(message);
				break;
			case WARN:
				log.warn(message);
				break;
			case ERROR:
				log.error(message);
				break;
			case FATAL:
				log.fatal(message);
				break;
			default:
				break;
		}
	}
	
	public final static void logTraceMessage(String message) { logMessage(Level.TRACE, message);}
	
	public final static void logDebugMessage(String message) { logMessage(Level.DEBUG, message);}
	
	public final static void logInfoMessage(String message) { logMessage(Level.INFO, message);}
	
	public final static void logWarnMessage(String message) { logMessage(Level.WARN, message);}
	
	public final static void logErrorMessage(String message) { logMessage(Level.ERROR, message);}
	
	public final static void logFatalMessage(String message) { logMessage(Level.FATAL, message);}

}
