/*
 * Creado el 02-jun-2005
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package dcc.com.agent.delegagent.core.EjemploVRS;

//import java.awt.Point;

/**
 * @author root
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class Planificador {
    Pizarra pizarra;
    double matriz[][]=new double[100][100];
    
	public Planificador(Pizarra tabla){
	   pizarra = tabla;		   
	   almacenarMatriz();  
	   
	}
	//devuelve el coste
	public double Plan(int indice,int[] listaPendiente){
		double cost = 10000000.0;
		int i;
		boolean vacio=true;
		double[] lista;
		int[] listaAuxiliar,listaAuxiliar2;
		
		listaAuxiliar = listaPendiente;
		
		lista = new double[listaAuxiliar.length];
		
		for (i=0;i<listaAuxiliar.length;i++){
			if (listaAuxiliar[i]==1){
				vacio=false;
				break;
			}
		}		
		if (vacio==true){
		   cost= matriz[0][indice];
		   System.out.println("coste de 0 --> "+indice+" : "+cost);
		}
		else
		{
		  for(i=0;i<listaAuxiliar.length;i++){
			  if (listaAuxiliar[i]==1){
				listaAuxiliar2 = listaAuxiliar;
				listaAuxiliar2[i]=0;
				lista[i]=Plan(i+1,listaAuxiliar2)+ matriz[i+1][indice];
			  }
			  else {
				lista[i]=1000000.0;
			   }	  
		   }
		  cost=min(lista);
		}
		return cost;
	}

	/**
	 * @param lista
	 * @return
	 */
	private double min(double[] lista) {
		// TODO Apéndice de método generado automáticamente
		double min;
		min = 1000000.0;
		int i;
		
		for (i=0;i<lista.length;i++){
			if (lista[i]<min){
				min = lista[i];
			}
		}	
		return min;
	}
	// Devuelve la distancia entre dos puntos
	private double distancia(Point p1, Point p2)
	{
		double x1 = p1.getX();
		double x2 = p2.getX();
		double y1 = p1.getY();
		double y2 = p2.getY();
		return 0.0;
		//return Point.distance(x1, y1, x2, y2);
	}
	
	private void almacenarMatriz()
	{
		int i,k;
		   double cost;
		   
		   matriz[0][0]=0.0;
		   matriz[0][pizarra.getNumeroPuntos()+1]=1000000.0;
		   
		   for (i=0;i<pizarra.getNumeroPuntos();i++){
		   	matriz[i+1][0]=1000000.0;
		   }
		   for (i=0;i<pizarra.getNumeroPuntos();i++){
		   	  cost = distancia(pizarra.getPuntoInicial(),pizarra.ViewPuntoObjeto(i));
		   	  matriz[0][i+1] = cost;
		   	  System.out.println("0 --> "+(i+1)+" = " + cost);
		   }

		   for (i=0;i<pizarra.getNumeroPuntos()-1;i++){
		   	   matriz[i+1][i+1] = 0.0;
		   	   System.out.println((i+1) + " --> "+ (i+1) + " = " + matriz[i+1][i+1]);
			 for (k=i+1;k<pizarra.getNumeroPuntos();k++){
		   	   cost = distancia(pizarra.ViewPuntoObjeto(i),pizarra.ViewPuntoObjeto(k));
		        matriz[i+1][k+1] = cost;
				matriz[k+1][i+1] = cost;
				System.out.println((i+1) + " --> "+ (k+1) + " = " + cost);
				System.out.println((k+1) + " --> "+ (i+1) + " = " + cost);
		    }
		   }
		   
		   for (i=0;i<pizarra.getNumeroPuntos();i++){
		    cost = distancia(pizarra.ViewPuntoObjeto(i),pizarra.getPuntoFinal());
		   	  matriz[i+1][pizarra.getNumeroPuntos()+1] = cost;
		   	System.out.println((i+1) + " --> "+ (pizarra.getNumeroPuntos()+1) + " = " +cost);
		   }
		   
		   for (i=0;i<pizarra.getNumeroPuntos()+2;i++){
		   	matriz[pizarra.getNumeroPuntos()+1][i]=1000000.0;
		   }
	}
	
	public void ImprimirMatriz(){
		int i,j;
		System.out.println();
		for (i=0;i<pizarra.getNumeroPuntos()+2;i++){
			for (j=0;j<pizarra.getNumeroPuntos()+2;j++){
				System.out.print(matriz[i][j]+ " ");
			}
			System.out.println();
		}
	}
}
