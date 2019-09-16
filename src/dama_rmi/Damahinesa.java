/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama_rmi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author ediberto
 */
public class Damahinesa extends JInternalFrame {

    private Container contents;
    private JButton[][] estrela = new JButton[17][26];
    private Color ColorBlack = Color.BLACK;
    private Color ColorBlue = Color.BLUE;
    private Color ColorRed = Color.RED;

    int row = 0;
    int col = 0;
    int xglobal = 0;
    int yglobal = 0;
    Color coratual = null;
    int cont = 0;
    Color cantes = null;
    boolean envia = false;
    Color corPeca = null;
    String userEnv = null;
    boolean turn = true;
    String user;

    public Damahinesa(String name, String cor, boolean turno) {
        super(name);
        if (cor == "azul") {
            corPeca = ColorBlue;
        }

        if (cor == "vermelha") {
            corPeca = ColorRed;
        }
        turn = turno;
        user = name;
        contents = getContentPane();
        contents.setLayout(new GridLayout(17, 26));

        ButtonHandler eventobutao = new ButtonHandler();
        for (int x = 0; x < 17; x++) {
            for (int y = 0; y < 25; y++) {

                estrela[x][y] = new JButton();
                estrela[x][y].setBackground(Color.WHITE);
                contents.add(estrela[x][y]);

            }
        }
//         estrela[0][12].setBackground(Color.BLACK);
//         estrela[1][11].setBackground(Color.BLACK);
        int linhax = 12;
        int aux = 0;
        int coll = 1;
        for (int colun = 0; colun < 4; colun++) {

            aux = linhax;
            for (int j = 0; j <= colun; j++) {
                estrela[colun][aux].setBackground(ColorBlack);
                estrela[colun][aux].addActionListener(eventobutao);
                aux += 2;
            }
            coll += 1;
            linhax -= 1;
        }
        linhax = 0;
        aux = 0;
        coll = 12;
        for (int colun = 4; colun < 9; colun++) {
            aux = linhax;
            for (int j = 0; j <= coll; j++) {
                estrela[colun][aux].setBackground(ColorBlack);
                estrela[colun][aux].addActionListener(eventobutao);
                aux += 2;
            }
            coll -= 1;
            linhax += 1;
        }
        linhax = 3;
        aux = 0;
        coll = 9;
        for (int colun = 9; colun <= 12; colun++) {
            aux = linhax;
            for (int j = 0; j <= coll; j++) {
                estrela[colun][aux].setBackground(ColorBlack);
                estrela[colun][aux].addActionListener(eventobutao);
                aux += 2;

            }
            coll += 1;
            linhax -= 1;
        }
        linhax = 9;
        aux = 0;
        coll = 3;
        for (int colun = 13; colun < 17; colun++) {
            aux = linhax;
            for (int j = 0; j <= coll; j++) {
                estrela[colun][aux].setBackground(ColorBlack);
                estrela[colun][aux].addActionListener(eventobutao);
                aux += 2;

            }
            coll -= 1;
            linhax += 1;
        }

//        for (int x = 0; x < 17; x++) {
//            for (int y = 0; y < 25; y++) {
//           Color cor=estrela[x][y].getBackground();
//           if(cor!=ColorBlack){
////           estrela[x][y]=new JButton();
////           estrela[x][y].setBackground(Color.WHITE);
//           contents.remove(estrela[x][y]);
//           }
//       } }
        setSize(500, 500);
        setResizable(false);
        // setLocationRelativeTo(null);
        //setVisible(true);
        iniciaJogo();
    }

    public void iniciaJogo() {
        int linhax = 12;
        int aux = 0;
        int coll = 1;
        for (int colun = 0; colun < 4; colun++) {

            aux = linhax;
            for (int j = 0; j <= colun; j++) {
                estrela[colun][aux].setBackground(ColorBlue);
                aux += 2;
            }
            coll += 1;
            linhax -= 1;
        }
        linhax = 9;
        aux = 0;
        coll = 3;
        for (int colun = 13; colun < 17; colun++) {
            aux = linhax;
            for (int j = 0; j <= coll; j++) {
                estrela[colun][aux].setBackground(ColorRed);
                aux += 2;

            }
            coll -= 1;
            linhax += 1;
        }

    }

    public boolean validMovi(int x, int y, Color corr) {
        int deltax = Math.abs(x - row);
        int deltay = Math.abs(y - col);
        if (x != row) {
            if (deltax == 1 &&  deltay==1) {
                envia = true;
                return true;
            }
            if (deltax == 2  &&  deltay==2) {
                return true;
            }
        }
        return false;
    }

    private void possiMovi(int x, int y) {
        int pos1x = x - 1;
        int pos1y = y + 1;
        int pos1x2 = x + 1;
        int pos1y2 = y - 1;
        if (estrela[pos1x][pos1y2].getBackground() == ColorBlack) {
            //borda diferente
        }
        if (estrela[pos1x2][pos1y2].getBackground() == ColorBlack) {
            //borda diferente
        }
        if (estrela[pos1x][pos1y].getBackground() == ColorBlack) {
            //borda diferente
        }
        if (estrela[pos1x2][pos1y].getBackground() == ColorBlack) {
            //borda diferente
        }
    }

    private class ButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            for (int x = 0; x < 17; x++) {
                for (int y = 0; y < 25; y++) {

                    if (source == estrela[x][y]) {
                        Color coratual = estrela[x][y].getBackground();
                        if (turn == true) {
                            if (corPeca == coratual || coratual == ColorBlack) {
                                if (coratual != ColorBlack) {
                                    if (cont == 0) {
                                        cantes = coratual;
                                        row = x;
                                        col = y;
                                        cont += 1;
                                        return;
                                    }
                                }
                                if (coratual == ColorBlack) {
                                    if (cont != 0) {
                                        proceClick(x, y, cantes);
                                        cont = 0;
                                        return;
                                    }
                                }
                                if (coratual != cantes && coratual != ColorBlack) {
                                    if (cont == 1) {
                                        cantes = coratual;
                                        row = x;
                                        col = y;
                                        cont = 1;
                                        return;
                                    }
                                }
                                if (coratual == cantes) {
                                    if (cont == 1) {
                                        row = x;
                                        col = y;
                                        cont = 1;
                                        return;
                                    }
                                }
                            }
                        }
                    }

                    {

                        // System.out.println(x + " " + y);
                        //System.out.println(ColorBlack);
                        //return;
                    }
                }
            }

        }

    }

    private void proceClick(int x, int y, Color corr) {
        xglobal = x;
        yglobal = y;
        //System.out.println("x "+x+" y "+y);
        coratual = corr;
        if (validMovi(x, y, corr) == false) {
            return;
        }

        estrela[row][col].setBackground(ColorBlack);
        estrela[x][y].setBackground(corr);
        envia();

    }

    void recebeJogada(int x, int y, int x1, int y1, Color corr) {
        row = x1;
        col = y1;
        estrela[row][col].setBackground(ColorBlack);
        estrela[x][y].setBackground(corr);

    }

    void envia() {
        try {
            // TODO add your handling code here:

            Mensagem m = new Mensagem("jogada");
            m.setParam("posicaox", xglobal);
            m.setParam("posicaoy", yglobal);
            m.setParam("row", row);
            m.setParam("col", col);
            m.setParam("cor", coratual);
            IterfeceRemota client1 = (IterfeceRemota) Naming.lookup("rmi://localhost:5555/" + user);

            client1.EnviarJogada(m);
        } catch (NotBoundException ex) {
            Logger.getLogger(loby.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(loby.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(loby.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public static void main(String[] args) {
//        Damahinesa gui = new Damahinesa("");
//        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
}
