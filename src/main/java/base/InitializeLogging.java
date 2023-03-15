package base;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.Logger;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

public class InitializeLogging {
	
	public static Logger log = LogManager.getLogger(InitializeLogging.class.getName());
	
	static {
		
		LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
		File file = new File("./src/main/resources/log4j2.xml");
		
		// this will force a reconfiguration
		context.setConfigLocation(file.toURI());

		// Log the message to file
		log.trace("Logging Initialized");
	}
}