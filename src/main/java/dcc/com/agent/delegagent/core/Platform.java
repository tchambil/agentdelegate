package dcc.com.agent.delegagent.core;

/**
 * Created by teo on 30/04/15.
 */
import dcc.com.agent.delegagent.core.kiwi.Kiwi;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Platform {

    // instance variables - replace th e example below with your own
    private Dispatcher dispatcher;
    private String idPlatform;
    public Kiwi kiwi;
    private AMS ams;
    public boolean Debug,Kiwi,Verbose;

    //Deberiamos incluir aqui el AMS

    /**
     * Constructor for objects of class Platform
     */
    public Platform(boolean Debug, boolean Kiwi,boolean Verbose)
    {
        this.Debug = Debug;
        this.Kiwi =Kiwi;
        this.Verbose = Verbose;
        // initialise instance variables
        System.out.println("cargando AMS");
        ams = new AMS(Debug,Verbose);
        System.out.println("cargando Dispatcher");
        dispatcher= new Dispatcher(Debug,Verbose);
        System.out.println("cargando Kiwi");

        if (Kiwi)
        {
            kiwi = new Kiwi();
            //CAB. KIWI
            kiwi.poner_cab("DECIMAL_DIGITS 3 \n");
            kiwi.poner_cab("DURATION 50 \n");
            kiwi.poner_cab("LINE_NAME 0 Comportamiento_Pizarra  \n");
            kiwi.poner_cab("LINE_NAME 1 Comportamiento_Bind  \n");
            kiwi.poner_cab("LINE_NAME 2 Comportamiento_Planificador \n");
            kiwi.poner_cab("LINE_NAME 3 Comportamiento_Recolector \n");
            kiwi.poner_cab("LINE_NAME 4 Comportamiento_Sensorizador \n");
            kiwi.poner_cab("PALETTE Rainbow \n");
            kiwi.poner_cab("ZOOM_X 6 \n");
            kiwi.poner_cab("ZOOM_Y 16 \n");
            kiwi.poner_cab("COLOR EXEC-E 0 orchid4 \n");
            //FIN CAB. KIWI
            kiwi.set_inicio_plan(System.currentTimeMillis());
        }

        try
        {
            InetAddress addr = InetAddress.getLocalHost();
            // Get IP Address //por si lo necesitamos, de momento no.
            byte[] ipAddr = addr.getAddress();

            // Get hostname
            this.idPlatform = addr.getHostName();
        } catch (UnknownHostException e)
        {
            System.out.println("no se ha obtenido la direccion de la maquina");
        }

    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public AMS getAMS() {
        return ams;
    }

    public String getidPlatform() {
        return idPlatform;
    }

}

