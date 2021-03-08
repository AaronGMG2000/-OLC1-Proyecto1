/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1.pkg201903872_proyecto1;

import java.io.FileInputStream;
import Estructuras.AFD;
import analizadores.Lexico;
import java.util.Map;
/**
 *
 * @author Marro
 */
public class OLC1201903872_Proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here;
    }
    
    private static void interpretar(String path) {
        analizadores.Sintactico pars;
        try {
            Lexico lexical = new analizadores.Lexico(new FileInputStream(path));
            pars=new analizadores.Sintactico(lexical);
            pars.parse();
            Map<String, AFD> arbol = pars.LIST_AFD;
            System.out.println(arbol);
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex.getCause());
        } 
    }
}
