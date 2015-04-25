package dcc.com.agent.message.si;

import java.math.BigInteger;
import org.apache.log4j.Logger;
import org.krams.tutorial.oxm.Inventory;

/**
 *  Concrete class for mapping inventory records
 *  
 *  @author Krams at {@link http://krams915@blogspot.com}
 */
public class InventoryMapper implements IMapper {

	protected static Logger logger = Logger.getLogger("integration");
	
	public Object map(String[] content) {
		logger.debug("Mapping content: " + content);
		
		Inventory inventory = new Inventory();
		inventory.setId(content[0]);
		inventory.setBranch(content[1]);
		inventory.setKeyword(content[2]);
		inventory.setProduct(content[3]);
		inventory.setBeginning(BigInteger.valueOf(Long.valueOf(content[4]).longValue()));
		inventory.setEnding(BigInteger.valueOf(Long.valueOf(content[5]).longValue()));
		
		return inventory;
	}
}
