/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marro
 */
public class AFN {
    public String nombre;
    public HOJA_AFN root;
    public int ident = 0;
    public String aceptacion = "";
    public List<HOJA_AFN[]> transiciones = new ArrayList<>();
    public AFN(HOJA_AFN root, String nombre){
        this.root = root;
        this.nombre = nombre;
    }
    
    public void CREAR_AFN(){
        _CREAR_AFN(root, "");
    }
    
    public HOJA_AFN _CREAR_AFN(HOJA_AFN nodo, String anterior){
        switch(nodo.tipo){
            case ".":
                HOJA_AFN hijo1 = nodo.hijos[0];
                HOJA_AFN hijo2 = nodo.hijos[1];
                if(hijo1.hijos!=null && hijo2.hijos!=null){
                    nodo.hijos[0] = _CREAR_AFN(nodo.hijos[0] , "no");
                    HOJA_AFN[] tran = {nodo.hijos[0].fin, nodo.hijos[1].hijos[0]};
                    transiciones.add(tran);
                    nodo.hijos[1] = _CREAR_AFN(nodo.hijos[1], "no");
                    if(anterior.isEmpty()){
                        nodo.hijos[2].identificador = "S"+ident;
                        ident++;
                        HOJA_AFN[] tran2 = {nodo.hijos[1].fin, nodo.hijos[2]};
                        transiciones.add(tran2);
                        nodo.fin = nodo.hijos[2];
                    }else{
                        nodo.fin = nodo.hijos[1].fin;
                    }
                }
                else if(hijo1.hijos!=null || hijo2.hijos!=null){
                    if(hijo1.hijos!=null){
                        nodo.hijos[0] = _CREAR_AFN(nodo.hijos[0], "no");
                        HOJA_AFN[] tran = {nodo.hijos[0].fin, nodo.hijos[1]};
                        nodo.hijos[1].identificador = "S"+ident;
                        ident++;
                        transiciones.add(tran);
                        if(anterior.isEmpty()){
                            nodo.hijos[2].identificador = "S"+ident;
                            ident++;
                            HOJA_AFN[] tran1 = {nodo.hijos[1], nodo.hijos[2]};
                            transiciones.add(tran1);
                            nodo.fin = nodo.hijos[2];
                        }else{
                            nodo.fin = nodo.hijos[1];
                        }
                    }
                    else if(hijo2.hijos!=null){
                        nodo.hijos[0].identificador = "S"+ident;
                        ident++;
                        HOJA_AFN[] tran = {nodo.hijos[0], nodo.hijos[1].hijos[0]};
                        transiciones.add(tran);
                        nodo.hijos[1] = _CREAR_AFN(nodo.hijos[1], "no");
                        if(anterior.isEmpty()){
                            nodo.hijos[2].identificador = "S"+ident;
                            ident++;
                            HOJA_AFN[] tran1 = {nodo.hijos[1].fin, nodo.hijos[2]};
                            transiciones.add(tran1);
                            nodo.fin = nodo.hijos[2];
                        }else{
                            nodo.fin = nodo.hijos[1].fin;
                        }
                    }
                }
                else{
                    nodo.hijos[0].identificador = "S"+ident;
                    ident++;
                    nodo.hijos[1].identificador = "S"+ident;
                    ident++;
                    
                    HOJA_AFN[] tran = {nodo.hijos[0], nodo.hijos[1]};
                    
                    transiciones.add(tran);
                    if(!anterior.isEmpty()){
                        nodo.fin = nodo.hijos[1];
                    }else{
                        nodo.hijos[2].identificador = "S"+ident;
                        ident++;
                        HOJA_AFN[] tran1 = {nodo.hijos[1], nodo.hijos[2]};
                        transiciones.add(tran1);
                        nodo.fin = nodo.hijos[2];
                    }
                    
                }
                if(anterior.isEmpty()){
                    this.aceptacion = nodo.hijos[2].identificador;
                }else{
                    nodo.identificador = nodo.hijos[0].identificador;
                }
                break;
            case "|":
                if(nodo.hijos.length==6){
                    for(int x=0; x<nodo.hijos.length;x++){
                        nodo.hijos[x].identificador = "S"+ident;
                        ident++;
                    }
                    HOJA_AFN[] tran = {nodo.hijos[0], nodo.hijos[1]};
                    HOJA_AFN[] tran1 = {nodo.hijos[0], nodo.hijos[4]};
                    HOJA_AFN[] tran2 = {nodo.hijos[1], nodo.hijos[2]};
                    HOJA_AFN[] tran3 = {nodo.hijos[2], nodo.hijos[3]};
                    HOJA_AFN[] tran4 = {nodo.hijos[4], nodo.hijos[5]};
                    HOJA_AFN[] tran5 = {nodo.hijos[5], nodo.hijos[3]};
                    transiciones.add(tran);
                    transiciones.add(tran1);
                    transiciones.add(tran2);
                    transiciones.add(tran3);
                    transiciones.add(tran4);
                    transiciones.add(tran5);
                    if(anterior.isEmpty()){
                        this.aceptacion = nodo.hijos[3].identificador;
                    }else{
                        nodo.identificador = nodo.hijos[0].identificador;
                        nodo.fin = nodo.hijos[3];
                    }
                }else if(nodo.hijos.length==5){
                    if (nodo.hijos[1].hijos!=null) {
                        hijo1 = nodo.hijos[1];
                        hijo2 = nodo.hijos[3];
                        nodo.hijos[0].identificador = "S"+ident;
                        ident++;
                        nodo.hijos[1] = _CREAR_AFN(nodo.hijos[1],"no");
                        for(int x=1; x<nodo.hijos.length;x++){
                            if(x!=1){
                                nodo.hijos[x].identificador = "S"+ident;
                                ident++;
                            }
                        }
                        HOJA_AFN[] tran = {nodo.hijos[0], nodo.hijos[1].hijos[0]};
                        HOJA_AFN[] tran1 = {nodo.hijos[0], nodo.hijos[3]};
                        HOJA_AFN[] tran2 = {nodo.hijos[1].fin, nodo.hijos[2]};
                        HOJA_AFN[] tran4 = {nodo.hijos[3], nodo.hijos[4]};
                        HOJA_AFN[] tran5 = {nodo.hijos[4], nodo.hijos[2]};
                        transiciones.add(tran);
                        transiciones.add(tran1);
                        transiciones.add(tran2);
                        transiciones.add(tran4);
                        transiciones.add(tran5);
                        if(anterior.isEmpty()){
                            this.aceptacion = nodo.hijos[2].identificador;
                        }else{
                            nodo.identificador = nodo.hijos[0].identificador;
                            nodo.fin = nodo.hijos[2];
                        }
                    }else{
                        hijo1 = nodo.hijos[1];
                        hijo2 = nodo.hijos[4];
                        HOJA_AFN[] tran = {nodo.hijos[0], nodo.hijos[1]};
                        HOJA_AFN[] tran2 = {nodo.hijos[1], nodo.hijos[2]};
                        HOJA_AFN[] tran3 = {nodo.hijos[2], nodo.hijos[3]};
                        HOJA_AFN[] tran1 = {nodo.hijos[0], nodo.hijos[4].hijos[0]};
                        for(int x=0; x<4;x++){
                                nodo.hijos[x].identificador = "S"+ident;
                                ident++;
                        }
                        transiciones.add(tran);
                        transiciones.add(tran2);
                        transiciones.add(tran3);
                        transiciones.add(tran1);
                        nodo.hijos[4] = _CREAR_AFN(nodo.hijos[4], "no");
                        HOJA_AFN[] tran4 = {nodo.hijos[4].fin, nodo.hijos[3]};
                        transiciones.add(tran4);
                        if(anterior.isEmpty()){
                            this.aceptacion = nodo.hijos[3].identificador;
                        }else{
                            nodo.identificador = nodo.hijos[0].identificador;
                            nodo.fin = nodo.hijos[3];
                        }
                    }
                }else{
                    hijo1 = nodo.hijos[1];
                    hijo2 = nodo.hijos[3];
                    nodo.hijos[0].identificador = "S"+ident;
                    ident++;
                    HOJA_AFN[] tran = {nodo.hijos[0], nodo.hijos[1].hijos[0]};
                    HOJA_AFN[] tran1 = {nodo.hijos[0], nodo.hijos[3].hijos[0]};
                    transiciones.add(tran);
                    transiciones.add(tran1);
                    nodo.hijos[1] = _CREAR_AFN(nodo.hijos[1], "no");
                    for(int x=1; x<4;x++){
                        if(x!=1 && x!=3){
                            nodo.hijos[x].identificador = "S"+ident;
                            ident++;
                        }
                    }
                    HOJA_AFN[] tran2 = {nodo.hijos[1].fin, nodo.hijos[2]};
                    transiciones.add(tran2);
                    nodo.hijos[3] = _CREAR_AFN(nodo.hijos[3], "no");
                    HOJA_AFN[] tran3 = {nodo.hijos[3].fin, nodo.hijos[2]};
                    transiciones.add(tran3);
                    if(anterior.isEmpty()){
                        this.aceptacion = nodo.hijos[2].identificador;
                    }else{
                        nodo.identificador = nodo.hijos[0].identificador;
                        nodo.fin = nodo.hijos[2];
                    }
                }
                break;
            case "?":
                    if(nodo.hijos[1].hijos!=null){
                        nodo.hijos[0].identificador = "S"+ident;
                        ident++;
                        hijo1 = nodo.hijos[1];
                        nodo.hijos[1] = _CREAR_AFN(nodo.hijos[1], "no");
                        HOJA_AFN[] trand = {nodo.hijos[0], nodo.hijos[1].hijos[0]};
                        HOJA_AFN[] trand1 = {nodo.hijos[0], nodo.hijos[2]};
                        HOJA_AFN[] trand2 = {nodo.hijos[1].fin, nodo.hijos[2]};
                        for(int x=1; x<nodo.hijos.length;x++){
                            if(x!=1){
                                nodo.hijos[x].identificador = "S"+ident;
                                ident++;
                            }
                        }
                        transiciones.add(trand);
                        transiciones.add(trand1);
                        transiciones.add(trand2);
                        if(anterior.isEmpty()){
                            this.aceptacion = nodo.hijos[2].identificador;
                        }else{
                            nodo.identificador = nodo.hijos[0].identificador;
                            nodo.fin = nodo.hijos[2];
                        }
                    }
                    else{
                        nodo.hijos[0].identificador = "S"+ident;
                        ident++;
                        hijo1 = nodo.hijos[1];
                        HOJA_AFN[] trand  = {nodo.hijos[0], nodo.hijos[1]};
                        HOJA_AFN[] trand1 = {nodo.hijos[0], nodo.hijos[3]};
                        HOJA_AFN[] trand2 = {nodo.hijos[1], nodo.hijos[2]};
                        HOJA_AFN[] trand4 = {nodo.hijos[2], nodo.hijos[3]};
                        for(int x=1; x<nodo.hijos.length;x++){
                                nodo.hijos[x].identificador = "S"+ident;
                                ident++;
                        }
                        transiciones.add(trand);
                        transiciones.add(trand1);
                        transiciones.add(trand2);
                        transiciones.add(trand4);
                        if(anterior.isEmpty()){
                            this.aceptacion = nodo.hijos[3].identificador;
                        }else{
                            nodo.identificador = nodo.hijos[0].identificador;
                            nodo.fin = nodo.hijos[3];
                        }
                    }
                break;
            case "+":
                    if(nodo.hijos[1].hijos!=null){
                        int k =2;
                        if (anterior.isEmpty()) {
                            k=3;
                        }
                        nodo.hijos[0].identificador = "S"+ident;
                        ident++;
                        hijo1 = nodo.hijos[1];
                        nodo.hijos[1] = _CREAR_AFN(nodo.hijos[1], "no");
                        HOJA_AFN[] trand = {nodo.hijos[0], nodo.hijos[1].hijos[0]};
                        
                        HOJA_AFN[] trand3 = {nodo.hijos[1].fin, nodo.hijos[1].hijos[0]};
                        for(int x=1; x<k;x++){
                            if(x!=1){
                                nodo.hijos[x].identificador = "S"+ident;
                                ident++;
                            }
                        }
                        transiciones.add(trand);
                        transiciones.add(trand3);
                        if(anterior.isEmpty()){
                            HOJA_AFN[] trand2 = {nodo.hijos[1].fin, nodo.hijos[2]};
                            transiciones.add(trand2);
                            this.aceptacion = nodo.hijos[2].identificador;
                        }else{
                            nodo.identificador = nodo.hijos[0].identificador;
                            nodo.fin = nodo.hijos[1].fin;
                        }
                    }
                    else{
                        int k =3;
                        if (anterior.isEmpty()) {
                            k=4;
                        }
                        nodo.hijos[0].identificador = "S"+ident;
                        ident++;
                        hijo1 = nodo.hijos[1];
                        HOJA_AFN[] trand  = {nodo.hijos[0], nodo.hijos[1]};
                        HOJA_AFN[] trand2 = {nodo.hijos[1], nodo.hijos[2]};
                        HOJA_AFN[] trand3 = {nodo.hijos[2], nodo.hijos[1]};
                        for(int x=1; x<k;x++){
                                nodo.hijos[x].identificador = "S"+ident;
                                ident++;
                        }
                        transiciones.add(trand);
                        transiciones.add(trand2);
                        transiciones.add(trand3);
                        if(anterior.isEmpty()){
                            HOJA_AFN[] trand4 = {nodo.hijos[2], nodo.hijos[3]};
                            transiciones.add(trand4);
                            this.aceptacion = nodo.hijos[3].identificador;
                        }else{
                            nodo.identificador = nodo.hijos[0].identificador;
                            nodo.fin = nodo.hijos[2];
                        }
                    }
                break;
            case "*":
                    if(nodo.hijos[1].hijos!=null){
                        nodo.hijos[0].identificador = "S"+ident;
                        ident++;
                        hijo1 = nodo.hijos[1];
                        nodo.hijos[1] = _CREAR_AFN(nodo.hijos[1], "no");
                        HOJA_AFN[] trand = {nodo.hijos[0], nodo.hijos[1].hijos[0]};
                        HOJA_AFN[] trand1 = {nodo.hijos[0], nodo.hijos[2]};
                        HOJA_AFN[] trand2 = {nodo.hijos[1].fin, nodo.hijos[2]};
                        HOJA_AFN[] trand3 = {nodo.hijos[1].fin, nodo.hijos[1].hijos[0]};
                        for(int x=1; x<nodo.hijos.length;x++){
                            if(x!=1){
                                nodo.hijos[x].identificador = "S"+ident;
                                ident++;
                            }
                        }
                        transiciones.add(trand);
                        transiciones.add(trand1);
                        transiciones.add(trand2);
                        transiciones.add(trand3);
                        if(anterior.isEmpty()){
                            this.aceptacion = nodo.hijos[2].identificador;
                        }else{
                            nodo.identificador = nodo.hijos[0].identificador;
                            nodo.fin = nodo.hijos[2];
                        }
                    }
                    else{
                        nodo.hijos[0].identificador = "S"+ident;
                        ident++;
                        hijo1 = nodo.hijos[1];
                        HOJA_AFN[] trand  = {nodo.hijos[0], nodo.hijos[1]};
                        HOJA_AFN[] trand1 = {nodo.hijos[0], nodo.hijos[3]};
                        HOJA_AFN[] trand2 = {nodo.hijos[1], nodo.hijos[2]};
                        HOJA_AFN[] trand3 = {nodo.hijos[2], nodo.hijos[1]};
                        HOJA_AFN[] trand4 = {nodo.hijos[2], nodo.hijos[3]};
                        for(int x=1; x<nodo.hijos.length;x++){
                                nodo.hijos[x].identificador = "S"+ident;
                                ident++;
                        }
                        transiciones.add(trand);
                        transiciones.add(trand1);
                        transiciones.add(trand2);
                        transiciones.add(trand3);
                        transiciones.add(trand4);
                        if(anterior.isEmpty()){
                            this.aceptacion = nodo.hijos[3].identificador;
                        }else{
                            nodo.identificador = nodo.hijos[0].identificador;
                            nodo.fin = nodo.hijos[3];
                        }
                    }
                break;
        }
        return nodo;
    }
    
    public void GenerarArbol(){
        File directorio = new File("./AFND_201903872");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        FileWriter fichero;
        PrintWriter escritor;
        try
        {
            List<String> T = new ArrayList<>();
            fichero = new FileWriter("./AFND_201903872/"+this.nombre+".dot");
            escritor = new PrintWriter(fichero);
            escritor.print("digraph grafica{\n"
                + "rankdir=LR;\n"
                + "forcelabels= true;\n"
                + "node [shape = circle];\n");
            for(int x = 0; x<this.ident;x++){
                if(("S"+x).equals(aceptacion)){
                    escritor.print("S"+x+"[shape = doublecircle];\n");
                }else{escritor.print("S"+x+";\n");}
            }
            for(HOJA_AFN[] tran: transiciones){
                String label = tran[0].dato;
                if (label.equals(" ")) {
                    label = "\\\" \\\"";
                }
                if (label.equals("\\n")) {
                    label = "\\\\n";
                }
                String tra = tran[0].identificador+"->"+tran[1].identificador+"[label=\""+label+"\"]";
                if(!T.contains(tra)){
                    T.add(tra);
                    escritor.print(tra+"\n");
                }
            }
            escritor.print("\n}");
            fichero.close();
            Runtime rt = Runtime.getRuntime();
            rt.exec( "dot -Tjpg -o ./AFND_201903872/"+nombre+".jpg graf ./AFND_201903872/"+nombre+".dot");
        }catch(IOException e){
            System.out.println("error al crear la grafica");
        } 
    }
}
