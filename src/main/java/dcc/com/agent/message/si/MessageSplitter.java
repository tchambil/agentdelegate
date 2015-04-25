package dcc.com.agent.message.si;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.integration.Message;
import org.springframework.integration.splitter.AbstractMessageSplitter;

/**
 *  Retrieves the message payload and returns it an array of messages. 
 *  For this application, the message payload is retrieved via JDBC
 *  
 *  @author Krams at {@link http://krams915@blogspot.com}
 */
public class MessageSplitter extends AbstractMessageSplitter {

	protected static Logger logger = Logger.getLogger("integration");

	@Override
	protected ArrayList<?> splitMessage(Message<?> message) {
		
		ArrayList<?> messages = (ArrayList<?>) message.getPayload();
		
		logger.debug("Total messages: " + messages.size());
		for (Object mess: messages) {
			logger.debug(mess.toString());
		}
		
		return messages;
	}

}
