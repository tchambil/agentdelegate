package dcc.com.agent.delegagent.core.Behaviours;

/**
 * Created by teo on 30/04/15.
 */


        import javax.realtime.*;


public class RTBehaviours extends Behaviours{

    protected RelativeTime cost;
    protected RelativeTime deadline;
    protected int priority;
    protected boolean force;

    /**
     * Constructor de la Clase RTBehaviours
     *
     */
    public RTBehaviours(){
        super();
    }

    /**
     * Constructor de la Clase RTBehaviours con parametros
     *
     */
    public RTBehaviours(RelativeTime cost, RelativeTime deadline, int priority)
    {
        SetCost(cost);
        SetDeadline(deadline);
        SetPriority(priority);
        this.force = false;
    }

    /**
     * Define la tarea que debe de realizar el comportamiento
     *
     */
    public void Task() {
    }

    /**
     * Manejador del evento Overrun
     *
     */
    public void HandlerOverrun() {
    }

    /**
     * Manejador del evento Deadline cumplido
     *
     */
    public void HandlerDeadline() {
    }

    /**
     * Metodo para introducir el coste de la tarea
     *
     */
    public void SetCost(RelativeTime cost) {
        this.cost=cost;
    }

    /**
     * Metodo para introducir el deadline de la tarea
     *
     */

    public void SetDeadline(RelativeTime deadline) {
        this.deadline=deadline;
    }

    /**
     * Metodo para introducir la prioridad de la tarea
     *
     */
    public void SetPriority(int priority) {
        this.priority=priority;
    }
    /**
     * Metodo para obtener el coste asociado a la tarea
     *@return RelativeTime cost
     */
    public RelativeTime GetCost() {
        return this.cost;
    }

    /**
     * Metodo para obtener el deadline de la tarea
     *@return RelativeTime deadline
     */
    public RelativeTime GetDeadline() {
        return this.deadline;
    }

    /**
     * Metodo para obtener la prioridad de la tarea
     * @return int priority
     */
    public int GetPriority() {
        return this.priority;
    }

    /**
     * Metodo para obtener si se debe forzar o no la integración
     * del comportamiento en la planificación
     * @return boolena force
     */
    public boolean GetFactibility() {
        return this.force;
    }

    /**
     * Metodo para indicar si se debe forzar o no la integración
     * del comportamiento en la planificación
     */
    public void ForceFactibility() {
        this.force = true;
    }
}

