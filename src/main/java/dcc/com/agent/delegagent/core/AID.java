package dcc.com.agent.delegagent.core;

/**
 * Created by teo on 30/04/15.
 */
 import java.util.*;
//import java.io.Writer; // FIXME: This must go away
//import java.io.IOException; // FIXME: This must go away


public class AID {
    // Unique ID of the platform, used to build the GUID of resident agents.
    private static String platformID;
    private String name = new String();
    private List addresses = new ArrayList();
    private List resolvers = new ArrayList();


    /**
     * Constructs an Agent-Identifier whose slot name is set to an empty string
     * (JADE)
     */
    public AID() {
        this("",ISGUID);
    }

    /** Constructor for an Agent-identifier
     * This constructor (which is deprecated), examines the name
     * to see if the "@" chararcter is present.  If so, it calls
     * <code> this(name, ISGUID)<code>
     * otherwise it calls <code>this(name, ISLOCALNAME)</code>
     * This ensures better compatibility with JADE2.2 code.
     * @param guid is the Globally Unique identifer for the agent. The slot name
     * assumes that value in the constructed object.
     * @deprecated This constructor might generate a wrong AID, if
     * the passed parameter is not a guid (globally unique identifier), but
     * the local name of an agent (e.g. "da0").
     * @see AID#AID(String boolean)
     */
    public AID(String guid) {
        this(guid,ISGUID);
    }

    /** Constructor for an Agent-identifier
     * @param name is the value for the slot name for the agent.
     * @param isGUID indicates if the passed <code>name</code>
     * is already a globally unique identifier or not. Two
     * constants <code>ISGUID</code>, <code>ISLOCALNAME</code>
     * have also been defined for setting a value for this parameter.
     * If the name is a local name, then the HAP (Home Agent Platform)
     * is concatenated to the name, separated by  "@".
     **/
    public AID(String name, boolean isGUID) {
        // initialize the static variable atHAP, if not yet initialized
        if (atHAP == null)
            atHAP = "@"+getPlatformID();
        if (isGUID)
            setName(name);
        else
            setLocalName(name);
    }

    //creada por mi
    public AID(String name, String platform) {
        // initialize the static variable atHAP, if not yet initialized
        if (atHAP == null)
            atHAP = "@"+ platform;
        setName(name);
    }

    static final String getPlatformID() {
        return platformID;
    }

    static final void setPlatformID(String id) {
        platformID = id;
    }

    /** constant to be used in the constructor of the AID **/
    public static final boolean ISGUID = true;
    /** constant to be used in the constructor of the AID **/
    public static final boolean ISLOCALNAME = false;

    /** private variable containing the right part of a local name **/
    private static String atHAP = null;

    /**
     * This method permits to set the symbolic name of an agent.
     * The passed parameter must be a GUID and not a local name.
     */
    public void setName(String n){
        name = n.trim();
    }

    /**
     * This method permits to set the symbolic name of an agent.
     * The passed parameter must be a local name.
     */
    public void setLocalName(String n){
        name = n.trim();
        if ((name != null) && (!name.toLowerCase().endsWith(atHAP.toLowerCase())))
            name = name.concat(atHAP);
    }

    /**
     * This method returns the name of the agent.
     */
    public String getName(){
        return name;
    }

    /**
     * This method permits to add a transport address where
     * the agent can be contacted.
     * The address is added only if not yet present
     */
    public void addAddresses(String url) {
        if (!addresses.contains(url)) {
            addresses.add(url);
        }
    }

    /**
     * To remove a transport address.
     * @param url the address to remove
     * @return true if the addres has been found and removed, false otherwise.
     */
    public boolean removeAddresses(String url) {
        return addresses.remove(url);
    }

    /**
     * To remove all addresses of the agent
     */
    public void clearAllAddresses(){
        addresses.clear();
    }



    /**
     * This method permits to add the AID of a resolver (an agent where name
     * resolution services for the agent can be contacted)
     */
    public void addResolvers(AID aid){
        resolvers.add(aid);
    }

    /**
     * To remove a resolver.
     * @param aid the AID of the resolver to remove
     * @return true if the resolver has been found and removed, false otherwise.
     */
    public boolean removeResolvers(AID aid){
        return resolvers.remove(aid);
    }

    /**
     * To remove all resolvers.
     */
    public void clearAllResolvers(){
        resolvers.clear();
    }

    /**
     * Returns an array of string containing all the addresses of the agent
     */
    public String[] getAddressesArray() {
        Object[] objs = addresses.toArray();
        String[] result = new String[objs.length];
        System.arraycopy(objs, 0, result, 0, objs.length);
        return result;
    }

    /**
     * Returns an array containing all the AIDs of the resolvers.
     */
    public AID[] getResolversArray() {
        Object[] objs = resolvers.toArray();
        AID[] result = new AID[objs.length];
        System.arraycopy(objs, 0, result, 0, objs.length);
        return result;
    }

    /**
     * @return the String full representation of this AID
     **/
    public String toString() {
        StringBuffer s = new StringBuffer("( agent-identifier ");
        if ((name!=null)&&(name.length()>0)) {
            s.append(" :name ");
            s.append(name);
        }
        if (addresses.size()>0)
            s.append(" :addresses (sequence ");
        for (int i=0; i<addresses.size(); i++)
            try {
                s.append((String)addresses.get(i));
                s.append(" ");
            }
            catch (IndexOutOfBoundsException e) {e.printStackTrace();}
        if (addresses.size()>0)
            s.append(")");
        if (resolvers.size()>0)
            s.append(" :resolvers (sequence ");
        for (int i=0; i<resolvers.size(); i++) {
            try {
                s.append(resolvers.get(i).toString());
            }
            catch (IndexOutOfBoundsException e) {e.printStackTrace();}
            s.append(" ");
        }
        if (resolvers.size()>0)
            s.append(")");
	    /*Enumeration e = userDefSlots.propertyNames();
	    String tmp;
	    while (e.hasMoreElements()) {
	        tmp = (String)e.nextElement();
	        s.append(" :X-");
	        s.append(tmp);
	        s.append(" ");
	        s.append(userDefSlots.getProperty(tmp));
	    }*/
        s.append(")");
        return s.toString();
    }



    /**
     Equality operation. This method compares an <code>AID</code> object with
     another or with a Java <code>String</code>. The comparison is case
     insensitive.
     @param o The Java object to compare this <code>AID</code> to.
     @return <code>true</code> if one of the following holds:
     <ul>
     <li> The argument <code>o</code> is an <code>AID</code> object
     with the same <em>GUID</em> in its name slot (apart from
     differences in case).
     <li> The argument <code>o</code> is a <code>String</code> that is
     equal to the <em>GUID</em> contained in the name slot of this
     Agent ID (apart from differences in case).
     </ul>
     */
	 /*
	  public boolean equals(Object o) {

	      if (o == null)
	      return false;
	    if(o instanceof String) {
	      return CaseInsensitiveString.equalsIgnoreCase(name, (String)o);
	    }
	    try {
	      AID id = (AID)o;
	      return CaseInsensitiveString.equalsIgnoreCase(name, id.name);
	    }
	    catch(ClassCastException cce) {
	      return false;
	    }

	  }
	*/

    /**
     Comparison operation. This operation imposes a total order
     relationship over Agent IDs.
     @param o Another <code>AID</code> object, that will be compared
     with the current <code>AID</code>.
     @return -1, 0 or 1 according to the lexicographical order of the
     <em>GUID</em> of the two agent IDs, apart from differences in
     case.
     */
    public int compareTo(Object o) {
        AID id = (AID)o;
        return name.toLowerCase().toUpperCase().compareTo(id.name.toLowerCase().toUpperCase());
    }


    /**
     Hash code. This method returns an hash code in such a way that two
     <code>AID</code> objects with equal names or with names differing
     only in case have the same hash code.
     @return The hash code for this <code>AID</code> object.
     */
	 /* public int hashCode() {
	    return name.toLowerCase().hashCode();
	  }
	*/
    /**
     * Returns the local name of the agent (without the HAP).
     * If the agent is not local, then the method returns its GUID.
     */
    public String getLocalName() {
        int atPos = name.lastIndexOf('@');
        if(atPos == -1)
            return name;
        else
            return name.substring(0, atPos);
    }

    /**
     * Returns the HAP of the agent.
     */
    String getHap() {
        int atPos = name.lastIndexOf('@');
        if(atPos == -1)
            return name;
        else
            return name.substring(atPos + 1);
    }

}

