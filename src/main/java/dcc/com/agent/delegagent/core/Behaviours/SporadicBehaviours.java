package dcc.com.agent.delegagent.core.Behaviours;

/**
 * Created by teo on 30/04/15.
 */
        import javax.realtime.*;

public class SporadicBehaviours extends RTBehaviours {

    protected RelativeTime minInterarrival;

    public SporadicBehaviours(){
        super();
    }

    public SporadicBehaviours(RelativeTime minInterarrival,RelativeTime cost, RelativeTime deadline,int priority)
    {
        super(cost,deadline,priority);
        SetminInterarrival(minInterarrival);
    }

    public void SetminInterarrival(RelativeTime minInterarrival){
        this.minInterarrival=minInterarrival;
    }

    public RelativeTime GetminInterarrival(){
        return this.minInterarrival;
    }
}

