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
public class Pizarra {
	
	private Point PuntoInicial;
	private Point PuntoFinal;	
	private Point PuntoActual;
	private double Angulo;
	
	private Point[] ListaPuntosObjetos;
	private Point[] ListaDestinos;
	
	private Point Destino;
	
	private int NumeroPuntos;
	private int NumeroDestinos;
	private int IndiceDestinos;
	
	//sensores
	
	private int[] Sensores;

	
	public Pizarra(){
	NumeroPuntos =0;
	ListaPuntosObjetos = new Point[10];
	ListaDestinos = new Point[10];
	Sensores = new int[8];
	IndiceDestinos = 0;
	}
	
	public synchronized int getNumeroPuntos(){
	return NumeroPuntos;
	}
	
	public synchronized void setPuntoInicial(Point punto){			
		PuntoInicial = punto;		
	}
	public synchronized void setPuntoFinal(Point punto){		
		PuntoFinal = punto;	
		
	}	public synchronized void setPuntoActual(Point punto){		
		PuntoActual = punto;		
	}	
	
	public synchronized Point getPuntoInicial(){
		return PuntoInicial;
	}
	
	public synchronized Point getPuntoFinal(){
		return PuntoFinal;
	}
	
	public synchronized Point getPuntoActual(){
		return PuntoActual;
	}
	
	
	public synchronized void AddPuntoObjeto(Point punto){
		ListaPuntosObjetos[NumeroPuntos] = punto;
		NumeroPuntos++;
	}
	
	public synchronized Point ExtractPuntoObjeto(int indice){
		int i;
		Point punto= ListaPuntosObjetos[indice];
		
		for (i=indice; i< NumeroPuntos-1;i++){
			ListaPuntosObjetos[i] = ListaPuntosObjetos[i+1];
		}	
		NumeroPuntos--;
		return punto;
	}
	
	public synchronized Point ViewPuntoObjeto(int indice){
		return ListaPuntosObjetos[indice];
	}
	
	public synchronized void AddDestinos(Point[] puntos){
		ListaDestinos = puntos;
		NumeroDestinos = ListaDestinos.length;
		IndiceDestinos = 0;
		Destino = ListaDestinos[IndiceDestinos];
	}
	
	public synchronized Point ViewDestino(int indice){
	
		return 	ListaDestinos[indice];	
	}
	
	public synchronized Point getDestino(){
		return Destino;
	}
	
	public synchronized void nextDestino(){
		IndiceDestinos = IndiceDestinos + 1;
		if (IndiceDestinos < NumeroDestinos)
		   Destino = ListaDestinos[IndiceDestinos];
		else
			Destino = null;
	}

	/**
	 * @return
	 */
	public synchronized Point[] getPuntosMedios() {

		return ListaPuntosObjetos;
	}
	
	public synchronized void setAngulo(double angle){
		Angulo = angle;
	}
	public synchronized double getAngulo(){
		return Angulo;
	}

	/**
	 * 
	 */
	public synchronized void tratarCadena(String cadena) {
		
		int i;
		char caracter;
		int posicion=1;
		int x,y;
		Point punto= new Point(0.0,0.0);
		StringBuffer buffer = new StringBuffer();
		
		System.out.println(cadena);
		for (i=0;i<cadena.length();i++) {
			
			caracter = cadena.charAt(i);	
			if (caracter == ';')
			{
				switch(posicion)
				{
				   case(1):
				   {
					punto.setX(Double.parseDouble(buffer.toString()));
				   	buffer.delete(0,buffer.length());
				   	posicion=2;
					System.out.println("X: " +punto.getX());
				   	break;
				   }
				   case(2):
				   {
				    punto.setY(Double.parseDouble(buffer.toString()));
					buffer.delete(0,buffer.length());
				    posicion=3;
				   	break;
				   }
				   case(3):
				   {
				    Angulo = Double.parseDouble(buffer.toString());
				    buffer.delete(0,buffer.length());
				    posicion=4;
				    break;
				   }
				   case(4):
				   {
				   	Sensores[0] = Integer.parseInt(buffer.toString());
				   	buffer.delete(0,buffer.length());
				    posicion=5;
				    break;
				   }
				   case (5):
				   {
				   	Sensores[1] =  Integer.parseInt(buffer.toString());
				   	buffer.delete(0,buffer.length());
				   	posicion=6;
				    break;
				   }
				   case(6):
				   {
				   	Sensores[2] =  Integer.parseInt(buffer.toString());
				   	buffer.delete(0,buffer.length());				 
				   	posicion=7;
				    break;				   	
				   }
				   case(7):
				   {
				   	Sensores[3] =  Integer.parseInt(buffer.toString());
				   	buffer.delete(0,buffer.length());
				   	posicion=8;
				    break;				   	
				   }
				   case(8):
				   {
				   	Sensores[4] =  Integer.parseInt(buffer.toString());
				 	buffer.delete(0,buffer.length());
				    posicion=9;
				    break;				   	
				   }
				   case(9):
				   {
				   	Sensores[5] =  Integer.parseInt(buffer.toString());
				   	buffer.delete(0,buffer.length());
				    posicion=10;
				    
				    break;
				   }
				   case(10):
				   {
				   	Sensores[6] =  Integer.parseInt(buffer.toString());
				   	buffer.delete(0,buffer.length());
				    posicion=11;
				    break;
				   }
				   case(11):
				   {
				   	Sensores[7] =  Integer.parseInt(buffer.toString());
				   
				    break;
				   }	
				}
			}	
			else
			{
				buffer.append(caracter);
			}			
		}
		setPuntoActual(punto);	
		//System.out.println("X: " + punto.getX() + "    Y: " + punto.getY() + "   Angulo: " + Angulo);
		}

	// Devuelve la distancia entre dos puntos
	public double distancia(Point p1, Point p2)
	{
		double x1 = p1.getX();
		double x2 = p2.getX();
		double y1 = p1.getY();
		double y2 = p2.getY();
		
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
		
		//return Point.distance(x1, y1, x2, y2);
	}

	public void setSensores(int a, int b, int c, int d, int e, int f, int g, int h)
	{
		Sensores[0]=  a;
		Sensores[1]=  b;
		Sensores[2]=  c;
		Sensores[3]=  d;
		Sensores[4]=  e;
		Sensores[5]=  f;
		Sensores[6]=  g;
		Sensores[7]=  h;
		
	}
	
	public void setSensor(int i, int a)
	{
		Sensores[i]=a;
	}
	public int getSensor(int i)
	{
		return Sensores[i];
	}
	
	public int[] getSensores()
	{
		return Sensores;
	}

}
	


