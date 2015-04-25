package dcc.com.agent.message.si;

import org.apache.log4j.Logger;
import org.springframework.integration.Message;

/**
 *  Filters messages based on their keyword. If an item is invalid,
 *  it gets dropped from the normal process.
 *  <p>
 *  Valid keywords are SALES, INVENTORY, ORDER
 *  
 *  @author Krams at {@link http://krams915@blogspot.com}
 */
public class ProductFilter {

	protected static Logger logger = Logger.getLogger("integration");
	
	public Boolean filter(Message<?> content) {
		logger.debug(content);
		
		if (content.getHeaders().get("keyword").toString().equalsIgnoreCase(ApplicationConstants.TYPE_SALES)) {
        	return true;
        } 
		
		if (content.getHeaders().get("keyword").toString().equalsIgnoreCase(ApplicationConstants.TYPE_INVENTORY)) {
			return true;
        } 
		
		if (content.getHeaders().get("keyword").toString().equalsIgnoreCase(ApplicationConstants.TYPE_ORDER)) {
			return true;
        }
		
		logger.debug("Invalid keyword found");
		return false;
	}
}
