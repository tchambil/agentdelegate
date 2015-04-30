package dcc.com.agent.delegagent.core.kiwi;

/**
 * Created by teo on 30/04/15.
 */

        import java.io.*;
//import java.util.*;

public class Kiwi {

    StringBuffer texto = new StringBuffer();
    long inicio_plan;
    int num_tarea_on;

    public Kiwi(){

        this.inicio_plan = 0;
        this.num_tarea_on = 0;
    }

    public void poner_cab(String text) {
        this.texto.append(text);
    }

    public void set_inicio_plan(long millis){
        this.inicio_plan = millis;
    }
    public void poner(long tiempo,String tipo, int tarea) {
        float tiempo_relativo;
        String cadena;

        tiempo_relativo = tiempo - this.inicio_plan;
        tiempo_relativo = tiempo_relativo/1000;

        cadena = tiempo_relativo +" ";
        cadena = cadena + tipo;
        cadena = cadena + " ";
        cadena = cadena + tarea;
        cadena = cadena + " \n";

        this.texto.append(cadena);

    }

    public void tarea_on() {

        this.num_tarea_on = this.num_tarea_on + 1;
    }

    public void tarea_off() {

        this.num_tarea_on = this.num_tarea_on - 1;
    }

    public int num_tareas_on() {

        return this.num_tarea_on;
    }

    public void volcar_resultados() {

        try {
            FileWriter fw = new FileWriter("kiwi.ktr");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            salida.println(this.texto);
            salida.close();
        } catch (java.io.IOException ioex) { }
    }
}

