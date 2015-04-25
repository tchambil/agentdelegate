package dcc.com.agent.message.si;

import java.math.BigInteger;
import org.apache.log4j.Logger;
import org.krams.tutorial.oxm.Order;

/**
 *  Concrete class for mapping order records
 *  
 *  @author Krams at {@link http://krams915@blogspot.com}
 */
public class OrderMapper implements IMapper {

	protected static Logger logger = Logger.getLogger("integration");
	
	public Object map(String[] content) {
		logger.debug("Mapping content: " + content);
		
		Order order = new Order();
		order.setId(content[0]);
		order.setBranch(content[1]);
		order.setKeyword(content[2]);
		order.setProduct(content[3]);
		order.setQuantity(BigInteger.valueOf(Long.valueOf(content[4]).longValue()));
		
		return order;
	}
}
