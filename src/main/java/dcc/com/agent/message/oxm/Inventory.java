
package dcc.com.agent.message.oxm;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://krams915.blogspot.com/ws/schema/oss}entity">
 *       &lt;sequence>
 *         &lt;element ref="{http://krams915.blogspot.com/ws/schema/oss}product"/>
 *         &lt;element ref="{http://krams915.blogspot.com/ws/schema/oss}beginning"/>
 *         &lt;element ref="{http://krams915.blogspot.com/ws/schema/oss}ending"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "product",
    "beginning",
    "ending"
})
@XmlRootElement(name = "inventory")
public class Inventory
    extends Entity
{

    @XmlElement(required = true)
    protected String product;
    @XmlElement(required = true)
    protected BigInteger beginning;
    @XmlElement(required = true)
    protected BigInteger ending;

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduct(String value) {
        this.product = value;
    }

    /**
     * Gets the value of the beginning property.
     * 
     * @return
     *     possible object is
     *     {@link java.math.BigInteger }
     *     
     */
    public BigInteger getBeginning() {
        return beginning;
    }

    /**
     * Sets the value of the beginning property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.math.BigInteger }
     *     
     */
    public void setBeginning(BigInteger value) {
        this.beginning = value;
    }

    /**
     * Gets the value of the ending property.
     * 
     * @return
     *     possible object is
     *     {@link java.math.BigInteger }
     *     
     */
    public BigInteger getEnding() {
        return ending;
    }

    /**
     * Sets the value of the ending property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.math.BigInteger }
     *     
     */
    public void setEnding(BigInteger value) {
        this.ending = value;
    }

}
