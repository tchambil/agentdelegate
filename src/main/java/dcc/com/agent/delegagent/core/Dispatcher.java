package dcc.com.agent.delegagent.core;

/**
 * Created by teo on 30/04/15.
 */



import dcc.com.agent.delegagent.core.Agents.Agent;
import java.util.Hashtable;
public class Dispatcher {
    private Hashtable agents;
    private boolean Debug,Verbose;

    /**
     * Constructor de la clase Dispatcher
     */
    public Dispatcher(boolean Debug,boolean Verbose)
    {
        agents = new Hashtable();
        this.Debug=Debug;
        this.Verbose=Verbose;
    }

    //metodo para introducir el agente en la tabla
    public synchronized void put (AID aid, Agent a)
    {
        if (Debug) System.out.println("DENTRO DEL METODO PUT DE LA CLASE DISPATCHER");
        agents.put(aid,a);
    }

    //metodo para extraer informacion sobre un agente de la tabla
    public synchronized Agent get(AID aid)
    {
        if (Debug) System.out.println("DENTRO DEL METODO GET DE LA CLASE DISPATCHER");
        if (agents.containsKey(aid))
        {
            return (Agent) agents.get(aid);
        }
        else return null;
    }

    //metodo para borrar un agente de la tabla
    public synchronized void remove(AID aid)
    {
        if (Debug) System.out.println("DENTRO DEL METODO REMOVE DE LA CLASE DISPATCHER");
        agents.remove(aid);
    }

    //metodo para limpiar una tabla
    public synchronized void clear()
    {
        if (Debug) System.out.println("DENTRO DEL METODO CLEAR DE LA CLASE DISPATCHER");
        agents.clear();
    }

    //metodo que devuelve un booleano para indicar si la tabla esta vacia
    public synchronized boolean isEmpty()
    {
        if (Debug) System.out.println("DENTRO DEL METODO ISEMPTY DE LA CLASE DISPATCHER");
        return agents.isEmpty();
    }

    public synchronized void PrintAll() {
        System.out.println("Tabla Hash de los agentes en el Dispatcher: ");
        System.out.println(agents.toString());
    }

}

