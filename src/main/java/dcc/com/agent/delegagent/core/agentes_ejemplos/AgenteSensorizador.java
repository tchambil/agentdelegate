/*
 * Creado el 30-sep-2005
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package dcc.com.agent.delegagent.core.agentes_ejemplos;

//import javax.realtime.RelativeTime;

import dcc.com.agent.delegagent.core.Agents.RTAgent;
import dcc.com.agent.delegagent.core.Behaviours.PeriodicBehaviours;
import dcc.com.agent.delegagent.core.Communication.ACLMessage;
import dcc.com.agent.delegagent.core.AID;
import dcc.com.agent.delegagent.core.Platform;
import dcc.com.agent.delegagent.core.EjemploVRS.Pizarra;


/**
 * @author Marti Navarro
 *
 * TODO Agente encargado de la localización de obstaculos
 */

public class AgenteSensorizador extends RTAgent
{
	Pizarra pizarra;
	
	public AgenteSensorizador(Platform platform, String name,Pizarra Tabla) 
    {
		
        super(platform,name);
        if (platform.Debug) System.out.println("INICIALIZANDO AGENTE SENSORIZADOR....");
        pizarra = Tabla;
          
    }


	 class Sensorizar extends PeriodicBehaviours 
	    {   
	       public Sensorizar(RelativeTime start,RelativeTime cost, RelativeTime deadline,int priority, RelativeTime period)
	       {          
	         super(start,cost,deadline,priority,period);
	   
	       }   
	       
	       public void Task()
	       { 
	       	long tiempo_coste,tiempo,tiempo_deadline;
	       	RelativeTime deadline;  
	       	int sensores[] = new int[8];
	      
	        if (platform.Verbose) System.out.println("Ejecutando tarea SENSORIZAR del agente SENSORIZADOR .......");
	       	
	       	//bind dependiente de la distancia.
	       	//mirar los sensores de enfrente los tres primeros de momento.
	       	//dependiendo donde este el objetivo girara hacia la izquierda o hacia la derecha
	       
	       	tiempo_coste= tiempo = System.currentTimeMillis();
	       	
	       	if (platform.Kiwi)
	       	{
	        platform.kiwi.poner(tiempo,"START",4);
	        platform.kiwi.poner(tiempo,"READY-B",4); 
	        platform.kiwi.poner(tiempo,"EXEC-B",4); 
	        deadline = GetDeadline();
	        tiempo_deadline = deadline.getMilliseconds();
	        tiempo = tiempo + tiempo_deadline;
	        platform.kiwi.poner(tiempo,"DEADLINE",4);
	       	}
	       	
	       	
	       	sensores = pizarra.getSensores();
	       	StringBuffer accion= new StringBuffer();

	       	
	       	if ((sensores[0]!=0) || (sensores[1]!=0) ||(sensores[2]!=0) ||(sensores[3]!=0) ||(sensores[4]!=0) ||(sensores[5]!=0) ||(sensores[6]!=0) || (sensores[7]!=0))
	       	{       		
	       		if (((sensores[1]>0) && (sensores[1]<500)) || ((sensores[7]>0) && (sensores[7]<500))){
	       			accion.append("Derecha");	
	       			accion.append(";8;");
	       			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		        	msg.setContent(accion.toString());
		        	msg.setSender(aidAgent);
		   	   
		        	//buscamos el AID del agente2   	    
		        	AID receiver = platform.getAMS().Search("AgenteControlador"); 
		        	msg.setReceiver(receiver); 
		        	Send(msg);  
	       		}
	       		else if ((sensores[6]>0) && (sensores[6]<500))	       		{
	       			accion.append("Avanzar");
	       			accion.append(";8;");
	       			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		        	msg.setContent(accion.toString());
		        	msg.setSender(aidAgent);
		   	   
		        	//buscamos el AID del agente2   	    
		        	AID receiver = platform.getAMS().Search("AgenteControlador"); 
		        	msg.setReceiver(receiver); 	
		        	Send(msg);  
	       		}
	       		
	           	if (platform.Kiwi)
	           	{
	           		tiempo = System.currentTimeMillis();
	           		platform.kiwi.poner(tiempo,"EXEC-E",4);
	           		platform.kiwi.poner(tiempo,"READY-E",4); 
	           		platform.kiwi.poner(tiempo,"STOP",4); 
	           }
	            
	        	tiempo_coste = System.currentTimeMillis()-tiempo_coste;
	        	System.out.println("coste de la tarea sensorizacion: " + tiempo_coste);
	       	}
	       	
  		
	       	}		 
	    } //fin comportamiento
	   
	    public void run() {    
	    Sensorizar behaviour1 = new Sensorizar( new RelativeTime(0,0),
	            new RelativeTime(20,0),
	            new RelativeTime(200,0),
	            27,
	            new RelativeTime(200,0));
	            
	    this.AddRTBehaviour(behaviour1);
	    }
}