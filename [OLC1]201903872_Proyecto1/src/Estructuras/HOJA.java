/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author Marro
 */
public class HOJA {
    public int identificador;
    public String dato;
    public boolean anulable;
    public String siguientes;
    public String ultimos;
    public HOJA izquierda;
    public HOJA derecha;
    public String tipo;
    
    public HOJA(String dato, HOJA izquierda, HOJA derecha, String tipo){
        this.dato = dato;
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.tipo = tipo;
    }
}
