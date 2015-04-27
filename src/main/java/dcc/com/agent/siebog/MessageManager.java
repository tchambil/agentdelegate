package dcc.com.agent.siebog;

/**
 * Created by teo on 27/04/15.
 */
public interface MessageManager {
    public static final String REPLY_WITH_TEST = "siebog-test";

    void post(ACLMessage message);

    void post(ACLMessage message, long delayMillisec);

    String ping();
}
