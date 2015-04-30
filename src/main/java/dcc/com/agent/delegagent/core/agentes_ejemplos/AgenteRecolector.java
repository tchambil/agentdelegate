
//import java.awt.Point;

package dcc.com.agent.delegagent.core.agentes_ejemplos;

import javax.realtime.RelativeTime;

import Agents.RTAgent;
import Behaviours.PeriodicBehaviours;
import Communication.ACLMessage;
import Core.AID;
import Core.Platform;
import EjemploVRS.Pizarra;

/*
 * Creado el 06-jun-2005
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */

/**
 * @author root
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class AgenteRecolector extends RTAgent {

	Pizarra pizarra;
	
	public AgenteRecolector(Platform platform, String name,Pizarra Tabla) 
    {
		
        super(platform,name);  
        if (platform.Debug) System.out.println("INICIALIZANDO AGENTE RECOLECTOR....");
        pizarra = Tabla;
       
    }
	
    class Recolectar extends PeriodicBehaviours 
    {   
       public Recolectar(RelativeTime start,RelativeTime cost, RelativeTime deadline,int priority, RelativeTime period)
       {          
         super(start,cost,deadline,priority,period);
       } 
       
       public void Task()
       {          	
       	long tiempo,tiempo_deadline,tiempo_coste;
       	RelativeTime deadline;  
       	boolean quedan_mensajes= true;
        ACLMessage msgreceive;
       
        if (platform.Verbose) System.out.println("Ejecutando tarea RECOLECTAR del agente RECOLECTOR .......");
        
       	tiempo=tiempo_coste=System.currentTimeMillis();
      
       	if (platform.Kiwi)
       	{
       		platform.kiwi.poner(tiempo,"START",3);
       		platform.kiwi.poner(tiempo,"READY-B",3); 
       		platform.kiwi.poner(tiempo,"EXEC-B",3); 
       		deadline = GetDeadline();
       		tiempo_deadline = deadline.getMilliseconds();
       		tiempo = tiempo + tiempo_deadline;
       		platform.kiwi.poner(tiempo,"DEADLINE",3);
       	}
       	
      // 	msgreceive = Receive();
       
  /*
     	while (quedan_mensajes)
   	 	{
   	 		msgreceive = Receive();	
   	 		if (msgreceive == null) 
   	 		{
   	 			quedan_mensajes = false;	
   	 			if (platform.Verbose) System.out.println("No quedan mas mensajes que tratar");
   	 		}
   	 		else 
   	 		{   	 			
   	 			if (msgreceive.getContent().equals("Hecho"))
   	 			{   
   	 				if (platform.Verbose) 
   	 				{
   	 					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
   	 					System.out.println("Se ha recogido el Objeto!!!!");
   	 					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
   	 				}	
   	 				pizarra.nextDestino();
   	 				if (pizarra.getDestino() !=null)
   	 				{
   	 					if (platform.Verbose) System.out.println("Siguiente Destino: " + pizarra.getDestino().getX() + "  " + pizarra.getDestino().getY());
   	 				}
   	 				else
   	 				{
   	 					System.out.println("NO HAY MAS OBJETOS POR RECOGER");
   	 				} 	 				
   	 			}
   	 		}
   	 	}
      */
    	if (pizarra.getDestino() != null)
       	{
       		if ((pizarra.getPuntoActual().getX() > pizarra.getDestino().getX()-100.0) && (pizarra.getPuntoActual().getX() < pizarra.getDestino().getX()+100.0))
       		{ 
       			if ((pizarra.getPuntoActual().getY() > pizarra.getDestino().getY() - 100.0) && (pizarra.getPuntoActual().getY() < pizarra.getDestino().getY() + 100.0))
       			{     	 
       				ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
       				msg.setSender(aidAgent);
       				//buscamos el AID del agente2
       				AID receiver = platform.getAMS().Search("AgenteControlador"); 
       				msg.setReceiver(receiver); 	     	
       				msg.setContent("Recolectar;10;");    		      	  
       				Send(msg);
       				
         	     
       		/*		if (msgreceive !=null)
       				{			
       					if (msgreceive.getContent().equals("Hecho"))
       					{       						
       						pizarra.nextDestino();
       					}
       				}
       		*/	
       			} 	
       		}      	
       	}
		
        
       	if (platform.Kiwi)
       	{
       		tiempo = System.currentTimeMillis();
       		platform.kiwi.poner(tiempo,"EXEC-E",3);
       		platform.kiwi.poner(tiempo,"READY-E",3); 
       		platform.kiwi.poner(tiempo,"STOP",3); 
         }
        
        tiempo_coste = System.currentTimeMillis()-tiempo_coste;
    	System.out.println("coste de la tarea colectar: " + tiempo_coste);
    }
     
    private void Recolectando() 
    { 	
       		System.out.println("RECOLECTADO OBJETO!!!!!!!!!!!!!!!!!!!!!!!");
    }
	 
    } //fin comportamiento
   
    public void run() 
    {    
    	Recolectar behaviour1 = new Recolectar( new RelativeTime(0,0),
    											new RelativeTime(20,0),
												new RelativeTime(200,0),
		 										27,
												new RelativeTime(200,0));
            
    	this.AddRTBehaviour(behaviour1);
    }
} //fin agente

