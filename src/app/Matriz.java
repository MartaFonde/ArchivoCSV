package app;

import java.util.ArrayList;

public class Matriz {
    int[] enteros;
    double[][] reales;

    public Matriz(int numFilas, int numColumnas, ArrayList<String[]> conjDatos){
        enteros = new int[numFilas];
        reales = new double[numFilas][numColumnas-1];

        try{
            for(int i=0; i<enteros.length; i++){
                enteros[i]=Integer.parseInt(conjDatos.get(i)[0]);
            }
            for(int i=0; i<reales.length; i++){
                int pos=1;
                for(int j=0; j<reales[i].length; j++){
                    reales[i][j]=Double.parseDouble(conjDatos.get(i)[pos]);
                    pos++;
                }
            }
        }catch(NumberFormatException e){
            System.out.println("Error. Hay algún dato no válido"+e.getMessage());
        }
    }

    public double mediaEnteros(int[] enteros){
        double suma=0;
        for(int i=0; i<enteros.length; i++){
            suma+=enteros[i];
        }
        return suma/enteros.length;
    }

    public double mediaReales(double[][] reales, int col){
        double suma=0;
        for(int i=0; i<reales.length; i++){
            suma+=reales[i][col];
        }
        return suma/reales.length;
    }    
}