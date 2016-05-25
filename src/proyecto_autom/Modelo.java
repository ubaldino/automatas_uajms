
package proyecto_autom;

import java.util.ArrayList;
import java.util.Arrays;

public class Modelo {

    private ArrayList<String> alfabeto;
    private ArrayList<String> estados;
    private String estadoInicial;
    private ArrayList<String> EstadosFinales;
    private int[][] MatrizTransicion;

    public ArrayList<String> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(ArrayList<String> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public ArrayList<String> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<String> estados) {
        this.estados = estados;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public ArrayList<String> getEstadosFinales() {
        return EstadosFinales;
    }

    public void setEstadosFinales(ArrayList<String> EstadosFinales) {
        this.EstadosFinales = EstadosFinales;
    }

    public int[][] getMatrizTransicion() {
        return MatrizTransicion;
    }

    public void setMatrizTransicion(int[][] MatrizTransicion) {
        this.MatrizTransicion = MatrizTransicion;
    }

    public Modelo() {
        this.alfabeto = new ArrayList<>();
        this.estados = new ArrayList<>();
        this.estadoInicial = null;
        this.EstadosFinales = new ArrayList<>();
        this.MatrizTransicion = null;
    }
    
    
    
    public ArrayList<String> parseAlfabeto( String alfabeto ){
        this.alfabeto.addAll( Arrays.asList( alfabeto.split("") ) );
        return this.alfabeto;
    }
    
    public ArrayList<String> parseEstados( String estados ){
        this.estados.addAll( Arrays.asList( estados.split(",") ) );
        return this.estados;
    }
    
    public void crearMatrizTransicion( int tamanioFilas , int tamanioColumnas ){
       this.MatrizTransicion = new int[ tamanioFilas ][ tamanioColumnas ];
    }
    
}
