
package dcc.com.agent.message.oxm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for record complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="record">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://krams915.blogspot.com/ws/schema/oss}sales"/>
 *         &lt;element ref="{http://krams915.blogspot.com/ws/schema/oss}inventory"/>
 *         &lt;element ref="{http://krams915.blogspot.com/ws/schema/oss}order"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "record", propOrder = {
    "sales",
    "inventory",
    "order"
})
public class Record {

    protected Sales sales;
    protected Inventory inventory;
    protected Order order;

    /**
     * Gets the value of the sales property.
     * 
     * @return
     *     possible object is
     *     {@link Sales }
     *     
     */
    public Sales getSales() {
        return sales;
    }

    /**
     * Sets the value of the sales property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sales }
     *     
     */
    public void setSales(Sales value) {
        this.sales = value;
    }

    /**
     * Gets the value of the inventory property.
     * 
     * @return
     *     possible object is
     *     {@link Inventory }
     *     
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Sets the value of the inventory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Inventory }
     *     
     */
    public void setInventory(Inventory value) {
        this.inventory = value;
    }

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link Order }
     *     
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order }
     *     
     */
    public void setOrder(Order value) {
        this.order = value;
    }

}
