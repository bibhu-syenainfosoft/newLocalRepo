import java.io.IOException;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;



public class Demo {
	private static Logger log=Logger.getLogger(Demo.class);

	public static void main(String[] args) throws IOException {
//		//create layout
//		//SimpleLayout ly=new SimpleLayout();
//		//Layout ly=new HTMLLayout();
//		//Layout ly=new XMLLayout();
////		Layout ly=new PatternLayout(" %d | %C | %M | %m | %p | %L | %l | %r | %% | %n");
//		Layout ly=new PatternLayout(" %d{yy-MMM-dd HH:mm:ss-SSS} %n");
//		
//		//create Appender + link to layout
//		//Appender ap=new ConsoleAppender(ly);
//		Appender ap=new FileAppender(ly, "C:/log4j/abc.log");
//		
//		//link appender to logger
//		log.addAppender(ap);
	
		
		log.debug("FROM DEBUG");
		log.info("FROM INFO");
		log.warn("FROM WARN");
		log.error("FROM ERROR");
		log.fatal("FROM FATAL");
	}

}