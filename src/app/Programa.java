package app;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Programa extends JFrame implements ActionListener{
    private JButton seleccion;
    private JButton crea;
    ArrayList<JLabel> etMedias = new ArrayList<JLabel>();
    private JLabel numFilas;
    private JTextField filas;
    Datos datos = new Datos();
    int numCol = datos.numColumnas;

    public Programa(){
        super("Archivo CSV");
        this.setLayout(null);

        seleccion = new JButton("Seleccionar archivo");
        seleccion.setSize(seleccion.getPreferredSize());
        seleccion.setLocation(100,10);
        seleccion.setVisible(true);
        seleccion.addActionListener(this);
        add(seleccion);

        crea = new JButton("Crear archivo");
        crea.setSize(crea.getPreferredSize());
        crea.setLocation(450,10);
        crea.setVisible(true);
        crea.addActionListener(this);
        add(crea);

        numFilas = new JLabel("Introduce el número de filas del archivo: ");
        numFilas.setSize(numFilas.getPreferredSize());
        numFilas.setLocation(200,150);
        numFilas.setVisible(false);
        add(numFilas);

        filas = new JTextField(3);
        filas.setSize(filas.getPreferredSize());
        filas.setLocation(200, 180);
        filas.setVisible(false);
        filas.addActionListener(this);
        add(filas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean correcto=true;
        if(e.getSource() == seleccion){
            numFilas.setVisible(false);
            filas.setVisible(false);

            JFileChooser cargar = new JFileChooser();
            int respuesta = cargar.showOpenDialog(this);
            if(respuesta == JFileChooser.APPROVE_OPTION){
                if(cargar.getSelectedFile().getAbsolutePath().toLowerCase().endsWith(".csv")){
                    try{
                        correcto = true;
                        datos.leerDatos(cargar.getSelectedFile().getAbsolutePath());
                    } catch (Exception excep){
                        correcto = false;
                        JOptionPane.showMessageDialog(this, "Error. El archivo no es válido");
                    }
                    if(correcto){
                        limpiar();
                        mediasLabels();
                        int posY = 150;

                        for(int i=0; i < etMedias.size(); i++){
                            etMedias.get(i).setSize(etMedias.get(i).getPreferredSize());
                            etMedias.get(i).setLocation(200, posY);
                            etMedias.get(i).setVisible(true);
                            this.add(etMedias.get(i));
                            posY+=50;
                            this.getContentPane().revalidate();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error. el archivo no es válido");
                }
                
            }
        }

        if(e.getSource() == crea){
            for(int i=0; i<etMedias.size(); i++){
                etMedias.get(i).setVisible(false);
            }
            numFilas.setVisible(true);
            filas.setVisible(true);
        }
        if(e.getSource() == filas){
            int numeroFilas=1;
            try{
                correcto = true;
                numeroFilas = Integer.parseInt(filas.getText());
            }catch(NumberFormatException excep){
                correcto = false;
                JOptionPane.showMessageDialog(this, "No has introducido un número");
            }
            if(correcto){
                JFileChooser guardar = new JFileChooser();
                int respuesta = guardar.showSaveDialog(this);
                if(respuesta == JFileChooser.APPROVE_OPTION){
                    try{
                        correcto = true;
                        datos.guardarDatos(numeroFilas, guardar.getSelectedFile().getAbsolutePath());
                    }catch(Exception excep){
                        JOptionPane.showMessageDialog(this, "Error al guardar el archivo");
                    }
                }
            }

        }
        
    }

    public void mediasLabels(){
        for(int i=0; i < numCol; i++){
            etMedias.add(new JLabel());
            etMedias.get(i).setText("Media columna "+(i+1)+": "+datos.medias.get(i));
        }
    }

    public void limpiar(){
        for(int i = 0; i < etMedias.size();i++){
            etMedias.get(i).setVisible(false);
        }
        etMedias.clear();
    }
}