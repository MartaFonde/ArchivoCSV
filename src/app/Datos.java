package app;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Datos {
    public int numColumnas = 3;
    String[] filaString = new String[numColumnas];
    ArrayList<String[]> conjDatos = new ArrayList<String[]>();
    int numFilas=0;
    Matriz matriz;
    int[] enteros;
    double[][] reales;
    DecimalFormat formato = new DecimalFormat("#.00");
    ArrayList<String> medias = new ArrayList<String>();

    public void leerDatos(String archivo){
        try(Scanner f = new Scanner(new File(archivo))){
            while(f.hasNext()){
                filaString = f.nextLine().split(",");
                conjDatos.add(filaString);
                numFilas++;
            }
            matriz = new Matriz(numFilas, numColumnas, conjDatos);
            medias = colecMedias(matriz.enteros, matriz.reales);
            
        }catch(IOException e){
            System.out.println("Error"+e.getMessage());
        }
    }

    public void guardarDatos(int filas, String archivo){
        enteros = new int[filas];
        reales = new double[filas][numColumnas-1];
        String numero;
        try(PrintWriter f = new PrintWriter(new FileWriter(archivo,false))){
            for(int i=0; i < filas; i++){
                enteros[i] = (int)(Math.random()*(100-10)+10);
                f.print(enteros[i]+",");
                for(int j=0; j < reales[i].length; j++){
                    if(j < reales[i].length -1){
                        reales[i][j] = Math.random()*1000+0;
                        numero=(""+formato.format(reales[i][j]));
                        f.print(numero.replace(",",".")+",");
                    }else{ 
                        reales[i][j] = Math.random()*(10000-100)+100;
                        numero=(""+formato.format(reales[i][j]));
                        f.printf("%s\n", numero.replace(",","."));
                    } 
                } 
            }
        }catch(IOException e){
            System.out.println("Error al guardar los datos: "+e.getMessage());   
        }  
    }

    public ArrayList<String> colecMedias(int[] enteros, double[][] reales){
        medias.add(0, formato.format(matriz.mediaEnteros(enteros)));
        for(int i=0; i<numColumnas-1; i++){
            medias.add(i+1, formato.format(matriz.mediaReales(reales,i)));
        }
        return medias;
    }
}
            
            
            