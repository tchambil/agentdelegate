package dcc.com.agent.delegagent.core.Communication;

/**
 * Created by teo on 30/04/15.
 */

import dcc.com.agent.delegagent.core.AID;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class ACLMessage {
    /** constant identifying the FIPA performative **/
    public static final int ACCEPT_PROPOSAL = 0;
    /** constant identifying the FIPA performative **/
    public static final int AGREE = 1;
    /** constant identifying the FIPA performative **/
    public static final int CANCEL = 2;
    /** constant identifying the FIPA performative **/
    public static final int CFP = 3;
    /** constant identifying the FIPA performative **/
    public static final int CONFIRM = 4;
    /** constant identifying the FIPA performative **/
    public static final int DISCONFIRM = 5;
    /** constant identifying the FIPA performative **/
    public static final int FAILURE = 6;
    /** constant identifying the FIPA performative **/
    public static final int INFORM = 7;
    /** constant identifying the FIPA performative **/
    public static final int INFORM_IF = 8;
    /** constant identifying the FIPA performative **/
    public static final int INFORM_REF = 9;
    /** constant identifying the FIPA performative **/
    public static final int NOT_UNDERSTOOD = 10;
    /** constant identifying the FIPA performative **/
    public static final int PROPOSE = 11;
    /** constant identifying the FIPA performative **/
    public static final int QUERY_IF = 12;
    /** constant identifying the FIPA performative **/
    public static final int QUERY_REF = 13;
    /** constant identifying the FIPA performative **/
    public static final int REFUSE = 14;
    /** constant identifying the FIPA performative **/
    public static final int REJECT_PROPOSAL = 15;
    /** constant identifying the FIPA performative **/
    public static final int REQUEST = 16;
    /** constant identifying the FIPA performative **/
    public static final int REQUEST_WHEN = 17;
    /** constant identifying the FIPA performative **/
    public static final int REQUEST_WHENEVER = 18;
    /** constant identifying the FIPA performative **/
    public static final int SUBSCRIBE = 19;
    /** constant identifying the FIPA performative **/
    public static final int PROXY = 20;
    /** constant identifying the FIPA performative **/
    public static final int PROPAGATE = 21;
    /** constant identifying an unknown performative **/
    public static final int UNKNOWN = -1;

    /**
     @serial
     */
    private int performative; // keeps the performative type of this object
    private static List performatives = new ArrayList(22);
    static { // initialization of the Vector of performatives
        performatives.add("ACCEPT-PROPOSAL");
        performatives.add("AGREE");
        performatives.add("CANCEL");
        performatives.add("CFP");
        performatives.add("CONFIRM");
        performatives.add("DISCONFIRM");
        performatives.add("FAILURE");
        performatives.add("INFORM");
        performatives.add("INFORM-IF");
        performatives.add("INFORM-REF");
        performatives.add("NOT-UNDERSTOOD");
        performatives.add("PROPOSE");
        performatives.add("QUERY-IF");
        performatives.add("QUERY-REF");
        performatives.add("REFUSE");
        performatives.add("REJECT-PROPOSAL");
        performatives.add("REQUEST");
        performatives.add("REQUEST-WHEN");
        performatives.add("REQUEST-WHENEVER");
        performatives.add("SUBSCRIBE");
        performatives.add("PROXY");
        performatives.add("PROPAGATE");
    }

    /**
     @serial
     */
    private AID source = null;

    //atributo añadido por mi para que solo tenga un receptor;
    /**
     @serial
     */
    private AID receiver = null;

    /**
     @serial
     */
    private ArrayList dests = new ArrayList();

    /**
     @serial
     */
    private ArrayList reply_to = new ArrayList();

    /**
     @serial
     */
    // At a given time or content or byteSequenceContent are != null,
    // it is not allowed that both are != null
    private StringBuffer content = null;

    /**
     @serial
     */
    private StringBuffer reply_with = null;

    /**
     @serial
     */
    private StringBuffer in_reply_to = null;

    /**
     @serial
     */
    private StringBuffer encoding = null;

    /**
     @serial
     */
    private StringBuffer language = null;

    /**
     @serial
     */
    private StringBuffer ontology = null;

    /**
     @serial
     */
    private long reply_byInMillisec = 0;

    /**
     @serial
     */
    private StringBuffer protocol = null;

    /**
     @serial
     */
    private StringBuffer conversation_id = null;


    /**
     @deprecated Since every ACL Message must have a message type, you
     should use the new constructor which gets a message type as a
     parameter.  To avoid problems, now this constructor silently sets
     the message type to <code>not-understood</code>.

     */
    public ACLMessage() {
        performative = NOT_UNDERSTOOD;
    }

    /**
     * This constructor creates an ACL message object with the specified
     * performative. If the passed integer does not correspond to any of
     * the known performatives, it silently initializes the message to
     * <code>not-understood</code>.
     **/
    public ACLMessage(int perf) {
        performative = perf;
    }

    /**
     Writes the <code>:sender</code> slot. <em><b>Warning:</b> no
     checks are made to validate the slot value.</em>


     */
    public void setSender(AID s) {
        if (s != null)
            source = s;
        else
            source = null;
    }

    public void setReceiver(AID s) {
        if (s != null)
            receiver = s;
        else
            receiver= null;
    }

    /**
     Adds a value to <code>:receiver</code> slot. <em><b>Warning:</b>
     no checks are made to validate the slot value.</em>
     @param r The value to add to the slot value set.
     */
    public void addReceiver(AID r) {
        if(r != null)
            dests.add(r);
    }

    /**
     Removes a value from <code>:receiver</code>
     slot. <em><b>Warning:</b> no checks are made to validate the slot
     value.</em>
     @param r The value to remove from the slot value set.
     @return true if the AID has been found and removed, false otherwise
     */
    public boolean removeReceiver(AID r) {
        if (r != null)
            return dests.remove(r);
        else
            return false;
    }

    /**
     Removes all values from <code>:receiver</code>
     slot. <em><b>Warning:</b> no checks are made to validate the slot
     value.</em>
     */
    public void clearAllReceiver() {
        dests.clear();
    }



    /**
     Adds a value to <code>:reply-to</code> slot. <em><b>Warning:</b>
     no checks are made to validate the slot value.</em>
     @param dest The value to add to the slot value set.
     */
    public void addReplyTo(AID dest) {
        if (dest != null)
            reply_to.add(dest);
    }

    /**
     Removes a value from <code>:reply_to</code>
     slot. <em><b>Warning:</b> no checks are made to validate the slot
     value.</em>
     @param dest The value to remove from the slot value set.
     @return true if the AID has been found and removed, false otherwise
     */
    public boolean removeReplyTo(AID dest) {
        if (dest != null)
            return reply_to.remove(dest);
        else
            return false;
    }

    /**
     Removes all values from <code>:reply_to</code>
     slot. <em><b>Warning:</b> no checks are made to validate the slot
     value.</em>
     */
    public void clearAllReplyTo() {
        reply_to.clear();
    }

    /**
     * set the performative of this ACL message object to the passed constant.
     * Remind to
     * use the set of constants (i.e. <code> INFORM, REQUEST, ... </code>)
     * defined in this class
     */
    public void setPerformative(int perf) {
        performative = perf;
    }

    /**
     * Writes the <code>:content</code> slot. <em><b>Warning:</b> no
     * checks are made to validate the slot value.</em> <p>
     * <p>Notice that, in general, setting a String content and getting
     * back a byte sequence content - or viceversa - does not return
     * the same value, i.e. the following relation does not hold
     * <code>
     * getByteSequenceContent(setByteSequenceContent(getContent().getBytes()))
     * is equal to getByteSequenceContent()
     * </code>
     * @param content The new value for the slot.

     */
    public void setContent(String content) {
        if (content != null)
            this.content = new StringBuffer(content);
        else
            this.content = null;
    }

    public void setContent(Object content)
    {
        if (content !=null)
            this.content = (StringBuffer) content;
        else
            this.content = null;
    }

    /**
     Writes the <code>:reply-with</code> slot. <em><b>Warning:</b> no
     checks are made to validate the slot value.</em>
     @param reply The new value for the slot.
     */
    public void setReplyWith(String reply) {
        if (reply != null)
            reply_with = new StringBuffer(reply);
        else
            reply_with = null;
    }

    /**
     Writes the <code>:in-reply-to</code> slot. <em><b>Warning:</b> no
     checks are made to validate the slot value.</em>

     */
    public void setInReplyTo(String reply) {
        if (reply != null)
            in_reply_to = new StringBuffer(reply);
        else
            in_reply_to = null;
    }

    /**
     Writes the <code>:encoding</code> slot. <em><b>Warning:</b> no
     checks are made to validate the slot value.</em>
     @param str The new value for the slot.

     */
    public void setEncoding(String str) {
        if (str != null)
            encoding = new StringBuffer(str);
        else
            encoding = null;
    }

    /**
     Writes the <code>:language</code> slot. <em><b>Warning:</b> no
     checks are made to validate the slot value.</em>
     @param str The new value for the slot.

     */
    public void setLanguage(String str) {
        if (str != null)
            language = new StringBuffer(str);
        else
            language = null;
    }

    /**
     Writes the <code>:ontology</code> slot. <em><b>Warning:</b> no
     checks are made to validate the slot value.</em>
     @param str The new value for the slot.

     */
    public void setOntology(String str) {
        if (str != null)
            ontology = new StringBuffer(str);
        else
            ontology = null;
    }


    /**
     Writes the <code>:protocol</code> slot. <em><b>Warning:</b> no
     checks are made to validate the slot value.</em>
     @param str The new value for the slot.

     */
    public void setProtocol( String str ) {
        if (str != null)
            protocol = new StringBuffer(str);
        else
            protocol = null;
    }

    /**
     Writes the <code>:conversation-id</code> slot. <em><b>Warning:</b> no
     checks are made to validate the slot value.</em>
     @param str The new value for the slot.

     */
    public void setConversationId( String str ) {
        if (str != null)
            conversation_id = new StringBuffer(str);
        else
            conversation_id = null;
    }



    /**
     Reads <code>:receiver</code> slot.
     @return An <code>Iterator</code> containing the Agent IDs of the
     receiver agents for this message.
     */
    public Iterator getAllReceiver() {
        return dests.iterator();
    }

    /**
     Reads <code>:reply_to</code> slot.
     @return An <code>Iterator</code> containing the Agent IDs of the
     reply_to agents for this message.
     */
    public Iterator getAllReplyTo() {
        return reply_to.iterator();
    }


    public AID getSender() {
        if(source != null)
            return (AID)source;
        else
            return null;
    }

    public AID getReceiver() {
        if(receiver != null)
            return (AID)receiver;
        else
            return null;
    }
    /**
     Returns the string corresponding to the integer for the performative
     @return the string corresponding to the integer for the performative;
     "NOT-UNDERSTOOD" if the integer is out of range.
     */
    public static String getPerformative(int perf){
        try {
            return new String((String)performatives.get(perf));
        } catch (Exception e) {
            return new String((String)performatives.get(NOT_UNDERSTOOD));
        }
    }

    /**
     Returns the integer corresponding to the performative
     @returns the integer corresponding to the performative; -1 otherwise
     */
    public static int getInteger(String perf)
    {
        return performatives.indexOf(perf.toUpperCase());
    }

    /**
     * return the integer representing the performative of this object
     * @return an integer representing the performative of this object
     */
    public int getPerformative() {
        return performative;
    }

    /**
     * Reads <code>:content</code> slot. <p>
     * <p>Notice that, in general, setting a String content and getting
     * back a byte sequence content - or viceversa - does not return
     * the same value, i.e. the following relation does not hold
     * <code>
     * getByteSequenceContent(setByteSequenceContent(getContent().getBytes()))
     * is equal to getByteSequenceContent()
     * </code>
     * @return The value of <code>:content</code> slot.

     * @see java.io.ObjectInputStream
     */
    public String getContent() {
        if(content != null)
            return new String(content);
        else
            return null;
    }



    /**
     Reads <code>:reply-with</code> slot.
     @return The value of <code>:reply-with</code>slot.

     */
    public String getReplyWith() {
        if(reply_with != null)
            return new String(reply_with);
        else return null;
    }

    /**
     Reads <code>:reply-to</code> slot.
     @return The value of <code>:reply-to</code>slot.

     */
    public String getInReplyTo() {
        if(in_reply_to != null)
            return new String(in_reply_to);
        else return null;
    }




    public String getEncoding() {
        if(encoding != null)
            return new String(encoding);
        else
            return null;
    }


    public String getLanguage() {
        if(language != null)
            return new String(language);
        else
            return null;
    }


    public String getOntology() {
        if(ontology != null)
            return new String(ontology);
        else
            return null;
    }


    public Date getReplyByDate() {
        if(reply_byInMillisec != 0)
            return new Date(reply_byInMillisec);
        else
            return null;
    }


    public String getProtocol() {
        if(protocol != null)
            return new String(protocol);
        else
            return null;
    }


    public String getConversationId() {
        if(conversation_id != null)
            return new String(conversation_id);
        else
            return null;
    }




    /**
     * Resets all the message slots.
     */
    public void reset() {
        source = null;
        dests.clear();
        reply_to.clear();
        performative = NOT_UNDERSTOOD;
        content = null;
        reply_with = null;
        in_reply_to = null;
        encoding = null;
        language = null;
        ontology = null;
        reply_byInMillisec = 0;
        protocol = null;
        conversation_id = null;

    }

    /**
     * create a new ACLMessage that is a reply to this message.
     * In particular, it sets the following parameters of the new message:
     * receiver, language, ontology, protocol, conversation-id,
     * in-reply-to, reply-with.
     * The programmer needs to set the communicative-act and the content.
     * Of course, if he wishes to do that, he can reset any of the fields.
     * @return the ACLMessage to send as a reply
     */
	 /* public ACLMessage createReply() {
	    ACLMessage m = (ACLMessage)clone();
	    m.clearAllReceiver();
	    Iterator it = reply_to.iterator();
	    while (it.hasNext())
	      m.addReceiver((AID)it.next());
	    if (reply_to.isEmpty())
	      m.addReceiver(getSender());
	    m.clearAllReplyTo();
	    m.setLanguage(getLanguage());
	    m.setOntology(getOntology());
	    m.setProtocol(getProtocol());
	    m.setSender(null);
	    m.setInReplyTo(getReplyWith());
	    if (source != null)
	      m.setReplyWith(source.getName() + java.lang.System.currentTimeMillis());
	    else
	      m.setReplyWith("X"+java.lang.System.currentTimeMillis());
	    m.setConversationId(getConversationId());
	    m.setReplyByDate(null);
	    m.setContent(null);
	    m.setEncoding(null);


	    return m;
	  }*/

    /**
     @return An Iterator over all the intended receivers of this
     message taking into account the Envelope ":intended-receiver"
     first, the Envelope ":to" second and the message ":receiver"
     last.
     */
	  /*public Iterator getAllIntendedReceiver() {
			Iterator it = null;
			//Envelope env = getEnvelope();
			if (env != null) {
				it = env.getAllIntendedReceiver();
				if (!it.hasNext()) {
					// The ":intended-receiver" field is empty --> try with the ":to" field
					it = env.getAllTo();
				}
			}
			if (it == null || !it.hasNext()) {
				// Both the ":intended-receiver" and the ":to" fields are empty -->
				// Use the ACLMessage receivers
				it = getAllReceiver();
			}
			return it;
	  }
	*/
}


