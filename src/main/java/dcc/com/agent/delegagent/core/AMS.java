package dcc.com.agent.delegagent.core;

/**
 * Created by teo on 30/04/15.
 */

import java.util.*;

public class AMS  {
    // agent list of the platform.
    private Hashtable agentlist;
    private boolean Debug;
    private boolean Verbose;

    /**
     * Constructor for objects of class AMS
     */
    public AMS(boolean Debug,boolean Verbose)
    {
        // initialise instance variables
        agentlist = new Hashtable();
        this.Debug=Debug;
        this.Verbose = Verbose;
    }

    /**
     * method for register.
     *
     * @param  agent   the AID agent.
     * @return     void
     */
    public void Register(String name, AID agent)
    {
        if (Debug) System.out.println("DENTRO DEL METODO REGISTER DE LA CLASE AMS");
        if (!agentlist.containsKey(name)) {
            agentlist.put(name,agent);
            if (Verbose) System.out.println("El agente " + agent.toString() + " se incluyo en la lista del AMS.");
        }
        else
        {
            if (Verbose) System.out.println("El agente " + agent.toString() + " ya estaba en la lista del AMS.");
        }
    }

    public synchronized void Delete(AID agent)
    {
        if (Debug) System.out.println("DENTRO DEL METODO DELETE DE LA CLASE AMS");
        if (agentlist.contains(agent)) {
            agentlist.remove(agent);
            if (Verbose) System.out.println("El agente " + agent.toString() + " fue eliminado de la lista de agentes.");
        }
        else
        {
            if (Verbose) System.out.println("El agente " + agent.toString() + " no esta en la lista.");
        }
    }

    public synchronized AID Search(String name)
    {
        if (Debug) System.out.println("DENTRO DEL METODO SEARCH DE LA CLASE AMS");
        if (agentlist.containsKey(name)){

            return (AID) agentlist.get(name);
        }
        else
            return null;
    }
    public synchronized void PrintAll() {
        System.out.println("Tabla Hash de los agentes en el AMS: ");
        System.out.println(agentlist.toString());
    }


}

