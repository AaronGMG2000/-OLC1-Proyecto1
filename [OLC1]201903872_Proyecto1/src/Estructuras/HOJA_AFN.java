/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marro
 */
public class HOJA_AFN {
    public String dato;
    public String identificador;
    public HOJA_AFN[] hijos;
    public List<HOJA_AFN[]> transiciones = new ArrayList<>();
    public HOJA_AFN(String dato, String[] alfabeto, HOJA_AFN izquierda, HOJA_AFN derecha, String tipo){
        
    }
    public void CREAR_HIJOS(String tipo, HOJA_AFN izquierda, HOJA_AFN derecha, String[] alfabeto){
        switch(tipo){
            case ".":
                hijos = new HOJA_AFN[2];
                HOJA_AFN[] trans ={hijos[0],hijos[1]};
                HOJA_AFN[] trans1 ={hijos[1],hijos[0]};
                transiciones.add(trans);
                transiciones.add(trans1);
                break;
            case "*":
                hijos = new HOJA_AFN[4];
                break;
            case "|":
                if (izquierda!=null && derecha!=null) {
                    hijos = new HOJA_AFN[4];
                    
                }else if(izquierda!=null || derecha!=null){
                    hijos = new HOJA_AFN[5];
                }else{
                    hijos = new HOJA_AFN[6];
                }
                break;
            case "+":
                hijos = new HOJA_AFN[4];
                break;
            case "?":
                hijos = new HOJA_AFN[4];
                break;
            default:
                break;
        }
        if(!tipo.equals("hoja")){
            for (HOJA_AFN H: hijos) {
                H = new HOJA_AFN("ε",null, null, null, "hoja");
                H.identificador = "S";
            }
            if(izquierda!=null && derecha!=null){
                hijos[0] = izquierda;
                hijos[1] = derecha;
            }else if(izquierda!=null || derecha!=null){
                if(tipo.equals("+") || tipo.equals("*") || tipo.equals("?")){
                    hijos[0] = izquierda;
                }else{
                    if (izquierda!=null) {
                        hijos[0] = izquierda;
                        hijos[1].dato = alfabeto[0];
                    }else{
                        hijos[0] = derecha;
                        hijos[1].dato = alfabeto[0];
                    }
                }
                
            }else{
               if(tipo.equals("+") || tipo.equals("*") || tipo.equals("?")){
                    hijos[0].dato = alfabeto[0];
                }else{
                    hijos[0].dato = alfabeto[0];
                    hijos[1].dato = alfabeto[1];
                } 
            }
        }
    }
}

