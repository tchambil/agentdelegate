/*
 * Creado el 11-may-2005
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
package dcc.com.agent.delegagent.core.agentes_ejemplos;
import dcc.com.agent.delegagent.core.Agents.RTAgent;
import dcc.com.agent.delegagent.core.Behaviours.*;
import dcc.com.agent.delegagent.core.Communication.ACLMessage;
import dcc.com.agent.delegagent.core.AID;
import dcc.com.agent.delegagent.core.Platform;

import javax.realtime.*;
 
public class Agente2 extends RTAgent {
	 public Agente2(Platform platform, String name) 
	    {
	        super(platform,name);    
	    }
	    public class ciclico2 extends PeriodicBehaviours 
	    {   
	       public ciclico2(RelativeTime start,RelativeTime cost, RelativeTime deadline,int priority, RelativeTime period)
	       {          
	         super(start,cost,deadline,priority,period);
	       }        
	       public void Task()
	       { 
	       
	        
	         long tiempo,tiempo_deadline;
	         RelativeTime deadline;    
	         
	         tiempo =  System.currentTimeMillis();
	        
	        //PINTAR KIWI
	          platform.kiwi.poner(tiempo,"START",1);
	          platform.kiwi.poner(tiempo,"READY-B",1); 
	          platform.kiwi.poner(tiempo,"EXEC-B",1); 
	          deadline = GetDeadline();
	          tiempo_deadline = deadline.getMilliseconds();
	          tiempo = tiempo + tiempo_deadline;
	          platform.kiwi.poner(tiempo,"DEADLINE",1);
	        //FIN PINTAR KIWI
	       
	        
	         
	          tiempo = cost.getMilliseconds();
	        
	            esperar(tiempo-200);
	             
	            ACLMessage msg = Receive();
	            
	            if (msg==null) {
	                System.out.println("mensaje aun no recibido");
	            }
	            else {
	               String mensaje = msg.getContent();
	               AID sender = msg.getSender();
	               System.out.println("el mensaje " + mensaje + " a sido enviado por " + sender.getName());
	            }
	          
	           
	            //PINTAR KIWI
	            tiempo = System.currentTimeMillis();
	            platform.kiwi.poner(tiempo,"EXEC-E",1);
	            platform.kiwi.poner(tiempo,"READY-E",1); 
	            platform.kiwi.poner(tiempo,"STOP",1); 
	         //FIN PINTAR KIWI
	     
	       } //fin metodo task() 
	    } //fin behaviour
	    
	    public void run() {
	     ciclico2 behaviour = new ciclico2( new RelativeTime(0,0), //start
	                                      new RelativeTime(300,0),  //cost
	                                      new RelativeTime(3200,0),  //deadline
	                                      10,                        //prioridad
	                                      new RelativeTime(3500,0)); //periodo
	                                      
	     this.AddRTBehaviour(behaviour);
	    }
}
