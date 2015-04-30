/*
 * Creado el 06-jun-2005
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package dcc.com.agent.delegagent.core.EjemploVRS;

/**
 * @author root
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class Point {
public double x,y;


public Point(double doubleX,double doubleY)
{
	x = doubleX;
	y = doubleY;
}
/**
 * @return
 */
public synchronized double getX() {
	// TODO Apéndice de método generado automáticamente
	return x;
}

public synchronized double getY() {
	// TODO Apéndice de método generado automáticamente
	return y;
}

public synchronized void setX(double X){
	this.x = X;
}

public synchronized void setY(double Y){
	this.y = Y;
}
}
