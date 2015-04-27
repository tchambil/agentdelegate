package dcc.com.agent.siebog;

/**
 * Created by teo on 27/04/15.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

/**
 * Represents a FIPA ACL message. Refer to <a
 * href="http://www.fipa.org/specs/fipa00061/SC00061G.pdf">FIPA ACL Message Structure
 * Specification</a> for more details.
 *
 * @author <a href="tntvteod@neobee.net">Teodor-Najdan Trifunov</a>
 * @author <a href="mitrovic.dejan@gmail.com">Dejan Mitrovic</a>
 */

public class ACLMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String USERARG_PREFIX = "X-";

    // Denotes the type of the communicative act of the ACL message.
   public Performative performative;
	/* Participants in Communication */

    // Denotes the identity of the sender of the message.
    public AID sender;
    // Denotes the identity of the intended recipients of the message.

    public List<AID> receivers;
    // This parameter indicates that subsequent messages in this conversation
    // thread are to be directed to the agent named in the reply-to parameter,
    // instead of to the agent named in the sender parameter.

    public AID replyTo;

	/* Description of Content */

    // Denotes the content of the message; equivalently denotes the
    // object of the action.

    public String content;
    public Serializable contentObj;
    public Map<String, Serializable> userArgs;
    // Denotes the language in which the content parameter is expressed.

    public String language;
    // Denotes the specific encoding of the content language expression.

    public String encoding;
    // Denotes the ontology(s) used to give a meaning to the symbols in
    // the content expression.

    public String ontology;

	/* Control of Conversation */

    // Denotes the interaction protocol that the sending agent is
    // employing with this ACL message.

    public String protocol;
    // Introduces an expression (a conversation identifier) which is used
    // to identify the ongoing sequence of communicative acts that
    // together form a conversation.

    public String conversationId;
    // Introduces an expression that will be used by the responding
    // agent to identify this message.

    public String replyWith;
    // Denotes an expression that references an earlier action to which
    // this message is a reply.

    public String inReplyTo;
    // Denotes a time and/or date expression which indicates the latest
    // time by which the sending agent would like to receive a reply.

    public long replyBy;

    public ACLMessage() {
        this(Performative.NOT_UNDERSTOOD);
    }

    public ACLMessage(Performative performative) {
        this.performative = performative;
        receivers = new ArrayList<>();
        userArgs = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public ACLMessage(String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        performative = Performative.valueOf(obj.getString("performative").toUpperCase());

        String str = obj.optString("sender");
        if (str != null && !str.isEmpty())
            sender = new AID(str);

        receivers = new ArrayList<>();
        JSONArray arr = obj.optJSONArray("receivers");
        if (arr != null && arr.length() > 0)
            for (int i = 0; i < arr.length(); i++)
                receivers.add(new AID(arr.getString(i)));

        str = obj.optString("replyTo");
        if (str != null && !str.isEmpty())
            replyTo = new AID(str);
        content = obj.optString("content");
        language = obj.optString("language");
        encoding = obj.optString("encoding");
        ontology = obj.optString("ontology");
        protocol = obj.optString("protocol");
        conversationId = obj.optString("conversationId");
        replyWith = obj.optString("replyWith");
        inReplyTo = obj.optString("inReplyTo");
        replyBy = obj.optLong("replyBy");
        // user args
        userArgs = new HashMap<>();
        Iterator<String> i = obj.keys();
        while (i.hasNext()) {
            String key = i.next();
            if (key.startsWith(USERARG_PREFIX)) {
                String subKey = key.substring(USERARG_PREFIX.length());
                Serializable value = (Serializable) obj.get(key);
                userArgs.put(subKey, value);
            }
        }
    }

    public boolean canReplyTo() {
        return sender != null || replyTo != null;
    }

    public ACLMessage makeReply(Performative performative) {
        if (!canReplyTo())
            throw new IllegalArgumentException("There's no-one to receive the reply.");
        ACLMessage reply = new ACLMessage(performative);
        // receiver
        reply.receivers.add(replyTo != null ? replyTo : sender);
        // description of content
        reply.language = language;
        reply.ontology = ontology;
        reply.encoding = encoding;
        // control of conversation
        reply.protocol = protocol;
        reply.conversationId = conversationId;
        reply.inReplyTo = replyWith;
        return reply;
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();
        try {
            // TODO Right now, JSONObject will contain only strings, which will need to be parse
            // manually later on. Implement a better JSON builder.
            obj.put("performative", performative);
            obj.put("sender", sender);
            JSONArray arr = new JSONArray(receivers);
            obj.put("receivers", arr);
            obj.put("replyTo", replyTo);
            obj.put("content", content);
            obj.put("language", language);
            obj.put("encoding", encoding);
            obj.put("ontology", ontology);
            obj.put("protocol", protocol);
            obj.put("conversationId", conversationId);
            obj.put("replyWith", replyWith);
            obj.put("inReplyTo", inReplyTo);
            obj.put("replyBy", replyBy);
            for (Entry<String, Serializable> e : userArgs.entrySet())
                obj.put(USERARG_PREFIX + e.getKey(), e.getValue());
        } catch (JSONException ex) {
        }
        return obj.toString();
    }
}
