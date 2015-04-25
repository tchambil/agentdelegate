
package dcc.com.agent.message.oxm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import java.math.BigInteger;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.blogspot.krams915.ws.schema.oss package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Ending_QNAME = new QName("http://krams915.blogspot.com/ws/schema/oss", "ending");
    private final static QName _Branch_QNAME = new QName("http://krams915.blogspot.com/ws/schema/oss", "branch");
    private final static QName _Quantity_QNAME = new QName("http://krams915.blogspot.com/ws/schema/oss", "quantity");
    private final static QName _Remarks_QNAME = new QName("http://krams915.blogspot.com/ws/schema/oss", "remarks");
    private final static QName _Code_QNAME = new QName("http://krams915.blogspot.com/ws/schema/oss", "code");
    private final static QName _Description_QNAME = new QName("http://krams915.blogspot.com/ws/schema/oss", "description");
    private final static QName _Keyword_QNAME = new QName("http://krams915.blogspot.com/ws/schema/oss", "keyword");
    private final static QName _Product_QNAME = new QName("http://krams915.blogspot.com/ws/schema/oss", "product");
    private final static QName _Amount_QNAME = new QName("http://krams915.blogspot.com/ws/schema/oss", "amount");
    private final static QName _Id_QNAME = new QName("http://krams915.blogspot.com/ws/schema/oss", "id");
    private final static QName _Beginning_QNAME = new QName("http://krams915.blogspot.com/ws/schema/oss", "beginning");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.blogspot.krams915.ws.schema.oss
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Record }
     * 
     */
    public Record createRecord() {
        return new Record();
    }

    /**
     * Create an instance of {@link Sales }
     * 
     */
    public Sales createSales() {
        return new Sales();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link AddListResponse }
     * 
     */
    public AddListResponse createAddListResponse() {
        return new AddListResponse();
    }

    /**
     * Create an instance of {@link Entity }
     * 
     */
    public Entity createEntity() {
        return new Entity();
    }

    /**
     * Create an instance of {@link AddListRequest }
     * 
     */
    public AddListRequest createAddListRequest() {
        return new AddListRequest();
    }

    /**
     * Create an instance of {@link Inventory }
     * 
     */
    public Inventory createInventory() {
        return new Inventory();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link java.math.BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://krams915.blogspot.com/ws/schema/oss", name = "ending")
    public JAXBElement<BigInteger> createEnding(BigInteger value) {
        return new JAXBElement<BigInteger>(_Ending_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://krams915.blogspot.com/ws/schema/oss", name = "branch")
    public JAXBElement<String> createBranch(String value) {
        return new JAXBElement<String>(_Branch_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link java.math.BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://krams915.blogspot.com/ws/schema/oss", name = "quantity")
    public JAXBElement<BigInteger> createQuantity(BigInteger value) {
        return new JAXBElement<BigInteger>(_Quantity_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://krams915.blogspot.com/ws/schema/oss", name = "remarks")
    public JAXBElement<String> createRemarks(String value) {
        return new JAXBElement<String>(_Remarks_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://krams915.blogspot.com/ws/schema/oss", name = "code")
    public JAXBElement<String> createCode(String value) {
        return new JAXBElement<String>(_Code_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://krams915.blogspot.com/ws/schema/oss", name = "description")
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://krams915.blogspot.com/ws/schema/oss", name = "keyword")
    public JAXBElement<String> createKeyword(String value) {
        return new JAXBElement<String>(_Keyword_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://krams915.blogspot.com/ws/schema/oss", name = "product")
    public JAXBElement<String> createProduct(String value) {
        return new JAXBElement<String>(_Product_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://krams915.blogspot.com/ws/schema/oss", name = "amount")
    public JAXBElement<Double> createAmount(Double value) {
        return new JAXBElement<Double>(_Amount_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://krams915.blogspot.com/ws/schema/oss", name = "id")
    public JAXBElement<String> createId(String value) {
        return new JAXBElement<String>(_Id_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link java.math.BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://krams915.blogspot.com/ws/schema/oss", name = "beginning")
    public JAXBElement<BigInteger> createBeginning(BigInteger value) {
        return new JAXBElement<BigInteger>(_Beginning_QNAME, BigInteger.class, null, value);
    }

}
