package dcc.com.agent.delegagent.core;

/**
 * Created by teo on 30/04/15.
 */
public class first {
    public static void main(String[] args)
    {

        Platform platform=new Platform(true, true, true);
        AID AIDAgent = new AID("first",platform.getidPlatform());
        AMS ams =new AMS(true,true);
        System.out.println(platform.getidPlatform());

        System.out.println(platform.getAMS());
        ams.Register("platafor",AIDAgent);
        ams.PrintAll();
        System.out.println(AIDAgent.getLocalName());


    }
}
