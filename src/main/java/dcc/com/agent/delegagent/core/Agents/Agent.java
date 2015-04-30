package dcc.com.agent.delegagent.core.Agents;

/**
 * Created by teo on 30/04/15.
 */


        import dcc.com.agent.delegagent.core.Behaviours.*;
        import dcc.com.agent.delegagent.core.Communication.ACLMessage;
        import dcc.com.agent.delegagent.core.Communication.MessageQueue;
        import dcc.com.agent.delegagent.core.AID;
        import dcc.com.agent.delegagent.core.AMS;
        import dcc.com.agent.delegagent.core.Dispatcher;
        import dcc.com.agent.delegagent.core.Platform;

public class Agent extends Thread {

    protected Behaviours currentBehaviour;
    protected ACLMessage currentMessage;
    protected MessageQueue ListMessage;
    protected AID aidAgent;
    protected Dispatcher dispatcher;
    protected AMS ams;
    protected Platform platform;
    protected int state_agent;


    /** This method adds a new non real time Behaviour to the agent.
     *
     */
    /** Constructor
     */

    public Agent()
    {
    }

    public  Agent(Platform platform,String name)
    {
        this.platform = platform;
        dispatcher =platform.getDispatcher();
        ams = platform.getAMS();
        ListMessage = new MessageQueue();
        aidAgent = CreateAID(platform,name);
        RegisterInDispatcher(aidAgent,this);
        RegisterInAMS(name,aidAgent);
        state_agent = 1;
    }


    public void AddBehaviour(Behaviours b) {
    }
    /** This method removes a given NRTBehaviour from the agent.
     */

    public void RemoveBehaviour(Behaviours b) {
    }

    /** Receves an ACL message from the agent message queue.
     *
     */

    public ACLMessage Receive() {

        if (this.ListMessage.isEmpty())
        {
            if (platform.Verbose) System.out.println("No hay ningun mensaje en la cola de mensajes");
            return null;
        }
        else
        {
            //if (platform.Debug) System.out.println("Hay algun mensaje y se invoca al metodo get de la clase MessageQueue");
            ACLMessage message = this.ListMessage.get();
            if (platform.Verbose) System.out.println("Mensaje recibido por el agente "+ this.aidAgent.getName() + " enviado por el agente " + message.getSender().getName());
            return message;
        }
    }
    /** Send an ACL message to another agent.
     */

    public ACLMessage Receive(AID sender) {
        return this.ListMessage.get(sender);
    }

    public void Send(ACLMessage message) {
        AID sender;
        Agent agent;
        AID receiver = (AID) message.getReceiver();

        agent = (Agent) dispatcher.get(receiver);

        if (agent != null) {
            agent.ListMessage.add(message);
            if (platform.Verbose) System.out.println("Mensaje enviado del agente "+ this.aidAgent.getName() + " al agente" + agent.aidAgent.getName());
        }
        else {
            if (platform.Verbose) System.out.println("No existe el agente receptor de este mensaje");
        }

    }

    protected AID CreateAID(Platform platform,String name)
    {
        AID AIDAgent = new AID(name,platform.getidPlatform());
        AIDAgent.addAddresses(platform.getidPlatform());
        return AIDAgent;
        //añadir resolvers, o mas direcciones (mas adelante)
    }

    /** Metodo para añadir en la tabla de la clase Dispatcher
     * el agente
     */
    protected void  RegisterInDispatcher(AID aidAgent,Agent agent)
    {
        dispatcher.put(aidAgent,agent);
        //System.out.println("Agente " +aidAgent.getName()+ " registrado en el Dispatcher.");
    }


    /** Metodo para añadir el agente en el AMS
     */
    protected void RegisterInAMS(String name,AID aidAgent)
    {
        ams.Register(name, aidAgent);
        // System.out.println("Agente " + name + " registrado en el AMS.");
    }

    /** This method is the main body of every agent.
     */


    public void run() {
    }
    /** This protecte method is an empty placeholder for application specific startup code.
     */

    protected void setup() {
    }

    protected void takedown(){
    }
}

