package dcc.com.agent.message.si;

import org.apache.log4j.Logger;
import org.springframework.integration.Message;

/**
 *  Routes messages based on their keyword. Invalid entries
 *  are routed to unknownChannel
 *  
 *  @author Krams at {@link http://krams915@blogspot.com}
 */
public class ProductRouter {

	protected static Logger logger = Logger.getLogger("integration");
	
	public String route(Message<?> content) {
		
		if (content.getHeaders().get("keyword").toString().equalsIgnoreCase(ApplicationConstants.TYPE_SALES)) {
    		logger.debug("Routing to salesChannel");
        	return "salesChannel";
        	
        } else if (content.getHeaders().get("keyword").toString().equalsIgnoreCase(ApplicationConstants.TYPE_INVENTORY)) {
        	logger.debug("Routing to inventoryChannel");
        	return "inventoryChannel";
        	
        } else if (content.getHeaders().get("keyword").toString().equalsIgnoreCase(ApplicationConstants.TYPE_ORDER)) {
        	logger.debug("Routing to orderChannel");
        	return "orderChannel";
        	
        } else  {
        	logger.debug("Routing to unknownChannel");
        	return "unknownChannel";
        } 
    }
}
