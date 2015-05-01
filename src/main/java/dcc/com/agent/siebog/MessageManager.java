package dcc.com.agent.siebog;

/**
 * Created by teo on 27/04/15.
 */
public interface MessageManager {
   void post(ACLMessage message);

    void post(ACLMessage message, long delayMillisec);

    String ping();
}
