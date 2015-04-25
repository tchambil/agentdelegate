package dcc.com.agent.message.si;

import org.apache.log4j.Logger;
import org.krams.tutorial.oxm.Sales;

/**
 *  Concrete class for mapping sales records
 *  
 *  @author Krams at {@link http://krams915@blogspot.com}
 */
public class SalesMapper implements IMapper {

	protected static Logger logger = Logger.getLogger("integration");
	
	public Object map(String[] content) {
		logger.debug("Mapping content: " + content);
		
		Sales sales = new Sales();
		sales.setId(content[0]);
		sales.setBranch(content[1]);
		sales.setKeyword(content[2]);
		sales.setAmount(Double.valueOf(content[3]));
		sales.setRemarks(content[4]);
		
		return sales;
	}
}
