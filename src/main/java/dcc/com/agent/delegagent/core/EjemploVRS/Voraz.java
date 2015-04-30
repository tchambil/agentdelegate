/*
 * Creado el 03-jun-2005
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
public class Voraz {
	 Pizarra pizarra;
	    double matriz[][]=new double[100][100];
	    
		public Voraz(Pizarra tabla){
		   pizarra = tabla;		   
	//	   almacenarMatriz();  
		   
		}
		
		public Point[] Plan(Point[] puntos){
			Point[] ruta;
			int i;
			Point[] lista;
			double min;
			double cost;
			int siguientepunto=-1;
			int j=0;
			
			//ruta = new Point[pizarra.getNumeroPuntos()+2];
			ruta = new Point[pizarra.getNumeroPuntos()+1];
			
			Point punto;
			
			lista = puntos;
			int longitud = pizarra.getNumeroPuntos();
			
			//ruta[j]=pizarra.getPuntoInicial();
			punto = pizarra.getPuntoInicial();
			while (longitud>0){		
				min = 10000000.0;
			    for (i=0;i<longitud;i++){
			    	cost = pizarra.distancia(punto,lista[i]);
			    	if (cost < min){
				    	min = cost;
				    	siguientepunto = i;
			     	}
			    }
			    ruta[j]=lista[siguientepunto];
			    j++;
			    for (i=siguientepunto;i<longitud-1;i++){
			    	lista[i] = lista[i+1];
			    }
			    longitud--;
			}
			ruta[j]=pizarra.getPuntoFinal();
			
			return ruta;
			
		}
		
		private int min(double[] lista) {
			// TODO Apéndice de método generado automáticamente
			int min;
			min = -1;
			int i;
			
			for (i=0;i<lista.length;i++){
				if (lista[i]<min){
					min = i;
				}
			}	
			return min;
		}
		// Devuelve la distancia entre dos puntos
/*		private double distancia(Point p1, Point p2)
		{
			double x1 = p1.getX();
			double x2 = p2.getX();
			double y1 = p1.getY();
			double y2 = p2.getY();
			
			return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
			
			//return Point.distance(x1, y1, x2, y2);
		}*/
		
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
			   	  cost = pizarra.distancia(pizarra.getPuntoInicial(),pizarra.ViewPuntoObjeto(i));
			   	  matriz[0][i+1] = cost;
			   	  System.out.println("0 --> "+(i+1)+" = " + cost);
			   }

			   for (i=0;i<pizarra.getNumeroPuntos()-1;i++){
			   	   matriz[i+1][i+1] = 0.0;
			   	   System.out.println((i+1) + " --> "+ (i+1) + " = " + matriz[i+1][i+1]);
				 for (k=i+1;k<pizarra.getNumeroPuntos();k++){
			   	   cost = pizarra.distancia(pizarra.ViewPuntoObjeto(i),pizarra.ViewPuntoObjeto(k));
			        matriz[i+1][k+1] = cost;
					matriz[k+1][i+1] = cost;
					System.out.println((i+1) + " --> "+ (k+1) + " = " + cost);
					System.out.println((k+1) + " --> "+ (i+1) + " = " + cost);
			    }
			   }
			   
			   for (i=0;i<pizarra.getNumeroPuntos();i++){
			    cost = pizarra.distancia(pizarra.ViewPuntoObjeto(i),pizarra.getPuntoFinal());
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
		
		public void ImprimirRuta(Point[] puntos){
			int i;
			for (i=0;i<puntos.length;i++){
				System.out.println("Punto Nº "+i+" : x="+puntos[i].x+"   y="+puntos[i].y);
			}
			
		}
}

