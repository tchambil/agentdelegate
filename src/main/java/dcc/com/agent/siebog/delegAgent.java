package dcc.com.agent.siebog;

/**
 * Created by teo on 27/04/15.
 */
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Base class for all agents.
 *
 * @author <a href="mitrovic.dejan@gmail.com">Dejan Mitrovic</a>
 * @author <a href="tntvteod@neobee.net">Teodor-Najdan Trifunov</a>
 */
public abstract class delegAgent implements Agent {
    private static final long serialVersionUID = 1L;
    // the access timeout is needed only when the system is under a heavy load.
    // under normal circumstances, all methods should return as quickly as possible
    public static final long ACCESS_TIMEOUT = 5;
    protected final Logger logger = Logger.getLogger(getClass().getName());
    protected AID myAid;
    private AgentManager agm;
    private MessageManager msm;

    // TODO : Restore support for heartbeats.
    // private transient long hbHandle;

    //@Override
    public void init(AID aid, AgentInitArgs args) {
        myAid = aid;
        onInit(args);
    }

    protected void onInit(AgentInitArgs args) {
    }

    @Override
    public void handleMessage(ACLMessage msg) {
        // TODO : check if the access to onMessage is protected
        // TODO : Restore support for heartbeats.
        if (msg instanceof HeartbeatMessage) {
            boolean repeat = onHeartbeat(msg.content);
            if (repeat)
                ; // executor().signalHeartbeat(hbHandle);
            else
                ; // executor().cancelHeartbeat(hbHandle);
        } else {
            if (filter(msg)) {
                try {
                    onMessage(msg);
                } catch (Exception ex) {
                    logger.log(Level.WARNING, "Error while delivering message " + msg, ex);
                }
            }
        }
    }

    protected abstract void onMessage(ACLMessage msg);

    protected boolean onHeartbeat(String content) {
        return false;
    }

    protected void onTerminate() {
    }

    @Override

    public void stop() {
        try {
            onTerminate();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error in onTerminate.", ex);
        }
    }

    protected ACLMessage receiveNoWait() {
        return null; // queue.poll(); // TODO : Implement receiveNoWait.
    }

    protected ACLMessage receiveWait(long timeout) {
        if (timeout < 0)
            throw new IllegalArgumentException("The timeout value cannot be negative.");
        ACLMessage msg = null;
        // TODO : Implement receiveWait.
        // try {
        // if (timeout == 0)
        // timeout = Long.MAX_VALUE;
        // msg = queue.poll(timeout, TimeUnit.MILLISECONDS);
        // } catch (InterruptedException ex) {
        // }
        return msg;
    }

    @Override
    public int hashCode() {
        return myAid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return myAid.equals(((delegAgent) obj).myAid);
    }

    /**
     * Before being finally delivered to the agent, the message will be passed to this filtering
     * function.
     *
     * @param msg
     * @return If false, the message will be discarded.
     */
    protected boolean filter(ACLMessage msg) {
        return true;
    }

    protected void registerHeartbeat(String content) {
        // TODO : Restore support for heartbeats.
        // hbHandle = executor().registerHeartbeat(myAid, 500, content);
    }

    protected void registerHeartbeat() {
        registerHeartbeat("");
    }

    public AID getAid() {
        return myAid;
    }

    protected String getNodeName() {
        return System.getProperty("jboss.node.name");
    }

    @Override
    public String ping() {
        return getNodeName();
    }

    protected AgentManager agm() {
        if (agm == null)
            agm = ObjectFactory.getAgentManager();
        return agm;
    }

    protected MessageManager msm() {
        if (msm == null)
            msm = ObjectFactory.getMessageManager();
        return msm;
    }
}
