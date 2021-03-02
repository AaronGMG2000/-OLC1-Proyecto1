/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marro
 */
public class AFD {
    HOJA cabeza;
    Map<String, String> CONJ = new HashMap<>();
    List<String> alfabeto;
    List<String> transiciones;
    String terminal;
    
    public AFD(HOJA cabeza){
        this.cabeza = cabeza;
    }
}
