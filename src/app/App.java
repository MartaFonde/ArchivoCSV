package app;

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        Programa prog = new Programa();
        prog.setSize(700,500);
        prog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prog.setVisible(true);
    }
}